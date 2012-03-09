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

import java.io.IOException;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
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
     * {@inheritDoc}
     * 
     * @see fr.dudie.keolis.client.KeolisClient#getAllBikeStations()
     */
    @Override
    public final List<BikeStation> getAllBikeStations() throws IOException {

        final StringBuilder requestUrl = new StringBuilder(baseUrl);
        requestUrl.append("&param[network]=levelostar");
        requestUrl.append("&cmd=getbikestations");
        requestUrl.append("&param[station]=all");

        LOGGER.debug("request url: {}", requestUrl);

        return httpClient.execute(new HttpGet(requestUrl.toString()), defaultBikeStationHandler)
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

        final StringBuilder requestUrl = new StringBuilder(baseUrl);
        requestUrl.append("&param[network]=levelostar");
        requestUrl.append("&cmd=getbikestations");
        requestUrl.append("&param[station]=proximity");
        requestUrl.append("&param[mode]=coord");
        requestUrl.append("&param[lat]=").append(latitude);
        requestUrl.append("&param[long]=").append(longitude);

        LOGGER.debug("request url: {}", requestUrl);

        return httpClient.execute(new HttpGet(requestUrl.toString()), defaultBikeStationHandler)
                .getOpendata().getAnswer().getData().getStations();

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

        LOGGER.debug("request url: {}", requestUrl);

        return httpClient.execute(new HttpGet(requestUrl.toString()), defaultBikeStationHandler)
                .getOpendata().getAnswer().getData().getStations().get(0);

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

        LOGGER.debug("request url: {}", requestUrl);

        return httpClient.execute(new HttpGet(requestUrl.toString()), defaultSubwayStationHandler)
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

        final StringBuilder requestUrl = new StringBuilder(baseUrl);
        requestUrl.append("&param[network]=star");
        requestUrl.append("&cmd=getmetrostations");
        requestUrl.append("&param[mode]=proximity");
        requestUrl.append("&param[type]=coords");
        requestUrl.append("&param[lat]=").append(latitude);
        requestUrl.append("&param[lng]=").append(longitude);

        LOGGER.debug("request url: {}", requestUrl);

        return httpClient.execute(new HttpGet(requestUrl.toString()), defaultSubwayStationHandler)
                .getOpendata().getAnswer().getData().getStations();

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

        LOGGER.debug("request url: {}", requestUrl);

        return httpClient.execute(new HttpGet(requestUrl.toString()), defaultSubwayStationHandler)
                .getOpendata().getAnswer().getData().getStations().get(0);

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

        LOGGER.debug("request url: {}", requestUrl);

        return httpClient.execute(new HttpGet(requestUrl.toString()), defaultLineIconHandler)
                .getOpendata().getAnswer().getData().getLines();

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

        LOGGER.debug("request url: {}", requestUrl);

        return httpClient.execute(new HttpGet(requestUrl.toString()), defaultRelayParkHandler)
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

        final StringBuilder requestUrl = new StringBuilder(baseUrl);
        requestUrl.append("&param[network]=star");
        requestUrl.append("&cmd=getrelayparks");
        requestUrl.append("&param[latitude]=").append(latitude);
        requestUrl.append("&param[longitude]=").append(longitude);

        LOGGER.debug("request url: {}", requestUrl);

        return httpClient.execute(new HttpGet(requestUrl.toString()), defaultRelayParkHandler)
                .getOpendata().getAnswer().getData().getRelayParks();

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

        LOGGER.debug("request url: {}", requestUrl);

        return httpClient.execute(new HttpGet(requestUrl.toString()), defaultLineAlertHandler)
                .getOpendata().getAnswer().getData().getAlerts();

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

        LOGGER.debug("request url: {}", requestUrl);

        return httpClient.execute(new HttpGet(requestUrl.toString()), defaultLineAlertHandler)
                .getOpendata().getAnswer().getData().getAlerts();

    }
}
