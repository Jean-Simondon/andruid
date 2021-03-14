package com.andruidteam.andruid.util;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class StringListMapConverter {
    @TypeConverter
    public Map<String, String> fromString(String value)  {
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        return new Gson().fromJson(value, type);
    }

    @TypeConverter
    public String fromStringMap(Map<String, String> map) {
        Gson gson = new Gson();
        return gson.toJson(map);
    }
}