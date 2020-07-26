package com.example.expensemanagerengine.activity.editor;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.expensemanagerengine.api.ApiClient;
import com.example.expensemanagerengine.api.ApiInterface;
import com.example.expensemanagerengine.model.Income;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditPresenter {

    private  EditView view;
    public  EditPresenter(EditView view){
        this.view= view;
    }
        void saveIncome(final String name, final String amuont, final String category, final  String note, final  int color) {
        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient()
                .create(ApiInterface.class);
        Call<Income> call = apiInterface.saveIncome(name,amuont,category,note,color);

        call.enqueue(new Callback<Income>() {
            @Override
            public void onResponse(@NonNull Call<Income> call, @NonNull Response<Income> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null){
                    Boolean success = response.body().getSuccess();
                    if (success){
                        view.onAddSuccess(response.body().getMessage());

                    }else
                    {
                        view.onAddError(response.body().getMessage());

                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<Income> call, @NonNull Throwable t) {
               view.hideProgress();
                view.onAddError(t.getLocalizedMessage());

            }
        });
    }
}
