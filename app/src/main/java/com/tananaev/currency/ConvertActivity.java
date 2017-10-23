package com.tananaev.currency;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class ConvertActivity extends AppCompatActivity implements ConvertContract.View {

    public static final String KEY_BASE = "base";
    public static final String KEY_CURRENCY = "currency";
    public static final String KEY_RATE = "rate";

    private ConvertContract.Presenter mPresenter;

    private EditText mAmount;
    private TextView mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);

        mPresenter = new ConvertPresenter();
        mPresenter.setView(this);

        mAmount = (EditText) findViewById(R.id.amount);
        mResult = (TextView) findViewById(R.id.result);

        ((TextView) findViewById(R.id.fromCurrency)).setText(getIntent().getStringExtra(KEY_BASE));
        ((TextView) findViewById(R.id.toCurrency)).setText(getIntent().getStringExtra(KEY_CURRENCY));

        mAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPresenter.onValueChanged(s.toString(), getIntent().getDoubleExtra(KEY_RATE, 0));
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public void showResult(String result) {
        mResult.setText(result);
    }

}
