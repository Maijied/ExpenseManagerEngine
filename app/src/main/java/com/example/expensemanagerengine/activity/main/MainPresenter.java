package com.example.expensemanagerengine.activity.main;

import androidx.annotation.NonNull;

import com.example.expensemanagerengine.api.ApiClient;
import com.example.expensemanagerengine.api.ApiInterface;
import com.example.expensemanagerengine.model.Income;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter {
    private MainView view;

    public MainPresenter(MainView view) {
        this.view = view;
    }
    void getData(){
        view.showLoading();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Income>> call = apiInterface.getIncomes();
        call.enqueue(new Callback<List<Income>>() {
            @Override
            public void onResponse(@NonNull Call<List<Income>> call, @NonNull Response<List<Income>> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null){
                    view.onGetResult(response.body());
                }

            }

            @Override
            public void onFailure( @NonNull Call<List<Income>> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
}
