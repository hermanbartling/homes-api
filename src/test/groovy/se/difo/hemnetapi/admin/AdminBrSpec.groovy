package se.difo.hemnetapi.admin


import se.difo.hemnetapi.BaseSpec
import se.difo.hemnetapi.admin.testdata.TestBr
import se.difo.hemnetapi.util.verifiers.AdminBrVerifier
import se.difo.httpclient.DifoHttpClient
import spock.lang.Unroll

class AdminBrSpec extends BaseSpec {


    def "Add new br should work fine"() {
        given:
        TestBr brToAdd = new TestBr()

        when:
        DifoHttpClient.Response rsp = adminClient.addBr(brToAdd)

        then:
        assert rsp.code == 201
        assert rsp.headers.containsKey('Location')
        DifoHttpClient.Response getRsp = httpClient.get("http://localhost:$port${rsp.headers['Location']}", [:], authHeader)

        and:
        assert getRsp.code == 200
        TestBr addedBr = TestBr.fromRsp(getRsp)
        AdminBrVerifier.assertEqual(brToAdd, addedBr)
    }

    @Unroll
    def "Invalid timestamp format should be rejected when adding br"() {
        when:
        DifoHttpClient.Response rsp = adminClient.addBr(
                new TestBr(timeAdded: timeAdded, timeUpdated: timeUpdated, timeRemoved: timeRemoved)
        )

        then:
        assert rsp.code == 400

        where:
        timeAdded                  | timeUpdated                | timeRemoved
        '2019-01-01 12:00:00'      | '2019-01-01T12:00:00.123Z' | '2019-01-01T12:00:00.123Z'
        '2019-01-01T12:00:00.123Z' | '2019-01-01 12:00:00'      | '2019-01-01T12:00:00.123Z'
        '2019-01-01T12:00:00.123Z' | '2019-01-01T12:00:00.123Z' | '2019-01-01 12:00:00'
    }

}
