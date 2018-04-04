package com.gulyaev.trainschedule;

import java.util.*;

public final class TrainSchedule {

    ArrayList<Train> trains;

    @Override
    public int hashCode() {

        return Objects.hash(trains);
    }

    TrainSchedule() {
        this.trains = new ArrayList<>();
    }

    /**
     * Simply adds new Train object to trains with all parameters required by constructor.
     *
     * @throws IllegalArgumentException if time format is invalid or in case of existence of a train with the same name
     *                                  and departure time.
     */
    public void addTrain(String name, int hours, int minutes, String endSt) {
        Train temp = Train.createTrain(name, hours, minutes, endSt);
        trains.forEach(train -> {
            if (train.equals(temp))
                throw new IllegalArgumentException(
                        "An entry of such train already exists, please delete old entry first");
        });
        trains.add(temp);
    }

    /**
     * Deletes train by name and departure time
     */
    public boolean deleteTrain(String name, int hours, int minutes) {
        int i = 0;
        boolean flag = false;
        Train temp = new Train(name, hours, minutes, "Any Street");
        while (i < trains.size()) {
            Train train = trains.get(i);
            if (train.equals(temp)) {
                trains.remove(i);
                flag = true;
            } else i++;
        }
        return flag;
    }

    /**
     * Seeks for train by station and current time.
     *
     * @param destination - needed station.
     * @param hours       - current hours.
     * @param minutes     - current minutes. These two parameters computed into one which is compared to each train's
     *                    departure time.
     * @return Train object which departure time is the closest to current time or null if such train doesn't exist.
     */
    public Optional<Train> findTrain(String destination, int hours, int minutes) {
        int currentTime = 60 * hours + minutes;
        int min = currentTime;
        Optional<Train> wanted = Optional.empty();
        for (int i = 0; i < trains.size(); i++) {
            Train temp = trains.get(i);
            if (temp.departure - currentTime > 0
                    && temp.departure - currentTime < min
                    && (temp.stations.contains(destination) || temp.endStation.equals(destination))) {
                min = temp.departure - currentTime;
                wanted = Optional.of(temp);
            }
        }
        return wanted;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || object.getClass() != this.getClass()) return false;
        if (object instanceof TrainSchedule) {
            TrainSchedule other = (TrainSchedule) object;
            return this.trains.equals(other.trains);
        }
        return false;
    }
}
