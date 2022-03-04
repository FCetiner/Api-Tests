package get_http_request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.Authentication;

import static io.restassured.RestAssured.given;

public class GetRequest12 extends Authentication {
    //Authentication Class'in içerisindeki generateToken methodu kullanilacak
    String endPoint="http://www.gmibank.com/api/tp-customers";
    @Test
    public void test12(){
        Response response=given()
                .header("Authorization","Bearer "+generateToken())
                .when()
                .get(endPoint)
                .then()
                .extract()
                .response();
        response.prettyPeek();
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON);
    }
}
