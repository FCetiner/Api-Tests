package get_http_request.day10;

import base_url.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostRequest01 extends HerOkuAppBaseUrl {
     /*
   https://restful-booker.herokuapp.com/booking
   { "firstname": "Ali",
              "lastname": "Can",
              "totalprice": 500,
              "depositpaid": true,
              "bookingdates": {
                  "checkin": "2022-03-01",
                  "checkout": "2022-03-11"
               }
}
gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
}
   "booking": {
       "firstname": "Ali",
       "lastname": "Can",
       "totalprice": 500,
       "depositpaid": true,
       "bookingdates": {
                           "checkin": "2022-03-01",
                            "checkout": "2022-03-11"
       }
   }
}
olduğunu test edin
    */

    @Test
    public void test01(){
        //1 Url olustur
        spec05.pathParam("parametre1","booking");
        //2 expected data
        HerOkuAppTestData testData=new HerOkuAppTestData();
        JSONObject expectedRequestData=testData.setupTestAndRequestData();
        System.out.println("expectedRequestData = " + expectedRequestData);
        //3 request ve response
        //post yaparken bazi siteler sifre istiyor,
        Response response=given()
                .contentType(ContentType.JSON)
                .auth()
                .basic("admin","password123")
                .spec(spec05)
                .body(expectedRequestData.toString())       //JsonObject'te toString() kullanmaliyiz. yoksa tanimaz
                .when()
                .post("/{parametre1}");
        response.prettyPrint();

        //4 Dogrulama
        //JsonPath
        JsonPath json=response.jsonPath();
        response.then().assertThat().statusCode(200);
        //expectedRequestData.getString("firstname") -> test data icerisindeki firstname
        //json.getString("booking.firstname") -> body deki firstname
        assertEquals(expectedRequestData.getString("firstname"),json.getString("booking.firstname"));
        assertEquals(expectedRequestData.getString("lastname"),json.getString("booking.lastname"));
        assertEquals(expectedRequestData.getInt("totalprice"),json.getInt("booking.totalprice"));
        assertEquals(expectedRequestData.getBoolean("depositpaid"),json.getBoolean("booking.depositpaid"));
        assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkin")
                ,json.getString("booking.bookingdates.checkin"));
        assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkout")
                ,json.getString("booking.bookingdates.checkout"));



    }
}
