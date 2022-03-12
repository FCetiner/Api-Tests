package get_http_request.day12;

import base_url.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import test_data.DummyTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class DeleteRequest extends DummyBaseUrl {
     /*
  http://dummy.restapiexample.com/api/v1/delete/2 bir DELETE request gönderdiğimde

Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
{
"status": "success",
"data": "2",
"message": "Successfully! Record has been deleted"
}
   */
    @Test
    public void test(){
        //1) URL OLUSTUR
    spec02.pathParams("1","api","2","v1","3","delete","4",2);
        //2) EXPECTED DATA
        DummyTestData testData=new DummyTestData();
        JSONObject expectedData=testData.setupDeleteExpectedData();
        System.out.println("expectedData = " + expectedData);
        //3) REQUEST VE RESPONSE
        Response response=given()
                .contentType(ContentType.JSON)
                .spec(spec02)
                .when()
                .delete("/{1}/{2}/{3}/{4}");
        //4) DOGRULAMA
        //json
        JsonPath json=response.jsonPath();
        assertEquals(expectedData.getString("status"),json.getString("status"));
        assertEquals(expectedData.getString("data"),json.getString("data"));
        assertEquals(expectedData.getString("message"),json.getString("message"));
        //Matcher
        response.then().statusCode(200);
        response.then().body("status",equalTo(expectedData.getString("status"))
                ,"data",equalTo(expectedData.getString("data"))
                ,"message",equalTo(expectedData.getString("message")));
        //de serialization
        HashMap<String,Object>actualData=response.as(HashMap.class);
        assertEquals(expectedData.getString("status"),actualData.get("status"));
        assertEquals(expectedData.getString("data"),actualData.get("data"));
        assertEquals(expectedData.getString("message"),actualData.get("message"));
    }
}
