package com.example.otpsender;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.otpsender.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button contact = (Button) findViewById(R.id.contacts);
        Button messages = (Button) findViewById(R.id.messages);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent contact_activity = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(contact_activity);

            }
        });

        messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent message_activity = new Intent(MainActivity.this, MessagesActivity.class);
                startActivity(message_activity);

            }
        });



    }
}
