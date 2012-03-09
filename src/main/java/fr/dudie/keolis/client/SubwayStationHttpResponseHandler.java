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
import fr.dudie.keolis.model.SubwayData;

/**
 * Handles http responses containing subway stations in json format.
 * 
 * <pre>
 * "station":[
 *    {
 *       "id":"ANF",
 *       "name":"Anatole France",
 *       "latitude":"48.11810000",
 *       "longitude":"-1.687460000",
 *       "hasPlatformDirection1":"1",
 *       "hasPlatformDirection2":"1",
 *       "rankingPlatformDirection1":"12",
 *       "rankingPlatformDirection2":"18",
 *       "floors":"-1",
 *       "lastupdate":"2010-11-23T23:15:58+01:00"
 *    }
 * ]
 * </pre>
 * 
 * @author Jérémie Huchet
 */
public final class SubwayStationHttpResponseHandler extends JsonResponseHandler<SubwayData> {

    @Override
    ApiResponse<SubwayData> handleJsonResponse(final InputStream inputStream) {

        final Type apiResponseType = new TypeToken<ApiResponse<SubwayData>>() {
        }.getType();
        return KeoUtils.getGsonInstance().fromJson(new InputStreamReader(inputStream),
                apiResponseType);

    }
}
