package se.difo.hemnetapi.admin.testdata

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import se.difo.httpclient.DifoHttpClient

class TestHouse {

    String address = "Paradisäppelvägen 314"
    String area = "Gnesta"
    String url = "http://house.home.com/" + UUID.randomUUID()
    String type = "Villa"
    Integer price = 3000000
    Integer costYear = 20000
    Integer builtYear = 1890
    BigDecimal rooms = 6
    BigDecimal sqmLiving = 200
    BigDecimal sqmLand = 10000
    BigDecimal sqmLivingAdditional = 60
    String municipality = "Strängnäs"

    TestCoordinate coordinate = new TestCoordinate()
    TestBroker broker = new TestBroker()

    String timeRemoved = "2019-01-01T12:00:01Z"
    String timeAdded = "2019-01-01T11:00:20Z"
    String timeUpdated = "2019-01-01T11:30:03Z"

    // set by server
    String id
    List<String> imageUrls
    Boolean removed

    static TestHouse fromRsp(DifoHttpClient.Response rsp) {
        Map houseInRsp = (Map) new JsonSlurper().parseText(rsp.body)
        return new TestHouse(
                id: houseInRsp.id,
                address: houseInRsp.address,
                area: houseInRsp.area,
                url: houseInRsp.url,
                price: houseInRsp.price,
                rooms: houseInRsp.rooms,
                type: houseInRsp.type,
                costYear: houseInRsp.costYear,
                builtYear: houseInRsp.builtYear,
                sqmLiving: houseInRsp.sqmLiving,
                sqmLivingAdditional: houseInRsp.sqmLivingAdditional,
                sqmLand: houseInRsp.sqmLand,
                municipality: houseInRsp.municipality,
                coordinate: TestCoordinate.fromRspBody((Map) houseInRsp.coordinate),
                broker: TestBroker.fromRspBody((Map) houseInRsp.broker),
                timeAdded: houseInRsp.timeAdded,
                timeRemoved: houseInRsp.timeRemoved,
                timeUpdated: houseInRsp.timeUpdated,
                imageUrls: houseInRsp.imageUrls,
                removed: houseInRsp.containsKey('removed') ? houseInRsp.removed : null
        )
    }

    Map asMap() {
        Map data = [
                address            : address,
                area               : area,
                url                : url,
                price              : price,
                rooms              : rooms,
                type               : type,
                costYear           : costYear,
                builtYear          : builtYear,
                sqmLiving          : sqmLiving,
                sqmLivingAdditional: sqmLivingAdditional,
                sqmLand            : sqmLand,
                municipality       : municipality,
                coordinate         : coordinate.asMap(),
                broker             : broker.asMap(),
                timeRemoved        : timeRemoved,
                timeAdded          : timeAdded,
                timeUpdated        : timeUpdated
        ]

        if (id != null) {
            data.id = id
        }

        if (imageUrls != null) {
            data.imageUrls = imageUrls
        }

        if (removed != null) {
            data.removed = removed
        }

        return data
    }

    String asJson() {
        return JsonOutput.toJson(asMap())
    }

}
