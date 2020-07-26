package com.example.expensemanagerengine.activity.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensemanagerengine.R;
import com.example.expensemanagerengine.model.Income;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.RecyclerViewAdapter> {
    private Context context;
    private  List<Income> incomes;
    private ItemClickListener itemClickListener;

    public MainAdapter(Context context, List<Income> incomes, ItemClickListener itemClickListener) {
        this.context = context;
        this.incomes = incomes;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_income,parent,false);
        return new RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter holder, int position) {
        Income income = incomes.get(position);
        holder.t_name.setText(income.getName());
        holder.t_amount.setText(income.getAmount());
        holder.t_category.setText(income.getCategory());
        holder.t_note.setText(income.getNote());
        holder.t_date.setText(income.getDate());
        holder.card_item.setCardBackgroundColor(income.getColor());
    }

    @Override
    public int getItemCount() {
        return incomes.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {


         TextView t_name,t_amount,t_category,t_note,t_date;
         CardView card_item;
         ItemClickListener itemClickListener;

         RecyclerViewAdapter(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);


            t_name = itemView.findViewById(R.id.name);
            t_amount = itemView.findViewById(R.id.amount);
            t_category = itemView.findViewById(R.id.category);
            t_note = itemView.findViewById(R.id.note);
            t_date = itemView.findViewById(R.id.date);
            card_item = itemView.findViewById(R.id.card_item);

             this.itemClickListener = itemClickListener;
            card_item.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
             itemClickListener.onItemClick(view, getAdapterPosition());

        }
    }
    public  interface ItemClickListener{
        void onItemClick(View view, int position);
    }
}
