package get_http_request.day06;

import base_url.GMIBankBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class GetRequest15 extends GMIBankBaseUrl {
    //https://www.gmibank.com/api/tp-customers/85694

    /*
https://www.gmibank.com/api/tp-customers/85694
        "login": "dino.kohler",
    "firstName": "Winona",
    "lastName": "Abernathy",
    "email": "winonaabernathy@gmail.com"

 */

    @Test
    public void test() {
        spec03.pathParams("bir", "tp-customers", "iki", "85694");
        Response response = given().spec(spec03).header("Authorization", "Bearer " + generateToken())
                .when().get("/{bir}/{iki}");
        response.prettyPrint();
        //Matcher
        response.then().body("user.login",equalTo("dino.kohler")
                ,"user.firstName",equalTo("Winona")
                ,"user.lastName",equalTo("Abernathy")
                ,"user.email",equalTo("winonaabernathy@gmail.com"));
        //Json
        JsonPath json=response.jsonPath();
        assertEquals("dino.kohler",json.getString("user.login"));
        assertEquals("Winona",json.getString("user.firstName"));
        assertEquals("Abernathy",json.getString("user.lastName"));
        assertEquals("winonaabernathy@gmail.com",json.getString("user.email"));
    }
}
