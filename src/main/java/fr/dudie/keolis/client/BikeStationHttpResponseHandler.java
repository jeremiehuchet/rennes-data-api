package fr.dudie.keolis.client;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;

import fr.dudie.keolis.model.ApiResponse;
import fr.dudie.keolis.model.BikeData;

/**
 * Handles http responses containing bike stations in json format.
 * 
 * <pre>
 * "station":[
 *    {
 *       "number":"75",
 *       "name":"ZAC SAINT SULPICE",
 *       "address":"RUE DE FOUG\u00c8RES",
 *       "state":"1",
 *       "latitude":"48.1321",
 *       "longitude":"-1.63528",
 *       "slotsavailable":"29",
 *       "bikesavailable":"1",
 *       "pos":"0",
 *       "district":"Maurepas - Patton",
 *       "lastupdate":"2010-11-24T00:03:05+01:00"
 *    }
 * ]
 * </pre>
 * 
 * @author Jérémie Huchet
 */
public final class BikeStationHttpResponseHandler extends JsonResponseHandler<BikeData> {

    @Override
    ApiResponse<BikeData> handleJsonResponse(final InputStream inputStream) {

        final Type apiResponseType = new TypeToken<ApiResponse<BikeData>>() {
        }.getType();
        return KeoUtils.getGsonInstance().fromJson(new InputStreamReader(inputStream),
                apiResponseType);

    }
}
