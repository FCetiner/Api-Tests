package get_http_request;

import base_url.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class GetRequest09 extends DummyBaseUrl {
    /*
http://dummy.restapiexample.com/api/v1/employee/12 URL'E GiT.
1) Matcher CLASS ile
2) JsonPath ile dogrulayin.
*/

    @Test
    public void test09(){
        spec02.pathParams("first","api"
                ,"second","v1"
                ,"third","employee"
                ,"fourth","12");
        Response response=given().spec(spec02).when().get("/{first}/{second}/{third}/{fourth}");
        response.prettyPrint();
        //Matchers class ile
        response.then().statusCode(200).contentType(ContentType.JSON);
        response.then().body("data.employee_name", equalTo("Quinn Flynn")
                ,"data.employee_salary",equalTo(342000)
                ,"data.employee_age",equalTo(22));
        //JsonPath
        JsonPath json=response.jsonPath();
        assertEquals("Quinn Flynn",json.getString("data.employee_name"));
        assertEquals(342000,json.getInt("data.employee_salary"));
        assertEquals(22,json.getInt("data.employee_age"));
    }
}
