package ru.aston.dao;

import ru.aston.db.DBConnection;
import ru.aston.model.ParkingSpot;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingSpotDao implements SimpleDao<ParkingSpot> {
    private final Connection connection;

    public ParkingSpotDao() {
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(ParkingSpot record) {
        String query = "INSERT INTO parking_spots(spot_number, is_available) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, record.getSpotNumber());
            ps.setBoolean(2, record.isAvailable());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to insert ParkingSpot record: " + e.getMessage());
        }
    }

    @Override
    public List<ParkingSpot> findAll() {
        String query = "SELECT * FROM parking_spots";
        List<ParkingSpot> parkingSpots = new ArrayList<>();
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                ParkingSpot parkingSpot = new ParkingSpot();
                parkingSpot.setId(rs.getInt("id"));
                parkingSpot.setSpotNumber(rs.getInt("spot_number"));
                parkingSpot.setAvailable(rs.getBoolean("is_available"));
                parkingSpots.add(parkingSpot);
            }
        } catch (SQLException e) {
            System.out.println("Failed to query ParkingSpot records: " + e.getMessage());
        }
        return parkingSpots;
    }

    @Override
    public Optional<ParkingSpot> findById(int id) {
        String query = "SELECT id, spot_number, is_available FROM parking_spots WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    ParkingSpot parkingSpot = new ParkingSpot();
                    parkingSpot.setId(resultSet.getInt("id"));
                    parkingSpot.setSpotNumber(resultSet.getInt("spot_number"));
                    parkingSpot.setAvailable(resultSet.getBoolean("is_available"));

                    return Optional.of(parkingSpot);
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to query ParkingSpot by Id: " + e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public void deleteById(int id) {
        String query = "DELETE FROM parking_spots WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to delete parking_spot by ID: " + id +" " + e.getMessage());
        }
    }
}
