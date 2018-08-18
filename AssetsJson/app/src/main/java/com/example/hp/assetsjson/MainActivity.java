package com.example.hp.assetsjson;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<JsonModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFind();
        new MyAsycTask().execute();
        setAdapter();
    }

    private void setAdapter() {
        recyclerView.setHasFixedSize(true);
        JsoneAdapter adapter = new JsoneAdapter(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void setFind() {
        recyclerView = findViewById(R.id.rec);
    }

    class MyAsycTask extends AsyncTask<Void, Void, List<JsonModel>> {


        @Override
        protected List<JsonModel> doInBackground(Void... voids) {
            List<JsonModel> m_list = new ArrayList<>();
            try {
                JSONArray jArry = new JSONArray(loadJSONFromAsset());
                for (int i = 0; i < jArry.length(); i++) {
                    JSONObject jsonObject = jArry.getJSONObject(i);
                    String name = jsonObject.getString("name");
                    String last = jsonObject.getString("last");
                    JsonModel jsonModel = new JsonModel(name, last);
                    list.add(jsonModel);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return m_list;
        }


        public String loadJSONFromAsset() {
            String json;
            try {
                InputStream is = MainActivity.this.getAssets().open("android_group.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json = new String(buffer, "UTF-8");
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
            return json;
        }

        @Override
        protected void onPostExecute(List<JsonModel> jsonModels) {
            super.onPostExecute(jsonModels);
            list = jsonModels;
        }
    }
}
