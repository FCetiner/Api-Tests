package get_http_request.day14;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.JsonUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequestObjectMapper01 extends JsonPlaceHolderBaseUrl {
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
        //MActher ile
        response.then().
                assertThat().
                statusCode(200).
                body("userId",equalTo(expectedData.get("userId"))
                        ,"id",equalTo(expectedData.get("id"))
                        ,"title",equalTo(expectedData.get("title"))
                        ,"completed",equalTo(expectedData.get("completed")));
        //De Serialization ile
        HashMap<String,Object>actualData1=response.as(HashMap.class);
        System.out.println(actualData1);
        Map<String,Object> actualData2=JsonUtil.convertJsonToJava(response.asString(),Map.class);
        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals(expectedData.get("userId"),actualData2.get("userId"));
        Assert.assertEquals(expectedData.get("idd"),actualData2.get("idd"));
        Assert.assertEquals(expectedData.get("title"),actualData2.get("title"));
        Assert.assertEquals(expectedData.get("completed"),actualData2.get("completed"));
        //JsonPath ile
        JsonPath json=response.jsonPath();
        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals(expectedData.get("userId"),json.getInt("userId"));
        Assert.assertEquals(expectedData.get("id"),json.getInt("id"));
        Assert.assertEquals(expectedData.get("title"),json.getString("title"));
        Assert.assertEquals(expectedData.get("completed"),json.getBoolean("completed"));

}
}
