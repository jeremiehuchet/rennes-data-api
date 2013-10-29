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
package fr.dudie.keolis.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import junit.framework.TestCase;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract class to write tests using a {@link JsonKeolisClient}.
 * 
 * @author Jérémie Huchet
 */
public abstract class AbstractJsonKeolisClientTest{

    /** The event logger. */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(AbstractJsonKeolisClientTest.class);

    /** Path to the properties file. */
    private static final String PROPS_PATH = "/keolis-client-test.properties";

    /** Loaded properties. */
    private static final Properties PROPS = new Properties();

    /** The tested keolis client. */
    private static JsonKeolisClient keolisClient;

    /**
     * Instantiates the test.
     * 
     * @throws IOException
     *             an error occurred durign initialization
     */
    public AbstractJsonKeolisClientTest() throws IOException {

        LOGGER.info("Loading configuration file {}", PROPS_PATH);
        final InputStream in = AbstractJsonKeolisClientTest.class.getResourceAsStream(PROPS_PATH);
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
     * Gets the json keolis client.
     * 
     * @return the json keolis client
     */
    public static final JsonKeolisClient getKeolisClient() {

        return keolisClient;
    }

}
