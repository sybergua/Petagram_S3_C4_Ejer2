package com.petagram_.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.petagram_.R;
import com.petagram_.adapters.MascotaAdaptador;
import com.petagram_.adapters.PerfilAdaptador;
import com.petagram_.models.Foto;
import com.petagram_.models.Mascota;

import java.util.ArrayList;

public class PerfilFragment extends Fragment {
    private ArrayList<Foto> fotos;
    private RecyclerView listaFotos;

    public PerfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        listaFotos = (RecyclerView) v.findViewById(R.id.rvFotosPerfil);

        //LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        //llm.setOrientation(RecyclerView.VERTICAL);
        listaFotos.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        inicializarListaFotos();
        inicializarAdaptador();

        return v;
    }

    private void inicializarListaFotos(){
        fotos = new ArrayList();

        fotos.add(new Foto(R.drawable.boxer, 2));
        fotos.add(new Foto(R.drawable.nemo, 4));
        fotos.add(new Foto(R.drawable.boxer, 1));
        fotos.add(new Foto(R.drawable.nemo, 3));
        fotos.add(new Foto(R.drawable.boxer, 8));
        fotos.add(new Foto(R.drawable.nemo, 9));

    }

    private void inicializarAdaptador(){
        PerfilAdaptador adaptador =new PerfilAdaptador(fotos, getActivity(), true);
        listaFotos.setAdapter(adaptador);
    }
}