package get_http_request.day05;

import base_url.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetRequest10 extends DummyBaseUrl {
    /*
    http://dummy.restapiexample.com/api/v1/employees
    url ine bir istek gönderildiğinde,
    status kodun 200,
    gelen body de,
    5. çalışanın isminin "Airi Satou" olduğunu ,
    6. çalışanın maaşının "372000" olduğunu ,
    Toplam 24 tane çalışan olduğunu,
    "Rhona Davidson" ın employee lerden biri olduğunu
    "21", "23", "61" yaşlarında employeeler olduğunu test edin
    JSONPATH KULLARAK
*/
    @Test
    public void test10() {
        spec02.pathParams("first", "api"
                , "second", "v1"
                , "third", "employees");
        Response response=given().spec(spec02).when().get("/{first}/{second}/{third}");
        JsonPath json=response.jsonPath();
        json.prettyPrint();
        assertEquals(200,response.statusCode());
        assertEquals("Airi Satou",json.getString("data.employee_name[4]"));
        assertEquals(372000,json.getInt("data.employee_salary[5]"));
        assertEquals(24,json.getList("data.id").size());
        assertTrue(json.getList("data.employee_name").contains("Rhona Davidson"));
        //first way
        List<Integer> ages = Arrays.asList(21,23,61);
       assertTrue(json.getList("data.employee_age").containsAll(ages));

    }

}
