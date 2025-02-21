package ru.aston.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "tickets")
public class Ticket implements ParkingObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "parking_time_in_hours",nullable = false)
    private int parkingTimeInHours;
    @Column(name = "start_of_parking",nullable = false)
    private Timestamp startOfParking;
    @Column(name = "end_of_parking",nullable = false)
    private Timestamp endOfParking;

    @ManyToOne
    @JoinColumn(name = "vehicle_id",nullable = false)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "parking_spot_id",nullable = false)
    private ParkingSpot parkingSpot;

    public Ticket() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public int getParkingTimeInHours() {
        return parkingTimeInHours;
    }

    public void setParkingTimeInHours(int parkingTimeInHourse) {
        this.parkingTimeInHours = parkingTimeInHourse;
        setStartOfParking();
    }

    public Timestamp getStartOfParking() {
        return startOfParking;
    }

    public void setStartOfParking(Timestamp time) {
        this.startOfParking = time;
    }

    public void setStartOfParking() {
        this.startOfParking = new Timestamp(System.currentTimeMillis());
        setEndOfParking(parkingTimeInHours);
    }

    public Timestamp getEndOfParking() {
        return endOfParking;
    }

    public void setEndOfParking(int parkingTimeInHours) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.endOfParking = new Timestamp(timestamp.getTime() + (long) parkingTimeInHours * 60 * 60 * 1000);
    }

    public void setEndOfParking(Timestamp time) {
        this.endOfParking = time;
    }

    @Override
    public String toString() {
        return "\n" + "Ticket{" +
                "id=" + id +
                ", vehicle_id=" + vehicle.getId() +
                ", park_spot_id=" + parkingSpot.getId() +
                ", parking_time_in_hours=" + parkingTimeInHours +
                ", start_of_parking=" + startOfParking +
                ", end_of_parking=" + endOfParking +
                '}';
    }
}
