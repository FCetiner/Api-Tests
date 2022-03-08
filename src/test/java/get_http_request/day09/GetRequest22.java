package get_http_request.day09;

import base_url.HerOkuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class GetRequest22 extends HerOkuAppBaseUrl {
    /*
https://restful-booker.herokuapp.com/booking/47
       {
           "firstname": "Ali",
           "lastname": "Can",
           "totalprice": 500,
           "depositpaid": true,
           "bookingdates": {
               "checkin": "2022-02-01",
               "checkout": "2022-02-11"
          }
       }
1) JsonPhat
2) De-Serialization
*/
    @Test
    public void test22(){
        //1 Url olustur
        spec05.pathParams("1","booking","2",40);
        //2 expected data olustur
        HerOkuAppTestData testDataObje=new HerOkuAppTestData();
        HashMap<String,Object>expectedData=testDataObje.setupTestData();
        System.out.println("Test data icindeki expected data : "+expectedData);
        //3 Request ve Response
        Response response=given().spec(spec05).when().get("/{1}/{2}");
        response.prettyPrint();
        //1 Matcher
        response.then().assertThat().body("firstname",equalTo(expectedData.get("firstname"))
                ,"lastname",equalTo(expectedData.get("lastname"))
                ,"totalprice",equalTo(expectedData.get("totalprice"))
                ,"depositpaid",equalTo(expectedData.get("depositpaid"))
                ,"bookingdates",equalTo(expectedData.get("bookingdates")));
        //2 json
        JsonPath json=response.jsonPath();
        assertEquals(expectedData.get("firstname"),json.getString("firstname"));
        assertEquals(expectedData.get("lastname"),json.getString("lastname"));
        assertEquals(expectedData.get("totalprice"),json.getInt("totalprice"));
        assertEquals(expectedData.get("depositpaid"),json.getBoolean("depositpaid"));
        assertEquals(((Map)expectedData.get("bookingdates")).get("checkin"),json.getString("bookingdates.checkin"));
        assertEquals(((Map)expectedData.get("bookingdates")).get("checkout"),json.getString("bookingdates.checkout"));

        //De serialization
        HashMap<String,Object>actualData=response.as(HashMap.class);
        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
        assertEquals(((Map)expectedData.get("bookingdates")).get("checkin"),((Map) actualData.get("bookingdates")).get("checkin"));
        assertEquals(((Map)expectedData.get("bookingdates")).get("checkout"),((Map) actualData.get("bookingdates")).get("checkout"));
    }
}
