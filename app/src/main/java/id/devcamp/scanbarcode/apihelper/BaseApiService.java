package id.devcamp.scanbarcode.apihelper;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface BaseApiService {

    @Headers({"Content-Type:application/json; charset=utf-8"})
    @POST("login")
    Call<User> getUser(@Header("Authorization") String token,@Body RequestBody body);
    @GET("mahasiswa")
    Call<UserData> getUserData(@Header("Authorization") String token);
}
