package se.difo.hemnetapi.admin.testdata

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import se.difo.httpclient.DifoHttpClient

class TestBrf {

    String name = "BRF Älgskyttarne 9 " + UUID.randomUUID()
    String status = "Äkta brf"
    Integer yearRegistered = 1980
    Integer memberCount = 20

    // set by server
    String id

    static TestBrf fromRsp(DifoHttpClient.Response rsp) {
        Map body = (Map) new JsonSlurper().parseText(rsp.body)
        return new TestBrf(
                id: body.id,
                name: body.name,
                status: body.status,
                yearRegistered: body.yearRegistered,
                memberCount: body.memberCount,
        )
    }

    Map asMap() {
        Map data = [
                name          : name,
                status        : status,
                yearRegistered: yearRegistered,
                memberCount   : memberCount,
        ]

        if (id != null) {
            data.id = id
        }

        return data
    }

    String asJson() {
        return JsonOutput.toJson(asMap())
    }

}
