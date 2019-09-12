package se.difo.hemnetapi.util.verifiers


import se.difo.hemnetapi.admin.testdata.TestCoordinate

class CoordinateVerifier extends BaseVerifier {

    static void assertEqual(TestCoordinate actual, TestCoordinate expected) {
        assert actual.lat == expected.lat
        assert actual.lng == expected.lng
    }

    static void verifyJsonFormat(Object coordinate) {
        verifyJson((Map) coordinate, ['lat', 'lng'])
    }

}
