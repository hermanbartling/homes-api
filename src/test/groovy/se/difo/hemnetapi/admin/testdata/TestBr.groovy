package se.difo.hemnetapi.admin.testdata

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import se.difo.httpclient.DifoHttpClient

class TestBr {

    String address = "Paradisäppelvägen 314"
    String area = "Gnesta"
    String url = "http://br.home.com/" + UUID.randomUUID()
    Integer price = 1234000
    Integer fee = 1234000
    BigDecimal sqmLiving = 67
    String municipality = "Gnesta"
    BigDecimal rooms = 6

    TestCoordinate coordinate = new TestCoordinate()
    TestBroker broker = new TestBroker()

    String timeAdded = "2019-01-01T11:00:01Z"
    String timeUpdated = "2019-01-01T11:30:03Z"
    String timeRemoved = "2019-01-01T12:00:04Z"

    String brfId = UUID.randomUUID().toString()

    // set by server
    String id
    List<String> imageUrls
    Boolean removed

    Integer visitCount = 123

    static TestBr fromRsp(DifoHttpClient.Response rsp) {
        Map brInRsp = (Map) new JsonSlurper().parseText(rsp.body)
        return new TestBr(
                id: brInRsp.id,
                address: brInRsp.address,
                area: brInRsp.area,
                url: brInRsp.url,
                price: brInRsp.price,
                fee: brInRsp.fee,
                sqmLiving: brInRsp.sqmLiving,
                rooms: brInRsp.rooms,
                municipality: brInRsp.municipality,
                coordinate: TestCoordinate.fromRspBody((Map) brInRsp.coordinate),
                broker: TestBroker.fromRspBody((Map) brInRsp.broker),
                timeRemoved: brInRsp.timeRemoved,
                timeAdded: brInRsp.timeAdded,
                timeUpdated: brInRsp.timeUpdated,
                imageUrls: brInRsp.imageUrls,
                removed: brInRsp.containsKey('removed') ? brInRsp.removed : null,
                visitCount: brInRsp.visitCount,
                brfId: brInRsp.brfId
        )
    }

    Map asMap() {
        Map data = [
                address     : address,
                area        : area,
                url         : url,
                price       : price,
                fee         : fee,
                sqmLiving   : sqmLiving,
                rooms       : rooms,
                municipality: municipality,
                coordinate  : coordinate.asMap(),
                broker      : broker.asMap(),
                timeUpdated : timeUpdated,
                timeRemoved : timeRemoved,
                timeAdded   : timeAdded,
                visitCount  : visitCount,
                brfId       : brfId
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
