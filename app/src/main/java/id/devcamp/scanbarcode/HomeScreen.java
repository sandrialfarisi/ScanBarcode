package id.devcamp.scanbarcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import id.devcamp.scanbarcode.Utils.LoginSession;

public class HomeScreen extends AppCompatActivity {
    Button btnLogout;
    TextView textView;
    String token;
    LoginSession loginSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        btnLogout = findViewById(R.id.btnLogout);
        textView = findViewById(R.id.tvToken);
//        loginSession = new LoginSession(this);
//        token = loginSession.getSPToken();
//        textView.setText(token);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                loginSession.saveSPBoolean(LoginSession.SP_SUDAH_LOGIN, false);

//                Intent intent = new Intent(HomeScreen.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//                finish();
            }
        });
    }
}
