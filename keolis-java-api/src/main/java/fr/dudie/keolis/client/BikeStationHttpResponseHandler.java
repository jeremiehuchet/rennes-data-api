package fr.dudie.keolis.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.dudie.keolis.StringUtils;
import fr.dudie.keolis.model.BikeStation;

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
public final class BikeStationHttpResponseHandler implements ResponseHandler<List<BikeStation>> {

    /** The event logger. */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(BikeStationHttpResponseHandler.class);

    /** Default character encoding to use. */
    private static final String DEFAULT_ENCODING = "utf-8";

    /**
     * {@inheritDoc}
     * 
     * @see org.apache.http.client.ResponseHandler#handleResponse(org.apache.http.HttpResponse)
     */
    @Override
    public List<BikeStation> handleResponse(final HttpResponse response)
            throws ClientProtocolException, IOException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("handleResponse.start");
        }
        final String content = EntityUtils.toString(response.getEntity(), DEFAULT_ENCODING);

        JSONObject data = null;
        try {
            data = KeoUtils.getServiceResponse(content);
        } catch (final JSONException e) {
            throw new IOException("Unable to parse the json response received from Keolis:\n"
                    + content);
        }

        List<BikeStation> listStations = null;

        if (null != data) {
            // try to handle the response as if there is one station
            final JSONObject jsonStation = data.optJSONObject("station");
            if (null != jsonStation) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("response contains 1 station");
                }
                listStations = new ArrayList<BikeStation>(1);
                listStations.add(convertJsonObjectToBikeStation(jsonStation));
            } else {
                // else handle multiple stations
                final JSONArray jsonStations = data.optJSONArray("station");
                if (null != jsonStations) {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("response contains multiple stations");
                    }
                    listStations = new ArrayList<BikeStation>();
                    for (int i = 0; !jsonStations.isNull(i); i++) {
                        listStations.add(convertJsonObjectToBikeStation(jsonStations
                                .optJSONObject(i)));
                    }
                }
            }
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("handleResponse.end");
        }
        return listStations;
    }

    /**
     * Converts a json object to a bean representing a bike station.
     * 
     * @param jsonObject
     *            the json object to convert to a bike station
     * @return the bike station bean
     */
    private BikeStation convertJsonObjectToBikeStation(final JSONObject jsonObject) {

        final BikeStation station = new BikeStation();
        station.setActive(KeoUtils.convertJsonIntToBoolean(jsonObject.optInt("state")));
        station.setAddress(StringUtils.capitalize(jsonObject.optString("address")));
        station.setAvailableBikes(jsonObject.optInt("bikesavailable"));
        station.setAvailableSlots(jsonObject.optInt("slotsavailable"));
        station.setDistrict(StringUtils.capitalize(jsonObject.optString("district")));
        station.setId(jsonObject.optString("number"));
        station.setLastUpdate(KeoUtils.convertJsonStringToDate(jsonObject.optString("lastupdate")));
        station.setLatitude((int) (jsonObject.optDouble("latitude") * 1E6));
        station.setLongitude((int) (jsonObject.optDouble("longitude") * 1E6));
        station.setName(StringUtils.capitalize(jsonObject.optString("name")));
        station.setPos(KeoUtils.convertJsonIntToBoolean(jsonObject.optInt("pos")));

        return station;
    }
}
