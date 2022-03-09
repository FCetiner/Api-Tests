package test_data;

import org.json.JSONObject;

import java.util.HashMap;

public class HerOkuAppTestData {


    public HashMap<String,Object>setupTestData(){
        HashMap<String,Object>bookingDates=new HashMap<>();
        bookingDates.put("checkin","2022-02-01");
        bookingDates.put("checkout","2022-02-11");
        HashMap<String,Object>expectedData=new HashMap<>();
        expectedData.put("firstname","Ali");
        expectedData.put("lastname","Can");
        expectedData.put("totalprice",500);
        expectedData.put("depositpaid",true);
        expectedData.put("bookingdates",bookingDates);
        return expectedData;
    }

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
    public JSONObject setupTestAndRequestData(){
        JSONObject bookingDates=new JSONObject();
        bookingDates.put("checkin","2022-03-01");
        bookingDates.put("checkout","2022-03-11");
        JSONObject expectedRequest=new JSONObject();
        expectedRequest.put("firstname","Ferhat");
        expectedRequest.put("lastname","Avci");
        expectedRequest.put("totalprice",500);
        expectedRequest.put("depositpaid",false);
        expectedRequest.put("bookingdates",bookingDates);

        return expectedRequest;
    }



}
