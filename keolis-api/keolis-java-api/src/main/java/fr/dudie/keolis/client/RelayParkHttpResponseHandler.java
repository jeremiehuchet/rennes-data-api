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

import fr.dudie.keolis.model.RelayPark;

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
public class RelayParkHttpResponseHandler implements ResponseHandler<List<RelayPark>> {

    /** The event logger. */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(RelayParkHttpResponseHandler.class);

    /** Default character encoding to use. */
    private static final String DEFAULT_ENCODING = "utf-8";

    /**
     * {@inheritDoc}
     * 
     * @see org.apache.http.client.ResponseHandler#handleResponse(org.apache.http.HttpResponse)
     */
    @Override
    public List<RelayPark> handleResponse(final HttpResponse response)
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

        List<RelayPark> listRelayParks = null;

        if (null != data) {
            // else handle multiple stations
            final JSONArray jsonParks = data.optJSONArray("relaypark");
            if (null != jsonParks) {
                listRelayParks = new ArrayList<RelayPark>();
                for (int i = 0; !jsonParks.isNull(i); i++) {
                    listRelayParks.add(convertJsonObjectToRelayPark(jsonParks.optJSONObject(i)));
                }
            } else {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("0 relay park found in response");
                }
            }
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("handleResponse.end");
        }
        return listRelayParks;
    }

    /**
     * Converts a json formatted relay park to an Object representation.
     * 
     * @param jsonPark
     *            the json representation
     * @return an object representation
     */
    private RelayPark convertJsonObjectToRelayPark(final JSONObject jsonPark) {

        final RelayPark park = new RelayPark();
        park.setName(jsonPark.optString("name"));
        park.setCarParkAvailable(jsonPark.optInt("carparkavailable"));
        park.setCarParkCapacity(jsonPark.optInt("carparkcapacity"));
        park.setLastUpdate(KeoUtils.convertJsonStringToDate(jsonPark.optString("lastupdate")));
        park.setLatitude((int) (jsonPark.optDouble("latitude") * 1E6));
        park.setLongitude((int) (jsonPark.optDouble("longitude") * 1E6));
        park.setState(jsonPark.optInt("state"));
        return park;
    }
}
