package heli.copter.diplom;


import heli.copter.diplom.requestBody.RequestOneObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HTTP;
import retrofit2.http.Path;

public interface Methods {
    @HTTP(method = "GET", path = "api/obj/{id}", hasBody = true)
    Call<MObj> getAllData(@Path("id") int groupId, @Body RequestOneObject token);
}
