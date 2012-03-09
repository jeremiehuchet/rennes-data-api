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
package fr.dudie.keolis.client;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;

import fr.dudie.keolis.model.ApiResponse;
import fr.dudie.keolis.model.RelayParkData;

/**
 * Handles http responses containing relay parks in json format.
 * 
 * <pre>
 * "relaypark": [
 *     {
 *         "name": "La Poterie"
 *         "latitude": "48.0868139293"
 *         "longitude": "-1.6434973209"
 *         "carparkavailable": "249"
 *         "carparkcapacity": "388"
 *         "lastupdate": "2011-09-03T18:26:11+02:00"
 *         "state": "0"
 *         "distance": "5184.2893316804"
 *     }
 * ]
 * </pre>
 * 
 * @author Jérémie Huchet
 */
public final class RelayParkHttpResponseHandler extends JsonResponseHandler<RelayParkData> {

    @Override
    ApiResponse<RelayParkData> handleJsonResponse(final InputStream inputStream) {

        final Type apiResponseType = new TypeToken<ApiResponse<RelayParkData>>() {
        }.getType();
        return KeoUtils.getGsonInstance().fromJson(new InputStreamReader(inputStream),
                apiResponseType);

    }
}
