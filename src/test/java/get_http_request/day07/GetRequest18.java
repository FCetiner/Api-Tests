package get_http_request.day07;

import base_url.GMIBankBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class GetRequest18 extends GMIBankBaseUrl {
    /*
http://www.gmibank.com/api/tp-customers/43703

"firstName": "Alda",
"lastName": "Monahan",
"middleInitial": "Nichelle Hermann Kohler",
"email": "com.github.javafaker.Name@7c011174@gmail.com",
"mobilePhoneNumber": "909-162-8114",
"city": "St Louis",
"ssn": "108-53-6655"

1) MATCHERS CLASS
2) JSON PATH
3) De-Serialization
 */
    @Test
    public void test18(){
        //1 Url olustur
        spec03.pathParams("bir","tp-customers","iki","43703");
        //2 expected data olsutur
        Map<String,Object> expectedData=new HashMap<>();
        expectedData.put("firstName","Alda");
        expectedData.put("lastName","Monahan");
        expectedData.put("middleInitial","Nichelle Hermann Kohler");
        expectedData.put("email","com.github.javafaker.Name@7c011174@gmail.com");
        expectedData.put("mobilePhoneNumber","909-162-8114");
        expectedData.put("city","St Louis");
        expectedData.put("ssn","108-53-6655");

        //3 Request ve Response.
        Response response=given()
                .spec(spec03)
                .header("Authorization","Bearer "+generateToken())
                .when()
                .get("/{bir}/{iki}");
        Map<String,Object> actualData=response.as(HashMap.class);
        //4 Dogrulama.
        //4.1 Deserialization
        assertEquals(expectedData.get("firstName"),actualData.get("firstName"));
        assertEquals(expectedData.get("lastName"),actualData.get("lastName"));
        assertEquals(expectedData.get("middleInitial"),actualData.get("middleInitial"));
        assertEquals(expectedData.get("email"),actualData.get("email"));
        assertEquals(expectedData.get("mobilePhoneNumber"),actualData.get("mobilePhoneNumber"));
        assertEquals(expectedData.get("city"),actualData.get("city"));
        assertEquals(expectedData.get("ssn"),actualData.get("ssn"));

        //4.2 Matcher
        response.then().assertThat().body("firstName",equalTo("Alda"));
        response.then().assertThat().body("lastName",equalTo("Monahan"));
        response.then().assertThat().body("middleInitial",equalTo("Nichelle Hermann Kohler"));
        response.then().assertThat().body("email",equalTo("com.github.javafaker.Name@7c011174@gmail.com"));
        response.then().assertThat().body("mobilePhoneNumber",equalTo("909-162-8114"));
        response.then().assertThat().body("city",equalTo("St Louis"));
        response.then().assertThat().body("ssn",equalTo("108-53-6655"));

        //4.3 Json
        JsonPath json=response.jsonPath();
        assertEquals("Alda",json.getString("firstName"));
        assertEquals("Monahan",json.getString("lastName"));
        assertEquals("Nichelle Hermann Kohler",json.getString("middleInitial"));
        assertEquals("com.github.javafaker.Name@7c011174@gmail.com",json.getString("email"));
        assertEquals("909-162-8114",json.getString("mobilePhoneNumber"));
        assertEquals("St Louis",json.getString("city"));
        assertEquals("108-53-6655",json.getString("ssn"));
    }



}
