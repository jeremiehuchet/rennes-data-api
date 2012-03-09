package fr.dudie.keolis.client;

import java.io.IOException;
import java.util.List;

import fr.dudie.keolis.model.BikeStation;
import fr.dudie.keolis.model.LineAlert;
import fr.dudie.keolis.model.LineIcon;
import fr.dudie.keolis.model.RelayPark;
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
     * @return a {@link List} containing all bike stations as {@link BikeStation}s
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
     * @return a {@link List} containing the 3 bike stations as {@link BikeStation}s
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

    /**
     * Makes a call to the Keolis API to get the list of all relay parks.
     * 
     * @return the list of relay parks
     * @throws IOException
     *             unable to get a result from the server
     */
    List<RelayPark> getAllRelayParks() throws IOException;

    /**
     * Makes a call to the Keolis API to get the 3 first nearest relay parks.
     * 
     * @param latitude
     *            the latitude
     * @param longitude
     *            the longitude
     * @return a list containing the 3 relay parks
     * @throws IOException
     *             unable to get a result from the server
     */
    List<RelayPark> getRelayParksNearFrom(int latitude, int longitude) throws IOException;

    /**
     * Makes a call to get all lines alerts.
     * 
     * @return the all lines alerts
     * @throws IOException
     *             unable to get a result from the server
     */
    List<LineAlert> getAllLinesAlerts() throws IOException;

    /**
     * Makes a call to get all alerts related to the given line name.
     * 
     * @param line
     *            the line name
     * @return the lines alerts related to the given line
     * @throws IOException
     *             unable to get a result from the server
     */
    List<LineAlert> getLinesAlertsForLine(String line) throws IOException;
}
