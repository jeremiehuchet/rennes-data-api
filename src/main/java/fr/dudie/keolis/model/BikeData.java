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

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * @author Olivier Boudet
 */
public class BikeData {

    /** The station field. */
    @SerializedName("station")
    private List<BikeStation> stations;

    /**
     * Gets the bike stations.
     * 
     * @return the list of bike stations
     */
    public final List<BikeStation> getStations() {

        return stations;
    }

    /**
     * Sets the stations.
     * 
     * @param stations
     *            the stations to set
     */
    public final void setStations(final List<BikeStation> stations) {

        this.stations = stations;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {

        final StringBuilder builder = new StringBuilder();
        builder.append("BikeData [stations=");
        builder.append(stations);
        builder.append("]");
        return builder.toString();
    }

}
