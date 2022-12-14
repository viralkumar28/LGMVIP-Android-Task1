package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Model> cdata = new ArrayList<>();
    Model covidData;
    private Adapter madapter;
    private RequestQueue mQueue;
    private String jsonResponse;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        mQueue = Volley.newRequestQueue(this);
        extractcovidData();
    }
    private void extractcovidData() {
        String JSON_URL = "https://data.covid19india.org/state_district_wise.json";

        StringRequest request = new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonobject = new JSONObject(response);
                    Iterator<String> keys = jsonobject.keys();
                    while (keys.hasNext()) {
                        String key = keys.next();
                        JSONObject jsonob = jsonobject.getJSONObject(key);
                        JSONObject jsonobb = jsonob.getJSONObject("districtData");
                        Iterator<String> superkeys = jsonobb.keys();
                        while (superkeys.hasNext()) {
                            String superkey = superkeys.next();
                            JSONObject jsonobbb = jsonobb.getJSONObject(superkey);
                            JSONObject jsonobbbb = jsonobbb.getJSONObject("delta");
                            String active = jsonobbb.getString("active");
                            String confirmed = jsonobbb.getString("confirmed");
                            String deceased = jsonobbb.getString("deceased");
                            String recovered = jsonobbb.getString("recovered");

                            String confirmeddelta = jsonobbbb.getString("confirmed");
                            String deceaseddelta = jsonobbbb.getString("deceased");
                            String recovereddelta = jsonobbbb.getString("recovered");
                            covidData = new Model(superkey, active, confirmed, recovered, deceased, confirmeddelta, deceaseddelta, recovereddelta);
                            cdata.add(covidData);
                        }
                    }
                    madapter = new CustomAdapter(MainActivity.this, cdata);
                    listView.setAdapter((ListAdapter) madapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: " + error.getMessage());
            }
        }
        );
        mQueue.add(request);
    }
};