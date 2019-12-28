package id.devcamp.scanbarcode;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import id.devcamp.scanbarcode.Utils.LoginSession;

public class ScanQRCodeActivity extends AppCompatActivity {
    Button btnScan;
    LoginSession loginSession;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qrcode);

        btnScan = findViewById(R.id.btnScan);
        loginSession = new LoginSession(this);

        token = loginSession.getSPToken();

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scannow();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result != null){
            if (result.getContents() == null){
                Toast.makeText(this,"Result Not Found", Toast.LENGTH_SHORT).show();
            }
            else{
                AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(this);
                alertdialogbuilder.setMessage("Token : "+token +"\n\n"+"Login Code : "+result.getContents());
                alertdialogbuilder.setTitle("Result Scanned");
                alertdialogbuilder.setPositiveButton("Absen", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        scannow();
                    }
                });
                alertdialogbuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog alertDialog = alertdialogbuilder.create();
                alertDialog.show();
            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    private void scannow() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(Portrait.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scan Your Barcode");
        integrator.initiateScan();
    }
}
