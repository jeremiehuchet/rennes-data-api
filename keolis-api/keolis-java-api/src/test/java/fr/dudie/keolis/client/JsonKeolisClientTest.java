package fr.dudie.keolis.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import junit.framework.TestCase;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
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
import fr.dudie.keolis.model.LineAlert;
import fr.dudie.keolis.model.LineIcon;
import fr.dudie.keolis.model.RelayPark;

/**
 * Test class for {@link JsonKeolisClient}.
 * 
 * @author Jérémie Huchet
 */
public final class JsonKeolisClientTest extends TestCase {

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
    public void testGetBikeStations() throws IOException {

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
    public void testGetLineIcons() throws IOException {

        LOGGER.info("testGetLineIcons.start");

        List<LineIcon> icons = null;

        icons = keolisClient.getAllLineIcons();

        assertNotNull("no line icons returned by the api", icons);
        assertTrue("at least one line icon should be returned by the api", icons.size() > 0);
        assertEquals("on September, 3rd 2011, the keolis API returns 130 line icons", 130,
                icons.size());

        for (final LineIcon icon : icons) {
            LOGGER.debug("checking {}", icon);
            assertFalse(String.format("station [%s] has no URL", icon),
                    StringUtils.isEmpty(icon.getIconUrl()));
            assertFalse(String.format("station [%s] has no line name", icon),
                    StringUtils.isEmpty(icon.getLine()));
        }

        LOGGER.info("testGetLineIcons.end");
    }

    /**
     * Test method for {@link JsonKeolisClient#getAllRelayParks()}.
     * 
     * @throws IOException
     *             an error occurred
     */
    @Test
    public void testGetAllRelayParks() throws IOException {

        LOGGER.info("testGetAllRelayParks.start");

        List<RelayPark> parks = null;

        parks = keolisClient.getAllRelayParks();

        assertNotNull("no relay parks returned by the api", parks);
        assertTrue("at least one park should be returned by the api", parks.size() > 0);
        assertEquals("on September, 3rd 2011, the keolis API returns 4 relay parks", 4,
                parks.size());

        for (final RelayPark park : parks) {
            LOGGER.debug("{}", ToStringBuilder.reflectionToString(park));
        }

        LOGGER.info("testGetAllRelayParks.end");
    }

    /**
     * Test method for {@link JsonKeolisClient#getRelayParksNearFrom(int, int)}.
     * 
     * @throws IOException
     *             an error occurred
     */
    @Test
    public void testRelayParksNearFrom() throws IOException {

        LOGGER.info("testRelayParksNearFrom.start");

        List<RelayPark> parks = null;

        parks = keolisClient.getRelayParksNearFrom(1600000, 1600000);

        assertNotNull("no relay parks returned by the api", parks);
        assertTrue("at least one park should be returned by the api", parks.size() > 0);
        assertEquals("on September, 3rd 2011, the keolis API returns 4 relay parks", 4,
                parks.size());

        for (final RelayPark park : parks) {
            LOGGER.debug("{}", ToStringBuilder.reflectionToString(park));
        }

        LOGGER.info("testRelayParksNearFrom.end");
    }

    /**
     * Test method for {@link JsonKeolisClient#getAllLinesAlerts()}.
     * 
     * @throws IOException
     *             an error occurred
     */
    @Test
    public void testGetAllLineAlerts() throws IOException {

        LOGGER.info("testGetAllLineAlerts.start");

        List<LineAlert> alerts = null;

        alerts = keolisClient.getAllLinesAlerts();

        assertNotNull("no alert returned by the api", alerts);
        assertTrue("at least one alert should be returned by the api", alerts.size() > 0);

        for (final LineAlert alert : alerts) {
            LOGGER.debug("{}", ToStringBuilder.reflectionToString(alert));
        }

        LOGGER.info("testGetAllLineAlerts.end");
    }

    /**
     * Test method for {@link JsonKeolisClient#getLinesAlertsForLine(String)} giving a line having
     * an alert.
     * 
     * @throws IOException
     *             an error occurred
     */
    @Test
    public void testGetLinesAlertsForLineWithAlert() throws IOException {

        LOGGER.info("testGetLinesAlertsForLineWithAlert.start");

        List<LineAlert> alerts = null;

        alerts = keolisClient.getLinesAlertsForLine("59");

        assertNotNull("an alert should be returned for line 59", alerts);
        assertTrue("at least one alert should be returned by the api", alerts.size() > 0);

        for (final LineAlert alert : alerts) {
            LOGGER.debug("{}", ToStringBuilder.reflectionToString(alert));
        }

        LOGGER.info("testGetLinesAlertsForLineWithAlert.start");
    }

    /**
     * Test method for {@link JsonKeolisClient#getLinesAlertsForLine(String)} giving an unexisting
     * line.
     * 
     * @throws IOException
     *             an error occurred
     */
    @Test
    public void testGetLinesAlertsForUnexistingLine() throws IOException {

        LOGGER.info("testGetLinesAlertsForUnexistingLine.start");

        List<LineAlert> alerts = null;

        alerts = keolisClient.getLinesAlertsForLine("unexistingLine");

        assertNull("no alert should be returned for line 'unexistingLine'", alerts);

        LOGGER.info("testGetLinesAlertsForUnexistingLine.start");
    }
}
