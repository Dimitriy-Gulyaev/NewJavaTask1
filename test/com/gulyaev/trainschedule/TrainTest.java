package com.gulyaev.trainschedule;

import org.junit.Test;

import static com.gulyaev.trainschedule.Train.createTrain;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class TrainTest {

    @Test
    public void testAddStation() {
        Train tr = createTrain("Sample", 0, 0, "End Station");
        String station1 = "Sample Town 1";
        String station2 = "Sample Town 2";
        tr.addStation(station1);
        tr.addStation(station2);
        ArrayList<String> ar = new ArrayList<>();
        ar.add("Sample Town 1");
        ar.add("Sample Town 2");
        assertEquals(ar, tr.stations);
    }

    @Test
    public void tryingToAddSameStation() {
        Train tr = createTrain("Sample", 0, 0, "End Station");
        String station1 = "Sample Town 1";
        String station2 = "Sample Town 1";
        try {
            tr.addStation(station1);
            tr.addStation(station2);
        } catch (IllegalArgumentException e) {
            assertEquals("Train already has this station", e.getMessage());
        }
    }

    @Test
    public void tryingToAddEmptyStringToStations() {
        Train tr = createTrain("Sample", 0, 0, "End Station");
        String station1 = "";
        try {
            tr.addStation(station1);
        } catch (IllegalArgumentException e) {
            assertEquals("Wrong format", e.getMessage());
        }
    }

    @Test
    public void tryingToAddNullToStations() {
        Train tr = createTrain("Sample", 0, 0, "End Station");
        try {
            tr.addStation(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Wrong format", e.getMessage());
        }
    }

    @Test
    public void testDeleteStation() {
        Train tr = createTrain("Sample", 0, 0, "End Station");
        String station1 = "Sample Town 1";
        String station2 = "Sample Town 2";
        tr.addStation(station1);
        tr.addStation(station2);
        tr.deleteStation(station1);
        ArrayList<String> ar = new ArrayList<>();
        ar.add("Sample Town 2");
        assertEquals(ar, tr.stations);
    }

    @Test
    public void tryingToDeleteStationThatNeverExisted() {
        Train tr = createTrain("Sample", 0, 0, "End Station");
        assertEquals(false, tr.deleteStation("dog"));
    }

    @Test
    public void tryingToDeleteNullStation() {
        Train tr = createTrain("Sample", 0, 0, "End Station");
        try {
            tr.deleteStation(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Wrong format", e.getMessage());
        }
    }

    @Test
    public void tryingToDeleteEmptyStation() {
        Train tr = createTrain("Sample", 0, 0, "End Station");
        try {
            tr.deleteStation("");
        } catch (IllegalArgumentException e) {
            assertEquals("Wrong format", e.getMessage());
        }
    }
}
