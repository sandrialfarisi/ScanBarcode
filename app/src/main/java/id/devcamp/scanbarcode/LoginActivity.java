package id.devcamp.scanbarcode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import id.devcamp.scanbarcode.Utils.LoginSession;
import id.devcamp.scanbarcode.apihelper.BaseApiService;
import id.devcamp.scanbarcode.apihelper.RetrofitClient;
import id.devcamp.scanbarcode.apihelper.User;
import id.devcamp.scanbarcode.apihelper.UtilsApi;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements Callback<User> {
    private static final String TAG = "LoginActivity";
    EditText etNim;
    EditText etPassword;
    Button btnLogin;
    Integer setVisible = 1;
    TextView ivVisible;
    String token;
    Context mContext;
    BaseApiService mApiService;

    LoginSession loginSession;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ivVisible = findViewById(R.id.tvShowPassword);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        loginSession = new LoginSession(mContext);

        if (loginSession.getSPSudahLogin()){
            startActivity(new Intent(LoginActivity.this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

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
                if (setVisible == 1) {
                    setVisible = 0;
                    etPassword.setTransformationMethod(null);
                    if (etPassword.getText().length() > 0)
                        etPassword.setSelection(etPassword.getText().length());
//                    ivVisible.setBackgroundResource(R.drawable.ic_remove_red_eye_black_24dp);
                } else {
                    setVisible = 1;
                    etPassword.setTransformationMethod(new PasswordTransformationMethod());
                    if (etPassword.getText().length() > 0)
                        etPassword.setSelection(etPassword.getText().length());
//                    ivVisible.setBackgroundResource(R.drawable.ic_remove_eye_black_24dp);
                }
            }
        });
    }

    private void requestLogin() {
        final String username, password;
        username = etNim.getText().toString();
        int user = Integer.parseInt(username);
        password = etPassword.getText().toString();

        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Please input NIM...", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please input Password...", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("username", user);
            paramObject.put("password", password);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), paramObject.toString());

            Call<User> userCall = mApiService.getUser("", requestBody);
            userCall.enqueue(this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        if (response.code()==200){
            assert response.body() != null;
            token = response.body().getToken();
            Log.d("token", token);
//            session.saveToken(token);
            loginSession.saveSPString(LoginSession.SP_TOKEN, token);
            loginSession.saveSPBoolean(LoginSession.SP_SUDAH_LOGIN, true);
            Intent intent = new Intent(mContext, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();

        }
//            Log.d(call.toString(), "onResponse: ");
    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }
}
