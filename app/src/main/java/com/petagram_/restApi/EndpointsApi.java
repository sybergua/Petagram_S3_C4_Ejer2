package com.petagram_.restApi;

import com.petagram_.models.Perfil;
import com.petagram_.restApi.model.ComentarioResponse;
import com.petagram_.restApi.model.FotosResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EndpointsApi {

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER_1)
    Call<FotosResponse> getRecentMedia1();

    @GET(ConstantesRestApi.URL_GET_DATOS_USUARIO_1)
    Call<Perfil> getPerfil1();

    @FormUrlEncoded
    @POST(ConstantesRestApi.URL_ENVIAR_COMENTARIO)
    Call<ComentarioResponse> enviarComentario(@Path ("id_img") String id_img, @Field("message") String mensaje);
}
