package com.petagram_;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import static android.app.ActionBar.*;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayOptions(DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.app_bar);

        ImageButton btnAtras = (ImageButton) findViewById(R.id.btnAtras);
        btnAtras.setVisibility(View.INVISIBLE);

        listaMascotas = (RecyclerView) findViewById(R.id.rvMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);
        listaMascotas.setLayoutManager(llm);

        inicializarListaMascotas();
        inicializarAdaptador();

        ImageButton btnFavorito = (ImageButton) findViewById(R.id.btnFavorito2);
        btnFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irFavoritos();
            }
        });
    }

    private void inicializarAdaptador(){
        MascotaAdaptador adaptador =new MascotaAdaptador(mascotas, this);
        listaMascotas.setAdapter(adaptador);
    }

    private void inicializarListaMascotas(){
        mascotas = new ArrayList();

        mascotas.add(new Mascota("Molly",R.drawable.molly,0,false));
        mascotas.add(new Mascota("Boxer", R.drawable.boxer,0,true));
        mascotas.add(new Mascota("Garfield", R.drawable.garfield,0,true));
        mascotas.add(new Mascota("Blu", R.drawable.blu,0, true));
        mascotas.add(new Mascota("Marie", R.drawable.marie,0,false));
        mascotas.add(new Mascota("Perla", R.drawable.perla,0, false));
        mascotas.add(new Mascota("Nemo", R.drawable.nemo,0, true));
        mascotas.add(new Mascota("Dory", R.drawable.dory,0, false));
    }

    public void irFavoritos(){
        Intent intent = new Intent(MainActivity.this, MascotasFavoritas.class);
        startActivity(intent);
        finish();


    }
}