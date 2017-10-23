package com.tananaev.currency;

public class MainContract {

    public interface View {
        void showError();
        void showRates(Rates rates);
    }

    public interface Presenter {
        void setView(View view);
        void onBaseSelected(String currency);
    }

}
