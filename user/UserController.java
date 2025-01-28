import java.util.List;

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void createUser(User user) {
        try {
            userService.addUser(user);
            System.out.println("User created successfully");
        } catch (Exception e) {
            System.err.println("Error creating user: " + e.getMessage());
        }
    }

    public void displayUser(int id) {
        try {
            User user = userService.getUserById(id);
            System.out.println("User: " + user.getUsername() + " | Email: " + user.getEmail());
        } catch (Exception e) {
            System.err.println("Error retrieving user: " + e.getMessage());
        }
    }

    public void displayAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            users.forEach(user -> System.out.println("User: " + user.getUsername() + " | Email: " + user.getEmail()));
        } catch (Exception e) {
            System.err.println("Error retrieving users: " + e.getMessage());
        }
    }

    public void removeUser(int id) {
        try {
            userService.deleteUser(id);
            System.out.println("User deleted successfully");
        } catch (Exception e) {
            System.err.println("Error deleting user: " + e.getMessage());
        }
    }
}
