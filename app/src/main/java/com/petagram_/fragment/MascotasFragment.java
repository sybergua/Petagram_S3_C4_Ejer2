package com.petagram_.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.petagram_.R;
import com.petagram_.adapters.MascotaAdaptador;
import com.petagram_.models.Mascota;

import java.util.ArrayList;

public class MascotasFragment extends Fragment {
    private ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;

    public MascotasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mascotas, container, false);

        listaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(RecyclerView.VERTICAL);
        listaMascotas.setLayoutManager(llm);

        inicializarListaMascotas();
        inicializarAdaptador();

        return v;
    }

    private void inicializarAdaptador(){
        MascotaAdaptador adaptador =new MascotaAdaptador(mascotas, getActivity());
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
}