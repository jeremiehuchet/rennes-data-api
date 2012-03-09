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
import fr.dudie.keolis.model.BikeStation;

/**
 * Deserializer for "station" element returned by getbikestations command of keolis api.
 * 
 * @author Olivier Boudet
 */
public final class BikeStationDeserializer implements JsonDeserializer<List<BikeStation>> {

    @Override
    public List<BikeStation> deserialize(final JsonElement json, final Type typeOfT,
            final JsonDeserializationContext context) {

        final ArrayList<BikeStation> bikeStations = new ArrayList<BikeStation>();

        if (json instanceof JsonArray) {
            for (int i = 0; i < ((JsonArray) json).size(); i++) {
                bikeStations.add(KeoUtils.getGsonInstance().fromJson(((JsonArray) json).get(i),
                        BikeStation.class));
            }

        } else {
            bikeStations.add((BikeStation) context.deserialize(json, BikeStation.class));
        }

        return bikeStations;
    }
}
