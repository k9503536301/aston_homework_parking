package ru.aston.model;

public class Vehicle implements ParkingObject {
    private int id;
    private String plate, model, release_year;

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
