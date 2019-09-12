package se.difo.hemnetapi.util.clients


import okhttp3.OkHttpClient
import se.difo.httpclient.DifoHttpClient
import se.difo.httpclient.DifoLoggingInterceptor

class PublicClient {

    private static final String BASE_PATH = "/api/v1/public/"

    DifoHttpClient httpClient = new DifoHttpClient(
            new OkHttpClient.Builder()
                    .addInterceptor(DifoLoggingInterceptor.builder()
                    .build())
                    .build()
    )

    final Map authHeader
    final Integer port

    PublicClient(Integer port) {
        this.authHeader = [:]
        this.port = port
    }

    DifoHttpClient.Response getBr(String id) {
        return httpClient.get(
                "http://localhost:$port${BASE_PATH}brs/$id",
                [:],
                authHeader
        )
    }

    DifoHttpClient.Response getBrs() {
        return httpClient.get(
                "http://localhost:$port${BASE_PATH}brs",
                [:],
                authHeader
        )
    }

    DifoHttpClient.Response getHouse(String id) {
        return httpClient.get(
                "http://localhost:$port${BASE_PATH}houses/$id",
                [:],
                authHeader
        )
    }

    DifoHttpClient.Response getHouses() {
        return httpClient.get(
                "http://localhost:$port${BASE_PATH}houses",
                [:],
                authHeader
        )
    }

}
