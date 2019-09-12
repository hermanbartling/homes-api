package se.difo.hemnetapi.admin

import se.difo.hemnetapi.BaseSpec
import se.difo.hemnetapi.admin.testdata.TestBr
import se.difo.hemnetapi.admin.testdata.TestHouse
import se.difo.hemnetapi.util.verifiers.AdminBrVerifier
import se.difo.hemnetapi.util.verifiers.AdminHouseVerifier
import se.difo.httpclient.DifoHttpClient
import spock.lang.Unroll

class AdminHouseSpec extends BaseSpec {


    def "Add new house should work fine"() {
        given:
        TestHouse houseToAdd = new TestHouse()

        when:
        DifoHttpClient.Response rsp = adminClient.addHouse(houseToAdd)

        then:
        assert rsp.code == 201
        assert rsp.headers.containsKey('Location')
        DifoHttpClient.Response getRsp = httpClient.get(
                "http://localhost:$port${rsp.headers['Location']}",
                [:],
                authHeader
        )

        and:
        assert getRsp.code == 200
        TestHouse addedHouse = TestHouse.fromRsp(getRsp)
        AdminHouseVerifier.assertEqual(addedHouse, houseToAdd)
    }

    @Unroll
    def "Invalid timestamp format should be rejected when adding house"() {
        when:
        DifoHttpClient.Response rsp = adminClient.addHouse(
                new TestHouse(timeAdded: timeAdded, timeUpdated: timeUpdated, timeRemoved: timeRemoved)
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
