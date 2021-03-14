package com.andruidteam.andruid.rds;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestQueueSingleton {

    public static final String TAG = "RequestQueueSingleton";
    
    private static RequestQueueSingleton requestQueueSingleton;
    private RequestQueue requestQueue;
    private static Context context;

    private RequestQueueSingleton(Context ctx){
        context = ctx;
        requestQueue = getRequestQueue();
        Log.d(TAG, "RequestQueueSingleton: ");
    }

    public static synchronized  RequestQueue getInstance(Context context){
        Log.d(TAG, "getInstance: ");
        if (requestQueueSingleton == null){
            requestQueueSingleton = new RequestQueueSingleton(context);
        }
        return requestQueueSingleton.getRequestQueue();
    }

    private RequestQueue getRequestQueue() {
        Log.d(TAG, "getRequestQueue: ");
        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }
}