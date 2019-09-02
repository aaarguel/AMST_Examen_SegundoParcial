package com.example.estudiante.a2daevaluacionpractica;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buscarHeroe(View view ){
        final TextView busqueda = (TextView) findViewById(R.id.txtIdHero);
        Intent heroe_lista = new Intent(getBaseContext(), HeroList.class);
        heroe_lista.putExtra("idheroe", busqueda.getText().toString());
        startActivity(heroe_lista);
    }

}
