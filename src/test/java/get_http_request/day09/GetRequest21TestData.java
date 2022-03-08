package get_http_request.day09;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class GetRequest21TestData extends JsonPlaceHolderBaseUrl {
    /*
https://jsonplaceholder.typicode.com/todos/2
1) Status kodunun 200,
2) respose body'de,
         "completed": değerinin false
         "title": değerinin "quis ut nam facilis et officia qui"
         "userId" sinin 1 ve
    header değerlerinden
         "via" değerinin "1.1 vegur" ve
         "Server" değerinin "cloudflare" olduğunu test edin…
*/
    @Test
    public void test21(){
        //1 URL olustur
        spec04.pathParams("1","todos","2",2);
        //2 Expected data olustur
        JsonPlaceHolderTestData expectedDataObje=new JsonPlaceHolderTestData();
        HashMap<String,Object> expectedData=(HashMap<String,Object>) expectedDataObje.setupTestData();
        System.out.println("Test data'nin icindeki expected data : "+expectedData);

        //3 Request ve Response
        Response response=given().spec(spec04).when().get("/{1}/{2}");
        response.prettyPrint();
        //{Server=cloudflare,
        // completed=false,
        // title=quis ut nam facilis et officia qui,
        // userId=1,
        // statusCode=200,
        // via=1.1 vegur}
        //4 Dogrulama
        //1. yol Matcher Class
        response.then().assertThat().statusCode((Integer) expectedData.get("statusCode"));
        response.then().assertThat()
                .body( "completed",equalTo(expectedData.get("completed"))
                        ,"title",equalTo(expectedData.get("title"))
                        ,"userId",equalTo(expectedData.get("userId")))
                .headers("via",equalTo(expectedData.get("via"))
                        ,"Server",equalTo(expectedData.get("Server")));
        //Json
        JsonPath json=response.jsonPath();
        assertEquals(expectedData.get("statusCode"),response.statusCode());
        assertEquals(expectedData.get("completed"),json.getBoolean("completed"));
        assertEquals(expectedData.get("title"),json.getString("title"));
        assertEquals(expectedData.get("userId"),json.getInt("userId"));
        assertEquals(expectedData.get("Server"),response.header("Server"));
        assertEquals(expectedData.get("via"),response.header("via"));

        //De serialization
        Map<String,Object> actualData=response.as(HashMap.class);
        assertEquals(expectedData.get("statusCode"),response.statusCode());
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("Server"),response.header("Server"));
        assertEquals(expectedData.get("via"),response.header("via"));
    }

}
