package com.andruidteam.andruid.rds;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Requests {

    public static final String TAG = "Requests";

    public static JsonObjectRequest GETJsonObject(String url, Response.Listener<JSONObject> responseListener) {
        Log.d(TAG, "GETJsonObject: ");
        return new JsonObjectRequest
                (Request.Method.GET, url, null, responseListener, error -> {
                    Log.e("httpRequest", error.getMessage());
                    Log.getStackTraceString(error);
                });
    }

    public static JsonArrayRequest GETJsonArray(String url, Response.Listener<JSONArray> responseListener) {
        Log.d(TAG, "GETJsonArray: ");
        return new JsonArrayRequest
                (Request.Method.GET, url, null, responseListener, error -> {
                    Log.e("httpRequest", error.getMessage());
                    Log.getStackTraceString(error);
                });
    }

    public static String getStringOrEmpty(JSONObject object, String index) {
        try {
            return object.getString(index);
        } catch (JSONException e) {
        }
        return "";
    }

    public static JSONArray getJSONArrayOrNull(JSONObject object, String index) {
        try {
            return object.getJSONArray(index);
        } catch (JSONException e) {
        }
        return null;
    }

    public static JSONObject getJSONObjectOrNull(JSONObject object, String index) {
        try {
            return object.getJSONObject(index);
        } catch (JSONException e) {
        }
        return null;
    }

}
