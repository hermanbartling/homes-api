package se.difo.hemnetapi.scenarios

import org.springframework.test.annotation.DirtiesContext
import se.difo.hemnetapi.BaseSpec
import se.difo.hemnetapi.admin.testdata.TestBr
import se.difo.hemnetapi.admin.testdata.TestBrf
import se.difo.hemnetapi.util.verifiers.AdminBrfVerifier
import se.difo.httpclient.DifoHttpClient

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class BrfScenariosSpec extends BaseSpec {

    def "Admin adds a new brf and associates a br to it"() {
        given: "the brf doesnt exist"
        TestBrf newBrf = new TestBrf()
        assert adminClient.getBrfByName(newBrf.name).code == 404

        when: "adding the brf"
        TestBrf addedBrf = TestBrf.fromRsp(adminClient.addAndGetBrf(newBrf))

        then: "the new brf is in the list"
        DifoHttpClient.Response listRsp = adminClient.getBrfs()
        assert listRsp.code == 200
        Map listRspBody = AdminBrfVerifier.verifyAndGetAdminBrfListRspBodyFormat(listRsp.body)

        and:
        assert listRspBody.brfs.size() == 1
        assert listRspBody.brfs.get(0).id == addedBrf.id

        when: "linking a br to the brf"
        TestBr addedBr = TestBr.fromRsp(adminClient.addAndGetBr(new TestBr(brfId: addedBrf.id)))

        then:
        assert addedBr.brfId == addedBrf.id

        when:
        DifoHttpClient.Response getBrfRsp = adminClient.getBrf(addedBrf.id)

        then:
        assert getBrfRsp.code == 200
        Map getRspBody = AdminBrfVerifier.verifyAndGetAdminBrfRspBodyFormat(getBrfRsp.body)
        getRspBody.linkedBrs.size() == 1
        getRspBody.linkedBrs.get(0) == addedBr.id

    }

    def "Admin updates an existing br"() {
        given:
        TestBrf existingBrf = TestBrf.fromRsp(adminClient.addAndGetBrf(new TestBrf()))

        and:
        assert adminClient.getBrfByName(existingBrf.name.toUpperCase()).code == 200

        existingBrf.status = "status updated"
        existingBrf.yearRegistered = 1803

        when:
        DifoHttpClient.Response updateRsp = adminClient.updateBrf(existingBrf)

        then:
        assert updateRsp.code == 200
        AdminBrfVerifier.verifyAndGetAdminBrfRspBodyFormat(updateRsp.body)
        AdminBrfVerifier.assertEqual(TestBrf.fromRsp(updateRsp), existingBrf)

        when:
        DifoHttpClient.Response getRsp = adminClient.getBrf(existingBrf.id)

        then:
        assert getRsp.code == 200
        AdminBrfVerifier.verifyAndGetAdminBrfRspBodyFormat(getRsp.body)
        AdminBrfVerifier.assertEqual(TestBrf.fromRsp(getRsp), existingBrf)
    }

//    def "Admin marks br as removed from the market"() {
//        given:
//        TestBr existingBr = TestBr.fromRsp(adminClient.addAndGetBr(new TestBr()))
//        existingBr.timeRemoved = TimeStampUtils.asString(Instant.now())
//        assert !existingBr.removed
//
//        when:
//        adminClient.removeBr(existingBr.id)
//
//        then:
//        TestBr updated = TestBr.fromRsp(extClient.getBr(existingBr.id))
//        assert updated.removed
//    }
//

}
