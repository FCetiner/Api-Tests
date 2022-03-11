package get_http_request.day11;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class PostRequest03 extends JsonPlaceHolderBaseUrl {
    /*
   https://jsonplaceholder.typicode.com/todos URL ine aşağıdaki body gönderildiğinde,
    {
    "userId": 55,
    "title": "Tidy your room",
    "completed": false
  }
    Dönen response un Status kodunun 201 ve response body nin aşağıdaki gibi olduğunu test edin
  {
    "userId": 55,
    "title": "Tidy your room",
    "completed": false,
    "id": …
   }
*/
    @Test
    public void test03(){
        //1 Url olustur
        spec04.pathParam("1","todos");
        //2 expected data
        JsonPlaceHolderTestData testObje=new JsonPlaceHolderTestData();
        JSONObject expectedRequest=testObje.setupPostData();
        System.out.println("expectedRequest = " + expectedRequest);
        //3 Request response
        Response response=given()
                .contentType(ContentType.JSON)
                .spec(spec04)
                .body(expectedRequest.toString())
                .when()
                .post("/{1}");  //son kismi POST
        response.prettyPrint();
        //4 dogrulama
        //Matcher
        response.then().assertThat().statusCode(expectedRequest.getInt("statusCode"));
                response.then().body("userId",equalTo(expectedRequest.get("userId")),
                "title",equalTo(expectedRequest.get("title")),
                "completed",equalTo(expectedRequest.get("completed")) ,
                "id",equalTo(expectedRequest.get("id")));
       //JsonPath
        JsonPath json=response.jsonPath();
        assertEquals(expectedRequest.get("id"),json.getInt("id"));
        assertEquals(expectedRequest.get("userId"),json.getInt("userId"));
        assertEquals(expectedRequest.get("statusCode"),json.getInt("statusCode"));
        assertEquals(expectedRequest.get("title"),json.getString("title"));
        assertEquals(expectedRequest.get("completed"),json.getBoolean("completed"));

        //de serialization
        HashMap<String,Object> actualDataMap=response.as((HashMap.class));
        assertEquals(expectedRequest.get("id"),actualDataMap.get("id"));
        assertEquals(expectedRequest.get("userId"),actualDataMap.get("userId"));
        assertEquals(expectedRequest.get("statusCode"),actualDataMap.get("statusCode"));
        assertEquals(expectedRequest.get("title"),actualDataMap.get("title"));
        assertEquals(expectedRequest.get("completed"),actualDataMap.get("completed"));



    }
}
