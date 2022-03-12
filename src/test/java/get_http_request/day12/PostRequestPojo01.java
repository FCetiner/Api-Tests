package get_http_request.day12;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostRequestPojo01 extends JsonPlaceHolderBaseUrl {
    /*
   https://jsonplaceholder.typicode.com/todos url 'ine bir request gönderildiğinde
Request body{
"userId": 21,
"id": 201,
"title": "Tidy your room",
"completed": false
}
Status kodun 201, response body 'nin ise

{
"userId": 21,
"id": 201,
"title": "Tidy your room",
"completed": false
}
*/
    @Test
    public void test(){
        //1) url olustur
        spec04.pathParam("1","todos");
        //2) expected data olustur
        JsonPlaceHolderPojo expectedData=new JsonPlaceHolderPojo(21,201,"Tidy your room",false);
        System.out.println("expectedData = " + expectedData);
        //3) request response
        Response response=given()
                .contentType(ContentType.JSON)
                .spec(spec04)
                .body(expectedData).when().post("/{1}");
        response.prettyPrint();

        //Dogrulama
        //de serialization
        JsonPlaceHolderPojo actualData=response.as(JsonPlaceHolderPojo.class);
        response.then().statusCode(201);
        assertEquals(expectedData.getUserId(),actualData.getUserId());
        assertEquals(expectedData.getId(),actualData.getId());
        assertEquals(expectedData.getTitle(),actualData.getTitle());
        assertEquals(expectedData.isCompleted(),actualData.isCompleted());
    }
}
