package ru.aston.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "vehicle")
public class Vehicle implements ParkingObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String plate;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private String release_year;

    public void setId(int id){this.id = id;}
    public int getId() {return id;}

    public void setPlate(String plate){this.plate = plate;}
    public String getPlate() {return plate;}

    public void setModel(String model){this.model = model;}
    public String getModel() {return model;}

    public void setRelease_year(String release_year){this.release_year = release_year;}
    public String getRelease_year() {return release_year;}

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", plate=" + plate +
                ", model=" + model +
                ", release_year=" + release_year +
                '}';
    }
}
