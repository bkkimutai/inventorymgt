package ke.co.safaricom.Models.login;

import ke.co.safaricom.DB.DbSystemUser;
import ke.co.safaricom.DB.DbSystemUsers;
//import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.sql2o.Connection;
import java.util.List;
import ke.co.safaricom.DB.DbSystemUsers;

public class loginDao implements DbSystemUser {

    @Override
    public void save() {

    }

    @Override
    public boolean authenticate() {
        return false;
    }
}
