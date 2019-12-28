package id.devcamp.scanbarcode.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import id.devcamp.scanbarcode.Adapter.RetrofitAdapter;
import id.devcamp.scanbarcode.R;
import id.devcamp.scanbarcode.ScanQRCodeActivity;
import id.devcamp.scanbarcode.Utils.LoginSession;
import id.devcamp.scanbarcode.apihelper.BaseApiService;
import id.devcamp.scanbarcode.apihelper.User;
import id.devcamp.scanbarcode.apihelper.UserData;
import id.devcamp.scanbarcode.apihelper.UtilsApi;
import id.devcamp.scanbarcode.apihelper.UtilsApi2;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment implements Callback<UserData> {

    Button btnAbsen;
    TextView textView;
    String token,nim;
    LoginSession loginSession;
    Context context;
    BaseApiService mApiService;
    private RetrofitAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =inflater.inflate(R.layout.fragment_home, container, false);
        btnAbsen = rootView.findViewById(R.id.btnAbsen);
        textView = rootView.findViewById(R.id.namaProfile);
        recyclerView = rootView.findViewById(R.id.rvMatkul);
        loginSession = new LoginSession(getContext());
        token = loginSession.getSPToken();
//        textView.setText(nim);
        mApiService = UtilsApi.getAPIService();

//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        btnAbsen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ScanQRCodeActivity.class);
                startActivity(intent);
            }
        });

        Call<UserData> userCall = mApiService.getUserData("Bearer "+token);
        userCall.enqueue(this);




        return rootView;
    }

    @Override
    public void onResponse(Call<UserData> call, Response<UserData> response) {
        if (response.code()==200){
            assert response.body() != null;
            String test =  response.body().toString();
            Log.d("test",test);
            //            try {
//                JSONObject jsonObject = new JSONObject();
//                JSONArray jsonArray = jsonObject.getJSONArray("data");
//                for(int i=0; i<jsonArray.length(); i++){
//                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
////                    textView = jsonObject1
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            try {
//                JSONObject obj = new JSONObject();
//                JSONObject obj2 = new JSONObject();
//                if (obj.equals("data")){
//                    if (obj.optString("success").equals("true")) {
//                        ArrayList<UserData> matkulRecyclerList = new ArrayList<>();
//                        JSONArray dataArray = obj2.getJSONArray("data");
//                        for (int i = 0; i < dataArray.length(); i++) {
//                            UserData matkulRecylcer = new UserData();
//                            JSONObject dataobj = dataArray.getJSONObject(i);
//
//                            matkulRecylcer.setNama_matkul(dataobj.getString("nama_matkul"));
//                            matkulRecylcer.setKode_matkul(dataobj.getString("kode_matkul"));
//                            matkulRecylcer.setHari(dataobj.getString("hari"));
//                            matkulRecylcer.setJam_mulai(dataobj.getString("jam_mulai"));
//
//                            matkulRecyclerList.add(matkulRecylcer);
//                        }
//                        adapter = new RetrofitAdapter(getActivity(), matkulRecyclerList);
//                        recyclerView.setAdapter(adapter);
//                    }
//                }else{
//                    Toast.makeText(getActivity(), obj.optString("message")+"",Toast.LENGTH_SHORT).show();
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
        }
    }

    @Override
    public void onFailure(Call<UserData> call, Throwable t) {

    }


    public void writeRecycler(String response){
        Log.d("message", response);

    }
}
