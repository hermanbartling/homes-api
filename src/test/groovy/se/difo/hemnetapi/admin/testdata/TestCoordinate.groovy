package se.difo.hemnetapi.admin.testdata

import groovy.json.JsonOutput

class TestCoordinate {

    BigDecimal lat = 56.23
    BigDecimal lng = 34.6788

    static TestCoordinate fromRspBody(Map json) {
        return json != null ? new TestCoordinate(
                lat: json.lat,
                lng: json.lng
        ) : null
    }

    Map asMap() {
        Map data = [
                lat: lat,
                lng: lng
        ]

        return data
    }

    String asJson() {
        return JsonOutput.toJson(asMap())
    }

}
