package com.petagram_.activities;

import com.petagram_.adapters.MascotaAdaptador;
import com.petagram_.models.Mascota;

import java.util.ArrayList;

public interface IMascotasFavoritasView {
    public void generarLinearLayoutVertical();
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> Mascotas);
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);
}
