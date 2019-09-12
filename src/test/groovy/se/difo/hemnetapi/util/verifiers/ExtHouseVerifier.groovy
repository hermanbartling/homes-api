package se.difo.hemnetapi.util.verifiers

import groovy.json.JsonSlurper
import se.difo.dates.TimeStampUtils
import se.difo.hemnetapi.admin.testdata.TestHouse

class ExtHouseVerifier extends BaseVerifier {

    static JsonSlurper jsonSlurper = new JsonSlurper()

    static void assertEqual(TestHouse actual, TestHouse expected) {
        assert actual.address == expected.address
        assert actual.area == expected.area
        assert actual.url == expected.url
        assert actual.price == expected.price
        assert actual.municipality == expected.municipality
        assert actual.rooms == expected.rooms
        assert actual.type == expected.type
        assert actual.costYear == expected.costYear
        assert actual.builtYear == expected.builtYear

        assert actual.sqmLiving == expected.sqmLiving
        assert actual.sqmLivingAdditional == expected.sqmLivingAdditional
        assert actual.sqmLand == expected.sqmLand

        if (expected.timeRemoved != null) {
            assert actual.timeRemoved != null
            assert TimeStampUtils.asInstant(actual.timeRemoved) == TimeStampUtils.asInstant(expected.timeRemoved)
        }
        assert TimeStampUtils.asInstant(actual.timeAdded) == TimeStampUtils.asInstant(expected.timeAdded)

        BrokerVerifier.assertEqual(actual.broker, expected.broker)
        CoordinateVerifier.assertEqual(actual.coordinate, expected.coordinate)

    }

    static Map verifyAndGetHouseListRspBodyFormat(String rspBody) {
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
                                "visitCount"
                        ]
                )
                verifyImageUrl((String) home.firstImageUrl)
                verifyTimestampFormat((String) home.timeAdded)
                verifyTimestampFormat((String) home.timeRemoved)

            }
        }

        return listRspBody
    }

    static Map verifyAndGetHouseRspBodyFormat(String rspBody) {
        Map body = (Map) jsonSlurper.parseText(rspBody)

        List expectedParams = [
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
                "imageUrls",
                "sqmLiving",
                "sqmLivingAdditional",
                "sqmLand",
                "rooms",
                "costYear",
                "builtYear",
                "type",
                "broker",
                "visitCount",
        ]
        verifyJson(body, expectedParams)
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
