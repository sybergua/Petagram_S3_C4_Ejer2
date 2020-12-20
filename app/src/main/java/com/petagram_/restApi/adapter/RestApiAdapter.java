package com.petagram_.restApi.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.petagram_.models.Perfil;
import com.petagram_.restApi.ConstantesRestApi;
import com.petagram_.restApi.EndpointsApi;
import com.petagram_.restApi.deserializador.FotosDeserializador;
import com.petagram_.restApi.deserializador.UsuarioDeserializador;
import com.petagram_.restApi.model.FotosResponse;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiAdapter {
    public EndpointsApi establecerConexionRestApiInstagram(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(EndpointsApi.class);
    }

    public Gson construyeGsonDeserializadorMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(FotosResponse.class, new FotosDeserializador());

        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorUsuario(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Perfil.class, new UsuarioDeserializador());

        return gsonBuilder.create();
    }
}
