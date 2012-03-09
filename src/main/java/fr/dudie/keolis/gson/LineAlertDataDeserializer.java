/*
 * Copyright (C) 2010 Dudie
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.dudie.keolis.gson;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;

import fr.dudie.keolis.model.LineAlert;
import fr.dudie.keolis.model.LineAlertData;

/**
 * Deserializer for "alert" element returned by getlinesalerts command of keolis api.
 * 
 * @author Olivier Boudet
 */
public final class LineAlertDataDeserializer implements JsonDeserializer<LineAlertData> {

    @Override
    public LineAlertData deserialize(final JsonElement json, final Type typeOfT,
            final JsonDeserializationContext context) {

        if (json instanceof JsonPrimitive) {
            return new LineAlertData();
        } else {
            // TOBO is there a way to avoid creating a new Gson ?
            final GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");

            // type adapter because list of only one element returned by keolis are not a JsonArray
            // but a JsonObject.
            final Type listOfLinesType = new TypeToken<List<LineAlert>>() {
            }.getType();
            gsonBuilder.registerTypeAdapter(listOfLinesType, new ListOfLineAlertDeserializer());

            return gsonBuilder.create().fromJson(json, typeOfT);

        }
    }
}
