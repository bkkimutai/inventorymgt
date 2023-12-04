package ke.co.safaricom.Models.Admin;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class materialNew {
    private  String itemCode;
    private String Description;
    private String UOM;

    public materialNew(String itemCode, String Description, String UOM){
        this.itemCode =itemCode;
        this. Description=Description;
        this.UOM=UOM;
    }

    public String getItemCode(){return itemCode;}

    public  String getDescription(){return Description;}

    public String getUOM(){return UOM;}

    public void setItemCode(String itemCode){this.itemCode=itemCode;}
    public void setDescription(String Description){this.itemCode=Description;}
    public void setUOM(String UOM){this.itemCode=UOM;}

public boolean equals(Object otherMaterials){
        if(!(otherMaterials instanceof materialNew)){
            return false;
        } else{
            materialNew newmaterialNew = (materialNew) otherMaterials;
            return this.getItemCode().equals(newmaterialNew.getItemCode())&&
                    this.getDescription().equals(newmaterialNew.getDescription())&&
                    this.getUOM().equals(newmaterialNew.getUOM());
        }
}
public void save(){
        try(java.sql.Connection connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/root", "postgres", "Moraa@2019")) {
            String sql = "INSERT INTO TABLE materials(itemcode, description, UOM) VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.NO_GENERATED_KEYS);
            statement.setString(1,this.itemCode);
            statement.setString(2,this.Description);
            statement.setString(3,this.UOM);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
