package com.example.expensemanagerengine.activity.editor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.expensemanagerengine.R;
import com.example.expensemanagerengine.api.ApiClient;
import com.example.expensemanagerengine.api.ApiInterface;
import com.example.expensemanagerengine.model.Income;
import com.thebluealliance.spectrum.SpectrumPalette;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditActivity extends AppCompatActivity implements EditView {
    EditText et_name,et_amount,et_category,et_note;
    ProgressDialog progressDialog;
    SpectrumPalette spectrumPalette;

    ApiInterface apiInterface;
    int color;

    EditPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        et_name =findViewById(R.id.name);
        et_amount =findViewById(R.id.amount);
        et_category =findViewById(R.id.category);
        et_note =findViewById(R.id.note);
        spectrumPalette =findViewById(R.id.colortool);

        spectrumPalette.setOnColorSelectedListener(
                clr -> color = clr

        );
        //Main color
        spectrumPalette.setSelectedColor(getResources().getColor(R.color.white));
        color = getResources().getColor(R.color.white);


        progressDialog= new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");

        presenter = new EditPresenter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case  R.id.save:
                //Save
                String name= et_name.getText().toString().trim();
                String amount= et_amount.getText().toString().trim();
                String category= et_category.getText().toString().trim();
                String note= et_note.getText().toString().trim();
                int color= this.color;
                if (name.isEmpty()){
                    et_name.setError("Please enter a name");
                }else if (amount.isEmpty()){
                    et_amount.setError("Please enter amount");
                }else if (category.isEmpty()){
                    et_category.setError("Please enter a category");
                }else if (note.isEmpty()){
                    et_note.setError("Please enter a note");
                }else {
                   presenter.saveIncome(name, amount, category, note, color);
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }


    @Override
    public void showProgress() {
        progressDialog.show();

    }

    @Override
    public void hideProgress() {
        progressDialog.hide();

    }

    @Override
    public void onAddSuccess(String message) {
        Toast.makeText(EditActivity.this,
               message,
                Toast.LENGTH_SHORT).show();
        finish();

    }

    @Override
    public void onAddError(String message) {
        Toast.makeText(EditActivity.this,
                message,
                Toast.LENGTH_SHORT).show();
        finish();

    }
}