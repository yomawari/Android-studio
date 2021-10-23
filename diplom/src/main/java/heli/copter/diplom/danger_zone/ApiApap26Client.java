package heli.copter.diplom.danger_zone;

import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ApiApap26Client {
    public static ApiApap26Client client;
    private CloseableHttpClient httpClient;
    private String token = "PGFMMURNTVKZLBACJVI58X5ZG9XUEL7AJR6E9FRQYFHH3FT8TM";
    private String URL = "http://attack-helicopter.ru:228/api/";
    private String URL_TYPE = "type/";
    private String URL_CATEGORY = "category/";
    private String URL_OBG = "obj/";
    private String URL_INVENTORY = "inventory/";

    private ApiApap26Client(){
        httpClient = HttpClients.createDefault();
    }
    public static ApiApap26Client getClient(){
        if(ApiApap26Client.client == null){
            ApiApap26Client.client = new ApiApap26Client();
        }
        return ApiApap26Client.client;
    }
    private<T extends HttpEntityEnclosingRequestBase> String httpRequest(String url, String request, T httprequest) throws URISyntaxException, IOException {
        httprequest.setURI(new URI(url));
        httprequest.setHeader("Content-Type","application/json");
        HttpEntity entity = new ByteArrayEntity(request.getBytes("UTF-8"));
        httprequest.setEntity(entity);
        HttpResponse httpresponse = httpClient.execute(httprequest);
        Log.d("HttpApacheTest", httpresponse.getStatusLine().toString());
        String result = EntityUtils.toString(httpresponse.getEntity());
        return result;
    }
    public String getType(int id){
        try {
            String suka = "{\n" +
                    "    \"token\": \""+ token +"\"\n" +
                    "}";
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGetWithEntity httpget = new HttpGetWithEntity();
            httpget.setURI(new URI(URL+URL_TYPE+id));
            httpget.setHeader("Content-Type","application/json");
            HttpEntity entity = new ByteArrayEntity(suka.getBytes("UTF-8"));
            httpget.setEntity(entity);
            HttpResponse httpresponse = httpclient.execute(httpget);
            Log.d("HttpApacheTest", httpresponse.getStatusLine().toString());
            String result = EntityUtils.toString(httpresponse.getEntity());
            return result;
        }catch (Exception e){
            Log.e("danger_zone.PizdecApiClient","pizdec");
        }
        return "Сань, хана, трактор приехал. ТРАААКТОР!!!";
    }
    public String getTypes(int start, int limit){
        MassiveRequest requestObject = new MassiveRequest();
        requestObject.start =start;
        requestObject.limit = limit;
        requestObject.token = token;
        String requestString = new Gson().toJson(requestObject);
        try {
            return httpRequest(URL+URL_TYPE, requestString, new HttpGetWithEntity());
        }catch (Exception e){
            Log.e("danger_zone.PizdecApiClient.getTypes","Http client Error");
        }
        return "Сань, хана, трактор приехал. ТРАААКТОР!!!";
    }
    public String getCategories(int start, int limit){
        MassiveRequest requestObject = new MassiveRequest();
        requestObject.start =start;
        requestObject.limit = limit;
        requestObject.token = token;
        String requestString = new Gson().toJson(requestObject);
        try {
            return httpRequest(URL+URL_CATEGORY, requestString, new HttpGetWithEntity());
        }catch (Exception e){
            Log.e("danger_zone.PizdecApiClient.getTypes","Http client Error");
        }
        return "Сань, хана, трактор приехал. ТРАААКТОР!!!";
    }
    public String getObj(int start, int limit){
        MassiveRequest requestObject = new MassiveRequest();
        requestObject.start =start;
        requestObject.limit = limit;
        requestObject.token = token;
        String requestString = new Gson().toJson(requestObject);
        try {
            return httpRequest(URL+URL_OBG, requestString, new HttpGetWithEntity());
        }catch (Exception e){
            Log.e("danger_zone.PizdecApiClient.getTypes","Http client Error");
        }
        return "Сань, хана, трактор приехал. ТРАААКТОР!!!";
    }
    public String getInventories(int start, int limit){
        InventoriesRequest requestObject = new InventoriesRequest();
        requestObject.dateTimeStart ="1970-04-17T00:00:00.1328768+05:00";
        requestObject.dateTimeStop = "2035-12-18T23:19:09.1328768+05:00";
        requestObject.token = token;
        String requestString = new Gson().toJson(requestObject);
        try {
            return httpRequest(URL+URL_INVENTORY, requestString, new HttpGetWithEntity());
        }catch (Exception e){
            Log.e("danger_zone.PizdecApiClient.getTypes","Http client Error");
        }
        return "Сань, хана, трактор приехал. ТРАААКТОР!!!";
    }
    public String newCategory(String name){
        CategoryRequest request = new CategoryRequest();
        request.token = token;
        request.data.name = name;
        request.data.id = 0;
        String requestString = new Gson().toJson(request);
        try {
            return httpRequest(URL+URL_CATEGORY, requestString, new HttpPost());
        }catch (Exception e){
            Log.e("danger_zone.PizdecApiClient.getTypes","Http client Error");
        }
        return "Сань, хана, трактор приехал. ТРАААКТОР!!!";
    }
    public String newObject(RequestObject request){
        request.token = token;
        request.data.id = 0;
        String requestString = new Gson().toJson(request);
        try {
            return httpRequest(URL+URL_OBG, requestString, new HttpPost());
        }catch (Exception e){
            Log.e("danger_zone.PizdecApiClient.getTypes","Http client Error");
        }
        return "Сань, хана, трактор приехал. ТРАААКТОР!!!";
    }
    public String newInventory(int readerid){
        InvRequest request = new InvRequest();
        request.token = token;
        request.UserId = "id";
        request.ReaderID = readerid;
        String requestString = new Gson().toJson(request);
        try {
            return httpRequest(URL+URL_INVENTORY, requestString, new HttpPost());
        }catch (Exception e){
            Log.e("danger_zone.PizdecApiClient.getTypes","Http client Error");
        }
        return "Сань, хана, трактор приехал. ТРАААКТОР!!!";
    }
    public String newType(String name, int categoryID){
        RequestType request = new RequestType();
        request.token = token;
        request.data.categoryID = categoryID;
        request.data.name = name;
        request.data.onesNumber= "ALARM!!!!";
        request.data.desk_text = "Это необходимо доработать!!";
        String requestString = new Gson().toJson(request);
        try {
            return httpRequest(URL+URL_TYPE, requestString, new HttpPost());
        }catch (Exception e){
            Log.e("danger_zone.PizdecApiClient.getTypes","Http client Error");
        }
        return "Сань, хана, трактор приехал. ТРАААКТОР!!!";
    }
    public String deleteCategory(int id){
        Request request = new Request();
        request.token = token;
        String requestString = new Gson().toJson(request);
        try {
            return httpRequest(URL+URL_CATEGORY+id, requestString, new HttpDeleteWithEntity());
        }catch (Exception e){
            Log.e("danger_zone.PizdecApiClient.getTypes","Http client Error");
        }
        return "Сань, хана, трактор приехал. ТРАААКТОР!!";
    }
    public String deleteType(int id){
        Request request= new Request();
        request.token = token;
        try {
            return httpRequest(URL+URL_CATEGORY+id, new Gson().toJson(request), new HttpDeleteWithEntity());
        }catch (Exception e){
            Log.e("danger_zone.PizdecApiClient.getTypes","Http client Error");
        }
        return "Ква";
    }

    protected class MassiveRequest{
        public int start;
        public int limit;
        public String token;
    }
    protected class InventoriesRequest{
        public String dateTimeStart;
        public String dateTimeStop;
        public String token;
    }
    public class Request{
        public String token;
    }
    public class CategoryRequest{
        public CategoryRequest(){
            data = new data();
        }
        public String token;
        public data data;
        public class data{
            public int id;
            public String name;
        }
    }
    public class InvRequest{
        public String token;
        public int ReaderID;
        public String UserId;
    }
}
