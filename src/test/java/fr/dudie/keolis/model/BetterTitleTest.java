package fr.dudie.keolis.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import junitx.util.PrivateAccessor;

import org.junit.Before;
import org.junit.Test;

/**
 * Check behavior of {@link LineAlert#getBetterTitle()}.
 * 
 * @author Jérémie Huchet
 */
public final class BetterTitleTest {

    private LineAlert alert;

    @Before
    public final void setup() throws NoSuchFieldException {

        final List<String> lines = new ArrayList<String>();
        lines.add("1");
        lines.add("2");
        lines.add("3");

        alert = new LineAlert();
        PrivateAccessor.setField(alert, "lines", lines);

    }

    @Test
    public final void testWithRegularTitle() {

        alert.setTitle("1 2 3 problem");
        assertEquals(alert.getBetterTitle(), "Problem");
    }

    @Test
    public final void testTitleWithUnknownLine() {

        alert.setTitle("1 2 3 4 1 2 3 problem");
        assertEquals(alert.getBetterTitle(), "4 1 2 3 problem");
    }

    @Test
    public final void testWithEmptyTitle() {

        alert.setTitle("");
        assertEquals(alert.getBetterTitle(), "");
    }

    @Test
    public final void testWithBlankTitle() {

        alert.setTitle(" ");
        assertEquals(alert.getBetterTitle(), " ");
    }
}
