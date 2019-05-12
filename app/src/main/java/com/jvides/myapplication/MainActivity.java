package com.jvides.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.jvides.myapplication.adapters.CustomerViewAdapter;
import com.jvides.myapplication.entities.NaturalPerson;

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
    private ArrayList<NaturalPerson> naturalPersons = new ArrayList<NaturalPerson>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //carga interfaz de usuario

        url = getResources().getString(R.string.host_name);

        queue = Volley.newRequestQueue(this);
        buildRecycleView();




        getMethod(url +"/billing/api/v1/natural-persons");
        Log.d("LOGCAT", "->"  +Integer.toString(naturalPersons.size())  );
    }

    public void getMethod(String url){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Gson gson = new GsonBuilder().create();
                Type listType = new TypeToken<ArrayList<NaturalPerson>>(){}.getType();
                List<NaturalPerson> yourClassList = new Gson().fromJson(response.toString(), listType);

                for(NaturalPerson n : yourClassList){
                    Log.i("LOGCAT", n.getIdentificationNumber());
                    naturalPersons.add(n);
                }

/*
                for (int i = 0; i < response.length(); i++) {
                    try{
                        JSONObject jsonObject = response.getJSONObject(i);
                        NaturalPerson naturalPerson = new NaturalPerson();

                        naturalPerson.setFirstName(jsonObject.getString("firstName"));
                        naturalPerson.setLastName(jsonObject.getString("lastName"));
                        naturalPerson.setComments(jsonObject.getString("comments"));
                        naturalPerson.setPhotoUrl(jsonObject.getString("photoUrl"));
                        naturalPerson.setBirthDate(jsonObject.getString("birthDate"));

                        naturalPersons.add(naturalPerson);

                    }catch (JSONException ex){ ex.printStackTrace();}

                }*/

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
        customerViewAdapter = new CustomerViewAdapter(naturalPersons);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(customerViewAdapter);

        customerViewAdapter.setOnItemClickListener(new CustomerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                naturalPersons.get(position);
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);


                intent.putExtra("NaturaPerson", naturalPersons.get(position));


                startActivity(intent);
            }
        });
    }


}
