package ke.co.safaricom.DB;

public interface DbSystemUser {
    void save();

    boolean authenticate();

    boolean equals(Object otherUser);

    boolean isValidUser(String email, String password);
}
