package com.jvides.myapplication.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.jvides.myapplication.R;
import com.jvides.myapplication.entities.NaturalPerson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class CustomerViewHolder extends RecyclerView.ViewHolder{

    public TextView tvname;
    public TextView tvcomments;
    public TextView tvbirthDate;
    public ImageView iv;

    public CustomerViewHolder(View itemItem){
        super(itemItem);

        iv = (ImageView) itemItem.findViewById(R.id.iv);
        tvname = (TextView) itemItem.findViewById(R.id.name);
        tvcomments = (TextView) itemItem.findViewById(R.id.comments);
        tvbirthDate = (TextView) itemItem.findViewById(R.id.birthDate);


    }
}

public class CustomerViewAdapter extends RecyclerView.Adapter<CustomerViewHolder>  {

    private ArrayList<NaturalPerson> naturalPersons;

    public CustomerViewAdapter(ArrayList<NaturalPerson> naturalPersons) {
        this.naturalPersons = naturalPersons;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.customer, viewGroup, false);
        return new CustomerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder customerViewHolder, int i) {
        //customerViewHolder.iv.setImageResource( naturalPersons.get(i).getId());
        customerViewHolder.tvcomments.setText(naturalPersons.get(i).getComments());
        customerViewHolder.tvname.setText(naturalPersons.get(i).getFirstName());
        customerViewHolder.tvbirthDate.setText(naturalPersons.get(i).getBirthDate().toString());
    }

    @Override
    public int getItemCount() {
        return this.naturalPersons.size();
    }
}
