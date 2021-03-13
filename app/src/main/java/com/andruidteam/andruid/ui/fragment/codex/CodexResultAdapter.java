package com.andruidteam.andruid.ui.fragment.codex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;

import com.andruidteam.andruid.R;
import com.andruidteam.andruid.databinding.FragmentCodexResultListItemBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CodexResultAdapter extends BaseAdapter {

    public static final String TAG = "CodexResultAdapter";

    //    private LayoutInflater layoutInflater;
    public JSONArray jsonList;

    public CodexResultAdapter(Context context, JSONArray jsonArray) {
//        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.jsonList = jsonArray;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return jsonList.length();
    }

    @Override
    public Object getItem(int position) {
        try {
            return jsonList.getJSONObject(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject getObject(int position) {
        try {
            return (JSONObject)jsonList.get(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void setJSONList(JSONArray jsonArray) {
        this.jsonList = jsonArray;
        notifyDataSetChanged();
    }

    public void update() {
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        FragmentCodexResultListItemBinding mBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.fragment_codex_result_list_item, parent, false);

        try {
            JSONObject test = (JSONObject)jsonList.get(position);
            mBinding.name.setText(test.getString("name")); // il faudrait mettre "class" pour l'appel vers starting-equipment
            return mBinding.getRoot();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return mBinding.getRoot();
    }
}
