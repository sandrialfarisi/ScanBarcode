package id.devcamp.scanbarcode.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class LoginSession {
    public static final String SP_MAHASISWA_APP = "spMahasiswaApp";

    public static final String SP_TOKEN = "spToken";

    public static final String SP_SUDAH_LOGIN = "spSudahLogin";

    private SharedPreferences sp;
    private SharedPreferences.Editor spEditor;

    @SuppressLint("CommitPrefEdits")
    public LoginSession(Context context){
        sp = context.getSharedPreferences(SP_MAHASISWA_APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public String getSPToken(){
        return sp.getString(SP_TOKEN, "");
    }

    public Boolean getSPSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }

}
