package com.example.expensemanagerengine.activity.main;

import com.example.expensemanagerengine.model.Income;

import java.util.List;

public interface MainView {
    void showLoading();
    void  hideLoading();
    void onGetResult(List<Income> incomes);
    void onErrorLoading(String message);
}
