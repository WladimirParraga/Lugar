package com.example.apprestful_formatojson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import WebServices.WebService;
import java.util.Map;
import java.util.HashMap;
import WebServices.Asynchtask;
public class turisticos extends AppCompatActivity implements Asynchtask{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turisticos);

        Map<String, String> datos = new HashMap<>();


        WebService ws = new WebService(
                "https://uealecpeterson.net/turismo/lugar_turistico/json_getlistadoGrid",
                datos,
                turisticos.this,
                turisticos.this);

        ws.execute("GET", "Public-Merchant-Id", "84e1d0de1fbf437e9779fd6a52a9ca18");

    }

    @Override
    public void processFinish(String result) throws JSONException {
        Log.d("API_RESPONSE", result);  //

        TextView txtLugares = findViewById(R.id.textlugar);
        JSONObject jsonResponse = new JSONObject(result);
        JSONArray lugaresArray = jsonResponse.getJSONArray("data");

        StringBuilder lugaresInfo = new StringBuilder();

        for (int i = 0; i < lugaresArray.length(); i++) {
            JSONObject lugar = lugaresArray.getJSONObject(i);

            if (lugar.has("categoria") && lugar.has("nombre_lugar") && lugar.has("telefono") &&
                    !lugar.isNull("categoria") && !lugar.isNull("nombre_lugar") && !lugar.isNull("telefono")) {
                String categoria = lugar.getString("categoria");
                String nombreLugar = lugar.getString("nombre_lugar");
                String telefono = lugar.getString("telefono");

                lugaresInfo.append("Categoría: ").append(categoria).append("\n");
                lugaresInfo.append("Nombre del lugar: ").append(nombreLugar).append("\n");
                lugaresInfo.append("Teléfono: ").append(telefono).append("\n\n");
            }
        }

        txtLugares.setText(lugaresInfo.toString());
    }

}