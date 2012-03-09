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
 * Class representing the "answer" field of keolis response.
 * 
 * @author Olivier Boudet
 * @param <T>
 *            type of data
 */
public class Answer<T> {

    /** Status field. */
    private Status status;

    /** Data field. */
    private T data;

    /**
     * Gets the data field.
     * 
     * @return the data field.
     */
    public final T getData() {

        return data;
    }

    /**
     * Gets the status field.
     * 
     * @return the status field.
     */
    public final Status getStatus() {

        return status;
    }

    /**
     * Sets the status.
     * 
     * @param status
     *            the status to set
     */
    public final void setStatus(final Status status) {

        this.status = status;
    }

    /**
     * Sets the data.
     * 
     * @param data
     *            the data to set
     */
    public final void setData(final T data) {

        this.data = data;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {

        final StringBuilder builder = new StringBuilder();
        builder.append("Answer [status=");
        builder.append(status);
        builder.append(", data=");
        builder.append(data);
        builder.append("]");
        return builder.toString();
    }

}
