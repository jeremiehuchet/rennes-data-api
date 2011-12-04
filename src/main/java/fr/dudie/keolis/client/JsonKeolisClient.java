package fr.dudie.keolis.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.dudie.keolis.model.BikeStation;
import fr.dudie.keolis.model.LineAlert;
import fr.dudie.keolis.model.LineIcon;
import fr.dudie.keolis.model.RelayPark;
import fr.dudie.keolis.model.SubwayStation;

/**
 * Manage calls to the Keolis API.
 * 
 * @author Jérémie Huchet
 */
public class JsonKeolisClient implements KeolisClient {

    /** The event logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKeolisClient.class);

    /** The Keolis API version this client use. */
    private static final String API_VERSION = "2.0";

    /** The HTTP header "Accept". */
    private static final String H_ACCEPT = "Accept";

    /** The keolis API url to use. */
    private final String keolisApiUrl;

    /** The keolis API key to use. */
    private final String keolisApiKey;

    /** The HTTP client. */
    private final HttpClient httpClient;

    /** The handler to receive bike stations. */
    private final BikeStationHttpResponseHandler defaultBikeStationHandler = new BikeStationHttpResponseHandler();

    /** The handler to receive subway stations. */
    private final SubwayStationHttpResponseHandler defaultSubwayStationHandler = new SubwayStationHttpResponseHandler();

    /** The handler to receive line icons. */
    private final LineIconsHttpResponseHandler defaultLineIconHandler = new LineIconsHttpResponseHandler();

    /** The handler to receive relay parks. */
    private final RelayParkHttpResponseHandler defaultRelayParkHandler = new RelayParkHttpResponseHandler();

    /** The handler to receive lines alerts. */
    private final LineAlertHttpResponseHandler defaultLineAlertHandler = new LineAlertHttpResponseHandler();

    /**
     * Creates a Keolis API client.
     * 
     * @param httpClient
     *            an http client
     * @param url
     *            the url of the Keolis API
     * @param key
     *            the API key
     */
    public JsonKeolisClient(final HttpClient httpClient, final String url, final String key) {

        this.httpClient = httpClient;
        this.keolisApiUrl = url;
        this.keolisApiKey = key;
    }

    /**
     * Creates a generic request to the Keolis API. This method will set the request headers, the
     * version and the API key needed to send a request to Keolis.
     * 
     * @param parameters
     *            the request parameters
     * @return an {@link HttpGet} to send to execute the request
     * @throws UnsupportedEncodingException
     *             unable to encode request parameters
     */
    private HttpGet createKeolisRequest(final List<NameValuePair> parameters)
            throws UnsupportedEncodingException {

        parameters.add(new BasicNameValuePair(Keo.API_VERSION, API_VERSION));
        parameters.add(new BasicNameValuePair(Keo.API_KEY, keolisApiKey));

        final StringBuilder requestUrl = new StringBuilder(keolisApiUrl);
        requestUrl.append('?');
        for (final NameValuePair param : parameters) {
            requestUrl.append('&').append(param.getName());
            requestUrl.append('=').append(param.getValue());
        }

        final HttpGet req = new HttpGet(requestUrl.toString());
        req.addHeader(H_ACCEPT, "text/json");
        req.addHeader(H_ACCEPT, "application/json");

        if (LOGGER.isDebugEnabled()) {
            final StringBuilder msg = new StringBuilder();
            msg.append(req.getURI().toString()).append("?");
            for (final NameValuePair param : parameters) {
                msg.append(param.getName()).append("=").append(param.getValue()).append("&");
            }
            msg.deleteCharAt(msg.length() - 1);
            LOGGER.debug("createKeolisRequest - {}", msg.toString());
        }

        return req;
    }

    @Override
    public final List<BikeStation> getAllBikeStations() throws IOException {

        final List<NameValuePair> params = new ArrayList<NameValuePair>(5);
        params.add(new BasicNameValuePair(Keo.Network.PARAM_NAME, Keo.Network.VALUE_LE_VELO_STAR));
        params.add(new BasicNameValuePair(Keo.Command.PARAM_NAME, Keo.Command.GET_BIKE_STATIONS));
        params.add(new BasicNameValuePair(Keo.GetBikeStations.PARAM_STATION,
                Keo.GetBikeStations.VALUE_STATION_ALL));

        return httpClient.execute(createKeolisRequest(params), defaultBikeStationHandler)
                .getOpendata().getAnswer().getData().getStations();

    }

