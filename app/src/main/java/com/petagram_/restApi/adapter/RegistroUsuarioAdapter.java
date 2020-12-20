package com.petagram_.restApi.adapter;

import com.petagram_.restApi.ConstantesRegistroUsuario;
import com.petagram_.restApi.EndpointRegistroUsuario;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistroUsuarioAdapter {
    public EndpointRegistroUsuario establecerConexion(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRegistroUsuario.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(EndpointRegistroUsuario.class);
    }
}
