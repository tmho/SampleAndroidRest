package com.example.sampleapplication.api;


import com.example.sampleapplication.model.Request;
import com.example.sampleapplication.model.Response;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface SaySomethingService {
    @POST("/sayHello")
    void login(@Body Request request, Callback<Response> cb);
}
