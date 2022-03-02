package get_http_request;

import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest02 {

    @Test
    public void test02(){
        String url="https://reqres.in/api/users";
        Response response=given().when().get(url);

        response.prettyPrint();       //response'daki body'i getirir
    //    response.prettyPeek();  //response daki her seyi getirir  prettyprinte gore daha cok islem yapma imkanimiz var
    //    response.then().log().all();    //response daki her seyi getirir

        //Headers
        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .statusLine("HTTP/1.1 200 OK");
        //Body
        response.then().body("data[0].first_name", equalTo("George")
                        ,"data[0].last_name", equalTo("Bluth")
                        ,"data[0].email",equalTo("george.bluth@reqres.in"));//Matchers.equalto classi kaldirdik static yaptik
        response.then().body("data[1].id",equalTo(2)
                ,"data[1].email",equalTo("janet.weaver@reqres.in")
                ,"data[1].first_name",equalTo("Janet")
                ,"data[1].last_name",equalTo("Weaver")
                ,"data[1].avatar",equalTo("https://reqres.in/img/faces/2-image.jpg"));


    }
}
