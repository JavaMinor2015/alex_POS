package javaminor.util;

import javaminor.domain.abs.ScanItem;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by alex on 9/30/15.
 */
public class PopulatorTest extends TestCase {

    // will work without database

    private Populator populator;

    @Before
    public void setUp() {
        populator = new Populator();
        populator.populate();
    }

    @Test
    public void testPopulate() throws Exception {
        populator.populate();
        assertThat(populator.getAllScanItems().size(), not(0));
    }

    @Test
    public void testGetAllScanItems() throws Exception {
        assertThat(populator.getAllScanItems().size(), notNullValue());
        assertThat(populator.getAllScanItems().get(0), isA(ScanItem.class));
        assertThat(populator.getAllScanItems().get(0).getName(), notNullValue());
    }

    @Test
    public void testGetRandomSelectionFromProductList() throws Exception {
        assertTrue(populator.getRandomSelectionFromProductList(5).size() < 6);
        assertThat(populator.getRandomSelectionFromProductList(5).get(0), notNullValue());
        assertThat(populator.getRandomSelectionFromProductList(5).get(1), notNullValue());
    }

    @Test
    public void testGetRandomFidelityCard() throws Exception {
        assertThat(populator.getRandomFidelityCard(),notNullValue());
    }

    @Test
    public void testGetScanItemsList() throws Exception {
        assertThat(populator.getScanItemsList(),notNullValue());
    }

    @Test
    public void testGetFidelityCardList() throws Exception {
        assertThat(populator.getFidelityCardList(),notNullValue());
    }
}