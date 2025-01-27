import java.sql.Timestamp;

public class Model {

    private Long id;
    private Integer parkingSlotNumber;
    private String name;
    private String email;
    private String phoneNumber;
    private String licencensePlate;
    private Timestamp createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getParkingSlotNumber() {
        return parkingSlotNumber;
    }

    public void setParkingSlotNumber(Integer parkingSlotNumber) {
        this.parkingSlotNumber = parkingSlotNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLicencensePlate() {
        return licencensePlate;
    }

    public void setLicencensePlate(String licencensePlate) {
        this.licencensePlate = licencensePlate;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
