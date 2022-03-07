package get_http_request.day08;

import base_url.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class GetRequest19 extends DummyBaseUrl {
/*
http://dummy.restapiexample.com/api/v1/employees
1) Status kodunun 200,
2) 10’dan büyük tüm id'leri ekrana yazdırın ve 10’dan büyük 14 id olduğunu,
3) 30’dan küçük tüm yaşları ekrana yazdırın ve bu yaşların içerisinde en büyük yaşın 23 olduğunu
4) Maası 350000 den büyük olan tüm employee name'leri ekrana yazdırın
     ve bunların içerisinde "Charde Marshall" olduğunu test edin
*/

    @Test
    public void test19(){
        //1) Url olustur
        spec02.pathParams("1","api","2","v1","3","employees");

        Response response=given().spec(spec02).when().get("/{1}/{2}/{3}");
       // response.prettyPrint();
        //1) Status code 200;
        response.then().statusCode(200);
        assertEquals(200,response.statusCode());

        //2) 10’dan büyük tüm id'leri ekrana yazdırın ve 10’dan büyük 14 id olduğunu,
        JsonPath json=response.jsonPath();

       // List<Integer> idList=json.getList("data.findAll{it.id>10}.id");
        List<Integer> idList=json.getList("data.id.findAll{it>10}");
        System.out.println("Id list : "+idList);
        //Groovy  Java platformu uzerinde calisan bir bilgisayar dilidir
        //Groovy ile loop kullanmadan response'dan gelen degerleri bir sarta gore alabiliriz
        assertEquals(14,idList.size());
        //3) 30’dan küçük tüm yaşları ekrana yazdırın ve bu yaşların içerisinde en büyük yaşın 23 olduğunu
        List<Integer>yasList=json.getList("data.employee_age.findAll{it<30}");
        System.out.println(yasList);
        Collections.sort(yasList);
        assertEquals((Integer)23,yasList.get(yasList.size()-1));
        //assertTrue(yasList.get(yasList.size()-1)==23);
        //4) Maası 350000 den büyük olan tüm employee name'leri ekrana yazdırın ve bunların içerisinde "Charde Marshall" olduğunu test edin
        List<Integer> maasList=json.getList("data.employee_salary.findAll{it>350000}");
        assertFalse(maasList.contains("Charde Marshall"));
    }

}
