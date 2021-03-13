package com.andruidteam.andruid.ui.fragment.codex.characterData;

import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.andruidteam.andruid.databinding.FragmentCodexResultDetailItemBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class AbilityScoresDynamicTemplate {

    public static final String TAG = "AbilityScoresDynamicTemplate";
    
    public static void SetDynamicTemplate(FragmentActivity context, JSONObject response, FragmentCodexResultDetailItemBinding binding) {
        Log.d(TAG, "SetDynamicTemplate: ");

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.setMargins(10, 10, 10, 10); // (left, top, right, bottom)

        /** Setting du titre */
        try {
            binding.title.setText(response.getString("full_name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /** Ajout de l'élément desc */

        TextView desc = new TextView(context);
        desc.setLayoutParams(layoutParams);
        try {
            desc.setText(response.getString("desc"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        binding.listItemDetail.addView(desc);

    }

}
