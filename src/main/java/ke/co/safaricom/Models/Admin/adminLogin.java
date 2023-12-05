package ke.co.safaricom.Models.Admin;

import ke.co.safaricom.DB.DbSystemUser;

public class adminLogin  implements DbSystemUser {
        private String email;
        private String password; // It's important to securely store and hash passwords in a real application.

        public adminLogin(String email, String password) {
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
        return false;
    }
}




