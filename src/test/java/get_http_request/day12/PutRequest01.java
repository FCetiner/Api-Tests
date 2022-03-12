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

public class PutRequest01 extends JsonPlaceHolderBaseUrl {
    /*
https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönerdiğimde

{
"userId": 21,
"title": "Wash the dishes",
"completed": false
}
Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
{
"userId": 21,
"title": "Wash the dishes",
"completed": false,
"id": 198
}
*/
    @Test
    public void test(){
        //Url olustur
        spec04.pathParams("1","todos","2","198");
        //Expected data olustur
        JsonPlaceHolderTestData testDataObje=new JsonPlaceHolderTestData();
        JSONObject expectedRequest=testDataObje.setupPutData();
        System.out.println("expectedRequest = " + expectedRequest);
        //Request response
        Response response=given()
                .contentType(ContentType.JSON)
                .spec(spec04)
                .body(expectedRequest.toString())       //Json objectte toString yapilmali
                .when()
                .put("/{1}/{2}");
        response.prettyPrint();
        //Dogrulama
        //Matcher
        response.then().statusCode(200);
        response.then().body("userId",equalTo(expectedRequest.getInt("userId"))
                ,"title",equalTo(expectedRequest.getString("title"))
                ,"completed",equalTo(expectedRequest.getBoolean("completed")));
        //json
        JsonPath json=response.jsonPath();
        assertEquals(expectedRequest.getInt("userId"),json.getInt("userId"));
        assertEquals(expectedRequest.getString("title"),json.getString("title"));
        assertEquals(expectedRequest.getBoolean("completed"),json.getBoolean("completed"));

        //de serialization
        HashMap<String,Object>actualData=response.as(HashMap.class);
        assertEquals(expectedRequest.getInt("userId"),actualData.get("userId"));
        assertEquals(expectedRequest.getString("title"),actualData.get("title"));
        assertEquals(expectedRequest.getBoolean("completed"),actualData.get("completed"));
    }
}
