package ke.co.safaricom.Models.login;

import ke.co.safaricom.DB.DbSystemUser;


public class loginDao implements DbSystemUser {

    @Override
    public void save() {

    }

    @Override
    public boolean authenticate() {
        return false;
    }
}
