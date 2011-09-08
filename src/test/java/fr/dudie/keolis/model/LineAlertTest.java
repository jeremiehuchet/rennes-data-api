package fr.dudie.keolis.model;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.dudie.keolis.client.AbstractJsonKeolisClientTest;

/**
 * Test the {@link LineAlert#getBetterTitle()} method.
 * 
 * @author Jérémie Huchet
 */
public final class LineAlertTest extends AbstractJsonKeolisClientTest {

    /** The event logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(LineAlertTest.class);

    /**
     * Constructor.
     * 
     * @param name
     *            the test name
     * @throws IOException
     *             an error occurred during initialization
     */
    public LineAlertTest(final String name) throws IOException {

        super(name);
    }

    /**
     * Test the {@link LineAlert#getBetterTitle()} method.
     * 
     * @throws IOException
     */
    @Test
    public void testBetterTitle() throws IOException {

        final List<LineAlert> allAlerts = getKeolisClient().getAllLinesAlerts();

        for (final LineAlert alert : allAlerts) {
            final String betterTitle = alert.getBetterTitle();
            assertNotNull("better title is not null", betterTitle);
            LOGGER.debug("before {}", alert.getTitle());
            LOGGER.debug("after  {}", betterTitle);
        }
    }
}
