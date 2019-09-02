package com.example.estudiante.a2daevaluacionpractica;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class HeroList extends AppCompatActivity {
    private RequestQueue mQueue;
    private String busqueda = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_list);
        mQueue = Volley.newRequestQueue(this);
        Intent main = getIntent();
        this.busqueda = (String)main.getExtras().get("idheroe");
        buscarHeroe(busqueda);

    }
    public void buscarHeroe(String busqueda){
        String login_url = "https://www.superheroapi.com/api.php/2718980624792231/search/"+busqueda;
        final TextView txtResultadosLen = (TextView) findViewById(R.id.txtResultados);
        Intent heroe_lista = new Intent(getBaseContext(), HeroList.class);


        final ScrollView scrollBusqueda = (ScrollView) findViewById(R.id.listaHeroes);
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, login_url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println(response.getJSONArray("results").length());
                            txtResultadosLen.setText( String.valueOf(response.getJSONArray("results").length()));
                            LinearLayout ll = (LinearLayout) findViewById(R.id.listaHero3);

                            for(int i = 0 ; i<response.getJSONArray("results").length();i++){
                                TextView prueba = new TextView(HeroList.this);
                                prueba.setTextSize(24);

                                final JSONObject j = (JSONObject)response.getJSONArray("results").get(i);
                                System.out.println(j.getString( "name"));
                                prueba.setText(j.getString("name").toString());
                                ll.addView(prueba);
                                System.out.println(i);

                                prueba.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        try {
                                            Intent habilidades_heroes = new Intent(getBaseContext(), graficaHabilidades.class);
                                            habilidades_heroes.putExtra("nombre_heroe", j.getString("name"));
                                            //habilidades_heroes.putExtra("nombre_heroe", j.g("name"));
                                            startActivity(habilidades_heroes);
                                            System.out.println(j.getString("name"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }
                            scrollBusqueda.addView(ll);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog alertDialog = new
                        AlertDialog.Builder(HeroList.this).create();
                alertDialog.setTitle("Alerta");
                alertDialog.setMessage("No se encuentra el superheroe");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int
                                    which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });
        mQueue.add(request);
    }
}
