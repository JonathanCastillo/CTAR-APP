package com.jvides.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.jvides.myapplication.adapters.CustomerViewAdapter;
import com.jvides.myapplication.entities.Usuarios;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RequestQueue queue;
    private String url;

    private RecyclerView recyclerView;
    private CustomerViewAdapter customerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Usuarios> usuarios = new ArrayList<Usuarios>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //carga interfaz de usuario

        url ="http://192.168.1.6:8080/ctar/api/v1/usuarios";

        queue = Volley.newRequestQueue(this);
        buildRecycleView();




       getMethod(url);
        Log.d("LOGCAT", "->"  +Integer.toString(usuarios.size())  );

    }

    public void getMethod(String url){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Gson gson = new GsonBuilder().create();
                Type listType = new TypeToken<ArrayList<Usuarios>>(){}.getType();
                List<Usuarios> Respuesta = new Gson().fromJson(response.toString(), listType);
                /*
                for(Usuarios n : Respuesta){
                    Log.d("LOGCAT", "LLEGANDO HASTA AQUI dentro de for" );
                    //Log.i("LOGCAT", n.getApellidos());
                    usuarios.add(n);
                }
                */
                CustomerViewAdapter adapter = new CustomerViewAdapter(usuarios);
                recyclerView.setAdapter(adapter);

                for (int i = 0; i < response.length(); i++) {
                    try{
                        JSONObject jsonObject = response.getJSONObject(i);
                        Usuarios naturalPerson = new Usuarios();

                        naturalPerson.setNombres(jsonObject.getString("nombres"));
                        naturalPerson.setApellidos(jsonObject.getString("apellidos"));
                        naturalPerson.setEmail(jsonObject.getString("email"));
                        naturalPerson.setFoto_Perfil(jsonObject.getString("foto_Perfil"));
                        naturalPerson.setUser_Name(jsonObject.getString("user_Name"));

                        usuarios.add(naturalPerson);

                    }catch (JSONException ex){ ex.printStackTrace();}

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(jsonArrayRequest);
    }

    public void buildRecycleView(){
        recyclerView = (RecyclerView)findViewById(R.id.recicler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        customerViewAdapter = new CustomerViewAdapter(usuarios);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(customerViewAdapter);

        customerViewAdapter.setOnItemClickListener(new CustomerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                usuarios.get(position);
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);


                intent.putExtra("Usuarios", usuarios.get(position));


                startActivity(intent);
            }
        });
    }


}
