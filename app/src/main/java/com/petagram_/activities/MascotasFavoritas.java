package com.petagram_.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.petagram_.db.ConstructorMascotasFavoritas;
import com.petagram_.models.Mascota;
import com.petagram_.adapters.MascotaAdaptador;
import com.petagram_.R;
import com.petagram_.presenter.IMascotasFavoritasPresenter;
import com.petagram_.presenter.MascotasFavoritasPresenter;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.app.ActionBar.DISPLAY_SHOW_CUSTOM;

public class MascotasFavoritas extends AppCompatActivity implements IMascotasFavoritasView {
    private Toolbar toolbar;
    private RecyclerView listaMascotas;
    private IMascotasFavoritasPresenter presenter;

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

        presenter = new MascotasFavoritasPresenter(this, getApplicationContext());


    }

    /*private void inicializarListaMascotas(){
        ConstructorMascotasFavoritas constructorMascotasFavoritas = new ConstructorMascotasFavoritas(getApplicationContext());
        mascotas = constructorMascotasFavoritas.obtenerMascotasFavoritas();
    }

    private void inicializarAdaptador(){
        MascotaAdaptador adaptador =new MascotaAdaptador(mascotas, this);
        listaMascotas.setAdapter(adaptador);
    }*/

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);
        listaMascotas.setLayoutManager(llm);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, MascotasFavoritas.this);
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        listaMascotas.setAdapter(adaptador);
    }
}
