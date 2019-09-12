package se.difo.hemnetapi.scenarios

import org.springframework.test.annotation.DirtiesContext
import se.difo.dates.TimeStampUtils
import se.difo.hemnetapi.BaseSpec
import se.difo.hemnetapi.admin.testdata.TestHouse
import se.difo.hemnetapi.admin.testdata.TestImage
import se.difo.hemnetapi.util.verifiers.AdminHouseVerifier
import se.difo.hemnetapi.util.verifiers.ExtHouseVerifier
import se.difo.httpclient.DifoHttpClient

import java.time.Instant

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class HouseScenariosSpec extends BaseSpec {

    def "Admin adds a new house"() {
        given:
        TestHouse newHouse = new TestHouse()
        assert adminClient.getHouseByUrl(newHouse.url).code == 404

        when:
        TestHouse existingHouse = TestHouse.fromRsp(adminClient.addAndGetHouse(newHouse))
        TestImage existingImg1 = TestImage.fromRsp(
                adminClient.addAndGetImage(
                        new TestImage(objectId: existingHouse.id, path: "image1.png")
                )
        )
        TestImage existingImg2 = TestImage.fromRsp(
                adminClient.addAndGetImage(
                        new TestImage(objectId: existingHouse.id, path: "image2.png")
                )
        )

        then:
        DifoHttpClient.Response listRsp = extClient.getHouses()
        assert listRsp.code == 200
        Map listRspBody = ExtHouseVerifier.verifyAndGetHouseListRspBodyFormat(listRsp.body)

        and:
        assert listRspBody.homes.size() == 1
        assert listRspBody.homes.get(0).id == existingHouse.id
        assert listRspBody.homes.get(0).firstImageUrl.endsWith(existingImg1.path)

        when:
        DifoHttpClient.Response getRsp = extClient.getHouse(existingHouse.id)

        then:
        assert getRsp.code == 200
        Map getRspBody = ExtHouseVerifier.verifyAndGetHouseRspBodyFormat(getRsp.body)
        assert getRspBody.imageUrls.findAll { it.endsWith(existingImg1.path) }.size() == 1
        assert getRspBody.imageUrls.findAll { it.endsWith(existingImg2.path) }.size() == 1

    }

    def "Admin updates an existing house"() {
        given:
        TestHouse existingHouse = TestHouse.fromRsp(adminClient.addAndGetHouse(new TestHouse()))
        TestImage.fromRsp(
                adminClient.addAndGetImage(
                        new TestImage(objectId: existingHouse.id, path: "image1.png")
                )
        )

        and:
        assert adminClient.getHouseByUrl(existingHouse.url).code == 200

        existingHouse.address = "address updated"
        existingHouse.area = "area updated"
        existingHouse.price = 987654321
        existingHouse.type = "type updated"
        existingHouse.rooms = 3.3
        existingHouse.sqmLiving = 300
        existingHouse.sqmLivingAdditional = 3003
        existingHouse.sqmLand = 300232
        existingHouse.costYear = 4321
        existingHouse.builtYear = 9876
        existingHouse.municipality = "municipality updated"
        existingHouse.coordinate.lat = 11.11
        existingHouse.coordinate.lng = 22.22

        existingHouse.broker.firmWebPage = "http://tjompa.com"
        existingHouse.broker.firmName = "brokerFirmName updated"
        existingHouse.broker.personName = "brokerPersonName updated"

        existingHouse.timeAdded = "2000-01-01T00:00:01Z"
        existingHouse.timeUpdated = "2000-01-02T00:00:20Z"
        existingHouse.timeRemoved = "2000-01-03T00:03:00Z"

        when:
        DifoHttpClient.Response updateRsp = adminClient.updateHouse(existingHouse)
        existingHouse.timeRemoved = null

        then:
        assert updateRsp.code == 200
        AdminHouseVerifier.verifyAndGetHouseRspBodyFormat(updateRsp.body)
        AdminHouseVerifier.assertEqual(TestHouse.fromRsp(updateRsp), existingHouse)

        when:
        DifoHttpClient.Response getRsp = extClient.getHouse(existingHouse.id)

        then:
        assert getRsp.code == 200
        ExtHouseVerifier.verifyAndGetHouseRspBodyFormat(getRsp.body)
        ExtHouseVerifier.assertEqual(TestHouse.fromRsp(getRsp), existingHouse)

    }

    def "Admin marks house as removed from the market"() {
        given:
        TestHouse existing = TestHouse.fromRsp(adminClient.addAndGetHouse(new TestHouse()))
        assert !existing.removed
        existing.timeRemoved = TimeStampUtils.asString(Instant.now())

        when:
        adminClient.removeHouse(existing.id)

        then:
        TestHouse updated = TestHouse.fromRsp(extClient.getHouse(existing.id))
        assert updated.removed
    }


}
