package com.superappli.lafourchette.data.source.remote;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class GsonConverter<T> implements com.google.gson.JsonDeserializer<T> {

    @Override
    public T deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
            throws JsonParseException {

        JsonElement content = je.getAsJsonObject().get("data");
        return new Gson().fromJson(content, type);
    }
}