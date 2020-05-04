package com.example.retrofit_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.tv);

        ApiController api=ApiController.getInstance();
        api.assign_instance("https://jsonplaceholder.typicode.com/");
        Retrofit retrofit=api.retrofit;

        jsonHolder jsonholder = retrofit.create(jsonHolder.class);

        Call<List<Post>> call=jsonholder.getPost();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if(!response.isSuccessful())
                {
                    tv.setText("Code: "+response.code());
                    return;
                }

                List<Post> posts = response.body();

                for(Post post : posts)
                {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";

                    tv.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                tv.setText(t.getMessage());

            }
        });
    }
}
