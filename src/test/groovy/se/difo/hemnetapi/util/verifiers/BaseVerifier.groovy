package se.difo.hemnetapi.util.verifiers

import groovy.json.JsonOutput
import org.apache.commons.validator.routines.UrlValidator

import java.time.Instant

import static java.time.format.DateTimeFormatter.ISO_INSTANT

class BaseVerifier {

    static void verifyCoordinateFormat(Object coordinate) {
        verifyJson((Map) coordinate, ["lat", "lng"])
    }

    static void verifyImageUrl(String url) {
        if (url == null) {
            return
        }

        assert url.startsWith("/images/")
//
//        String[] schemes = ["http", "https"]
//        UrlValidator urlValidator = new UrlValidator(schemes, UrlValidator.ALLOW_LOCAL_URLS)
//
//        if (!urlValidator.isValid(url)) {
//            println("Invalid URL detected: $url")
//            assert false
//        }
    }

    static void verifyTimestampFormat(String timestamp) {
        if (timestamp == null) {
            return
        }
        assert timestamp ==~ /\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}Z/
        assert Instant.from(ISO_INSTANT.parse(timestamp)) != null
    }

    static void verifyJson(Map json, List expectedParams) {
        try {

            json.keySet().each { presentParam ->
                assert expectedParams.contains(presentParam)
            }
            expectedParams.each { expectedParam ->
                assert json.keySet().contains(expectedParam)
            }
        } catch (Throwable t) {
            println("JSON format issue:")
            println("")
            println("Expected parameters:")
            println(JsonOutput.prettyPrint(JsonOutput.toJson(expectedParams.sort())))
            println("")
            println("Encountered parameters:")
            println(JsonOutput.prettyPrint(JsonOutput.toJson(json.sort()*.key)))
            throw t
        }
    }

}
