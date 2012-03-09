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

/**
 * Class representing a Keolis Api Reponse.
 * 
 * @author Olivier Boudet
 * @param <T>
 */
public class ApiResponse<T> {

    /** The opendata field. */
    private OpenData<T> opendata;

    /**
     * Gets the opendata field.
     * 
     * @return the opendata field.
     */
    public final OpenData<T> getOpendata() {

        return opendata;
    }

    /**
     * Sets the opendata.
     * 
     * @param opendata
     *            the opendata to set
     */
    public final void setOpendata(final OpenData<T> opendata) {

        this.opendata = opendata;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {

        final StringBuilder builder = new StringBuilder();
        builder.append("ApiResponse [opendata=");
        builder.append(opendata);
        builder.append("]");
        return builder.toString();
    }

}
