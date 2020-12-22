package com.petagram_.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.petagram_.R;
import com.petagram_.adapters.MascotaAdaptador;
import com.petagram_.adapters.PerfilAdaptador;
import com.petagram_.models.Foto;
import com.petagram_.models.Mascota;
import com.petagram_.models.Perfil;
import com.petagram_.presenter.IPerfilFragmentPresenter;
import com.petagram_.presenter.PerfilFragmentPresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PerfilFragment extends Fragment implements IPerfilFragment{
    private ArrayList<Foto> fotos;
    private RecyclerView listaFotos;
    private IPerfilFragmentPresenter presenter;
    private Perfil perfil;
    private View v;
    private String cuenta;

    public PerfilFragment(String cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_perfil, container, false);

        listaFotos = (RecyclerView) v.findViewById(R.id.rvFotosPerfil);

        //LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        //llm.setOrientation(RecyclerView.VERTICAL);

        if(!cuenta.isEmpty()){
            presenter = new PerfilFragmentPresenter(this, getContext(), cuenta);
        }

        return v;
    }

    @Override
    public PerfilAdaptador crearAdaptador(ArrayList<Foto> fotos) {
        PerfilAdaptador adaptador =new PerfilAdaptador(fotos, getActivity(), true, cuenta);
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(PerfilAdaptador adaptador) {
        listaFotos.setAdapter(adaptador);
    }

    @Override
    public void generarGridLayout() {
        listaFotos.setLayoutManager(new GridLayoutManager(getActivity(), 3));
    }

    @Override
    public void inicializarDatosPerfil(Perfil perfil) {
        this.perfil = perfil;

        CircularImageView imgPerfil = (CircularImageView) v.findViewById(R.id.imgPerfil);

        Picasso.get()
                .load(perfil.getProfile_picture_url())
                .placeholder(R.drawable.boxer)
                .into(imgPerfil);

        EditText etPerfil = (EditText) v.findViewById(R.id.etPerfil);
        etPerfil.setText(perfil.getName());
    }

}