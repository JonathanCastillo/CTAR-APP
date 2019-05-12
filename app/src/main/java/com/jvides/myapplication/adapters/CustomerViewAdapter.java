package com.jvides.myapplication.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
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

    public CustomerViewHolder(View itemItem, final CustomerViewAdapter.OnItemClickListener listener){
        super(itemItem);

        iv = (ImageView) itemItem.findViewById(R.id.ivFoto);
        tvname = (TextView) itemItem.findViewById(R.id.name);
        tvcomments = (TextView) itemItem.findViewById(R.id.comments);
        tvbirthDate = (TextView) itemItem.findViewById(R.id.birthDate);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(listener != null){
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    listener.onItemClick(position);
                }
            }
            }
        });

    }
}

public class CustomerViewAdapter extends RecyclerView.Adapter<CustomerViewHolder>  {

    private ArrayList<NaturalPerson> naturalPersons;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public CustomerViewAdapter(ArrayList<NaturalPerson> naturalPersons) {
        this.naturalPersons = naturalPersons;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.customer, viewGroup, false);
        return new CustomerViewHolder(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder customerViewHolder, int i) {
        //customerViewHolder.iv.setImageResource( naturalPersons.get(i).getId());
        customerViewHolder.tvcomments.setText(naturalPersons.get(i).getComments());
        customerViewHolder.tvname.setText(naturalPersons.get(i).getFirstName() + " " + naturalPersons.get(i).getLastName() );
        customerViewHolder.tvbirthDate.setText(naturalPersons.get(i).getBirthDate());
        Log.i("LOGCAT", naturalPersons.get(i).getBirthDate());

        if(naturalPersons.get(i).getPhotoUrl() != null && !naturalPersons.get(i).getPhotoUrl().isEmpty()){
            Picasso.get().load(naturalPersons.get(i).getPhotoUrl())

                    .fit()
                    // To prevent fade animation
                    .noFade()
                    .into(customerViewHolder.iv);
        }else{
            //customerViewHolder.iv.setImageDrawable(ContextCompat.getDrawable(F.context,R.drawable.default_placeholder));
        }




        //customerViewHolder.iv.set
    }

    @Override
    public int getItemCount() {
        return this.naturalPersons.size();
    }
}
