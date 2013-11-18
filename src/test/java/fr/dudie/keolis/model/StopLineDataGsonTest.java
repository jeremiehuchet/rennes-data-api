package fr.dudie.keolis.model;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.reflect.TypeToken;

import fr.dudie.keolis.client.KeoUtils;

public class StopLineDataGsonTest {

    private InputStream JSON_DATA;

    private Calendar expectedDate, expectedDepartureDate;

    @Before
    public void openJsonData() {
        JSON_DATA = getClass().getResourceAsStream("busnextdepartures-sample.json");
    }

    @After
    public void closeJsonData() throws IOException {
        JSON_DATA.close();
    }

    @Before
    public void initializeExpectedDates() {
        // "2013-11-18T00:44:41+01:00"
        expectedDate = Calendar.getInstance();
        expectedDate.set(2013, 10, 18, 0, 44, 41);
        expectedDate.set(Calendar.MILLISECOND, 0);

        // "2013-11-18T00:46:19+01:00"
        expectedDepartureDate = Calendar.getInstance();
        expectedDepartureDate.set(2013, 10, 18, 0, 46, 19);
        expectedDepartureDate.set(Calendar.MILLISECOND, 0);
    }

    @Test
    public void canDeserializeStopLineData() {

        final Type apiResponseType = new TypeToken<ApiResponse<StopLineData>>() {
        }.getType();
        final ApiResponse<StopLineData> data = KeoUtils.getGsonInstance().fromJson(new InputStreamReader(JSON_DATA), apiResponseType);

        final StopLineData sld = data.getOpendata().getAnswer().getData();

        assertEquals(expectedDate.getTime(), sld.getServerDateTime());

        assertEquals(18, sld.getRawStopLines().size());
        assertEquals(18, sld.getStopLines().size());

        final StopLine firstRaw = sld.getRawStopLines().get(0);
        assertExpectedFirstStopLine(firstRaw);
        assertExpectedFirstStopLineRawDeparture(firstRaw.getDepartures().get(0));

        final StopLine first = sld.getStopLines().get(0);
        assertExpectedFirstStopLine(first);
    }

    private void assertExpectedFirstStopLine(final StopLine sl) {
        assertEquals("1041", sl.getStop());
        assertEquals("0008", sl.getRoute());
        assertEquals(1, sl.getDirection());
        assertEquals(1, sl.getDepartures().size());
    }

    private void assertExpectedFirstStopLineRawDeparture(final StopLineDeparture sld) {
        assertEquals(expectedDepartureDate.getTime(), sld.getDate());
        assertEquals("Saint-Grégoire | Champ Daguet", sld.getHeadsign());
    }
}
