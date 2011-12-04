package fr.dudie.keolis.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public final class BikeStationHttpResponseHandler implements ResponseHandler<ApiResponse<BikeData>> {

    /** The event logger. */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(BikeStationHttpResponseHandler.class);

    /**
     * {@inheritDoc}
     * 
     * @see org.apache.http.client.ResponseHandler#handleResponse(org.apache.http.HttpResponse)
     */
    @Override
    public ApiResponse<BikeData> handleResponse(final HttpResponse response)
            throws ClientProtocolException, IOException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("handleResponse.start");

        }

        Type apiResponseType = new TypeToken<ApiResponse<BikeData>>() {
        }.getType();

        final InputStream inputStream = response.getEntity().getContent();

        final ApiResponse<BikeData> apiResponse = KeoUtils.getGsonInstance().fromJson(
                new InputStreamReader(inputStream), apiResponseType);

        inputStream.close();

        try {
            KeoUtils.checkResponse(apiResponse);
        } catch (JSONException e) {
            throw new IOException("Unable to parse the json response received from Keolis:\n" + e);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("handleResponse.end");
        }
        return apiResponse;
    }
}
