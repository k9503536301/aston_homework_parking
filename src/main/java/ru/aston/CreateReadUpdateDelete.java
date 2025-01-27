package ru.aston;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreateReadUpdateDelete {
    public static final String GET_ALL = "SELECT * FROM tickets";
    private static final String INSERT_Ticket = "INSERT INTO tickets(user_id, car_id, park_spot_id, parking_time_in_hours, start_of_parking, end_of_parking) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_Ticket = "UPDATE tickets SET parking_time_in_hours = ? WHERE id = ?";
    private static final String DELETE_Ticket = "DELETE FROM tickets WHERE id = ?";

    public static List<Ticket> getTicketData() {
        List<Ticket> tickets = new ArrayList<>();

        try (Connection connection = DataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("user_id");
                int carId = resultSet.getInt("car_id");
                int parkSpotId = resultSet.getInt("park_spot_id");
                int parkingTimeInHours = resultSet.getInt("parking_time_in_hours");
                Timestamp startOfParking = resultSet.getTimestamp("start_of_parking");
                Timestamp endOfParking = resultSet.getTimestamp("end_of_parking");

                tickets.add(new Ticket(id, userId, carId, parkSpotId, parkingTimeInHours, startOfParking, endOfParking));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tickets;
    }

    public static List<Ticket> insertTicket(Ticket ticket) {
        List<Ticket> tickets = new ArrayList<>();

        System.out.println(ticket);

        try (Connection connection = DataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_Ticket)) {

            preparedStatement.setInt(1, ticket.getUserId());
            preparedStatement.setInt(2, ticket.getCarId());
            preparedStatement.setInt(3, ticket.getParkSpotId());
            preparedStatement.setInt(4, ticket.getParkingTimeInHours());
            preparedStatement.setTimestamp(5, ticket.getStartOfParking());
            preparedStatement.setTimestamp(6, ticket.getEndOfParking());
            preparedStatement.executeUpdate();

            PreparedStatement allTicket = connection.prepareStatement("SELECT * FROM tickets");
            System.out.println(allTicket);
            ResultSet resultSet = allTicket.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("user_id");
                int carId = resultSet.getInt("car_id");
                int parkSpotId = resultSet.getInt("park_spot_id");
                int parkingTimeInHours = resultSet.getInt("parking_time_in_hours");
                Timestamp startOfParking = resultSet.getTimestamp("start_of_parking");
                Timestamp endOfParking = resultSet.getTimestamp("end_of_parking");

                tickets.add(new Ticket(id, userId, carId, parkSpotId, parkingTimeInHours, startOfParking, endOfParking));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tickets;
    }

    public static List<Ticket> deleteTicket(int TicketId) {
        List<Ticket> updateTicket = new ArrayList<>();
        try (Connection connection = DataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_Ticket)) {

            preparedStatement.setInt(1, TicketId);
            preparedStatement.executeUpdate();

            PreparedStatement allTicket = connection.prepareStatement("SELECT * FROM tickets");
            ResultSet resultSet = allTicket.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("user_id");
                int carId = resultSet.getInt("car_id");
                int parkSpotId = resultSet.getInt("park_spot_id");
                int parkingTimeInHours = resultSet.getInt("parking_time_in_hours");
                Timestamp startOfParking = resultSet.getTimestamp("start_of_parking");
                Timestamp endOfParking = resultSet.getTimestamp("end_of_parking");

                updateTicket.add(new Ticket(id, userId, carId, parkSpotId, parkingTimeInHours, startOfParking, endOfParking));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updateTicket;
    }
}


