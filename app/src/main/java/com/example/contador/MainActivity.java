package com.example.contador;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button btnSumar, btnRestar, btnSaludo;
    private TextView txtContador, txtSaludo;
    private EditText editText;
    private int contador = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //definimos el layout
        setContentView(R.layout.activity_main);
        //Obtenemos una referencia a los controles de la interfaz
        txtContador = (TextView)findViewById(R.id.txtContador);
        txtSaludo = (TextView)findViewById(R.id.txtSaludo);
        editText = (EditText)findViewById(R.id.editText);
        btnSumar = (Button)findViewById(R.id.btnSumar);
        btnRestar = (Button)findViewById(R.id.btnRestar);
        btnSaludo = (Button)findViewById(R.id.btnSaludo);
        //definimos los eventos de los botones
        btnSumar.setOnClickListener(this);
        btnRestar.setOnClickListener(this);
        btnSaludo.setOnClickListener(this);
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
                break;
        }
    }

}
