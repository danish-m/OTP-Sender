package com.example.otpsender;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.example.otpsender.R;


public class ContactActivity extends AppCompatActivity {
    List<ContactModel> cList;

    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        JSONReader json_obj = new JSONReader();
        JSONObject obj;
        try {
            obj = new JSONObject(json_obj.loadJSONFromAsset(this));

            //Log.v("MainActivity", obj.getString("fname"));
            JSONArray json_array = obj.getJSONArray("contacts");
            //System.out.println("in try",+ m_jArry.length());
            //ArrayList<HashMap<String, String>> formList = ew ArrayList<HashMap<String, String>>();
            cList= new ArrayList<ContactModel>();

            for (int i = 0; i < json_array.length(); i++) {

                JSONObject obj2 = json_array.getJSONObject(i);


                Log.v("Details-->", obj2.getString("fname"));

                String fname = obj2.getString("fname");
                String lname = obj2.getString("lname");
                String pnum =obj2.getString("pnum");
                cList.add(new ContactModel(fname,lname,pnum));

            }




        }

        catch (JSONException e)
        {
            e.printStackTrace();
        }

        mRecyclerView = (RecyclerView)findViewById(R.id.recycleview);

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new ContactViewAdapter(this,cList);
        mRecyclerView.setAdapter(mAdapter);

    }
}
