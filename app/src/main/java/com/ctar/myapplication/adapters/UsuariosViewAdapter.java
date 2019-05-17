package com.ctar.myapplication.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.ctar.myapplication.R;
import com.ctar.myapplication.entities.Usuarios;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class UsuariosViewHolder extends RecyclerView.ViewHolder{

    public TextView tvname;
    public TextView tvcomments;
    public TextView tvbirthDate;
    public ImageView iv;

    public UsuariosViewHolder(View itemItem, final UsuariosViewAdapter.OnItemClickListener listener){
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

public class UsuariosViewAdapter extends RecyclerView.Adapter<UsuariosViewHolder>  {

    private ArrayList<Usuarios> usuarios;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public UsuariosViewAdapter(ArrayList<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }

    @NonNull
    @Override
    public UsuariosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.customer, viewGroup, false);
        return new UsuariosViewHolder(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuariosViewHolder customerViewHolder, int i) {
        //customerViewHolder.iv.setImageResource( usuarios.get(i).getId());
        customerViewHolder.tvcomments.setText(usuarios.get(i).getUser_Name());
        customerViewHolder.tvname.setText(usuarios.get(i).getNombres() + " " + usuarios.get(i).getApellidos() );
        customerViewHolder.tvbirthDate.setText(usuarios.get(i).getEmail());
        //Log.i("LOGCAT", usuarios.get(i).getUser_Name());

        if(usuarios.get(i).getFoto_Perfil() != null && !usuarios.get(i).getFoto_Perfil().isEmpty()){
            Picasso.get().load(usuarios.get(i).getFoto_Perfil())

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
        return this.usuarios.size();
    }
}
