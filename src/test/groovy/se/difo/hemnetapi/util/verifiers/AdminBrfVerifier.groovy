package se.difo.hemnetapi.util.verifiers

import groovy.json.JsonSlurper
import se.difo.hemnetapi.admin.testdata.TestBrf

class AdminBrfVerifier extends BaseVerifier {

    static JsonSlurper jsonSlurper = new JsonSlurper()

    static void assertEqual(TestBrf actual, TestBrf expected) {
        assert actual.name == expected.name
        assert actual.status == expected.status
        assert actual.yearRegistered == expected.yearRegistered
    }

    static Map verifyAndGetAdminBrfListRspBodyFormat(String rspBody) {
        Map listRspBody = (Map) jsonSlurper.parseText(rspBody)
        verifyJson(listRspBody, ['brfs', 'pagination'])
        verifyJson((Map) listRspBody.pagination, ["number", "size", "totalPages", "totalElements"])

        if (listRspBody.brfs.size() > 0) {
            listRspBody.brfs.each { Map home ->
                verifyJson(
                        home,
                        [
                                "id",
                                "name",
                                "status",
                                "yearRegistered",
                                "memberCount",
                        ]
                )
            }
        }

        return listRspBody
    }

    static Map verifyAndGetAdminBrfRspBodyFormat(String rspBody) {
        Map body = (Map) jsonSlurper.parseText(rspBody)

        verifyJson(
                body,
                [
                        "id",
                        "name",
                        "status",
                        "yearRegistered",
                        "memberCount",
                        "linkedBrs"
                ]
        )

        return body
    }

}
