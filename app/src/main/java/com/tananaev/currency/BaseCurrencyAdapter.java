package com.tananaev.currency;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class BaseCurrencyAdapter extends ArrayAdapter<String> {

    public BaseCurrencyAdapter(Context context, String[] objects) {
        super(context, R.layout.item_base, android.R.id.text1, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item_base, parent, false);
        }
        Context context = parent.getContext();
        TextView view = (TextView) convertView;
        String currency = getItem(position);
        view.setText(currency);
        int drawableId = context.getResources().getIdentifier(
                "flag_" + currency.toLowerCase(), "drawable", context.getPackageName());
        view.setCompoundDrawablesWithIntrinsicBounds(drawableId, 0, 0, 0);
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

}
