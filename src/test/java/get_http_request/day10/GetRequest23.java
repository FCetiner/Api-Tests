package get_http_request.day10;

import base_url.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.DummyTestData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetRequest23 extends DummyBaseUrl {
    /*
http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
Status kodun 200 olduğunu,
14. Çalışan isminin "Haley Kennedy" olduğunu,
Çalışan sayısının 24 olduğunu,
Sondan 3. çalışanın maaşının 675000 olduğunu
40,21 ve 19 yaslarında çalışanlar olup olmadığını
10. Çalışan bilgilerinin bilgilerinin aşağıdaki gibi

{
        "id": 10,
        "employee_name": "Sonya Frost",

        "employee_age": 23,
        "profile_image": ""
 }

  olduğunu test edin.
*/
    @Test
    public void test23(){
        //1 Url olustur
        spec02.pathParams("1","api","2","v1","3","employees");
        //2 Expected data olustur
        DummyTestData testDataObje=new DummyTestData();
        HashMap<String,Object> expectedData=testDataObje.setupTestData();
        System.out.println("expectedData = " + expectedData);
        //expectedtest data : {arananYaslar=[40, 21, 19]
        // , onuncuCalisaninBilgileri={profile_image=, employee_name=Sonya Frost, id=10, employee_age=23}
        // , sondanUcuncuCalisaninMaasi=675000
        // , calisanSayisi=24
        // , statusCode=200
        // , ondorduncuCalisan=Haley Kennedy}
        //3 Request response
        Response response=given().spec(spec02).contentType(ContentType.JSON).when().get("/{1}/{2}/{3}");
       // response.prettyPrint();
        //4 Dogrulama
        //De serialization
        HashMap<String,Object>actualData=response.as(HashMap.class);
        assertEquals(expectedData.get("statusCode"),response.statusCode());
        assertEquals(expectedData.get("ondorduncuCalisan"),((Map)((List) actualData.get("data")).get(13)).get("employee_name"));
        assertEquals(expectedData.get("calisanSayisi"),((List) actualData.get("data")).size());
        assertEquals(expectedData.get("sondanUcuncuCalisaninMaasi"),((Map)((List)actualData.get("data")).get(((List)actualData.get("data")).size()-3)).get("employee_salary"));

        int dataSize=((List<?>) actualData.get("data")).size();
        List<Integer> actualYasListesi=new ArrayList<>();
        for(int i=0 ; i < dataSize ; i++){
            actualYasListesi.add((Integer)((Map)((((List<?>) actualData.get("data")).get(i)))).get("employee_age"));
        }
        assertTrue(actualYasListesi.containsAll(((List)expectedData.get("arananYaslar"))));
    }

}
