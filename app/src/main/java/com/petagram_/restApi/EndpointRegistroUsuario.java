package com.petagram_.restApi;

import com.petagram_.restApi.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface EndpointRegistroUsuario {
    @FormUrlEncoded
    @POST(ConstantesRegistroUsuario.KEY_POST_ID_TOKEN)
    Call<UsuarioResponse> registrarUsuario(@Field("idDispositivo") String idDispositivo, @Field("usuarioInstagram") String usuarioInstagram);
}
