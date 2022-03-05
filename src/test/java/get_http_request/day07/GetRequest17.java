package get_http_request.day07;

import base_url.GMIBankBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequest17 extends GMIBankBaseUrl {
     /*
   http://www.gmibank.com/api/tp-customers/114351 adresindeki müşteri bilgilerini doğrulayın

{
   "firstName": "Della",
   "lastName": "Heaney",
   "email": "ricardo.larkin@yahoo.com",
   "mobilePhoneNumber": "123-456-7893",
}
    */

    @Test
    public void test17(){
        //1 Url olustur
        spec03.pathParams("bir","tp-customers","iki","114351");
        //Expected data olustur
        Map<String,Object> expectedData=new HashMap<>();
        expectedData.put("firstName","Della");
        expectedData.put("lastName","Heaney");
        expectedData.put("email","ricardo.larkin@yahoo.com");
        expectedData.put("mobilePhoneNumber","123-456-7893");
        //3 Resquest ve response
        Response response=given()
                .spec(spec03)
                .header("Authorization","Bearer "+generateToken())
                .when().get("/{bir}/{iki}");
        response.prettyPrint();

        Map<String,Object> actualData=response.as(HashMap.class);   //Deserialization
        //Dogrulama
        assertEquals(expectedData.get("firstName"),actualData.get("firstName"));
        assertEquals(expectedData.get("lastName"),actualData.get("lastName"));
        assertEquals(expectedData.get("email"),actualData.get("email"));
        assertEquals(expectedData.get("mobilePhoneNumber"),actualData.get("mobilePhoneNumber"));
    }


}
