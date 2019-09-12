package se.difo.hemnetapi.scenarios

import org.springframework.test.annotation.DirtiesContext
import se.difo.dates.TimeStampUtils
import se.difo.hemnetapi.BaseSpec
import se.difo.hemnetapi.admin.testdata.TestBr
import se.difo.hemnetapi.admin.testdata.TestBrf
import se.difo.hemnetapi.admin.testdata.TestImage
import se.difo.hemnetapi.util.verifiers.AdminBrVerifier
import se.difo.hemnetapi.util.verifiers.ExtBrVerifier
import se.difo.httpclient.DifoHttpClient

import java.time.Instant

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class BrScenariosSpec extends BaseSpec {

    def "Admin adds a new br"() {
        given: "an existing brf"
        TestBrf existingBrf = TestBrf.fromRsp(adminClient.addAndGetBrf(new TestBrf()))

        and:
        TestBr newBr = new TestBr()
        newBr.brfId = existingBrf.id
        assert adminClient.getBrByUrl(newBr.url).code == 404

        when:
        TestBr existingBr = TestBr.fromRsp(adminClient.addAndGetBr(newBr))
        TestImage existingImg1 = TestImage.fromRsp(
                adminClient.addAndGetImage(
                        new TestImage(objectId: existingBr.id, path: "image1.png")
                )
        )
        TestImage existingImg2 = TestImage.fromRsp(
                adminClient.addAndGetImage(
                        new TestImage(objectId: existingBr.id, path: "image2.png")
                )
        )

        then:
        DifoHttpClient.Response listRsp = extClient.getBrs()
        assert listRsp.code == 200
        Map listRspBody = ExtBrVerifier.verifyAndGetAdminBrListRspBodyFormat(listRsp.body)

        and:
        assert listRspBody.homes.size() == 1
        assert listRspBody.homes.get(0).id == existingBr.id
        assert listRspBody.homes.get(0).firstImageUrl.endsWith(existingImg1.path)

        when:
        DifoHttpClient.Response getRsp = extClient.getBr(existingBr.id)

        then:
        assert getRsp.code == 200
        Map getRspBody = ExtBrVerifier.verifyAndGetAdminBrRspBodyFormat(getRsp.body)
        assert getRspBody.imageUrls.findAll { it.endsWith(existingImg1.path) }.size() == 1
        assert getRspBody.imageUrls.findAll { it.endsWith(existingImg2.path) }.size() == 1
        assert getRspBody.brfId == existingBrf.id

    }

    def "Admin updates an existing br"() {
        given:
        TestBr existingBr = TestBr.fromRsp(adminClient.addAndGetBr(new TestBr()))
        TestImage.fromRsp(
                adminClient.addAndGetImage(
                        new TestImage(objectId: existingBr.id, path: "image1.png")
                )
        )

        and:
        assert adminClient.getBrByUrl(existingBr.url).code == 200

        existingBr.address = "address updated"
        existingBr.area = "area updated"
        existingBr.price = 987654321
        existingBr.fee = 123
        existingBr.rooms = 3.3
        existingBr.sqmLiving = 300
        existingBr.municipality = "municipality updated"
        existingBr.coordinate.lat = 11.11
        existingBr.coordinate.lng = 22.22

        existingBr.broker.firmWebPage = "http://tjompa.com"
        existingBr.broker.firmName = "brokerFirmName updated"
        existingBr.broker.personName = "brokerPersonName updated"

        existingBr.timeAdded = "2000-01-01T00:00:01Z"
        existingBr.timeUpdated = "2000-01-02T00:00:02Z"

        when:
        DifoHttpClient.Response updateRsp = adminClient.updateBr(existingBr)
        existingBr.timeRemoved = null

        then:
        assert updateRsp.code == 200
        AdminBrVerifier.verifyAndGetAdminBrRspBodyFormat(updateRsp.body)
        AdminBrVerifier.assertEqual(TestBr.fromRsp(updateRsp), existingBr)

        when:
        DifoHttpClient.Response getRsp = extClient.getBr(existingBr.id)

        then:
        assert getRsp.code == 200
        ExtBrVerifier.verifyAndGetAdminBrRspBodyFormat(getRsp.body)
        ExtBrVerifier.assertEqual(TestBr.fromRsp(getRsp), existingBr)
    }

    def "Admin marks br as removed from the market"() {
        given:
        TestBr existingBr = TestBr.fromRsp(adminClient.addAndGetBr(new TestBr()))
        existingBr.timeRemoved = TimeStampUtils.asString(Instant.now())
        assert !existingBr.removed

        when:
        adminClient.removeBr(existingBr.id)

        then:
        TestBr updated = TestBr.fromRsp(extClient.getBr(existingBr.id))
        assert updated.removed
    }


}
