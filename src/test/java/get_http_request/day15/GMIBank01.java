package get_http_request.day15;

import base_url.GMIBankBaseUrl;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.ReadText;
import utilities.WriteToText;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GMIBank01 extends GMIBankBaseUrl {
      /*
    http://www.gmibank.com/api/tp-customers end point'ine
    request gönderin
     1) Tüm Customer bilgilerini ekrana yazdırırn.
     2) Tüm Customer SSN lerini ekrana yazdırın.
     3) Tüm Customer SSN lerini text dosyası olarak kaydedin
     4) Olusturduğunuz text dosyasından  SSNleri okuyarak
        "531-95-8437", "049-43-2360", "123-34-3434" SSNlerinin olduğunu doğrulayın
     */

    @Test
    public void test01() throws JsonProcessingException {
        Customer [] customers;


        spec03.pathParam("1","tp-customers");
        Response response=given().headers("Authorization","Bearer "+generateToken())
                .when().spec(spec03).get("/{1}").then().contentType(ContentType.JSON)
                .extract().response();//response ise yaramadiginda extrect komutu eklenebiliyor
       // response.prettyPrint();

        //OnjectMapper  De serialization yapmak icin
        //Json veya Java data okumak icin kullanabiliriz
        ObjectMapper obj=new ObjectMapper();
        customers=obj.readValue(response.asString(),Customer[].class);
        //1) Tüm Customer bilgilerini ekrana yazdırırn.
        for (int i = 0; i < customers.length; i++) {
            System.out.println((i+1)+". customer : "+customers[i]);
        }
        // 2) Tüm Customer SSN lerini ekrana yazdırın.
        for (int i = 0; i < customers.length; i++) {
            System.out.println((i+1)+". customer SSN : "+customers[i].getSsn());
        }
        //3) Tüm Customer SSN lerini text dosyası olarak kaydedin
        String fileName="src/test/java/get_http_request/day15/GMIBankTextData/SSNList.txt";
        WriteToText.saveSSNData(fileName,customers);

        // 4) Olusturduğunuz text dosyasından  SSNleri okuyarak
        // "531-95-8437", "049-43-2360", "123-34-3434" SSNlerinin olduğunu doğrulayın

        List<String> expectedSSNIds=new ArrayList<>();
        expectedSSNIds.add("531-95-8437");
        expectedSSNIds.add("049-43-2360");
        expectedSSNIds.add("123-34-3434");

        List<String> actualSSNIds= ReadText.readCustomerSSNList(fileName);
        Assert.assertTrue("SSN'ler eslesmiyor",actualSSNIds.containsAll(expectedSSNIds));
    }
}
