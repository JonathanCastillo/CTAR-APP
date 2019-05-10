package com.jvides.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jvides.myapplication.adapters.CustomerViewAdapter;
import com.jvides.myapplication.entities.NaturalPerson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RequestQueue queue;
    private String url;

    private RecyclerView recyclerView;
    private CustomerViewAdapter customerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<NaturalPerson> naturalPersons = new ArrayList<NaturalPerson>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //carga interfaz de usuario

        url = getResources().getString(R.string.host_name);

        queue = Volley.newRequestQueue(this);


        recyclerView = (RecyclerView)findViewById(R.id.recicler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        customerViewAdapter = new CustomerViewAdapter(naturalPersons);
        recyclerView.setAdapter(customerViewAdapter);

        getMethod(url +"/billing/api/v1/natural-persons");
        Log.d("LOGCAT", "->"  +Integer.toString(naturalPersons.size())  );
    }

    public void getMethod(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Type listType = new TypeToken<ArrayList<NaturalPerson>>(){}.getType();
                        naturalPersons = gson.fromJson(response, listType);
                        Log.d("LOGCAT", Integer.toString(naturalPersons.size())  );
                        Log.d("LOGCAT", "RESPONSE FROM SERVER: " + naturalPersons);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try{
                            //String responseBody = new String(error.networkResponse.data, "utf-8");
                            Log.d("LOGCAT", "ERROR" + error );
                        }catch (Exception ex){
                            Log.d("LOGCAT","msg:" + ex.toString());
                        }
                    }
                });
        queue.add(stringRequest);
    }

}
