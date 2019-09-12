package se.difo.hemnetapi.auth

import se.difo.hemnetapi.BaseSpec
import se.difo.httpclient.DifoHttpClient

class AuthSpec extends BaseSpec {

    def "Accessing public api not logged in should work fine"() {
        when:
        DifoHttpClient.Response rsp = httpClient.get(
                "http://localhost:$port/api/v1/public/houses",
                [:],
                [:]
        )

        then:
        rsp.code == 200
    }

    def "Accessing admin api not logged in should be blocked"() {
        when:
        DifoHttpClient.Response rsp = httpClient.get(
                "http://localhost:$port/api/v1/admin/houses",
                [:],
                [:]
        )

        then:
        rsp.code == 401
    }

    def "Accessing admin api logged in with valid credentials should work fine"() {
        when:
        DifoHttpClient.Response rsp = httpClient.get(
                "http://localhost:$port/api/v1/admin/brs",
                [:],
                [
                        Authorization: "Basic " + "$username:$password".bytes.encodeBase64()
                ]
        )

        then:
        rsp.code == 200
    }

    def "Accessing admin api logged in with invalid credentials should be blocked"() {
        when:
        DifoHttpClient.Response rsp = httpClient.get(
                "http://localhost:$port/api/v1/admin/brs",
                [:],
                [
                        Authorization: "Basic " + "Knasen:$password".bytes.encodeBase64()
                ]
        )

        then:
        rsp.code == 401
    }

}
