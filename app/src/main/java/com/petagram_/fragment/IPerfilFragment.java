package com.petagram_.fragment;

import com.petagram_.adapters.PerfilAdaptador;
import com.petagram_.models.Foto;
import com.petagram_.models.Perfil;

import java.util.ArrayList;

public interface IPerfilFragment {
    public PerfilAdaptador crearAdaptador(ArrayList<Foto> fotos);
    public void inicializarAdaptadorRV(PerfilAdaptador adaptador);
    public void generarGridLayout();
    public void inicializarDatosPerfil(Perfil perfil);
}
