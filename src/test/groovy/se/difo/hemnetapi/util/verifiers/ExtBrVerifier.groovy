package se.difo.hemnetapi.util.verifiers

import groovy.json.JsonSlurper
import se.difo.dates.TimeStampUtils
import se.difo.hemnetapi.admin.testdata.TestBr

class ExtBrVerifier extends BaseVerifier {

    static JsonSlurper jsonSlurper = new JsonSlurper()

    static void assertEqual(TestBr actual, TestBr expected) {
        assert actual.address == expected.address
        assert actual.area == expected.area
        assert actual.url == expected.url
        assert actual.price == expected.price
        assert actual.municipality == expected.municipality
        assert actual.rooms == expected.rooms
        assert actual.fee == expected.fee

        assert actual.sqmLiving == expected.sqmLiving

        assert TimeStampUtils.asInstant(actual.timeRemoved) == TimeStampUtils.asInstant(expected.timeRemoved)
        assert TimeStampUtils.asInstant(actual.timeAdded) == TimeStampUtils.asInstant(expected.timeAdded)

        BrokerVerifier.assertEqual(actual.broker, expected.broker)
        CoordinateVerifier.assertEqual(actual.coordinate, expected.coordinate)

    }

    static Map verifyAndGetAdminBrListRspBodyFormat(String rspBody) {
        Map listRspBody = (Map) jsonSlurper.parseText(rspBody)

        verifyJson(listRspBody, ['homes', 'pagination', 'mapConfig'])
        verifyJson((Map) listRspBody.pagination, ["number", "size", "totalPages", "totalElements"])

        if (listRspBody.mapConfig != null) {
            verifyJson((Map) listRspBody.mapConfig, ["centerCoordinate", "boundingBox"])
            CoordinateVerifier.verifyJsonFormat(listRspBody.mapConfig.centerCoordinate)
            verifyJson((Map) listRspBody.mapConfig.boundingBox, ["corner1", "corner2"])
            CoordinateVerifier.verifyJsonFormat(listRspBody.mapConfig.boundingBox.corner1)
            CoordinateVerifier.verifyJsonFormat(listRspBody.mapConfig.boundingBox.corner2)
        }

        if (listRspBody.homes.size() > 0) {
            listRspBody.homes.each { Map home ->
                verifyJson(
                        home,
                        [
                                "id",
                                "address",
                                "area",
                                "municipality",
                                "url",
                                "removed",
                                "firstImageUrl",
                                "timeAdded",
                                "timeRemoved",
                                "price",
                                "coordinate",
                                "sqmLiving",
                                "pricePerSqm",
                                "fee",
                                "rooms",
                                "broker",
                                "visitCount",
                        ]
                )
                verifyImageUrl((String) home.firstImageUrl)
                verifyTimestampFormat((String) home.timeAdded)
                verifyTimestampFormat((String) home.timeRemoved)
                BrokerVerifier.verifyJsonFormat(home.broker)
            }
        }

        return listRspBody
    }

    static Map verifyAndGetAdminBrRspBodyFormat(String rspBody) {
        Map body = (Map) jsonSlurper.parseText(rspBody)

        verifyJson(
                body,
                [
                        "id",
                        "address",
                        "area",
                        "municipality",
                        "url",
                        "timeAdded",
                        "timeRemoved",
                        "price",
                        "coordinate",
                        "imageUrls",
                        "firstImageUrl",
                        "sqmLiving",
                        "rooms",
                        "fee",
                        "broker",
                        "removed",
                        "visitCount",
                        "brfId",
                ]
        )

        verifyCoordinateFormat(body.coordinate)
        verifyImageUrl((String) body.firstImageUrl)
        verifyTimestampFormat((String) body.timeAdded)
        verifyTimestampFormat((String) body.timeRemoved)

        body.imageUrls.each {
            verifyImageUrl((String) it)
        }

        return body
    }

}
