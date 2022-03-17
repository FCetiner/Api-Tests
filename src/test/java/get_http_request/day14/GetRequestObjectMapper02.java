package get_http_request.day14;

import base_url.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.JsonUtil;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class GetRequestObjectMapper02 extends HerOkuAppBaseUrl {

    //https://restful-booker.herokuapp.com/booking/2 url’ine bir get request gönderildiğinde,
    // status kodun 200 ve response body’nin
    //{
    //“firstname”: “Ali”,
    //“lastname”: “Can”,
    //“totalprice”: 500,
    //“depositpaid”: true,
    //“bookingdates”: {
    //“checkin”: “2022-03-01”,
    //“checkout”: “2022-03-11”
    //},
    //“additionalneeds”: “Breakfast”
    //}
    //Olduğunu Object Mapper kullanarak test edin
    @Test
    public void test(){
        //url olustur
        spec05.pathParams("1","booking","2",13);//buradaki son rakam surekli degisitigi icin daha sonra tes fail olabilir
        //expected data
        String jsonData="{\n" +
                "   \"firstname\": \"Ali\",\n" +
                "   \"lastname\": \"Can\",\n" +
                "   \"totalprice\": 500,\n" +
                "   \"depositpaid\": true,\n" +
                "   \"bookingdates\": {\n" +
                "   \"checkin\": \"2022-03-01\",\n" +
                "   \"checkout\": \"2022-03-11\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "    }";
       HashMap<String,Object>expectedData=  JsonUtil.convertJsonToJava(jsonData, HashMap.class);
        System.out.println("expectedData = " + expectedData);

        //request response
        Response response=given()
                .contentType(ContentType.JSON)
                .spec(spec05)
                .when().get("/{1}/{2}");
        response.prettyPrint();
        //dogrulama
        //de serialization
        HashMap<String,Object>actualData=JsonUtil.convertJsonToJava(response.asString(),HashMap.class);
        System.out.println("actualData = " + actualData);

        response.then().assertThat().statusCode(200);
        Assert.assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        Assert.assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        Assert.assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        Assert.assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
        Assert.assertEquals(expectedData.get("bookingdates.checkin"),actualData.get("bookingdates.checkin"));
        Assert.assertEquals(expectedData.get("bookingdates.checkout"),actualData.get("bookingdates.checkout"));
        Assert.assertEquals(expectedData.get("additionalneeds"),actualData.get("additionalneeds"));
    }
}
