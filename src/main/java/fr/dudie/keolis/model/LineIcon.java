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
 * Contains informations to retrieve the icon of a transport line.
 * 
 * <pre>
 * "baseurl":"http:\/\/data.keolis-rennes.com\/uploads\/tx_icsinfotrafic\/",
 * "line":[
 *   {
 *     "name":"1",
 *     "picto":"LM1.png"
 *   }
 * ]
 * </pre>
 * 
 * @author Jérémie Huchet
 */
public class LineIcon {

    /** The line identifier. */
    @SerializedName("name")
    private String line;

    /** The icon's name. */
    @SerializedName("picto")
    private String iconName;

    /**
     * Gets the line identifier.
     * 
     * @return the line identifier
     */
    public final String getLine() {

        return line;
    }

    /**
     * Gets the icon's name.
     * 
     * @return the name of the icon
     */
    public final String getIconName() {

        return iconName;
    }

    /**
     * Sets the line.
     * 
     * @param line
     *            the line to set
     */
    public final void setLine(final String line) {

        this.line = line;
    }

    /**
     * Sets the iconName.
     * 
     * @param iconName
     *            the iconName to set
     */
    public final void setIconName(final String iconName) {

        this.iconName = iconName;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {

        final StringBuilder builder = new StringBuilder();
        builder.append("LineIcon [line=");
        builder.append(line);
        builder.append(", iconName=");
        builder.append(iconName);
        builder.append("]");
        return builder.toString();
    }

}
