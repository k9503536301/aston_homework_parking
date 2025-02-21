package ru.aston.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicles")
public class Vehicle implements ParkingObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String plate;
    @Column(nullable = false)
    private String model;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    public void setId(int id){this.id = id;}
    public int getId() {return id;}

    public void setPlate(String plate){this.plate = plate;}
    public String getPlate() {return plate;}

    public void setModel(String model){this.model = model;}
    public String getModel() {return model;}

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Vehicle {" +
                "id=" + id +
                ", plate=" + plate +
                ", model=" + model +
                ", ownerId=" + owner.getId() +
                '}';
    }
}
