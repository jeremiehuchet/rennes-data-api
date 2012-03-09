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
public class RelayParkData {

    /** The list of relay parks. */
    @SerializedName("relaypark")
    private List<RelayPark> relayparks;

    /**
     * Gets the list of relay parks.
     * 
     * @return the list of relay parks.
     */
    public final List<RelayPark> getRelayParks() {

        return relayparks;
    }

    /**
     * Gets the relayparks.
     * 
     * @return the relayparks
     */
    public final List<RelayPark> getRelayparks() {

        return relayparks;
    }

    /**
     * Sets the relayparks.
     * 
     * @param relayparks
     *            the relayparks to set
     */
    public final void setRelayparks(final List<RelayPark> relayparks) {

        this.relayparks = relayparks;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {

        final StringBuilder builder = new StringBuilder();
        builder.append("RelayParkData [relayparks=");
        builder.append(relayparks);
        builder.append("]");
        return builder.toString();
    }
}
