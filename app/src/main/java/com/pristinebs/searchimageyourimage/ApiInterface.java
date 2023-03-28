package com.pristinebs.searchimageyourimage;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/search.json")
    Call<ImgSearchModel> getImage(@Query("engine") String engine, @Query("url") String url, @Query("api_key") String api_key);


}
