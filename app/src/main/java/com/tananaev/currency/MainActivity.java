package com.tananaev.currency;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainContract.Presenter mPresenter;

    private String[] mCurrencies;

    private Spinner mBaseSpinner;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new MainPresenter();
        mPresenter.setView(this);

        mCurrencies = getResources().getStringArray(R.array.currencies);

        mBaseSpinner = (Spinner) findViewById(R.id.base_selector);
        mRecyclerView = (RecyclerView) findViewById(android.R.id.list);

        mBaseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mPresenter.onBaseSelected(mCurrencies[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mPresenter.onBaseSelected(null);
            }
        });

        mBaseSpinner.setAdapter(new BaseCurrencyAdapter(this, mCurrencies));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.setView(null);
    }

    @Override
    public void showError() {
        mRecyclerView.setAdapter(null);
        Snackbar.make(findViewById(android.R.id.content),
                R.string.service_error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showRates(Rates rates) {
        mRecyclerView.setAdapter(
                new CurrencyAdapter((String) mBaseSpinner.getSelectedItem(), rates.getRates()));
    }

}
