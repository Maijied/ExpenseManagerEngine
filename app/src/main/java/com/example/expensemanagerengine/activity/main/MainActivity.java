package com.example.expensemanagerengine.activity.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.expensemanagerengine.R;
import com.example.expensemanagerengine.activity.editor.EditActivity;
import com.example.expensemanagerengine.model.Income;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {
    FloatingActionButton fab;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;

    MainPresenter presenter;
    MainAdapter adapter;
    MainAdapter.ItemClickListener itemClickListener;

    List<Income> income;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab = findViewById(R.id.add);
        swipeRefresh = findViewById(R.id.swipe_refresh);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


       fab.setOnClickListener(view ->
               startActivity(new Intent(this, EditActivity.class))
               );

       presenter = new MainPresenter( this);
       presenter.getData();

       swipeRefresh.setOnRefreshListener(
               () -> presenter.getData()
       );
       itemClickListener = ((view, position) -> {
          String name = income.get(position).getName();
          Toast.makeText(this,name,Toast.LENGTH_SHORT).show();
       });

    }

    @Override
    public void showLoading() {
        swipeRefresh.setRefreshing(true);

    }

    @Override
    public void hideLoading() {
        swipeRefresh.setRefreshing(false);

    }

    @Override
    public void onGetResult(List<Income> incomes) {
        adapter = new MainAdapter(this, incomes, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        income = incomes;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this,"message",Toast.LENGTH_SHORT).show();

    }
}