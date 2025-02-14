package ru.aston.model;

public class ParkingSpot implements ParkingObject{
    private int id;
    private int spotNumber;
    private boolean isAvailable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(int spotNumber) {
        this.spotNumber = spotNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "id=" + id +
                ", spotNumber=" + spotNumber +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
