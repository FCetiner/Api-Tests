package test_data;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DummyTestData {
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
    public HashMap<String,Object> setupTestData(){
        //40,21 ve 19 yaslarında çalışanlar olup olmadığını
       List<Integer> yaslar=new ArrayList<>();
       yaslar.add(40);
       yaslar.add(21);
       yaslar.add(19);
       // 10. Çalışan bilgilerinin bilgilerinin aşağıdaki gibi
        HashMap<String,Object>onuncu=new HashMap<>();
        onuncu.put("id",10);
        onuncu.put("employee_name","Sonya Frost");
        onuncu.put("employee_age",23);
        onuncu.put("profile_image","");

        //Status kodun 200 olduğunu,
        //14. Çalışan isminin "Haley Kennedy" olduğunu,
        //Çalışan sayısının 24 olduğunu,
        //Sondan 3. çalışanın maaşının 675000 olduğunu
        //40,21 ve 19 yaslarında çalışanlar olup olmadığını
        //10. Çalışan bilgilerinin bilgilerinin aşağıdaki gibi
        HashMap<String,Object>expectedData=new HashMap<>();
        expectedData.put("statusCode",200);
        expectedData.put("ondorduncuCalisan","Haley Kennedy");
        expectedData.put("calisanSayisi",24);
        expectedData.put("sondanUcuncuCalisaninMaasi",675000);
        expectedData.put("arananYaslar",yaslar);
        expectedData.put("onuncuCalisaninBilgileri",onuncu);

        return expectedData;
    }


    //Request olarak gonderdigimiz kisim
    public HashMap<String,Object> setupRequestBody(){
        HashMap<String,Object>requestBody =new HashMap<>();
        requestBody .put("name","Ferhat Can");
        requestBody .put("salary","2000");
        requestBody .put("age","40");
       return requestBody ;
    }


    //Cevap olacak gelecek kisim
    public HashMap<String,Object>setupExpectedData(){
        HashMap<String,Object>expectedData=new HashMap<>();
        expectedData.put("statusCode",200);
        expectedData.put("status","success");
        expectedData.put("message","Successfully! Record has been added.");
        return expectedData;
    }


    //Delete Class için olusturuldu
    public JSONObject setupDeleteExpectedData(){
        JSONObject expectedData=new JSONObject();
        expectedData.put("status","success");
        expectedData.put("data","2");
        expectedData.put("message","Successfully! Record has been deleted");
        return expectedData;
    }
}
