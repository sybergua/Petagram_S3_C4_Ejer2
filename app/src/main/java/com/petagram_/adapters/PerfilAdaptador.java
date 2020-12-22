package com.petagram_.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.petagram_.activities.DetalleFoto;
import com.petagram_.R;
import com.petagram_.models.Foto;
import com.petagram_.models.Perfil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PerfilAdaptador extends RecyclerView.Adapter<PerfilAdaptador.PerfilViewHolder>{
    private ArrayList<Foto> fotos;
    private Activity activity;
    private boolean sexo;
    private Perfil perfil;
    private String cuenta;


    public PerfilAdaptador(ArrayList<Foto> fotos, Activity activity, boolean sexo, String cuenta){
        this.activity = activity;
        this.fotos = fotos;
        this.sexo = sexo;
        this.cuenta = cuenta;
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
        Picasso.get()
                .load(foto.getFoto())
                .placeholder(R.drawable.boxer)
                .into(perfilViewHolder.imgFotoPerfil);
        perfilViewHolder.tvRaitingPerfil.setText("" + foto.getRaiting());
        perfilViewHolder.tvCantComentariosPerfil.setText("" + foto.getComments_count());

        if(sexo){
            perfilViewHolder.imgFotoPerfil.setBackgroundResource(R.color.celeste);
        }else{
            perfilViewHolder.imgFotoPerfil.setBackgroundResource(R.color.rosado);
        }

        perfilViewHolder.imgFotoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetalleFoto.class);
                intent.putExtra("url", foto.getFoto());
                intent.putExtra("like", foto.getRaiting());
                intent.putExtra("comments_count", foto.getComments_count());
                intent.putExtra("id", foto.getId());
                intent.putExtra("cuenta", cuenta);
                //intent.putExtra("email", contacto.getEmail());
                activity.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return fotos.size();
    }

    public static class PerfilViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgFotoPerfil;
        private TextView tvRaitingPerfil;
        private TextView tvCantComentariosPerfil;

        public PerfilViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFotoPerfil = (ImageView) itemView.findViewById(R.id.imgFotoPerfil);
            tvRaitingPerfil = (TextView) itemView.findViewById(R.id.tvRaitingPerfil);
            tvCantComentariosPerfil = (TextView) itemView.findViewById(R.id.tvCantComentariosPerfil);
        }
    }
}
