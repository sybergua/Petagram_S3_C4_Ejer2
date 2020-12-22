package com.petagram_.restApi.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.petagram_.models.Foto;
import com.petagram_.restApi.JsonKeys;
import com.petagram_.restApi.model.FotosResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FotosDeserializador implements JsonDeserializer<FotosResponse> {
    @Override
    public FotosResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        FotosResponse fotosResponse = gson.fromJson(json,FotosResponse.class);
        JsonArray fotoResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        fotosResponse.setFotos(deserializarFotoDeJson(fotoResponseData));

        return fotosResponse;
    }

    private ArrayList<Foto> deserializarFotoDeJson(JsonArray fotoResponseData){
        ArrayList<Foto> fotos = new ArrayList<>();
        for (int i = 0; i < fotoResponseData.size(); i++) {
            JsonObject fotoResponseDataObject = fotoResponseData.get(i).getAsJsonObject();
            String urlFoto = fotoResponseDataObject.get(JsonKeys.MEDIA_URL).getAsString();

            int likes = fotoResponseDataObject.get(JsonKeys.MEDIA_LIKES).getAsInt();
            int comments_count = fotoResponseDataObject.get(JsonKeys.MEDIA_COMMENTS_COUNT).getAsInt();
            String id = fotoResponseDataObject.get(JsonKeys.MEDIA_ID).getAsString();

            Foto fotoActual = new Foto();
            fotoActual.setFoto(urlFoto);
            fotoActual.setRaiting(likes);
            fotoActual.setComments_count(comments_count);
            fotoActual.setId(id);


            fotos.add(fotoActual);
        }

        return fotos;
    }
}
