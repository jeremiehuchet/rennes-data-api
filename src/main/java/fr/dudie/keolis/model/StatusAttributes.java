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
 * Class representing attributes field in Keolis Api response.
 * 
 * @author Olivier Boudet
 */
public class StatusAttributes {

    /** The code. */
    private int code;

    /** The message. */
    private String message;

    /**
     * Gets the code.
     * 
     * @return the code
     */
    public final int getCode() {

        return code;
    }

    /**
     * Sets the code.
     * 
     * @param code
     *            the code to set
     */
    public final void setCode(final int code) {

        this.code = code;
    }

    /**
     * Gets the message.
     * 
     * @return the message
     */
    public final String getMessage() {

        return message;
    }

    /**
     * Sets the message.
     * 
     * @param message
     *            the message to set
     */
    public final void setMessage(final String message) {

        this.message = message;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {

        final StringBuilder builder = new StringBuilder();
        builder.append("StatusAttributes [code=");
        builder.append(code);
        builder.append(", message=");
        builder.append(message);
        builder.append("]");
        return builder.toString();
    }

}
