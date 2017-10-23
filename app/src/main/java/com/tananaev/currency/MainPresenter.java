package com.tananaev.currency;

import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;

    private FixerService mService;

    public MainPresenter() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.fixer.io/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
        mService = retrofit.create(FixerService.class);
    }

    @Override
    public void setView(MainContract.View view) {
        mView = view;
    }

    @Override
    public void onBaseSelected(String base) {
        mService.latest(base).enqueue(new Callback<Rates>() {
            @Override
            public void onResponse(Call<Rates> call, Response<Rates> response) {
                if (mView != null) {
                    mView.showRates(response.body());
                }
            }

            @Override
            public void onFailure(Call<Rates> call, Throwable t) {
                if (mView != null) {
                    mView.showError();
                }
            }
        });

    }

}
