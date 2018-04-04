package com.gulyaev.trainschedule;

import java.util.ArrayList;
import java.util.Objects;

public final class Train {

    @Override
    public int hashCode() {

        return Objects.hash(name, departure);
    }

    /**
     * Trains considered equal if their names and departure times are equal.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || object.getClass() != this.getClass()) return false;
        if (object instanceof Train) {
            Train other = (Train) object;
            return this.name.equals(other.name) && this.departure == other.departure;
        }
        return false;
    }

    final String name;
    final int departure;
    final String endStation;
    ArrayList<String> stations;

    /**
     * Constructor for Train object.
     * Hours and minutes are converted into one value for simplified comparison of Train objects.
     */
    Train(String s, int hours, int minutes, String e) {
        this.name = s;
        this.departure = 60 * hours + minutes;
        this.endStation = e;
        this.stations = new ArrayList<>();
    }

    private static boolean stringFormatCheck(String s) {
        if (s == null || s.isEmpty()) throw new IllegalArgumentException("Wrong format");
        return true;
    }

    public static Train createTrain(String s, int hours, int minutes, String e) {
        stringFormatCheck(s);
        stringFormatCheck(e);
        if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59)
            throw new IllegalArgumentException("Wrong time format");
        return new Train(s, hours, minutes, e);
    }

    String getName() {
        return this.name;
    }

    int getDeparture() {
        return this.departure;
    }

    String getEndStation() {
        return this.endStation;
    }

    ArrayList<String> getStations() {
        return this.stations;
    }

    public void addStation(String station) {
        stringFormatCheck(station);
        if (this.stations.contains(station)) throw new IllegalArgumentException("Train already has this station");
        else this.stations.add(station);
    }

    public boolean deleteStation(String station) {
        stringFormatCheck(station);
        return stations.remove(station);
    }
}