    /**
     * {@inheritDoc}
     * 
     * @see fr.dudie.keolis.client.KeolisClient#getBikeStationsNearFrom(int, int)
     */
    @Override
    public final List<BikeStation> getBikeStationsNearFrom(final int latitude, final int longitude)
            throws IOException {

        final List<NameValuePair> params = new ArrayList<NameValuePair>(8);
        params.add(new BasicNameValuePair(Keo.Network.PARAM_NAME, Keo.Network.VALUE_LE_VELO_STAR));
        params.add(new BasicNameValuePair(Keo.Command.PARAM_NAME, Keo.Command.GET_BIKE_STATIONS));
        params.add(new BasicNameValuePair(Keo.GetBikeStations.PARAM_STATION,
                Keo.GetBikeStations.VALUE_STATION_PROXIMITY));
        params.add(new BasicNameValuePair(Keo.GetBikeStations.PARAM_MODE,
                Keo.GetBikeStations.VALUE_MODE_COORDINATES));
        params.add(new BasicNameValuePair(Keo.GetBikeStations.PARAM_LATITUDE, String
                .valueOf(latitude)));
        params.add(new BasicNameValuePair(Keo.GetBikeStations.PARAM_LONGITUDE, String
                .valueOf(longitude)));

        return httpClient.execute(createKeolisRequest(params), defaultBikeStationHandler)
                .getOpendata().getAnswer().getData().getStations();

    }

    /**
     * {@inheritDoc}
     * 
     * @see fr.dudie.keolis.client.KeolisClient#getBikeStation(java.lang.String)
     */
    @Override
    public final BikeStation getBikeStation(final String id) throws IOException {

        final List<NameValuePair> params = new ArrayList<NameValuePair>(6);
        params.add(new BasicNameValuePair(Keo.Network.PARAM_NAME, Keo.Network.VALUE_LE_VELO_STAR));
        params.add(new BasicNameValuePair(Keo.Command.PARAM_NAME, Keo.Command.GET_BIKE_STATIONS));
        params.add(new BasicNameValuePair(Keo.GetBikeStations.PARAM_STATION,
                Keo.GetBikeStations.VALUE_STATION_IDENTIFIER));
        params.add(new BasicNameValuePair(Keo.GetBikeStations.PARAM_VALUE, id));

        return httpClient.execute(createKeolisRequest(params), defaultBikeStationHandler)
                .getOpendata().getAnswer().getData().getStations().get(0);

    }

    /**
     * {@inheritDoc}
     * 
     * @see fr.dudie.keolis.client.KeolisClient#getAllSubwayStations()
     */
    @Override
    public final List<SubwayStation> getAllSubwayStations() throws IOException {

        final List<NameValuePair> params = new ArrayList<NameValuePair>(5);
        params.add(new BasicNameValuePair(Keo.Network.PARAM_NAME, Keo.Network.VALUE_STAR));
        params.add(new BasicNameValuePair(Keo.Command.PARAM_NAME, Keo.Command.GET_METRO_STATIONS));
        params.add(new BasicNameValuePair(Keo.GetSubwayStations.PARAM_MODE,
                Keo.GetSubwayStations.VALUE_MODE_ALL));

        return httpClient.execute(createKeolisRequest(params), defaultSubwayStationHandler)
                .getOpendata().getAnswer().getData().getStations();

    }

    /**
     * {@inheritDoc}
     * 
     * @see fr.dudie.keolis.client.KeolisClient#getSubwayStationsNearFrom(int, int)
     */
    @Override
    public final List<SubwayStation> getSubwayStationsNearFrom(final int latitude,
            final int longitude) throws IOException {

        final List<NameValuePair> params = new ArrayList<NameValuePair>(8);
        params.add(new BasicNameValuePair(Keo.Network.PARAM_NAME, Keo.Network.VALUE_STAR));
        params.add(new BasicNameValuePair(Keo.Command.PARAM_NAME, Keo.Command.GET_METRO_STATIONS));
        params.add(new BasicNameValuePair(Keo.GetSubwayStations.PARAM_MODE,
                Keo.GetSubwayStations.VALUE_MODE_PROXIMITY));
        params.add(new BasicNameValuePair(Keo.GetSubwayStations.PARAM_PROXIMITY_TYPE,
                Keo.GetSubwayStations.VALUE_PROXIMITY_TYPE_COORDINATES));
        params.add(new BasicNameValuePair(Keo.GetSubwayStations.PARAM_LATITUDE, String
                .valueOf(latitude)));
        params.add(new BasicNameValuePair(Keo.GetSubwayStations.PARAM_LONGITUDE, String
                .valueOf(longitude)));

        return httpClient.execute(createKeolisRequest(params), defaultSubwayStationHandler)
                .getOpendata().getAnswer().getData().getStations();

    }

    /**
     * {@inheritDoc}
     * 
     * @see fr.dudie.keolis.client.KeolisClient#getSubwayStation(java.lang.String)
     */
    @Override
    public final SubwayStation getSubwayStation(final String id) throws IOException {

        final List<NameValuePair> params = new ArrayList<NameValuePair>(6);
        params.add(new BasicNameValuePair(Keo.Network.PARAM_NAME, Keo.Network.VALUE_STAR));
        params.add(new BasicNameValuePair(Keo.Command.PARAM_NAME, Keo.Command.GET_METRO_STATIONS));
        params.add(new BasicNameValuePair(Keo.GetSubwayStations.PARAM_MODE,
                Keo.GetSubwayStations.VALUE_MODE_STATION));
        params.add(new BasicNameValuePair(Keo.GetSubwayStations.PARAM_STATION_IDENTIFIER, id));

        return httpClient.execute(createKeolisRequest(params), defaultSubwayStationHandler)
                .getOpendata().getAnswer().getData().getStations().get(0);

    }

