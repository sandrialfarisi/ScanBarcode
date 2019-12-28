package id.devcamp.scanbarcode.apihelper;

public class UtilsApi2 {
    public static final String BASE_URL_API = "http://laravel-siakad.herokuapp.com/api/";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitClient2.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
