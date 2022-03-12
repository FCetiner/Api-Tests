package test_data;

import org.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {


    public Map<String,Object> setupTestData(){
         Map<String,Object>expectedData=new HashMap<>();
               expectedData.put("statusCode",200);
               expectedData.put("completed",false);
               expectedData.put("title","quis ut nam facilis et officia qui");
               expectedData.put("userId",1);
               expectedData.put("via","1.1 vegur");
               expectedData.put("Server","cloudflare");

               return expectedData;
    }

    //gonderilen veri ile data ayni olacagi icin JsonObject kullanilmali
    public JSONObject setupPostData(){
        JSONObject expectedRequest=new JSONObject();
        expectedRequest.put("userId",55);
        expectedRequest.put("title","Tidy your room");
        expectedRequest.put("completed",false);
        expectedRequest.put("statusCode",201);
        expectedRequest.put("id",201);
        return expectedRequest;
    }
    //Putrequest01 için
    public JSONObject setupPutData(){
        JSONObject expectedRequest=new JSONObject();
        expectedRequest.put("userId",21);
        expectedRequest.put("title","Wash the dishes");
        expectedRequest.put("completed",false);
        return expectedRequest;
    }

    //Patchrequest01 için
    public JSONObject setupPatchRequestData(){
        JSONObject requestData=new JSONObject();
        requestData.put("title","Batch44");
        return requestData;
    }

    //Patchrequest01 için
    public JSONObject setupPatchExpectedData(){
        JSONObject expectedData=new JSONObject();
        expectedData.put("userId",10);
        expectedData.put("title","Batch44");
        expectedData.put("completed",true);
        expectedData.put("id",198);
        return expectedData;
    }
}
