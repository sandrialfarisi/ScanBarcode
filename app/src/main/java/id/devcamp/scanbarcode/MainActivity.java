package id.devcamp.scanbarcode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import id.devcamp.scanbarcode.Fragment.AkunFragment;
import id.devcamp.scanbarcode.Fragment.HomeFragment;
import id.devcamp.scanbarcode.Fragment.JadwalFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        bottomNavigationView.setSelectedItemId(R.id.nav1);

    }

    HomeFragment homefragment = new HomeFragment();
    JadwalFragment jadwalfragment = new JadwalFragment();
//    CameraFragment camerafragment = new CameraFragment();
    AkunFragment akunfragment = new AkunFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.nav1:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.fl_container, homefragment).commit();
                return true;

            case R.id.nav2:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.fl_container, jadwalfragment).commit();
                return true;

//            case R.id.nav3:
//                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.fl_container, camerafragment).commit();
//                return true;

            case R.id.nav4:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.fl_container, akunfragment).commit();
        }

        return true;
    }
}
