package com.andruidteam.andruid.rds;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

public class Requests {

    public static JsonObjectRequest GETJsonObject(String url, Response.Listener<JSONObject> responseListener) {
        return new JsonObjectRequest
                (Request.Method.GET, url, null, responseListener, error -> {
                    Log.e("httpRequest", error.getMessage());
                    Log.getStackTraceString(error);
                });
    }

    public static JsonArrayRequest GETJsonArray(String url, Response.Listener<JSONArray> responseListener) {
        return new JsonArrayRequest
                (Request.Method.GET, url, null, responseListener, error -> {
                    Log.e("httpRequest", error.getMessage());
                    Log.getStackTraceString(error);
                });
    }

}
