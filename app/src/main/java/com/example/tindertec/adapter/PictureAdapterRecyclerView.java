package com.example.tindertec.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tindertec.R;
import com.example.tindertec.models.Usuario;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PictureAdapterRecyclerView extends  RecyclerView.Adapter<PictureAdapterRecyclerView.PictureViewHolder> {

    private List<Usuario> item;

    public PictureAdapterRecyclerView(List<Usuario> item) {
        this.item=item;
    }

    @NonNull
    @Override
    public PictureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuario,parent,false);

        return new PictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PictureViewHolder holder, int position) {
        Usuario items =item.get(position);
        holder.usuarioCarrera.setText(items.getNom_carrera());
        holder.usuarioNamecard.setText(items.getNombres());
        Picasso.get().load(items.getFoto1()).into(holder.pictureCard );



    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public static class PictureViewHolder extends RecyclerView.ViewHolder{
        private ImageView pictureCard;
        private TextView usuarioNamecard;
        private TextView usuarioCarrera;

        public PictureViewHolder(@NonNull View itemView) {
            super(itemView);

            pictureCard =(ImageView) itemView.findViewById(R.id.pictureCard);
            usuarioNamecard=(TextView) itemView.findViewById(R.id.usuarioNamecard);
            usuarioCarrera=(TextView) itemView.findViewById(R.id.usuarioCarrera);
        }
    }



}
