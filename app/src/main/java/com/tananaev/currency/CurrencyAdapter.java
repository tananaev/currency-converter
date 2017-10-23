package com.tananaev.currency;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.ViewHolder> {

    private String mBase;
    private ArrayList<Map.Entry<String, Double>> mCurrencies;

    public CurrencyAdapter(String base, Map<String, Double> currencies) {
        mBase = base;
        mCurrencies = new ArrayList<>(currencies.entrySet());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.item_rate, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Map.Entry<String, Double> entry = mCurrencies.get(position);
        final Context context = holder.itemView.getContext();
        holder.currency.setText(entry.getKey());
        int drawableId = context.getResources().getIdentifier(
                "flag_" + entry.getKey().toLowerCase(), "drawable", context.getPackageName());
        holder.currency.setCompoundDrawablesWithIntrinsicBounds(drawableId, 0, 0, 0);
        holder.rate.setText(String.format(Locale.getDefault(), "%.2f", entry.getValue()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ConvertActivity.class);
                intent.putExtra(ConvertActivity.KEY_BASE, mBase);
                intent.putExtra(ConvertActivity.KEY_CURRENCY, entry.getKey());
                intent.putExtra(ConvertActivity.KEY_RATE, entry.getValue());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCurrencies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView currency;
        private TextView rate;

        public ViewHolder(View itemView) {
            super(itemView);
            currency = itemView.findViewById(android.R.id.text1);
            rate = itemView.findViewById(android.R.id.text2);
        }

    }

}
