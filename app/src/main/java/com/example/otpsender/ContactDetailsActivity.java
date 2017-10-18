package com.example.otpsender;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.otpsender.R;

public class ContactDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        Bundle bundle = getIntent().getExtras();
        final String f_name = bundle.getString("fname");
        final String l_name = bundle.getString("lname");
        final String p_num = bundle.getString("pnumber");

        TextView fname_view = (TextView) findViewById(R.id.contact_details_name);
        String full_name = f_name + " "+l_name;
        fname_view.setText("Name    : "+full_name);

        TextView pnumber_view = (TextView) findViewById(R.id.contact_details_phone_number);
        pnumber_view.setText("Number : "+p_num);

        Button send = (Button) findViewById(R.id.contact_details_send_message);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), OTPActivity.class);

                Bundle extras = new Bundle();
                extras.putString("fname",f_name);
                extras.putString("lname",l_name);
                extras.putString("pnumber",p_num);
                intent.putExtras(extras);
                startActivity(intent);
                finish();
            }
        });



    }
}
