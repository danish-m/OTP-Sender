package com.example.otpsender;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.otpsender.R;

public class MessagesActivity extends AppCompatActivity {

    DatabaseHelper helper;
    List<MessageModel> mList;

    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        helper = new DatabaseHelper(MessagesActivity.this);



        mList= new ArrayList<MessageModel>();
        mList = helper.getAllMessages();
        Log.v("MesageA",""+mList.size());


        if (mList.size()>0) {
            mRecyclerView = (RecyclerView) findViewById(R.id.message_recycle_view);

            mRecyclerView.setHasFixedSize(true);

            // use a linear layout manager
            mLayoutManager = new LinearLayoutManager(MessagesActivity.this);
            mRecyclerView.setLayoutManager(mLayoutManager);

            // specify an adapter (see also next example)
            mAdapter = new MessageViewAdapter(MessagesActivity.this, mList);
            mRecyclerView.setAdapter(mAdapter);
        }
        else
        {
            Toast.makeText(this,"Message List is empty.",Toast.LENGTH_SHORT).show();
        }


    }
}
