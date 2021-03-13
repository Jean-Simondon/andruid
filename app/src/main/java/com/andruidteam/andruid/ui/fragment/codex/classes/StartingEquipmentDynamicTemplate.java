package com.andruidteam.andruid.ui.fragment.codex.classes;

import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.andruidteam.andruid.databinding.FragmentCodexResultDetailItemBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StartingEquipmentDynamicTemplate {

    public static final String TAG = "StartingEquipmentDynamicTemplate";

    public static void SetDynamicTemplate(FragmentActivity context, JSONObject response, FragmentCodexResultDetailItemBinding binding) {
        Log.d(TAG, "SetDynamicTemplate: ");

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.setMargins(10, 10, 10, 10); // (left, top, right, bottom)

        JSONArray responseArray = new JSONArray();
        try {
            responseArray = response.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /** Setting du titre */
        binding.title.setText("Starting Equipement");

        /** Ajout de l'élément type */
        TextView desc = new TextView(context);
        desc.setLayoutParams(layoutParams);
        try {
            desc.setText(responseArray.getString(2));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        binding.listItemDetail.addView(desc);

    }
}
