package com.example.otpsender;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Random;

import com.example.otpsender.R;


public class OTPActivity extends AppCompatActivity {

    final int INITIAL = 100000;
    final int END = 999999;
    final String API_KEY = "XXXXXXXX";
    final String API_SECRET = "XXXXXXXXXXXXXXXX";

    MessageModel model;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        Bundle extra = getIntent().getExtras();
        final String p_num = extra.getString("pnumber");
        final String f_name = extra.getString("fname");
        final String l_name = extra.getString("lname");



        final String full_name = f_name + " "+l_name;


        TextView otp = (TextView) findViewById(R.id.otp);
        TextView otp_text = (TextView) findViewById(R.id.otp_text);

        final int random_otp = createRandomNumber();

        otp_text.setText("Hi. Your OTP is:");
        otp.setText(""+random_otp);

        Button send = (Button) findViewById(R.id.otp_send_button);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                helper = new DatabaseHelper(OTPActivity.this);
                model = new MessageModel(full_name,random_otp );
                helper.insertMessageDetails(model);

                sendOTP(p_num, random_otp);
                finish();




            }
        });

    }

    public void sendOTP(String number, int otp) {
        String url = "https://rest.nexmo.com/sms/json";
        HashMap<String, String> params = new HashMap<String, String>();


        params.put("api_key",API_KEY );
        params.put("api_secret",API_SECRET );
        params.put("from", "NEXMO");
        params.put("to", number);
        params.put("text", "Hi. Your OTP is: "+Integer.toString(otp));

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>()  {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int total_messages = response.getInt("message-count");
                            System.out.println("total number of messages retrieved: " + total_messages);
                            JSONArray messages = response.getJSONArray("messages");
                            String messages_status[] = new String[20];
                            String messages_id[] = new String[20];
                            String messages_to[] = new String[20];
                            String messages_remaining_balance[] = new String[20];
                            String messages_price[] = new String[20];
                            String messages_network[] = new String[20];

                            for (int i = 0; i < messages.length(); i++) {
                                JSONObject current_message = messages.getJSONObject(i);
                                messages_status[i] = current_message.get("status").toString();

                                messages_id[i] = current_message.get("message-id").toString();
                                messages_to[i] = current_message.get("to").toString();
                                messages_remaining_balance[i] = current_message.get("remaining-balance").toString();
                                messages_price[i] = current_message.get("message-price").toString();
                                messages_network[i] = current_message.get("network").toString();
                            }

                            for (int i = 0; i < total_messages; i++) {
                                if (messages_status[i].equals("0")) {
                                    Toast.makeText(OTPActivity.this, "Message is Delivered",Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(OTPActivity.this, "Message is Not Delivered",Toast.LENGTH_SHORT).show();
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });
        Singelton.getInstance(this).addToRequestQueue(jsObjRequest);

    }

    public int createRandomNumber()
    {




        Random r = new Random();
        int randomNum = r.nextInt((END - INITIAL) + 1) + INITIAL;

        return randomNum;


    }
}
