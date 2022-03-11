package get_http_request.day11;

import base_url.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.DummyTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class PostRequest02 extends DummyBaseUrl{
    /*
http://dummy.restapiexample.com/api/v1/create url ine, Request Body olarak
{
    "name":"Ali Can",
    "salary":"2000",
    "age":"40",
}
gönderildiğinde,Status kodun 200 olduğunu ve dönen response body nin,

{
    "status": "success",
    "data": {
    "id":…
},
    "message": "Successfully! Record has been added."
}

olduğunu test edin
 */
    @Test
    public void test02(){
        //1 Url olustur
        spec02.pathParams("1","api","2","v1","3","create");
        //2 Expected Data olustur
        DummyTestData testData=new DummyTestData();
        //REquest için:
        HashMap<String,Object> expectedDataMap=testData.setupExpectedData();
        System.out.println("expectedData = " + expectedDataMap);
        //Expected Data için:
        HashMap<String,Object> requestBosyMap=testData.setupRequestBody();
        //3 Request response
        Response response=given()
                .contentType(ContentType.JSON)
                .spec(spec02)
                .body(requestBosyMap)
                .when()
                .post("/{1}/{2}/{3}");

        //Post işleminde Map kullandigimizda toString'e gerek yok toString sadece JsonObjectte kullanilmali
      //  response.prettyPrint();
        //4 dogrulama
        //De serialization
        HashMap<String,Object> actualData=response.as(HashMap.class);
        System.out.println("actualData = " + actualData);
        assertEquals(expectedDataMap.get("statusCode"),response.statusCode());
        assertEquals(expectedDataMap.get("message"),actualData.get("message"));
        assertEquals(expectedDataMap.get("status"),actualData.get("status"));

        //Matcher class
        response.then().assertThat().body("status",equalTo(expectedDataMap.get("status")));
        response.then().assertThat().body("message",equalTo(expectedDataMap.get("message")));

        //Json ile
        JsonPath json=response.jsonPath();
        assertEquals(expectedDataMap.get("message"),json.getString("message"));
        assertEquals(expectedDataMap.get("status"),json.getString("status"));
    }
}
