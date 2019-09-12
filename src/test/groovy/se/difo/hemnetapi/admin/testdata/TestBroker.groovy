package se.difo.hemnetapi.admin.testdata

import groovy.json.JsonOutput

class TestBroker {

    String personName = "Ingela Mäklaren"
    String firmName = "Mecklare"
    String firmWebPage = "http://www.porr.com"

    static TestBroker fromRspBody(Map json) {
        return json != null ? new TestBroker(
                personName: json.personName,
                firmName: json.firmName,
                firmWebPage: json.firmWebPage,
        ) : null
    }

    Map asMap() {
        Map data = [
                personName : personName,
                firmName   : firmName,
                firmWebPage: firmWebPage
        ]

        return data
    }

    String asJson() {
        return JsonOutput.toJson(asMap())
    }

}
