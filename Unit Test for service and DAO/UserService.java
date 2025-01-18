public class UserService {
    private UserDAO userDAO;

    // Constructor to inject DAO dependency
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    // Method to get user details by email
    public User getUserDetailsByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }
}
