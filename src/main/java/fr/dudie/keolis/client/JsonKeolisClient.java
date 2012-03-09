package fr.dudie.keolis.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

    /** The keolis API base URL with key. */
    private final String baseUrl;

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
        // http://api.keolis.net/api?version=2.0&key=AZERTY1234
        this.baseUrl = String.format("%s?version=%s&key=%s", url, API_VERSION, key);
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
        parameters.add(new BasicNameValuePair(Keo.API_KEY, ""));

        final StringBuilder requestUrl = new StringBuilder("");
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

        final StringBuilder requestUrl = new StringBuilder(baseUrl);
        requestUrl.append("&param[network]=levelostar");
        requestUrl.append("&cmd=getbikestations");
        requestUrl.append("&param[station]=all");

        final HttpGet get = new HttpGet(requestUrl.toString());
        get.addHeader(H_ACCEPT, "text/json");
        get.addHeader(H_ACCEPT, "application/json");

        return httpClient.execute(get, defaultBikeStationHandler).getOpendata().getAnswer()
                .getData().getStations();

    }

    /**
     * {@inheritDoc}
     * 
     * @see fr.dudie.keolis.client.KeolisClient#getBikeStationsNearFrom(int, int)
     */
    @Override
    public final List<BikeStation> getBikeStationsNearFrom(final int latitude, final int longitude)
            throws IOException {

        final StringBuilder requestUrl = new StringBuilder(baseUrl);
        requestUrl.append("&param[network]=levelostar");
        requestUrl.append("&cmd=getbikestations");
        requestUrl.append("&param[station]=proximity");
        requestUrl.append("&param[mode]=coord");
        requestUrl.append("&param[lat]=").append(latitude);
        requestUrl.append("&param[long]=").append(longitude);

        final HttpGet get = new HttpGet(requestUrl.toString());
        get.addHeader(H_ACCEPT, "text/json");
        get.addHeader(H_ACCEPT, "application/json");

        return httpClient.execute(get, defaultBikeStationHandler).getOpendata().getAnswer()
                .getData().getStations();

    }

    /**
     * {@inheritDoc}
     * 
     * @see fr.dudie.keolis.client.KeolisClient#getBikeStation(java.lang.String)
     */
    @Override
    public final BikeStation getBikeStation(final String id) throws IOException {

        final StringBuilder requestUrl = new StringBuilder(baseUrl);
        requestUrl.append("&param[network]=levelostar");
        requestUrl.append("&cmd=getbikestations");
        requestUrl.append("&param[station]=number");
        requestUrl.append("&param[value]=").append(id);

        final HttpGet get = new HttpGet(requestUrl.toString());
        get.addHeader(H_ACCEPT, "text/json");
        get.addHeader(H_ACCEPT, "application/json");

        return httpClient.execute(get, defaultBikeStationHandler).getOpendata().getAnswer()
                .getData().getStations().get(0);

    }

    /**
     * {@inheritDoc}
     * 
     * @see fr.dudie.keolis.client.KeolisClient#getAllSubwayStations()
     */
    @Override
    public final List<SubwayStation> getAllSubwayStations() throws IOException {

        final StringBuilder requestUrl = new StringBuilder(baseUrl);
        requestUrl.append("&param[network]=star");
        requestUrl.append("&cmd=getmetrostations");
        requestUrl.append("&param[mode]=all");

        final HttpGet get = new HttpGet(requestUrl.toString());
        get.addHeader(H_ACCEPT, "text/json");
        get.addHeader(H_ACCEPT, "application/json");

        return httpClient.execute(get, defaultSubwayStationHandler).getOpendata().getAnswer()
                .getData().getStations();

    }

    /**
     * {@inheritDoc}
     * 
     * @see fr.dudie.keolis.client.KeolisClient#getSubwayStationsNearFrom(int, int)
     */
    @Override
    public final List<SubwayStation> getSubwayStationsNearFrom(final int latitude,
            final int longitude) throws IOException {

        final StringBuilder requestUrl = new StringBuilder(baseUrl);
        requestUrl.append("&param[network]=star");
        requestUrl.append("&cmd=getmetrostations");
        requestUrl.append("&param[mode]=proximity");
        requestUrl.append("&param[type]=coords");
        requestUrl.append("&param[lat]=").append(latitude);
        requestUrl.append("&param[lng]=").append(longitude);

        final HttpGet get = new HttpGet(requestUrl.toString());
        get.addHeader(H_ACCEPT, "text/json");
        get.addHeader(H_ACCEPT, "application/json");

        return httpClient.execute(get, defaultSubwayStationHandler).getOpendata().getAnswer()
                .getData().getStations();

    }

    /**
     * {@inheritDoc}
     * 
     * @see fr.dudie.keolis.client.KeolisClient#getSubwayStation(java.lang.String)
     */
    @Override
    public final SubwayStation getSubwayStation(final String id) throws IOException {

        final StringBuilder requestUrl = new StringBuilder(baseUrl);
        requestUrl.append("&param[network]=star");
        requestUrl.append("&cmd=getmetrostations");
        requestUrl.append("&param[mode]=station");
        requestUrl.append("&param[station]=").append(id);

        final HttpGet get = new HttpGet(requestUrl.toString());
        get.addHeader(H_ACCEPT, "text/json");
        get.addHeader(H_ACCEPT, "application/json");

        return httpClient.execute(get, defaultSubwayStationHandler).getOpendata().getAnswer()
                .getData().getStations().get(0);

    }

    /**
     * {@inheritDoc}
     * 
     * @see fr.dudie.keolis.client.KeolisClient#getAllLineIcons()
     */
    @Override
    public final List<LineIcon> getAllLineIcons() throws IOException {

        final StringBuilder requestUrl = new StringBuilder(baseUrl);
        requestUrl.append("&param[network]=star");
        requestUrl.append("&cmd=getlines");
        requestUrl.append("&param[mode]=all");

        final HttpGet get = new HttpGet(requestUrl.toString());
        get.addHeader(H_ACCEPT, "text/json");
        get.addHeader(H_ACCEPT, "application/json");

        return httpClient.execute(get, defaultLineIconHandler).getOpendata().getAnswer().getData()
                .getLines();

    }

    /**
     * {@inheritDoc}
     * 
     * @see fr.dudie.keolis.client.KeolisClient#getAllRelayParks()
     */
    @Override
    public final List<RelayPark> getAllRelayParks() throws IOException {

        final StringBuilder requestUrl = new StringBuilder(baseUrl);
        requestUrl.append("&param[network]=star");
        requestUrl.append("&cmd=getrelayparks");

        final HttpGet get = new HttpGet(requestUrl.toString());
        get.addHeader(H_ACCEPT, "text/json");
        get.addHeader(H_ACCEPT, "application/json");

        return httpClient.execute(get, defaultRelayParkHandler).getOpendata().getAnswer().getData()
                .getRelayParks();

    }

    /**
     * {@inheritDoc}
     * 
     * @see fr.dudie.keolis.client.KeolisClient#getRelayParksNearFrom(int, int)
     */
    @Override
    public final List<RelayPark> getRelayParksNearFrom(final int latitude, final int longitude)
            throws IOException {

        final StringBuilder requestUrl = new StringBuilder(baseUrl);
        requestUrl.append("&param[network]=star");
        requestUrl.append("&cmd=getrelayparks");
        requestUrl.append("&param[latitude]=").append(latitude);
        requestUrl.append("&param[longitude]=").append(longitude);

        final HttpGet get = new HttpGet(requestUrl.toString());
        get.addHeader(H_ACCEPT, "text/json");
        get.addHeader(H_ACCEPT, "application/json");

        return httpClient.execute(get, defaultRelayParkHandler).getOpendata().getAnswer().getData()
                .getRelayParks();

    }

    /**
     * {@inheritDoc}
     * 
     * @see fr.dudie.keolis.client.KeolisClient#getAllLinesAlerts()
     */
    @Override
    public final List<LineAlert> getAllLinesAlerts() throws IOException {

        final StringBuilder requestUrl = new StringBuilder(baseUrl);
        requestUrl.append("&param[network]=star");
        requestUrl.append("&cmd=getlinesalerts");
        requestUrl.append("&param[mode]=all");

        final HttpGet get = new HttpGet(requestUrl.toString());
        get.addHeader(H_ACCEPT, "text/json");
        get.addHeader(H_ACCEPT, "application/json");

        return httpClient.execute(get, defaultLineAlertHandler).getOpendata().getAnswer().getData()
                .getAlerts();

    }

    /**
     * {@inheritDoc}
     * 
     * @see fr.dudie.keolis.client.KeolisClient#getLinesAlertsForLine(java.lang.String)
     */
    @Override
    public final List<LineAlert> getLinesAlertsForLine(final String line) throws IOException {

        final StringBuilder requestUrl = new StringBuilder(baseUrl);
        requestUrl.append("&param[network]=star");
        requestUrl.append("&cmd=getlinesalerts");
        requestUrl.append("&param[mode]=line");
        requestUrl.append("&param[line]=").append(line);

        final HttpGet get = new HttpGet(requestUrl.toString());
        get.addHeader(H_ACCEPT, "text/json");
        get.addHeader(H_ACCEPT, "application/json");

        return httpClient.execute(get, defaultLineAlertHandler).getOpendata().getAnswer().getData()
                .getAlerts();

    }
}
