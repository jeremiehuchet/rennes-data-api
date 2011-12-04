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

import com.google.gson.GsonBuilder;
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
public final class SubwayStationHttpResponseHandler implements
        ResponseHandler<ApiResponse<SubwayData>> {

    /** The event logger. */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(SubwayStationHttpResponseHandler.class);

    /**
     * {@inheritDoc}
     * 
     * @see org.apache.http.client.ResponseHandler#handleResponse(org.apache.http.HttpResponse)
     */
    @Override
    public ApiResponse<SubwayData> handleResponse(final HttpResponse response)
            throws ClientProtocolException, IOException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("handleResponse.start");
        }

        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        Type apiResponseType = new TypeToken<ApiResponse<SubwayData>>() {
        }.getType();

        final InputStream inputStream = response.getEntity().getContent();

        final ApiResponse<SubwayData> apiResponse = KeoUtils.getGsonInstance().fromJson(
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
