package com.c1ph3r.myapplication.ui.main;


import retrofit2.Call;
import retrofit2.http.GET;

public interface MyApi  {
    @GET("products")
    Call<Model> getData();
}
