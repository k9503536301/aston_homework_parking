import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    private final String url = "jdbc:mysql://localhost:3306/";
    private final String user = "root";
    private final String password = "ps";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url + user, password, url);
    }

    // Создание нового пользователя
    public void save(User user) {
        String sql = "INSERT INTO users (username, email, phone_number, license_plate, parking_slot_number) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPhoneNumber());
            stmt.setString(4, user.getLicensePlate());
            stmt.setObject(5, user.getParkingSlotNumber(), Types.INTEGER);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving user", e);
        }
    }

    // Получение пользователя по ID
    public User findById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToUser(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding user by id", e);
        }
        return null;
    }

    // Получение всех пользователей
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all users", e);
        }
        return users;
    }

    // Удаление пользователя
    public void deleteById(int id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting user", e);
        }
    }

    // Вспомогательный метод для маппинга ResultSet -> User
    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPhoneNumber(rs.getString("phone_number"));
        user.setLicensePlate(rs.getString("license_plate"));
        user.setParkingSlotNumber(rs.getObject("parking_slot_number", Integer.class));
        user.setCreatedAt(rs.getTimestamp("created_at"));
        return user;
    }
}
