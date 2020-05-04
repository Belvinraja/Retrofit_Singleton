package com.example.retrofit_2;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiController {

    private static ApiController apiController_instance=null;

    Retrofit retrofit;

    private ApiController()
    {

    }
    public void assign_instance(String url)
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiController getInstance()
    {
        if(apiController_instance == null)
        {
            apiController_instance=new ApiController();
        }
        return apiController_instance;
    }
}
