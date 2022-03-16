package get_http_request.day14;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.JsonUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequestObjectMapper01_1 extends JsonPlaceHolderBaseUrl {
    /*
       https://jsonplaceholder.typicode.com/todos/198 url’ine bir get request gönderildiğinde,
   Dönen response ’un status kodunun 200 ve body kısmının
    {
    "userId": 10,
    "id": 198,
    "title": "quis eius est sint explicabo",
    "completed": true
    }
   Olduğunu Object Mapper kullanarak test edin
        */
    @Test
    public void test(){
        // 1 URL OLUSTUR
        spec04.pathParams("param1","todos","param2",198);
        // 2 EXPECTED DATA
        String jsonData = "{\n" +
                " \"userId\": 10,\n" +
                " \"id\": 198,\n" +
                " \"title\": \"quis eius est sint explicabo\",\n" +
                " \"completed\": true\n" +
                " }";
        Map<String,Object> expectedData =  JsonUtil.convertJsonToJava(jsonData, LinkedHashMap.class);
        //JsonUtil.convertJsonToJava(jsonData, HashMap.class);
        //JsonUtil.convertJsonToJava(jsonData, List.class);
        System.out.println("MapData "+ expectedData);
        System.out.println("jsonData" + jsonData);
        // 3 RESPONSE REQUEST
        Response response = given().contentType(ContentType.JSON).spec(spec04)
                .when().get("/{param1}/{param2}");
        response.prettyPrint();
        // Dogrulama
        // De-Serialization
        Map<String,Object> actualData = response.as(LinkedHashMap.class);
        System.out.println(actualData);
        Assert.assertEquals(expectedData,actualData);

    }
}