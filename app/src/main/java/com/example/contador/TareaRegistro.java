package com.example.contador;

import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by Camilo on 15/10/2016.
 */

public class TareaRegistro extends AsyncTask<String, String, String> {

    /*definicion de los elementos para la conexion*/
    HttpURLConnection conexion;
    BufferedReader rd;
    StringBuffer  result;
    MainActivity menup;

    /*Este contructor permite la interacción con la clase principal*/
    public TareaRegistro(MainActivity princ){
        this.menup = princ;
    };

    @Override
    protected String doInBackground(String... str) {
        try {
            URL url = new URL(str[0]);
            conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");
            conexion.connect();
            //InputStream is = (conexion.getResponseCode()==201||conexion.getResponseCode()==200)? conexion.getInputStream(): null;

            InputStream is = conexion.getInputStream();
            rd = new BufferedReader(new InputStreamReader(is));
            result = new StringBuffer();
            String line = "";
            
            while ((line = rd.readLine()) != null) {
                result.append(line+'\n');
            }

            String data = result.toString();

            //JSONObject jsonObj = new JSONObject(new JSONTokener(result.toString()));
            JSONArray jsonArray = new JSONArray( data );
            String msg = "";

            for(int i=0;i<=jsonArray.length()-1;i++){
                JSONObject obj1 = jsonArray.getJSONObject( i );
                String id = obj1.getString("Id");
                String fecha = obj1.getString("FechaRegistro");
                String lectura = obj1.getString("Lectura");
                String sensorId = obj1.getString("SensorId");
                msg += "\n IdRegistro: "+id+"\n Fecha: "+fecha+"\n Mensaje: "+lectura+"\n SensorId: "+sensorId+"\n" ;
            }
            return msg;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if(conexion != null){
                conexion.disconnect();
            }
            try {
                if(rd != null){
                    rd.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        menup.setTxtCosulta("Consulta: \n"+s);
    }
}
