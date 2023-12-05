package ke.co.safaricom.Models.login;
import java.sql.*;


import ke.co.safaricom.DB.DbSystemUser;
import spark.Spark;

public class login  implements DbSystemUser{
        private String email;
        private String password; // It's important to securely store and hash passwords in a real application.

        public login() {
            this.email = email;
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

    @Override
    public void save() {

    }

    @Override
    public boolean authenticate() {
        return false;
    }

    @Override
    public boolean isValidUser(String email, String password) {
        String query = "SELECT * FROM loginCredentials WHERE email=? AND password=?";

        try (Connection connection = DriverManager.getConnection(email);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Returns true if there is at least one row
            }

        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }

    public static void main(String[] args) {
        Spark.post("/user/login", (req, res) -> {
            String email = req.queryParams("email");
            String password = req.queryParams("password");

            login loginService = new login();

            if (loginService.isValidUser(email, password)) {
                // Redirect to the home page
                res.redirect("/");
            } else {
                // Redirect back to the login page with an error message
                res.redirect("/login?error=1");
            }

            return null;
        });

    }
}
