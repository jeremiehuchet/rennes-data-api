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
public class LineIconData {

    /** The base URL to fetch icon from. */
    private String baseUrl;

    /** The list of lines and icon files names. */
    @SerializedName("line")
    private List<LineIcon> lines;

    /**
     * Gets the baseUrl.
     * 
     * @return the baseUrl
     */
    public final String getBaseUrl() {

        return baseUrl;
    }

    /**
     * Gets the lines.
     * 
     * @return the lines
     */
    public final List<LineIcon> getLines() {

        return lines;
    }

    /**
     * Sets the baseUrl.
     * 
     * @param baseUrl
     *            the baseUrl to set
     */
    public final void setBaseUrl(final String baseUrl) {

        this.baseUrl = baseUrl;
    }

    /**
     * Sets the lines.
     * 
     * @param lines
     *            the lines to set
     */
    public final void setLines(final List<LineIcon> lines) {

        this.lines = lines;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {

        final StringBuilder builder = new StringBuilder();
        builder.append("LineIconData [baseUrl=");
        builder.append(baseUrl);
        builder.append(", line=");
        builder.append(lines);
        builder.append("]");
        return builder.toString();
    }

}
