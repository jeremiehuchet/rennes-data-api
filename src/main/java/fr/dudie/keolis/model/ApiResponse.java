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

        StringBuilder builder = new StringBuilder();
        builder.append("ApiResponse [opendata=");
        builder.append(opendata);
        builder.append("]");
        return builder.toString();
    }

}
