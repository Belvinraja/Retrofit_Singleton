package com.example.retrofit_2;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface jsonHolder {

    @GET("posts")
    Call<List<Post>> getPost();
}
