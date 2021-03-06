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

import com.google.gson.annotations.SerializedName;

/**
 * Class representing the status field in Keolis Api response.
 * 
 * @author Olivier Boudet
 */
public class Status {

    /** THe attributes field. */
    @SerializedName("@attributes")
    private StatusAttributes attributes;

    /**
     * Gets the attributes field.
     * 
     * @return the attributes field
     */
    public final StatusAttributes getAttributes() {

        return attributes;
    }

    /**
     * Sets the attributes.
     * 
     * @param attributes
     *            the attributes to set
     */
    public final void setAttributes(final StatusAttributes attributes) {

        this.attributes = attributes;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {

        final StringBuilder builder = new StringBuilder();
        builder.append("Status [attributes=");
        builder.append(attributes);
        builder.append("]");
        return builder.toString();
    }

}
