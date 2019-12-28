package id.devcamp.scanbarcode.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Objects;

import id.devcamp.scanbarcode.LoginActivity;
import id.devcamp.scanbarcode.R;
import id.devcamp.scanbarcode.Utils.LoginSession;


public class AkunFragment extends Fragment {
    public LoginSession loginSession;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_akun, container, false);

        Button btnLogout = rootView.findViewById(R.id.logout);
        loginSession = new LoginSession(getContext());
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                saveStatus();
                Intent intent = new Intent(getActivity(), LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                Objects.requireNonNull(getActivity()).finish();
            }
        });

        return rootView;
    }

    public void saveStatus(){
        loginSession.saveSPBoolean(loginSession.SP_SUDAH_LOGIN, false);
    }
}
