package com.petagram_.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.petagram_.models.Mascota;
import com.petagram_.adapters.MascotaAdaptador;
import com.petagram_.R;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.app.ActionBar.DISPLAY_SHOW_CUSTOM;

public class MascotasFavoritas extends AppCompatActivity {
    private Toolbar toolbar;
    private ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mascotas_favoritas);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayOptions(DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setCustomView(R.layout.app_bar);
        }

        ImageButton btnFavorito = (ImageButton) findViewById(R.id.btnFavorito2);
        btnFavorito.setVisibility(View.INVISIBLE);

        ImageButton btnAtras = (ImageButton) findViewById(R.id.btnAtras);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MascotasFavoritas.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        listaMascotas = (RecyclerView) findViewById(R.id.rvFavoritos);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);
        listaMascotas.setLayoutManager(llm);

        inicializarListaMascotas();
        inicializarAdaptador();

    }

    private void inicializarListaMascotas(){
        mascotas = new ArrayList();

        mascotas.add(new Mascota("Molly",R.drawable.molly,0, false));
        mascotas.add(new Mascota("Garfield", R.drawable.garfield,0, true));
        mascotas.add(new Mascota("Perla", R.drawable.perla,0, false));
        mascotas.add(new Mascota("Nemo", R.drawable.nemo,0, true));
        mascotas.add(new Mascota("Dory", R.drawable.dory,0, false));
    }

    private void inicializarAdaptador(){
        MascotaAdaptador adaptador =new MascotaAdaptador(mascotas, this);
        listaMascotas.setAdapter(adaptador);
    }
}
