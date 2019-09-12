package se.difo.hemnetapi.util.verifiers

import groovy.json.JsonSlurper
import se.difo.hemnetapi.admin.testdata.TestBroker

class BrokerVerifier extends BaseVerifier {

    static void assertEqual(TestBroker actual, TestBroker expected) {
        assert actual.personName == expected.personName
        assert actual.firmName == expected.firmName
        assert actual.firmWebPage == expected.firmWebPage
    }

    static void verifyJsonFormat(Object coordinate) {
        verifyJson((Map) coordinate, ['personName', 'firmName', 'firmWebPage'])
    }

}
