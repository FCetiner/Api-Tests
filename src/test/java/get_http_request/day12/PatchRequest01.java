package get_http_request.day12;

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

public class PatchRequest01 extends JsonPlaceHolderBaseUrl {
      /*
   https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönderdiğimde
  {

     "title": "Batch44"

    }
Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
{
"userId": 10,
"title": "Batch44"
"completed": true,
"id": 198
}
    */

    @Test
    public void test01(){
        //url olustur
        spec04.pathParams("1","todos","2",198);
        //expected data
        JsonPlaceHolderTestData testDataObje=new JsonPlaceHolderTestData();
        JSONObject requestData=testDataObje.setupPatchRequestData();
        JSONObject expectedData=testDataObje.setupPatchExpectedData();
        //Request response
        Response response=given()
                .contentType(ContentType.JSON)
                 .spec(spec04)
                .body(requestData.toString())
                .when()
                .patch("/{1}/{2}");
        //dogrulama
        //Json
        JsonPath json=response.jsonPath();
        assertEquals(expectedData.getString("title"),json.getString("title"));
        assertEquals(expectedData.getBoolean("completed"),json.getBoolean("completed"));
        assertEquals(expectedData.getInt("userId"),json.getInt("userId"));
        assertEquals(expectedData.getInt("id"),json.getInt("id"));
        //Matcher
        response.then().statusCode(200);
        response.then().body("title",equalTo(expectedData.getString("title"))
                ,"completed",equalTo(expectedData.getBoolean("completed"))
                ,"userId",equalTo(expectedData.getInt("userId"))
                ,"id",equalTo(expectedData.getInt("id")));
        //de serializatiion
        HashMap<String,Object> actualData=response.as(HashMap.class);
        assertEquals(expectedData.getString("title"),actualData.get("title"));
        assertEquals(expectedData.getBoolean("completed"),actualData.get("completed"));
        assertEquals(expectedData.getInt("userId"),actualData.get("userId"));
        assertEquals(expectedData.getInt("id"),actualData.get("id"));
    }
}
