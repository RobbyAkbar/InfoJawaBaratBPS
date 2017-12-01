package es.esy.android_inyourhand.infojawabarat.models;

import android.app.ProgressDialog;
import android.content.Context;

import es.esy.android_inyourhand.infojawabarat.R;

/**
 * Created by robby on 08/11/17.
 */

public class progressDialogModel {

    static ProgressDialog progressDialog;

    public static void pdMenyiapkanDataLogin(Context context){
        progressDialog=new ProgressDialog(context, R.style.AppCompatAlertDialogStyle);
        progressDialog.setMessage("Menyiapkan Data....");
        progressDialog.setTitle("Silahkan Tunggu");
        progressDialog.show();
    }

    public static void hideProgressDialog(){
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

}
