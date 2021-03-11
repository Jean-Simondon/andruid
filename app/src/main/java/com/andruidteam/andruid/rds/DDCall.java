package com.andruidteam.andruid.rds;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.apache.commons.lang3.StringUtils;

public class DDCall {

    private static String url = "https://www.dnd5eapi.co/api/";

    public static void callToDD (String index) {

        if( !StringUtils.isBlank(index)) {
            DDCall.url += index;
        }




    }



}
