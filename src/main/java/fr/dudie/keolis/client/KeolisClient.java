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

import fr.dudie.keolis.model.BikeStation;
import fr.dudie.keolis.model.LineAlert;
import fr.dudie.keolis.model.LineIcon;
import fr.dudie.keolis.model.RelayPark;
import fr.dudie.keolis.model.StopLine;
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

    /**
     * Makes a call to get the bus next departures for the given stops.
     * 
     * @param stops
     *            the stop ids (max 5)
     * @return the bus next departures for the given stops
     * @throws IOException
     *             unable to get a result from the server
     */
    List<StopLine> getBusNextDeparturesForStop(String... stops) throws IOException;

    /**
     * Makes a call to get the bus next departures for the given route.
     * 
     * @param route
     *            the route id
     * @param direction
     *            the route direction
     * @return the bus next departures for the given line
     * @throws IOException
     *             unable to get a result from the server
     */
    List<StopLine> getBusNextDeparturesForLine(String route, int direction) throws IOException;
}
