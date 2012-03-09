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

import com.google.gson.annotations.SerializedName;

/**
 * Bean representing a bike station.
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
public class BikeStation {

    /** The identifier of the station. */
    @SerializedName("number")
    private String id;

    /** The name of the station. */
    @SerializedName("name")
    private String name;

    /** The address of the station. */
    @SerializedName("address")
    private String address;

    /** The state of the station. */
    @SerializedName("state")
    private boolean active;

    /** The latitude of the station. */
    @SerializedName("latitude")
    private double latitude;

    /** The longitude of the station. */
    @SerializedName("longitude")
    private double longitude;

    /** The amount of available slots in the station. */
    @SerializedName("slotsavailable")
    private int availableSlots;

    /** The amount of available bikes in the station. */
    @SerializedName("bikesavailable")
    private int availableBikes;

    /** The district of the station. */
    @SerializedName("district")
    private String district;

    /** The last update date of these informations. */
    @SerializedName("lastupdate")
    private Date lastUpdate;

    /** True if this bike station is a the point of sale. */
    @SerializedName("pos")
    private boolean pos;

    /**
     * Gets the identifier of the station.
     * 
     * @return the identifier of the station
     */
    public final String getId() {

        return id;
    }

    /**
     * Gets the name of the station.
     * 
     * @return the name of the station
     */
    public final String getName() {

        return name;
    }

    /**
     * Gets the address of the station.
     * 
     * @return the address of the station
     */
    public final String getAddress() {

        return address;
    }

    /**
     * Gets the state of the station.
     * 
     * @return the state of the station
     */
    public final boolean isActive() {

        return active;
    }

    /**
     * Gets the latitude of the station.
     * 
     * @return the latitude of the station
     */
    public final double getLatitude() {

        return latitude;
    }

    /**
     * Gets the longitude of the station.
     * 
     * @return the longitude of the station
     */
    public final double getLongitude() {

        return longitude;
    }

    /**
     * Gets the amount of available slots in the station.
     * 
     * @return the amount of available slots in the station
     */
    public final int getAvailableSlots() {

        return availableSlots;
    }

    /**
     * Gets the amount of available bikes in the station.
     * 
     * @return the amount of available bikes in the station
     */
    public final int getAvailableBikes() {

        return availableBikes;
    }

    /**
     * Gets the district of the station.
     * 
     * @return the district of the station
     */
    public final String getDistrict() {

        return district;
    }

    /**
     * Gets the last update date of these informations.
     * 
     * @return the last update date of these informations
     */
    public final Date getLastUpdate() {

        return lastUpdate;
    }

    /**
     * Returns true if this bike station is a the point of sale.
     * 
     * @return true if this bike station is a the point of sale else false
     */
    public final boolean isPos() {

        return pos;
    }

    /**
     * Sets the id.
     * 
     * @param id
     *            the id to set
     */
    public final void setId(final String id) {

        this.id = id;
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
     * Sets the address.
     * 
     * @param address
     *            the address to set
     */
    public final void setAddress(final String address) {

        this.address = address;
    }

    /**
     * Sets the active.
     * 
     * @param active
     *            the active to set
     */
    public final void setActive(final boolean active) {

        this.active = active;
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
     * Sets the availableSlots.
     * 
     * @param availableSlots
     *            the availableSlots to set
     */
    public final void setAvailableSlots(final int availableSlots) {

        this.availableSlots = availableSlots;
    }

    /**
     * Sets the availableBikes.
     * 
     * @param availableBikes
     *            the availableBikes to set
     */
    public final void setAvailableBikes(final int availableBikes) {

        this.availableBikes = availableBikes;
    }

    /**
     * Sets the district.
     * 
     * @param district
     *            the district to set
     */
    public final void setDistrict(final String district) {

        this.district = district;
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
     * Sets the pos.
     * 
     * @param pos
     *            the pos to set
     */
    public final void setPos(final boolean pos) {

        this.pos = pos;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {

        final StringBuilder builder = new StringBuilder();
        builder.append("BikeStation [id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(name);
        builder.append(", address=");
        builder.append(address);
        builder.append(", active=");
        builder.append(active);
        builder.append(", latitude=");
        builder.append(latitude);
        builder.append(", longitude=");
        builder.append(longitude);
        builder.append(", availableSlots=");
        builder.append(availableSlots);
        builder.append(", availableBikes=");
        builder.append(availableBikes);
        builder.append(", district=");
        builder.append(district);
        builder.append(", lastUpdate=");
        builder.append(lastUpdate);
        builder.append(", pos=");
        builder.append(pos);
        builder.append("]");
        return builder.toString();
    }
}
