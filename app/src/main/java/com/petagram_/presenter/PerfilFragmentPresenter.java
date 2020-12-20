package com.petagram_.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.petagram_.fragment.IPerfilFragment;
import com.petagram_.models.Foto;
import com.petagram_.models.Perfil;
import com.petagram_.restApi.ConstantesRestApi;
import com.petagram_.restApi.EndpointsApi;
import com.petagram_.restApi.adapter.RestApiAdapter;
import com.petagram_.restApi.model.FotosResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilFragmentPresenter implements IPerfilFragmentPresenter {
    private IPerfilFragment iPerfilFragment;
    private Context context;
    private ArrayList<Foto> fotos;
    private Perfil perfil;
    private String cuenta;

    public PerfilFragmentPresenter(IPerfilFragment iPerfilFragment, Context context, String cuenta){
        this.iPerfilFragment = iPerfilFragment;
        this.context = context;
        this.cuenta = cuenta;
        obtenerDatosPerfil();
        obtenerFotos();
    }

    @Override
    public void mostrarFotosRV() {
        iPerfilFragment.inicializarAdaptadorRV(iPerfilFragment.crearAdaptador(fotos));
        iPerfilFragment.generarGridLayout();
    }

    @Override
    public void obtenerFotos() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<FotosResponse> fotosResponseCall = ConstantesRestApi.metodoMedia(cuenta, endpointsApi);
        fotosResponseCall.enqueue(new Callback<FotosResponse>() {
            @Override
            public void onResponse(Call<FotosResponse> call, Response<FotosResponse> response) {
                FotosResponse fotosResponse = response.body();
                fotos = fotosResponse.getFotos();
                mostrarFotosRV();
            }

            @Override
            public void onFailure(Call<FotosResponse> call, Throwable t) {
                Toast.makeText(context, "No se establecio conexion, intenta de nuevo", Toast.LENGTH_LONG).show();
                Log.e("Fallo conexion fotos", t.toString());
            }
        });
    }

    @Override
    public void obtenerDatosPerfil() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonUsuario = restApiAdapter.construyeGsonDeserializadorUsuario();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonUsuario);
        Call<Perfil> usuario = ConstantesRestApi.metodoPerfil(cuenta, endpointsApi);
        usuario.enqueue(new Callback<Perfil>() {
            @Override
            public void onResponse(Call<Perfil> call, Response<Perfil> response) {
                perfil = response.body();
                mostrarDatosPerfil();
            }

            @Override
            public void onFailure(Call<Perfil> call, Throwable t) {
                Toast.makeText(context, "No se establecio conexion, intenta de nuevo", Toast.LENGTH_LONG).show();
                Log.e("Fallo conexion Usuario ", t.toString());
            }
        });
    }

    @Override
    public void mostrarDatosPerfil() {
        iPerfilFragment.inicializarDatosPerfil(perfil);
    }
}
