package fr.dudie.keolis.client;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.dudie.keolis.model.BikeStation;
import fr.dudie.keolis.model.LineIcon;
import fr.dudie.keolis.model.SubwayStation;

/**
 * Interface to query the Keolis API.
 * 
 * @author Jérémie Huchet
 */
public interface KeolisClient {

    /**
     * Makes a call to the Keolis API to get all bike stations.
     * 
     * @return a {@link JSONArray} containing all bike stations as {@link JSONObject}s
     * @throws IOException
     *             unable to get a result from the server
     */
    List<BikeStation> getAllBikeStations() throws IOException;

    /**
     * Makes a call to the Keolis API to get the 3 first nearest bike stations.
     * 
     * @param latitude
     *            the latitude
     * @param longitude
     *            the longitude
     * @return a {@link JSONArray} containing the 3 bike stations as {@link JSONObject}s
     * @throws IOException
     *             unable to get a result from the server
     */
    List<BikeStation> getBikeStationsNearFrom(int latitude, int longitude) throws IOException;

    /**
     * Makes a call to the Keolis API to get the bike station related to the given identifier.
     * 
     * @param id
     *            the identifier of the bike station
     * @return a bike station
     * @throws IOException
     *             unable to get a result from the server
     */
    BikeStation getBikeStation(String id) throws IOException;

    /**
     * Makes a call to the Keolis API to get all subway stations.
     * 
     * @return a list of subway stations
     * @throws IOException
     *             unable to get a result from the server
     */
    List<SubwayStation> getAllSubwayStations() throws IOException;

    /**
     * Makes a call to the Keolis API to get the 3 first nearest subway stations.
     * 
     * @param latitude
     *            the latitude
     * @param longitude
     *            the longitude
     * @return a list containing the 3 subway stations
     * @throws IOException
     *             unable to get a result from the server
     */
    List<SubwayStation> getSubwayStationsNearFrom(int latitude, int longitude) throws IOException;

    /**
     * Makes a call to the Keolis API to get the subway station related to the given identifier.
     * 
     * @param id
     *            the identifier of the subway station
     * @return a subway station
     * @throws IOException
     *             unable to get a result from the server
     */
    SubwayStation getSubwayStation(String id) throws IOException;

    /**
     * Makes a call to the Keolis API to get the list of URLs to fetch the icons of the transport
     * lines.
     * 
     * @return a bean containing the base URL and the file names
     * @throws IOException
     *             unable to get a result from the server
     */
    List<LineIcon> getAllLineIcons() throws IOException;
}
