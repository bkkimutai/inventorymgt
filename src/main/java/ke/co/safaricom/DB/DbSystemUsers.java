package ke.co.safaricom.DB;

import org.sql2o.Sql2o;

public class DbSystemUsers {
    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5433/systemUser", "postgres", " Moraa@2019");
    //public static Sql2o SystemUserDb = new Sql2o("jdbc:postgresql://localhost:5433/systemUser", "postgres", " Moraa@2019");
}
