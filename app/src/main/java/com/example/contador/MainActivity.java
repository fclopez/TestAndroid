package com.example.contador;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends Activity implements View.OnClickListener {

    private Button btnSumar, btnRestar, btnSaludo, btnConsulta, btnApagado, btnEncendido;
    private TextView txtContador, txtSaludo, txtConsulta,txtEstado, txtEstadoSocket;
    private EditText editText;
    private int contador = 0;
    private ConexionWs app;

  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //definimos el layout
        setContentView(R.layout.activity_main);
        //Obtenemos una referencia a los controles de la interfaz
        txtContador = (TextView)findViewById(R.id.txtContador);
        txtSaludo = (TextView)findViewById(R.id.txtSaludo);
        txtConsulta = (TextView)findViewById(R.id.txtConsulta);
        txtEstado = (TextView)findViewById(R.id.txEstado);
        txtEstadoSocket = (TextView)findViewById(R.id.txEstadoSocket);
        editText = (EditText)findViewById(R.id.editText);
        btnSumar = (Button)findViewById(R.id.btnSumar);
        btnRestar = (Button)findViewById(R.id.btnRestar);
        btnSaludo = (Button)findViewById(R.id.btnSaludo);
        btnConsulta = (Button)findViewById(R.id.btnConsultar);
        btnApagado = (Button)findViewById(R.id.btnApagado);
        btnEncendido = (Button)findViewById(R.id.btnEncendido);
        //definimos los eventos de los botones
        btnSumar.setOnClickListener(this);
        btnRestar.setOnClickListener(this);
        btnSaludo.setOnClickListener(this);
        btnConsulta.setOnClickListener(this);
        btnApagado.setOnClickListener(this);
        btnEncendido.setOnClickListener(this);

        //Se inicia el socket
        app = new ConexionWs(MainActivity.this);
    }
    // encapsulamiento
    public String getTxtCosulta(){
        return (String) this.txtConsulta.getText();
    }
    public void setTxtCosulta(String txt){
        this.txtConsulta.setText( txt );
    }
    public void setTxtEstadoSocket(String txt){this.txtEstadoSocket.setText(txt);}

    @Override
    public void onDestroy(){

    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSumar:
                contador++;
                txtContador.setText("Contador: "+contador);
                break;
            case R.id.btnRestar:
                contador--;
                txtContador.setText("Contador: "+contador);
                break;
            case R.id.btnSaludo:
                txtSaludo.setText("Hola: "+editText.getText());
                //notificacion pop-up
                Toast.makeText(this,"Hola: "+editText.getText(),Toast.LENGTH_LONG ).show();
                break;
            case R.id.btnConsultar:
                //se envia una comunicacion
                new RegistroSensor(this).execute("http://hometic.apphb.com/api/Registro");
                break;
            case R.id.btnApagado:
                app.enviarMensaje("SensorPIR","false");
                txtEstado.setText("Apagado");
                break;
            case R.id.btnEncendido:
                app.enviarMensaje("SensorPIR","true");
                txtEstado.setText("Encendido");
                break;
        }
    }
}
