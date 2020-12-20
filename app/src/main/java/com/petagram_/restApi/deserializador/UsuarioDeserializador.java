package com.petagram_.restApi.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.petagram_.models.Perfil;
import com.petagram_.restApi.JsonKeys;
import com.petagram_.restApi.model.FotosResponse;

import java.lang.reflect.Type;

public class UsuarioDeserializador implements JsonDeserializer<Perfil> {
    @Override
    public Perfil deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        Perfil perfil = gson.fromJson(json,Perfil.class);

        return perfil;
    }
}
