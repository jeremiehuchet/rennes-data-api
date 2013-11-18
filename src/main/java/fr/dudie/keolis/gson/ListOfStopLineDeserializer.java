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
import fr.dudie.keolis.model.StopLine;

/**
 * Deserializer for "stopline.departure" element returned by getbusnextdepartures command of keolis api.
 * 
 * @author Jeremie Huchet
 */
public final class ListOfStopLineDeserializer implements JsonDeserializer<List<StopLine>> {

    @Override
    public List<StopLine> deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) {

        final ArrayList<StopLine> stopLines = new ArrayList<StopLine>();

        if (json instanceof JsonArray) {
            final JsonArray jsonArray = (JsonArray) json;

            for (int i = 0; i < jsonArray.size(); i++) {
                stopLines.add(KeoUtils.getGsonInstance().fromJson(jsonArray.get(i), StopLine.class));
            }
        } else {
            stopLines.add(KeoUtils.getGsonInstance().fromJson(json, StopLine.class));
        }

        return stopLines;
    }
}
