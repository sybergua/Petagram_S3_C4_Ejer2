package com.petagram_.presenter;

import android.content.Context;

import com.petagram_.activities.IMascotasFavoritasView;
import com.petagram_.db.ConstructorBD;
import com.petagram_.models.Mascota;

import java.util.ArrayList;

public class MascotasFavoritasPresenter implements IMascotasFavoritasPresenter{
    private IMascotasFavoritasView iMascotasFavoritasView;
    private Context context;
    private ConstructorBD constructorBD;
    private ArrayList<Mascota> mascotas;

    public MascotasFavoritasPresenter(IMascotasFavoritasView iMascotasFavoritasView, Context context){
        this.iMascotasFavoritasView = iMascotasFavoritasView;
        this.context = context;
        obtenerMascotasFavoritasBaseDatos();
    }

    @Override
    public void obtenerMascotasFavoritasBaseDatos() {
        constructorBD = new ConstructorBD(context);
        mascotas = constructorBD.obtenerMascotasFavoritas();
        mostrarMascotasFavoritasRV();
    }

    @Override
    public void mostrarMascotasFavoritasRV() {
        iMascotasFavoritasView.inicializarAdaptadorRV(iMascotasFavoritasView.crearAdaptador(mascotas));
        iMascotasFavoritasView.generarLinearLayoutVertical();
    }
}
