package fr.dudie.keolis.model;

import java.util.Date;

/**
 * Represents a relay park.
 * 
 * @author Jérémie Huchet
 */
public class RelayPark {

    /** The relay park name. */
    private String name;

    /** The relay park latitude. */
    private int latitude;

    /** The relay park longitude. */
    private int longitude;

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
     * Sets the name.
     * 
     * @param name
     *            the name to set
     */
    public final void setName(final String name) {

        this.name = name;
    }

    /**
     * Gets the latitude.
     * 
     * @return the latitude
     */
    public final int getLatitude() {

        return latitude;
    }

    /**
     * Sets the latitude.
     * 
     * @param latitude
     *            the latitude to set
     */
    public final void setLatitude(final int latitude) {

        this.latitude = latitude;
    }

    /**
     * Gets the longitude.
     * 
     * @return the longitude
     */
    public final int getLongitude() {

        return longitude;
    }

    /**
     * Sets the longitude.
     * 
     * @param longitude
     *            the longitude to set
     */
    public final void setLongitude(final int longitude) {

        this.longitude = longitude;
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
     * Sets the carParkAvailable.
     * 
     * @param carParkAvailable
     *            the carParkAvailable to set
     */
    public final void setCarParkAvailable(final int carParkAvailable) {

        this.carParkAvailable = carParkAvailable;
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
     * Sets the carParkCapacity.
     * 
     * @param carParkCapacity
     *            the carParkCapacity to set
     */
    public final void setCarParkCapacity(final int carParkCapacity) {

        this.carParkCapacity = carParkCapacity;
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
     *            the state to set (0: OPEN, 1: CLOSED, 2: FULL, 3: UNAVAILABLE)
     */
    public final void setState(final int state) {

        this.state = state;
    }

    /**
     * Gets the state.
     * 
     * @return the state
     */
    public final RelayParkState getState() {

        return RelayParkState.values()[state];
    }

}
