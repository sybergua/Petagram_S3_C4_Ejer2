package com.petagram_.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.petagram_.R;
import com.petagram_.models.Foto;
import com.petagram_.models.Mascota;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PerfilAdaptador extends RecyclerView.Adapter<PerfilAdaptador.PerfilViewHolder>{
    private ArrayList<Foto> fotos;
    private Activity activity;
    private boolean sexo;


    public PerfilAdaptador(ArrayList<Foto> fotos, Activity activity, boolean sexo){
        this.activity = activity;
        this.fotos = fotos;
        this.sexo = sexo;
    }

    @NonNull
    @Override
    public PerfilViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_perfil, parent, false);
        return new PerfilAdaptador.PerfilViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PerfilViewHolder perfilViewHolder, int position) {
        final Foto foto = fotos.get(position);
        perfilViewHolder.imgFotoPerfil.setImageResource(foto.getFoto());
        perfilViewHolder.tvRaitingPerfil.setText("" + foto.getRaiting());

        if(sexo){
            perfilViewHolder.imgFotoPerfil.setBackgroundResource(R.color.celeste);
        }else{
            perfilViewHolder.imgFotoPerfil.setBackgroundResource(R.color.rosado);
        }
    }

    @Override
    public int getItemCount() {
        return fotos.size();
    }

    public static class PerfilViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgFotoPerfil;
        private TextView tvRaitingPerfil;

        public PerfilViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFotoPerfil = (ImageView) itemView.findViewById(R.id.imgFotoPerfil);
            tvRaitingPerfil = (TextView) itemView.findViewById(R.id.tvRaitingPerfil);
        }
    }
}
