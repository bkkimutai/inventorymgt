package ke.co.safaricom.Models.Admin;

import ke.co.safaricom.DB.DbSystemUser;
import java.sql.*;
import java.util.Collections;
import java.util.List;
import java.sql.DriverManager;
import java.sql.Connection;


public class SystemUser implements DbSystemUser {

    private int userId;
    private List<String> roles;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String email;

    private String dateRegistered;
    private String company;

   /*   public SystemUser(String firstName, String lastName, String email, String company, String roles, String phoneNumber) {
          this.firstName = firstName;
           this.lastName = lastName;
           this.email = email;
           this.company = company;
           this.roles = roles;
       }*/

      public SystemUser(String firstName, String lastName, String email, String company, String roles, String phoneNumber) {
          this.firstName = firstName;
          this.lastName = lastName;
          this.email = email;
          this.company = company;
          this.roles = Collections.singletonList(roles);
          this.phoneNumber = phoneNumber;
      }

    public void setUserId(int id) {
        this.userId = userId;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }


    /*    public boolean equals(Object otherLoginUser;){
            if (!(otherLoginUser instanceof SystemUser)) {
                return false;
            } else {
                SystemUser newSystemUser = (SystemUser) otherLoginUser;
                return this.getFirstName().equals(newSystemUser.getFirstName()) &&
                        this.getLastName().equals(newSystemUser.getLastName()) &&
                        this.getEmail().equals(newSystemUser.getEmail()) &&
                        this.getCompany().equals(newSystemUser.getCompany()) &&
                        this.getRoles().equals(newSystemUser.getRoles()) &&
                        this.getPhoneNumber().equals(newSystemUser.getPhoneNumber());
            }
        }*/

    @Override
    public void save() {
        try (java.sql.Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/systemUser", "postgres", "Moraa@2019")) {
            String sql = "INSERT INTO User (firstname, lastName, email, company, roles,phoneNumber) VALUES (?,?,?,?,?,?)";
            PreparedStatement statement =connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(2, this.firstName);
            statement.setString(3, this.lastName);
            statement.setString(4, this.email);
            statement.setString(5, this.company);
            statement.setString(6, String.join(",", this.roles)); // Assuming roles is a List<String>
            statement.setString(7, this.phoneNumber);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    this.userId = generatedKeys.getInt(1);
                }
            }
          connection.commit();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

    }

    @Override
    public boolean authenticate() {
        return false;
    }

    public static List<SystemUser> all () {
        try (java.sql.Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/systemUser", "postgres", "Moraa@2019")) {
            String sql = "SELECT * FROM User";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

        public SystemUser find(int userId){
            try (java.sql.Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/systemUser", "postgres", "Moraa@2019")) {
                String sql = "SELECT * FROM User where UserId = :Id";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, String.valueOf(this.userId));

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        }
    }