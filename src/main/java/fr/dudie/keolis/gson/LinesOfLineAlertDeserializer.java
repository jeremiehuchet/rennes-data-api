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

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import fr.dudie.keolis.client.KeoUtils;

/**
 * Deserializer for "line" element returned by getlinesalerts command of keolis api.
 * 
 * @author Olivier Boudet
 */
public final class LinesOfLineAlertDeserializer implements JsonDeserializer<List<String>> {

    @Override
    public List<String> deserialize(final JsonElement json, final Type typeOfT,
            final JsonDeserializationContext context) {

        JsonElement lines = json.getAsJsonObject().get("line");
        if (lines instanceof JsonArray) {
            return KeoUtils.getGsonInstance().fromJson(lines, typeOfT);
        }

        String line = context.deserialize(lines, String.class);
        ArrayList<String> list = new ArrayList<String>();
        list.add(line);
        return list;
    }
}
