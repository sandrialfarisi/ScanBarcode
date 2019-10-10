package id.devcamp.scanbarcode.apihelper;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class BaseApiService {
    @FormUrlEncoded
    @POST("login.php")
    public Call<ResponseBody> loginRequest(@Field("nim") String nim,
                                           @Field("password") String password) {
        return null;
    }
}
