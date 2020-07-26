package com.example.expensemanagerengine.activity.editor;

public interface EditView {

    void showProgress();
    void hideProgress();
    void onAddSuccess(String message);
    void onAddError(String message);

}
