package se.difo.hemnetapi.admin


import se.difo.hemnetapi.BaseSpec
import se.difo.hemnetapi.admin.testdata.TestBr
import se.difo.hemnetapi.admin.testdata.TestHouse
import se.difo.hemnetapi.admin.testdata.TestImage
import se.difo.httpclient.DifoHttpClient

class AdminImageSpec extends BaseSpec {

    def "Add new image to br should work fine"() {
        given:
        TestBr existingBr = TestBr.fromRsp(adminClient.addAndGetBr(new TestBr()))
        TestImage imgToAdd = new TestImage(objectId: existingBr.id)

        when:
        DifoHttpClient.Response rsp = adminClient.addImage(imgToAdd)

        then:
        assert rsp.code == 201
        assert rsp.headers.containsKey('Location')
        DifoHttpClient.Response getRsp = httpClient.get("http://localhost:$port${rsp.headers['Location']}", [:], authHeader)

        and:
        assert getRsp.code == 200
        TestImage addedImage = TestImage.fromRsp(getRsp)
        assert addedImage.objectId == imgToAdd.objectId
        assert addedImage.path == imgToAdd.path
        assert addedImage.id != null
        assert addedImage.timeAdded != null

        and:
        assert existingBr.imageUrls == null || existingBr.imageUrls.isEmpty()
        TestBr existingAfterImgAdded = TestBr.fromRsp(adminClient.getBr(existingBr.id))
        assert existingAfterImgAdded.imageUrls.size() == 1
        assert existingAfterImgAdded.imageUrls[0].endsWith(imgToAdd.path)

    }

    def "Add new image to house should work fine"() {
        given:
        TestHouse existingHouse = TestHouse.fromRsp(adminClient.addAndGetHouse(new TestHouse()))
        TestImage imgToAdd = new TestImage(objectId: existingHouse.id)

        when:
        DifoHttpClient.Response rsp = adminClient.addImage(imgToAdd)

        then:
        assert rsp.code == 201
        assert rsp.headers.containsKey('Location')
        DifoHttpClient.Response getRsp = httpClient.get("http://localhost:$port${rsp.headers['Location']}", [:], authHeader)

        and:
        assert getRsp.code == 200
        TestImage addedImage = TestImage.fromRsp(getRsp)
        assert addedImage.objectId == imgToAdd.objectId
        assert addedImage.path == imgToAdd.path
        assert addedImage.id != null
        assert addedImage.timeAdded != null

        and:
        assert existingHouse.imageUrls == null || existingHouse.imageUrls.isEmpty()
        TestHouse existingAfterImgAdded = TestHouse.fromRsp(adminClient.getHouse(existingHouse.id))
        assert existingAfterImgAdded.imageUrls.size() == 1
        assert existingAfterImgAdded.imageUrls[0].endsWith(imgToAdd.path)

    }

    def "Add new image with non-existent associated object should be blocked"() {
        given:
        TestImage imgToAdd = new TestImage(objectId: UUID.randomUUID())

        when:
        DifoHttpClient.Response rsp = adminClient.addImage(imgToAdd)

        then:
        assert rsp.code == 400

    }

}
