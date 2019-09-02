package com.example.estudiante.a2daevaluacionpractica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class graficaHabilidades extends AppCompatActivity {
    String  nombre_heroe = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafica_habilidades);
        Intent heroelista = getIntent();
        this.nombre_heroe= (String)heroelista.getExtras().get("nombre_heroe");
        cargarDatos();
    }

    public void cargarDatos() {
        final TextView alias = (TextView) findViewById(R.id.txtAlias);
        final TextView nombre = (TextView) findViewById(R.id.txtNombre);
        alias.setText(nombre_heroe);
        nombre.setText("prueba");

    }
}
