package com.petagram_.restApi;

import com.petagram_.models.Perfil;
import com.petagram_.restApi.model.FotosResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EndpointsApi {
    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER_1)
    Call<FotosResponse> getRecentMedia1();

    @GET(ConstantesRestApi.URL_GET_DATOS_USUARIO_1)
    Call<Perfil> getPerfil1();

}
