package com.petagram_.restApi;

import com.petagram_.models.Perfil;
import com.petagram_.restApi.model.FotosResponse;

import retrofit2.Call;

public final class ConstantesRestApi {
    static final String VERSION = "/v9.0/";
    public static final String ROOT_URL = "https://graph.facebook.com" + VERSION;
    static final String ACCESS_TOKEN = "EAAFxXvFjcsUBAJ7ntOQHUjZBPxslYLAZA9iNfPbX4HFbZCEHSWOmDsQe17Y9tZBzhpsELR0ZBrJJ83cErRXsA5Pf6IXBZCsPu3yr2i9S20Y2f8rOoiA1VfFZClLdBBhoA2HmeD5RkOw75BcRiYXZBqiPlVJKPMjniO3b1denHv2MjAZDZD";
    static final String KEY_ACCESS_TOKEN = "&access_token=";
    static final String DATOS_MEDIA = "?fields=like_count,media_url";
    static final String KEY_GET_USER = "17841444945970879/";
    static final String KEY_GET_RECENT_MEDIA_USER = KEY_GET_USER + "media";
    public static final String URL_GET_RECENT_MEDIA_USER_1 = KEY_GET_RECENT_MEDIA_USER + DATOS_MEDIA + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    static final String DATOS_USER = "?fields=name,profile_picture_url";
    public static final String URL_GET_DATOS_USUARIO_1 = KEY_GET_USER + DATOS_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;



    public static boolean existeCuenta (String cuenta){
        boolean resultado;
        switch (cuenta){
            case "boxer_87":
                resultado = true;
                break;
            default:
                resultado = false;
                break;
        }
        return resultado;
    }

    public static Call<Perfil> metodoPerfil(String cuenta, EndpointsApi endpointsApi){
        Call<Perfil> metodo;
        switch (cuenta){
            case "boxer_87":
                metodo = endpointsApi.getPerfil1();
                break;
            default:
                metodo = null;
                break;
        }
        return metodo;
    }

    public static Call<FotosResponse> metodoMedia(String cuenta, EndpointsApi endpointsApi){
        Call<FotosResponse> metodo;
        switch (cuenta){
            case "boxer_87":
                metodo = endpointsApi.getRecentMedia1();
                break;
            default:
                metodo = null;
                break;
        }
        return metodo;
    }
}
