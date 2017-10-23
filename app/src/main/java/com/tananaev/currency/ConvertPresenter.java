package com.tananaev.currency;

import java.util.Locale;

public class ConvertPresenter implements ConvertContract.Presenter {

    private ConvertContract.View mView;

    @Override
    public void setView(ConvertContract.View view) {
        mView = view;
    }

    @Override
    public void onValueChanged(String value, double rate) {
        try {
            double result = Double.parseDouble(value) * rate;
            mView.showResult(String.format(Locale.getDefault(), "%.2f", result));
        } catch (NumberFormatException e) {
            mView.showResult(null);
        }
    }

}
