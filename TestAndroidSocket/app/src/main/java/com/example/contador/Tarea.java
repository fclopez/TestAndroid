package com.example.contador;

import android.os.AsyncTask;
import org.json.JSONObject;

/**
 * Created by flopez on 01/11/2016.
 */

public class Tarea extends AsyncTask<String, String, String> {

    private MainActivity menup;

    /*Este contructor permite la interacción con la clase principal*/
    public Tarea(MainActivity princ){
        this.menup = princ;
    };

    @Override
    protected String doInBackground(String... params) {
        String json = (String) params[0];
        return  json;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        menup.setTxtEstadoSocket("Respuesta del servidor: "+s);
    }

}
