package com.manuni.covidtrack_19.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiData {
    @GET("countries")
    Call<List<MainObjectData>> getCountryData();
}
