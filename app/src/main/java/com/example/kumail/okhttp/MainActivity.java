package com.example.kumail.okhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    Button btn;
    RecyclerView rv;
    EditText et;
    ArrayList<GithubUser>githubUsers=new ArrayList<>();
    GitAdapter gitAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.btn);
        rv=findViewById(R.id.rv);
        et=findViewById(R.id.et);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeNetworkCall("https://api.github.com/search/users?q="+et.getText().toString());
            }
        });

         gitAdapter=new GitAdapter(githubUsers,this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(gitAdapter);



    }

    public void makeNetworkCall(String s) {

        OkHttpClient okHttpClient= new OkHttpClient();

        Request request= new Request.Builder().url(s).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String responseBody=response.body().string();
                Gson gson=new Gson();

                GithubResponse githubResponse=gson.fromJson(responseBody,GithubResponse.class);
                githubUsers.addAll(githubResponse.getItems());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        gitAdapter.notifyDataSetChanged();
                    }
                });


            }
        });
    }
}
