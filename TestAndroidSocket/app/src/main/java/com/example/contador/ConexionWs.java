package com.example.contador;

import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by flopez on 01/11/2016.
 */

public class ConexionWs {

    private Socket ws;
    private MainActivity principal;

    public ConexionWs(MainActivity pr) {
        try {
            this.principal = pr;
            this.ws = IO.socket("http://172.30.60.86:8000");
            this.ws.on("status", estado());
            this.ws.open();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void onDestroy(){
        ws.close();
    }

    /*Eventos Emmit*/
    public void enviarMensaje(String evento, String msg){
        this.ws.emit(evento,msg);
    }
    /*Eventos Listener*/
    public Emitter.Listener estado(){
        Emitter.Listener estado = new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                principal.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String data = (String)args[0];
                        principal.setTxtEstadoSocket("Respuesta del servidor: "+ data);

                    }
                });
                //new Tarea(principal).execute((String)args[0]);
            }
        };
        return estado;
    }

}
