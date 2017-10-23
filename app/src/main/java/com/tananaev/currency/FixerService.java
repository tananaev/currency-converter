package com.tananaev.currency;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FixerService {

    @GET("latest")
    Call<Rates> latest(@Query("base") String base);

}
