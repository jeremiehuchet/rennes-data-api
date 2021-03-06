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
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import fr.dudie.keolis.model.LineAlert;

/**
 * Deserializer for "line" element returned by getlinesalerts command of keolis api.
 * 
 * @author Olivier Boudet
 */
public final class ListOfLineAlertDeserializer implements JsonDeserializer<List<LineAlert>> {

    /** Gson Instance. */
    private static Gson gsonInstance;

    static {
        // TOBO is there a way to avoid creating a new Gson ?
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        final Type linesType = new TypeToken<List<String>>() {
        }.getType();
        gsonBuilder.registerTypeAdapter(linesType, new LinesOfLineAlertDeserializer());
        gsonInstance = gsonBuilder.create();
    }

    @Override
    public List<LineAlert> deserialize(final JsonElement json, final Type typeOfT,
            final JsonDeserializationContext context) {

        final ArrayList<LineAlert> lineAlerts = new ArrayList<LineAlert>();

        if (json instanceof JsonArray) {
            final JsonArray jsonArray = (JsonArray) json;

            for (int i = 0; i < jsonArray.size(); i++) {
                lineAlerts.add(gsonInstance.fromJson(jsonArray.get(i), LineAlert.class));
            }
        } else {
            lineAlerts.add(gsonInstance.fromJson(json, LineAlert.class));
        }

        return lineAlerts;
    }
}
