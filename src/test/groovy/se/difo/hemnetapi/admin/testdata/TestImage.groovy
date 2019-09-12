package se.difo.hemnetapi.admin.testdata

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import se.difo.httpclient.DifoHttpClient

class TestImage {

    String objectId = UUID.randomUUID().toString()
    String path = "here/we/go/" + UUID.randomUUID() + ".png"

    // set by server
    Long id
    String timeAdded

    Map asMap() {
        Map data = [
                objectId: objectId,
                path    : path
        ]

        if (id != null) {
            data.id = id
        }
        if (timeAdded != null) {
            data.timeAdded = timeAdded
        }

        return data
    }

    String asJson() {
        return JsonOutput.toJson(asMap())
    }

    static TestImage fromRsp(DifoHttpClient.Response rsp) {
        Map brInRsp = (Map) new JsonSlurper().parseText(rsp.body)
        return new TestImage(
                id: brInRsp.id,
                objectId: brInRsp.objectId,
                path: brInRsp.path,
                timeAdded: brInRsp.timeAdded
        )
    }

    static TestImage fromMap(Map map) {
        return new TestImage(
                id: map.id,
                objectId: map.objectId,
                path: map.path,
                timeAdded: map.timeAdded
        )
    }


}
