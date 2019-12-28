package id.devcamp.scanbarcode.apihelper;

import java.io.IOException;

import id.devcamp.scanbarcode.Utils.LoginSession;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient2 {
    private static Retrofit retrofit = null;
    LoginSession loginSession;
    static String token;

    public LoginSession getLoginSession() {
        token = loginSession.getSPToken();
        return loginSession;
    }

    public static Retrofit getClient(String baseUrl) {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
//                        .addHeader("Authorization","Bearer " + token)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();


        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}
