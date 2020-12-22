package com.petagram_.restApi.adapter;

import com.petagram_.restApi.ConstantesRegistroFireBase;
import com.petagram_.restApi.EndpointRegistroFireBase;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistroFireBaseAdapter {
    public EndpointRegistroFireBase establecerConexion(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRegistroFireBase.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(EndpointRegistroFireBase.class);
    }
}
