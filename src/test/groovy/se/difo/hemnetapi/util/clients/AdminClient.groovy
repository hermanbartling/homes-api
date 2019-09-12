package se.difo.hemnetapi.util.clients

import groovy.json.JsonOutput
import okhttp3.OkHttpClient
import se.difo.hemnetapi.admin.testdata.TestBr
import se.difo.hemnetapi.admin.testdata.TestBrf
import se.difo.hemnetapi.admin.testdata.TestHouse
import se.difo.hemnetapi.admin.testdata.TestImage
import se.difo.httpclient.DifoHttpClient
import se.difo.httpclient.DifoLoggingInterceptor

class AdminClient {

    private static final String BASE_PATH = "/api/v1/admin"

    DifoHttpClient httpClient = new DifoHttpClient(
            new OkHttpClient.Builder()
                    .addInterceptor(DifoLoggingInterceptor.builder()
                    .logReqHeaders(true)
                    .build())
                    .build()
    )

    final Map authHeader
    final Integer port

    AdminClient(Map authHeader, Integer port) {
        this.authHeader = authHeader
        this.port = port
    }

    DifoHttpClient.Response addBr(TestBr brToAdd) {
        return httpClient.post(
                "http://localhost:$port${BASE_PATH}/brs",
                JsonOutput.toJson(brToAdd.asMap()),
                authHeader
        )
    }

    DifoHttpClient.Response updateBr(TestBr brToUpdate) {
        return httpClient.put(
                "http://localhost:$port${BASE_PATH}/brs/${brToUpdate.id}",
                JsonOutput.toJson(brToUpdate.asMap()),
                authHeader
        )
    }

    DifoHttpClient.Response removeBr(String id) {
        return httpClient.post(
                "http://localhost:$port${BASE_PATH}/brs/$id/remove",
                "",
                authHeader
        )
    }

    DifoHttpClient.Response addAndGetBr(TestBr brToAdd) {
        DifoHttpClient.Response addRsp = addBr(brToAdd)
        return httpClient.get("http://localhost:$port${addRsp.headers['Location']}", [:], authHeader)
    }

    DifoHttpClient.Response getBr(String id) {
        return httpClient.get(
                "http://localhost:$port${BASE_PATH}/brs/$id",
                [:],
                authHeader
        )
    }

    DifoHttpClient.Response getBrByUrl(String url) {
        return httpClient.get(
                "http://localhost:$port${BASE_PATH}/brbyurl/",
                [url: url],
                authHeader
        )
    }

    DifoHttpClient.Response getBrs() {
        return httpClient.get(
                "http://localhost:$port${BASE_PATH}/brs",
                [:],
                authHeader
        )
    }

    DifoHttpClient.Response addHouse(TestHouse houseToAdd) {
        return httpClient.post(
                "http://localhost:$port${BASE_PATH}/houses",
                houseToAdd.asJson(),
                authHeader
        )
    }

    DifoHttpClient.Response addAndGetHouse(TestHouse brToAdd) {
        DifoHttpClient.Response addRsp = addHouse(brToAdd)
        return httpClient.get("http://localhost:$port${addRsp.headers['Location']}", [:], authHeader)
    }

    DifoHttpClient.Response getHouseByUrl(String url) {
        return httpClient.get(
                "http://localhost:$port${BASE_PATH}/housebyurl/",
                [url: url],
                authHeader
        )
    }


    DifoHttpClient.Response updateHouse(TestHouse toUpdate) {
        return httpClient.put(
                "http://localhost:$port${BASE_PATH}/houses/${toUpdate.id}",
                JsonOutput.toJson(toUpdate.asMap()),
                authHeader
        )
    }

    DifoHttpClient.Response removeHouse(String id) {
        return httpClient.post(
                "http://localhost:$port${BASE_PATH}/houses/$id/remove",
                "",
                authHeader
        )
    }

    DifoHttpClient.Response getHouse(String id) {
        return httpClient.get(
                "http://localhost:$port${BASE_PATH}/houses/$id",
                [:],
                authHeader
        )
    }

    DifoHttpClient.Response getHouses() {
        return httpClient.get(
                "http://localhost:$port${BASE_PATH}/houses",
                [:],
                authHeader
        )
    }

    DifoHttpClient.Response addImage(TestImage toAdd) {
        return httpClient.post(
                "http://localhost:$port${BASE_PATH}/images",
                toAdd.asJson(),
                authHeader
        )
    }

    DifoHttpClient.Response addAndGetImage(TestImage toAdd) {
        DifoHttpClient.Response addRsp = addImage(toAdd)
        return httpClient.get("http://localhost:$port${addRsp.headers['Location']}", [:], authHeader)
    }

    DifoHttpClient.Response getImage(String id) {
        return httpClient.get(
                "http://localhost:$port${BASE_PATH}/images/$id",
                [:],
                authHeader
        )
    }

    DifoHttpClient.Response getImages() {
        return httpClient.get(
                "http://localhost:$port${BASE_PATH}/images",
                [:],
                authHeader
        )
    }


    DifoHttpClient.Response addBrf(TestBrf brfToAdd) {
        return httpClient.post(
                "http://localhost:$port${BASE_PATH}/brfs",
                JsonOutput.toJson(brfToAdd.asMap()),
                authHeader
        )
    }

    DifoHttpClient.Response updateBrf(TestBrf brfToUpdate) {
        return httpClient.put(
                "http://localhost:$port${BASE_PATH}/brfs/${brfToUpdate.id}",
                JsonOutput.toJson(brfToUpdate.asMap()),
                authHeader
        )
    }

    DifoHttpClient.Response removeBrf(String id) {
        return httpClient.post(
                "http://localhost:$port${BASE_PATH}/brfs/$id/remove",
                "",
                authHeader
        )
    }

    DifoHttpClient.Response addAndGetBrf(TestBrf brfToAdd) {
        DifoHttpClient.Response addRsp = addBrf(brfToAdd)
        return httpClient.get("http://localhost:$port${addRsp.headers['Location']}", [:], authHeader)
    }

    DifoHttpClient.Response getBrf(String id) {
        return httpClient.get(
                "http://localhost:$port${BASE_PATH}/brfs/$id",
                [:],
                authHeader
        )
    }

    DifoHttpClient.Response getBrfNames() {
        return httpClient.get(
                "http://localhost:$port${BASE_PATH}/brfnames/",
                [:],
                authHeader
        )
    }

    DifoHttpClient.Response getBrfByName(String name) {
        return httpClient.get(
                "http://localhost:$port${BASE_PATH}/brfbyname/",
                [name: name],
                authHeader
        )
    }

    DifoHttpClient.Response getBrfs() {
        return httpClient.get(
                "http://localhost:$port${BASE_PATH}/brfs",
                [:],
                authHeader
        )
    }


}
