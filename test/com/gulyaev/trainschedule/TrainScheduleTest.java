package com.gulyaev.trainschedule;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Optional;

public class TrainScheduleTest {

    @Test
    public void testAddTrain() {
        String name = "Train";
        int hours = 0;
        int minutes = 0;
        String endSt = "Street";
        TrainSchedule instance = new TrainSchedule();
        instance.addTrain(name, hours, minutes, endSt);
        ArrayList<Train> trSc = new ArrayList<>();
        trSc.add(new Train(name, hours, minutes, endSt));
        assertEquals(trSc, instance.trains);
    }

    @Test
    public void tryingToAddTrainWithWrongTimeFormat() {
        TrainSchedule instance = new TrainSchedule();
        try {
            instance.addTrain("Bombom", 27, 61, "Station");
        } catch (IllegalArgumentException e) {
            assertEquals("Wrong time format", e.getMessage());
        }
    }

    @Test
    public void tryingToAddTrainWithWrongNameFormat() {
        TrainSchedule instance = new TrainSchedule();
        try {
            instance.addTrain("", 19, 50, "Station");
        } catch (IllegalArgumentException e) {
            assertEquals("Wrong format", e.getMessage());
        }
        try {
            instance.addTrain(null, 19, 50, "Station");
        } catch (IllegalArgumentException e) {
            assertEquals("Wrong format", e.getMessage());
        }
    }

    @Test
    public void tryingToAddTrainWithWrongEndStationNameFormat() {
        TrainSchedule instance = new TrainSchedule();
        try {
            instance.addTrain("Train", 19, 50, "");
        } catch (IllegalArgumentException e) {
            assertEquals("Wrong format", e.getMessage());
        }
        try {
            instance.addTrain("Train", 19, 50, null);
        } catch (IllegalArgumentException e) {
            assertEquals("Wrong format", e.getMessage());
        }
    }

    @Test
    public void tryingToAddAlreadyExistingTrain() {
        String name = "Train";
        int hours = 0;
        int minutes = 0;
        String endSt = "Street";
        TrainSchedule instance = new TrainSchedule();
        instance.addTrain(name, hours, minutes, endSt);
        try {
            instance.addTrain(name, hours, minutes, endSt);
        } catch (IllegalArgumentException e) {
            assertEquals("An entry of such train already exists, please delete old entry first",
                    e.getMessage());
        }
    }

    @Test
    public void testDeleteTrain() {
        String name = "Sample1";
        int hours = 1;
        int minutes = 1;
        TrainSchedule instance = new TrainSchedule();
        instance.addTrain("Sample1", 1, 1, "Sample Town1");
        instance.addTrain("Sample2", 2, 2, "Sample Town2");
        instance.addTrain("Sample3", 3, 3, "Sample Town3");
        instance.deleteTrain(name, hours, minutes);
        ArrayList<Train> ar = new ArrayList<>();
        ar.add(new Train("Sample2", 2, 2, "Sample Town2"));
        ar.add(new Train("Sample3", 3, 3, "Sample Town3"));
        assertEquals(ar, instance.trains);
    }

    @Test
    public void makingSureThatTrainWasReallyDeleted() {
        String name = "Sample1";
        int hours = 1;
        int minutes = 1;
        TrainSchedule instance = new TrainSchedule();
        instance.addTrain("Sample1", 1, 1, "Sample Town1");
        instance.addTrain("Sample2", 2, 2, "Sample Town2");
        assertEquals(true, instance.deleteTrain(name, hours, minutes));
    }

    @Test
    public void ifSuchTrainNeverExisted() {
        String name = "Sample3";
        int hours = 3;
        int minutes = 3;
        TrainSchedule instance = new TrainSchedule();
        instance.addTrain("Sample1", 1, 1, "Sample Town1");
        instance.addTrain("Sample2", 2, 2, "Sample Town2");
        assertEquals(false, instance.deleteTrain(name, hours, minutes));
    }

    @Test
    public void testFindTrain() {
        String destination = "Sample Town";
        int hours = 15;
        int minutes = 30;
        TrainSchedule instance = new TrainSchedule();
        instance.addTrain("Bombom1", 15, 45, "Station1");
        instance.trains.get(0).stations.add("Sample Town");
        instance.addTrain("Bombom2", 16, 30, "Sample Town");
        instance.addTrain("Bombom3", 15, 29, "Station3");
        Optional<Train> result = instance.findTrain(destination, hours, minutes);
        assertEquals(Optional.of(instance.trains.get(0)), result);
    }

    @Test
    public void ifThereIsNoAppropriateTrain() {
        String destination = "Sample Town";
        int hours = 15;
        int minutes = 30;
        TrainSchedule instance = new TrainSchedule();
        instance.addTrain("Bombom1", 15, 29, "Sample Town");
        instance.addTrain("Bombom2", 15, 31, "Station");
        assertEquals(Optional.empty(), instance.findTrain(destination, hours, minutes));
    }

}
