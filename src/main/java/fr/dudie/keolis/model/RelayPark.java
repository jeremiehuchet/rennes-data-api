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
package fr.dudie.keolis.model;

import java.util.Date;

/**
 * Represents a relay park.
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
public class RelayPark {

    /** The relay park name. */
    private String name;

    /** The relay park latitude. */
    private double latitude;

    /** The relay park longitude. */
    private double longitude;

    /** The amount of parks available. */
    private int carParkAvailable;

    /** The relay park capacity. */
    private int carParkCapacity;

    /** The last update date. */
    private Date lastUpdate;

    /** The park state (0: OPEN, 1: CLOSED, 2: FULL, 3: UNAVAILABLE). */
    private int state;

    /**
     * Gets the name.
     * 
     * @return the name
     */
    public final String getName() {

        return name;
    }

    /**
     * Gets the latitude.
     * 
     * @return the latitude
     */
    public final int getLatitude() {

        return (int) (latitude * 1E6);
    }

    /**
     * Gets the longitude.
     * 
     * @return the longitude
     */
    public final int getLongitude() {

        return (int) (longitude * 1E6);
    }

    /**
     * Gets the carParkAvailable.
     * 
     * @return the carParkAvailable
     */
    public final int getCarParkAvailable() {

        return carParkAvailable;
    }

    /**
     * Gets the carParkCapacity.
     * 
     * @return the carParkCapacity
     */
    public final int getCarParkCapacity() {

        return carParkCapacity;
    }

    /**
     * Gets the lastUpdate.
     * 
     * @return the lastUpdate
     */
    public final Date getLastUpdate() {

        return lastUpdate;
    }

    /**
     * Gets the state.
     * 
     * @return the state
     */
    public final RelayParkState getState() {

        return RelayParkState.values()[state];
    }

    /**
     * Sets the name.
     * 
     * @param name
     *            the name to set
     */
    public final void setName(final String name) {

        this.name = name;
    }

    /**
     * Sets the latitude.
     * 
     * @param latitude
     *            the latitude to set
     */
    public final void setLatitude(final double latitude) {

        this.latitude = latitude;
    }

    /**
     * Sets the longitude.
     * 
     * @param longitude
     *            the longitude to set
     */
    public final void setLongitude(final double longitude) {

        this.longitude = longitude;
    }

    /**
     * Sets the carParkAvailable.
     * 
     * @param carParkAvailable
     *            the carParkAvailable to set
     */
    public final void setCarParkAvailable(final int carParkAvailable) {

        this.carParkAvailable = carParkAvailable;
    }

    /**
     * Sets the carParkCapacity.
     * 
     * @param carParkCapacity
     *            the carParkCapacity to set
     */
    public final void setCarParkCapacity(final int carParkCapacity) {

        this.carParkCapacity = carParkCapacity;
    }

    /**
     * Sets the lastUpdate.
     * 
     * @param lastUpdate
     *            the lastUpdate to set
     */
    public final void setLastUpdate(final Date lastUpdate) {

        this.lastUpdate = lastUpdate;
    }

    /**
     * Sets the state.
     * 
     * @param state
     *            the state to set
     */
    public final void setState(final int state) {

        this.state = state;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {

        final StringBuilder builder = new StringBuilder();
        builder.append("RelayPark [name=");
        builder.append(name);
        builder.append(", latitude=");
        builder.append(latitude);
        builder.append(", longitude=");
        builder.append(longitude);
        builder.append(", carParkAvailable=");
        builder.append(carParkAvailable);
        builder.append(", carParkCapacity=");
        builder.append(carParkCapacity);
        builder.append(", lastUpdate=");
        builder.append(lastUpdate);
        builder.append(", state=");
        builder.append(state);
        builder.append("]");
        return builder.toString();
    }

}
