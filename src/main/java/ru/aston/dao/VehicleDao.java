package ru.aston.dao;

import ru.aston.db.DBConnection;
import ru.aston.model.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VehicleDao implements SimpleDao<Vehicle> {
    private Connection connection;

    public VehicleDao() {
        try{
            connection = DBConnection.getInstance().getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void save (Vehicle rec) {
        String sql = "INSERT INTO vehicle (plate, model, release_year) VALUES (?, ?, ?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, rec.getPlate());
            ps.setString(2, rec.getModel());
            ps.setString(3, rec.getRelease_year());
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        String deleteByIdSql = "DELETE FROM vehicle WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(deleteByIdSql)){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to delete by id" + e.getMessage());
        }
    }

    @Override
    public List<Vehicle> findAll(){
        String findAllSql = "SELECT * FROM vehicle";
        List<Vehicle> vehicles = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(findAllSql);
            ResultSet result = ps.executeQuery()){

            while(result.next()){
                Vehicle veh = new Vehicle();
                veh.setId(result.getInt("id"));
                veh.setPlate(result.getString("plate"));
                veh.setModel(result.getString("model"));
                veh.setRelease_year(result.getString("release_year"));
                vehicles.add(veh);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return vehicles;
    }

    @Override
    public Optional<Vehicle> findById(int id){
        String findByIdSql = "SELECT * FROM vehicle WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(findByIdSql)){
            ps.setInt(1, id);
            try(ResultSet result = ps.executeQuery()){
                if(result.next()){
                    Vehicle veh = new Vehicle();
                    veh.setId(result.getInt("id"));
                    veh.setPlate(result.getString("plate"));
                    veh.setModel(result.getString("model"));
                    veh.setRelease_year(result.getString("release_year"));
                    return Optional.of(veh);
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to find by id" + e.getMessage());
        }
        return Optional.empty();
    }

}
