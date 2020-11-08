package com.petagram_.presenter;

import android.content.Context;

import com.petagram_.activities.IMascotasFavoritasView;
import com.petagram_.db.ConstructorMascotasFavoritas;
import com.petagram_.models.Mascota;

import java.util.ArrayList;

public class MascotasFavoritasPresenter implements IMascotasFavoritasPresenter{
    private IMascotasFavoritasView iMascotasFavoritasView;
    private Context context;
    private ConstructorMascotasFavoritas constructorMascotasFavoritas;
    private ArrayList<Mascota> mascotas;

    public MascotasFavoritasPresenter(IMascotasFavoritasView iMascotasFavoritasView, Context context){
        this.iMascotasFavoritasView = iMascotasFavoritasView;
        this.context = context;
        obtenerMascotasFavoritasBaseDatos();
    }

    @Override
    public void obtenerMascotasFavoritasBaseDatos() {
        constructorMascotasFavoritas = new ConstructorMascotasFavoritas(context);
        mascotas =constructorMascotasFavoritas.obtenerMascotasFavoritas();
        mostrarMascotasFavoritasRV();
    }

    @Override
    public void mostrarMascotasFavoritasRV() {
        iMascotasFavoritasView.inicializarAdaptadorRV(iMascotasFavoritasView.crearAdaptador(mascotas));
        iMascotasFavoritasView.generarLinearLayoutVertical();
    }
}
