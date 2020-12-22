package com.petagram_.restApi;

import com.petagram_.restApi.model.RegistroComentarioResponse;
import com.petagram_.restApi.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface EndpointRegistroFireBase {
    @FormUrlEncoded
    @POST(ConstantesRegistroFireBase.KEY_POST_ID_TOKEN)
    Call<UsuarioResponse> registrarUsuario(@Field("idDispositivo") String idDispositivo, @Field("usuarioInstagram") String usuarioInstagram);

    @FormUrlEncoded
    @POST(ConstantesRegistroFireBase.KEY_POST_COMENTARIO)
    Call<RegistroComentarioResponse> registrarComentario(@Field("idImg") String idImg, @Field("idUsuario") String idUsuario, @Field("idDispositivo") String idDispositivo, @Field("comentario") String comentario);
}
