package com.example.apprestful_formatojson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity
        extends AppCompatActivity
        implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void clickLogin(View v){
        Bundle bundle = this.getIntent().getExtras();
        Map<String, String> datos = new HashMap<String, String>();
        String Usr, Clave;
        EditText txtusuario = findViewById(R.id.txtusuario);
        EditText txtclave = findViewById(R.id.txtclave);
        WebService ws= new WebService(
                "https://revistas.uteq.edu.ec/ws/login.php?usr=" + txtusuario.getText().toString() + "&pass=" + txtclave.getText().toString()
                ,
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");


    }

    @Override
    public void processFinish(String result) throws JSONException {

        TextView txtRespuesta = findViewById(R.id.txtresp);

        if (result.equals("Login Correcto!")){
            Intent intent = new Intent(this, turisticos.class);
            startActivity(intent);
        }
        else {
            txtRespuesta.setText(result);
        }
    }
}