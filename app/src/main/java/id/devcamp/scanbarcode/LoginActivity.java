package id.devcamp.scanbarcode;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import id.devcamp.scanbarcode.apihelper.BaseApiService;
import id.devcamp.scanbarcode.apihelper.UtilsApi;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText etNim;
    EditText etPassword;
    Button btnLogin;
    ProgressDialog loading;
    Integer setVisible = 1;
    ImageView ivVisible;

    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ivVisible = findViewById(R.id.ivVisible);

        mContext = this;
//        mApiService = UtilsApi.getAPIService();
        initComponents();
    }

    private void initComponents() {
        etNim = findViewById(R.id.etNim);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestLogin();
            }
        });

        ivVisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (setVisible == 1){
                    setVisible = 0;
                    etPassword.setTransformationMethod(null);
                    if (etPassword.getText().length()>0)
                        etPassword.setSelection(etPassword.getText().length());
                    ivVisible.setBackgroundResource(R.drawable.ic_remove_red_eye_black_24dp);
                } else {
                    setVisible = 1;
                    etPassword.setTransformationMethod(new PasswordTransformationMethod());
                    if (etPassword.getText().length()>0)
                        etPassword.setSelection(etPassword.getText().length());
                    ivVisible.setBackgroundResource(R.drawable.ic_remove_eye_black_24dp);
                }
            }
        });
    }

    private void requestLogin() {
        final String nim, password;
        nim = etNim.getText().toString();
        password = etPassword.getText().toString();

        if (TextUtils.isEmpty(nim)){
            Toast.makeText(this, "Please input NIM...", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please input Password...", Toast.LENGTH_SHORT).show();
            return;
        }

        mApiService.loginRequest(nim, password)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                        if (response.isSuccessful()){
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("error").equals("false")){
                                    Toast.makeText(mContext, "BERHASIL LOGIN", Toast.LENGTH_SHORT).show();
                                    String nama = jsonRESULTS.getJSONObject("user").getString("nama");
                                    Intent intent = new Intent(mContext, MainActivity.class);
                                    intent.putExtra("result_nama", nama);
                                    startActivity(intent);
                                } else {
                                    String error_message = jsonRESULTS.getString("error_msg");
                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        loading.dismiss();
                    }
                });
    }


}
