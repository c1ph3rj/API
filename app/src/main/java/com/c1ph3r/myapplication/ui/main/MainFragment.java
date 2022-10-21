package com.c1ph3r.myapplication.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.c1ph3r.myapplication.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainFragment extends Fragment{
    Button button;

    private MainViewModel mViewModel;


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }


    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if(view!=null){
            button = view.findViewById(R.id.button);
            Retrofit retrofit = new Retrofit.Builder().baseUrl("https://dummyjson.com/").addConverterFactory(GsonConverterFactory.create()).build();

            MyApi apiCall = retrofit.create(MyApi.class);
            Call<Model> call = apiCall.getData();

            TextView textView = view.findViewById(R.id.textView);

            call.enqueue(new Callback<Model>() {
                @Override
                public void onResponse(@NonNull Call<Model> call, Response<Model> response) {
                    if(response.code()!=200){
                        System.out.println("Error.");
                    }
                    if(response.code()==200)
                        System.out.println("textView.getText()");

                }

                @Override
                public void onFailure(Call<Model> call, Throwable t) {
                    System.out.println("Error 404.");
                }
            });


        }
    }
}