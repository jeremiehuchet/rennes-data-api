package fr.dudie.keolis.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import junit.framework.TestCase;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.dudie.keolis.model.BikeStation;
import fr.dudie.keolis.model.LineIcon;

/**
 * Test class for {@link JsonKeolisClient}.
 * 
 * @author Jérémie Huchet
 */
public class JsonKeolisClientTest extends TestCase {

    /** The event logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKeolisClientTest.class);

    /** Path to the properties file. */
    private static final String PROPS_PATH = "/keolis-client-test.properties";

    /** Loaded properties. */
    private static final Properties PROPS = new Properties();

    /** The tested keolis client. */
    private static JsonKeolisClient keolisClient;

    /**
     * Instantiates the test.
     * 
     * @param name
     *            the test name
     * @throws IOException
     *             an error occurred durign initialization
     */
    public JsonKeolisClientTest(final String name) throws IOException {

        super(name);

        LOGGER.info("Loading configuration file {}", PROPS_PATH);
        final InputStream in = JsonKeolisClientTest.class.getResourceAsStream(PROPS_PATH);
        PROPS.load(in);

        LOGGER.info("Preparing http client");
        final SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("http", new PlainSocketFactory(), 80));
        final ClientConnectionManager connexionManager = new SingleClientConnManager(null, registry);
        final HttpClient httpClient = new DefaultHttpClient(connexionManager, null);

        final String url = PROPS.getProperty("keolis.api.url");
        final String key = PROPS.getProperty("keolis.api.key");
        keolisClient = new JsonKeolisClient(httpClient, url, key);

    }

    /**
     * Test method for {@link JsonKeolisClient#getAllBikeStations()}.
     * 
     * @throws IOException
     *             an error occurred
     */
    @Test
    public final void testGetBikeStations() throws IOException {

        LOGGER.info("testGetBikeStations.start");

        List<BikeStation> stations = null;

        stations = keolisClient.getAllBikeStations();

        assertNotNull("no bike stations returned by the api", stations);
        assertTrue("at least one bike station should be returned by the api", stations.size() > 0);
        assertEquals("on November, 27th 2010, the keolis API returns 83 bike stations", 83,
                stations.size());

        for (final BikeStation station : stations) {
            LOGGER.debug("checking {}", station);
            assertFalse(String.format("station [%s] has no id", station),
                    StringUtils.isEmpty(station.getId()));
            assertFalse(String.format("station [%s] has no name", station),
                    StringUtils.isEmpty(station.getName()));
            assertNotNull(String.format("station [%s] has latitude", station.getLatitude()),
                    station.getLatitude());
            assertNotNull(String.format("station [%s] has longitude", station.getLongitude()),
                    station.getLongitude());
        }

        LOGGER.info("testGetBikeStations.end");
    }

    /**
     * Test method for {@link JsonKeolisClient#getAllLineIcons()}.
     * 
     * @throws IOException
     *             an error occurred
     */
    @Test
    public final void testGetLineIcons() throws IOException {

        LOGGER.info("testGetLineIcons.start");

        List<LineIcon> icons = null;

        icons = keolisClient.getAllLineIcons();

        assertNotNull("no line icons returned by the api", icons);
        assertTrue("at least one line icon should be returned by the api", icons.size() > 0);
        assertEquals("on June, 29th 2010, the keolis API returns 70 line icons", 70, icons.size());

        for (final LineIcon icon : icons) {
            LOGGER.debug("checking {}", icon);
            assertFalse(String.format("station [%s] has no URL", icon),
                    StringUtils.isEmpty(icon.getIconUrl()));
            assertFalse(String.format("station [%s] has no line name", icon),
                    StringUtils.isEmpty(icon.getLine()));
        }

        LOGGER.info("testGetLineIcons.end");
    }
}
