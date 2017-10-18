package com.example.otpsender;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Singelton {


        private static Singelton mInstance;
        private RequestQueue mRequestQueue;
        private static Context mCtx;

        private Singelton(Context context) {
            mCtx = context;
            mRequestQueue = getRequestQueue();
        }

        public static synchronized Singelton getInstance(Context context) {
            if (mInstance == null) {
                mInstance = new Singelton(context);
            }
            return mInstance;
        }

        public RequestQueue getRequestQueue() {
            if (mRequestQueue == null) {
                // getApplicationContext() is key, it keeps you from leaking the
                // Activity or BroadcastReceiver if someone passes one in.
                mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
            }
            return mRequestQueue;
        }

        public <T> void addToRequestQueue(Request<T> req) {
            getRequestQueue().add(req);
        }


    }

