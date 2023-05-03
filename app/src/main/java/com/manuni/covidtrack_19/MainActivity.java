package com.manuni.covidtrack_19;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.manuni.covidtrack_19.api.ApiClient;
import com.manuni.covidtrack_19.api.ApiData;
import com.manuni.covidtrack_19.api.MainObjectData;
import com.manuni.covidtrack_19.databinding.ActivityMainBinding;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private Retrofit retrofit;
    private List<MainObjectData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        list = new ArrayList<>();

        retrofit = ApiClient.getRETROFIT();
       ApiData apiData = retrofit.create(ApiData.class);
        Call<List<MainObjectData>> objectDataCall = apiData.getCountryData();
        objectDataCall.enqueue(new Callback<List<MainObjectData>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<List<MainObjectData>> call, Response<List<MainObjectData>> response) {
                list.addAll(response.body());
                for (int i= 0; i<list.size(); i++){
                    if (list.get(i).getCountry().equals("Bangladesh")){
                        int confirm = Integer.parseInt(list.get(i).getCases());
                        int active = Integer.parseInt(list.get(i).getActive());

                        binding.totalConfirm.setText(confirm);
                        binding.totalActive.setText(active);
                    }
                }
               /* for (MainObjectData mainObjectData: response.body()){
                    if (mainObjectData.getCountry().equals("Bangladesh")){
                        int confirm = mainObjectData.getCases();
                        int active = mainObjectData.getActive();
                        int recovered = mainObjectData.getRecovered();
                        int deaths = mainObjectData.getDeaths();
                        int tests = mainObjectData.getTests();

                        //belows for today cases
                        int todayConfirm = mainObjectData.getTodayCases();
                        int todayRecovered = mainObjectData.getTodayRecovered();
                        int todayDeaths = mainObjectData.getTodayDeaths();

                        binding.todayConfirm.setText(NumberFormat.getInstance().format(confirm+""));
                        binding.totalActive.setText(NumberFormat.getInstance().format(active+""));
                        binding.totalRecovered.setText(NumberFormat.getInstance().format(recovered+""));
                        binding.totalDeath.setText(NumberFormat.getInstance().format(deaths+""));
                        binding.totalTests.setText(NumberFormat.getInstance().format(tests+""));

                        binding.todayConfirm.setText(todayConfirm+"");
                        binding.todayRecovered.setText(todayRecovered+"");
                        binding.todayDeath.setText(todayDeaths+"");
                    }
                }*/
            }

            @Override
            public void onFailure(Call<List<MainObjectData>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}