    /**
     * {@inheritDoc}
     * 
     * @see fr.dudie.keolis.client.KeolisClient#getAllLineIcons()
     */
    @Override
    public final List<LineIcon> getAllLineIcons() throws IOException {

        final List<NameValuePair> params = new ArrayList<NameValuePair>(4);
        params.add(new BasicNameValuePair(Keo.Network.PARAM_NAME, Keo.Network.VALUE_STAR));
        params.add(new BasicNameValuePair(Keo.Command.PARAM_NAME, Keo.Command.GET_LINES_ICONS));
        params.add(new BasicNameValuePair(Keo.GetLinesIcons.PARAM_MODE,
                Keo.GetLinesIcons.VALUE_MODE_ALL));

        return httpClient.execute(createKeolisRequest(params), defaultLineIconHandler)
                .getOpendata().getAnswer().getData().getLines();

    }

    /**
     * {@inheritDoc}
     * 
     * @see fr.dudie.keolis.client.KeolisClient#getAllRelayParks()
     */
    @Override
    public final List<RelayPark> getAllRelayParks() throws IOException {

        final List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(Keo.Network.PARAM_NAME, Keo.Network.VALUE_STAR));
        params.add(new BasicNameValuePair(Keo.Command.PARAM_NAME, Keo.Command.GET_RELAY_PARKS));

        return httpClient.execute(createKeolisRequest(params), defaultRelayParkHandler)
                .getOpendata().getAnswer().getData().getRelayParks();

    }

    /**
     * {@inheritDoc}
     * 
     * @see fr.dudie.keolis.client.KeolisClient#getRelayParksNearFrom(int, int)
     */
    @Override
    public final List<RelayPark> getRelayParksNearFrom(final int latitude, final int longitude)
            throws IOException {

        final List<NameValuePair> params = new ArrayList<NameValuePair>(6);
        params.add(new BasicNameValuePair(Keo.Network.PARAM_NAME, Keo.Network.VALUE_STAR));
        params.add(new BasicNameValuePair(Keo.Command.PARAM_NAME, Keo.Command.GET_RELAY_PARKS));

        params.add(new BasicNameValuePair(Keo.GetRelayParks.PARAM_LATITUDE, String
                .valueOf(latitude)));
        params.add(new BasicNameValuePair(Keo.GetRelayParks.PARAM_LONGITUDE, String
                .valueOf(longitude)));

        return httpClient.execute(createKeolisRequest(params), defaultRelayParkHandler)
                .getOpendata().getAnswer().getData().getRelayParks();

    }

    /**
     * {@inheritDoc}
     * 
     * @see fr.dudie.keolis.client.KeolisClient#getAllLinesAlerts()
     */
    @Override
    public final List<LineAlert> getAllLinesAlerts() throws IOException {

        final List<NameValuePair> params = new ArrayList<NameValuePair>(5);
        params.add(new BasicNameValuePair(Keo.Network.PARAM_NAME, Keo.Network.VALUE_STAR));
        params.add(new BasicNameValuePair(Keo.Command.PARAM_NAME, Keo.Command.GET_LINES_ALERTS));

        params.add(new BasicNameValuePair(Keo.GetLinesAlerts.PARAM_MODE,
                Keo.GetLinesAlerts.VALUE_MODE_ALL));

        return httpClient.execute(createKeolisRequest(params), defaultLineAlertHandler)
                .getOpendata().getAnswer().getData().getAlerts();

    }

    /**
     * {@inheritDoc}
     * 
     * @see fr.dudie.keolis.client.KeolisClient#getLinesAlertsForLine(java.lang.String)
     */
    @Override
    public final List<LineAlert> getLinesAlertsForLine(final String line) throws IOException {

        final List<NameValuePair> params = new ArrayList<NameValuePair>(6);
        params.add(new BasicNameValuePair(Keo.Network.PARAM_NAME, Keo.Network.VALUE_STAR));
        params.add(new BasicNameValuePair(Keo.Command.PARAM_NAME, Keo.Command.GET_LINES_ALERTS));

        params.add(new BasicNameValuePair(Keo.GetLinesAlerts.PARAM_MODE,
                Keo.GetLinesAlerts.VALUE_MODE_LINE));
        params.add(new BasicNameValuePair(Keo.GetLinesAlerts.PARAM_LINE, line));

        return httpClient.execute(createKeolisRequest(params), defaultLineAlertHandler)
                .getOpendata().getAnswer().getData().getAlerts();

    }
}
