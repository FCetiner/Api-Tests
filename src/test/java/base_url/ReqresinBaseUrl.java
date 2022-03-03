package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class ReqresinBaseUrl {
   protected RequestSpecification spec01;

    @Before
    public void setup(){
        spec01=new RequestSpecBuilder().setBaseUri("https://reqres.in").build();

    }
}
