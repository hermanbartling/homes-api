package se.difo.hemnetapi


import groovy.json.JsonSlurper
import okhttp3.OkHttpClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import se.difo.hemnetapi.util.clients.AdminClient
import se.difo.hemnetapi.util.clients.PublicClient
import se.difo.httpclient.DifoHttpClient
import se.difo.httpclient.DifoLoggingInterceptor
import spock.lang.Specification

@SpringBootTest(classes = [App.class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
//@DirtiesContext()
class BaseSpec extends Specification {

    static JsonSlurper jsonSlurper = new JsonSlurper()

    static DifoHttpClient httpClient = new DifoHttpClient(
            new OkHttpClient.Builder()
                    .addInterceptor(DifoLoggingInterceptor.builder()
                    .logReqHeaders(true)
                    .build())
                    .build()
    )

    static String username = 'tjorven'
    static String password = 'korven'

    static Map authHeader = [
            Authorization: "Basic " + "$username:$password".bytes.encodeBase64()
    ]

    static final String PROP_SECURITY_USERNAME = "security.username"
    static final String PROP_SECURITY_PASSWORD = "security.password"

    @LocalServerPort
    int port

    private String testCaseName
    private String testCaseDelimiterLine
    AdminClient adminClient
    PublicClient extClient


    def setupSpec() {
        System.setProperty(PROP_SECURITY_USERNAME, username)
        System.setProperty(PROP_SECURITY_PASSWORD, password)
        printProps()
    }

    def setup() {
        adminClient = new AdminClient(authHeader, port)
        extClient = new PublicClient(port)

        testCaseName = "${this.getClass().simpleName} : '$specificationContext.currentIteration.name'"
        testCaseDelimiterLine = "*" * testCaseName.length()

        println()
        println(testCaseDelimiterLine)
        println(testCaseName)
        println(testCaseDelimiterLine)
    }

    def cleanup() {
        println(testCaseDelimiterLine)
    }

    void printProps() {
        println("-------------------------------")
        println("SYSTEM PROPERTIES")
        println("-------------------------------")
        [
                PROP_SECURITY_USERNAME,
                PROP_SECURITY_PASSWORD
        ].sort().each {
            println("$it: ${System.getProperty(it)}")
        }
        println("-------------------------------")
    }

    static Map parseJson(String json) {
        return (Map) jsonSlurper.parseText(json)
    }

    static List parseJsonList(String json) {
        return (List) jsonSlurper.parseText(json)
    }

}