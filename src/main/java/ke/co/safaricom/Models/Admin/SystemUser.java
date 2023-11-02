package ke.co.safaricom.Models.Admin;

import ke.co.safaricom.DB.DbSystemUser;
import org.sql2o.Connection;
import java.util.Collections;
import java.util.List;
import ke.co.safaricom.DB.DbSystemUsers;


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

    public void setuserId(int id) {
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
        try (Connection con = DbSystemUsers.sql2o.beginTransaction()) {
            String sql = "INSERT INTO systemUser (firstname, lastName, email, company, roles,phoneNumber) VALUES (:firstname, :lastName, :email, :company, :roles, :phoneNumber)";
            con.createQuery(sql)
                    .addParameter("firstname", this.firstName)
                    .addParameter("lastname", this.lastName)
                    .addParameter("email", this.email)
                    .addParameter("company", this.company)
                    .addParameter("roles", this.roles)
                    .addParameter("phoneNumber", this.phoneNumber)
                    .executeUpdate();
            String idQuery = "SELECT lastval()";
            this.userId = con.createQuery(idQuery).executeScalar(Integer.class);

            con.commit();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

    }
    public static List<SystemUser> all () {
        String sql = "SELECT * FROM systemUser";
        try (Connection con = DbSystemUsers.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(SystemUser.class);
        }
    }
    public static SystemUser find (int userId){
        try (Connection con = DbSystemUsers.sql2o.open()) {
            String sql = "SELECT * FROM systemUser where UserId = :Id";
            return con.createQuery(sql)
                    .addParameter("userId", userId)
                    .executeAndFetchFirst(SystemUser.class);
        }
    }

    @Override
    public boolean authenticate() {
        return false;
    }

}



