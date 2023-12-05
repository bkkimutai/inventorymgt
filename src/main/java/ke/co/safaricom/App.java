package ke.co.safaricom;
import ke.co.safaricom.Models.InventoryItem;
import ke.co.safaricom.Models.ItemWithPartnerISP;
import ke.co.safaricom.Models.PartnerISP;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ke.co.safaricom.Models.Admin.SystemUser;
import ke.co.safaricom.Models.Admin.materialNew;


import ke.co.safaricom.Models.login.login;


import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
       staticFileLocation("/public");

        get("/user/Login", (request, response) -> {
            Map<String, Object> payload=new HashMap<>();
            return new ModelAndView(payload, "/login/login.hbs");
        }, new HandlebarsTemplateEngine());

        get("/decom", (request, response) -> {
            Map<String, Object> payload=new HashMap<>();
            return new ModelAndView(payload, "/decom.hbs");
        }, new HandlebarsTemplateEngine());

        post("/user/Login", (request, response) -> {
            Map<String, Object> payload = new HashMap<>();
            String email = request.queryParams("email");
            String password = request.queryParams("password");
            login newlogin = new login();
            newlogin.save();
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/admin/Login", (request, response) -> {
            Map<String, Object> payload=new HashMap<>();
            return new ModelAndView(payload, "/admin/adminLogin.hbs");
        }, new HandlebarsTemplateEngine());

        get("/admin/home", (request, response) -> {
            Map<String, Object> payload=new HashMap<>();
            return new ModelAndView(payload, "/admin/adminHome.hbs");
        }, new HandlebarsTemplateEngine());

        get("/", (request, response) -> {
            Map<String, Object> payload=new HashMap<>();
            return new ModelAndView(payload, "layout.hbs");
        }, new HandlebarsTemplateEngine());

        get("/user/new", (request, response) -> {
            Map<String, Object> payload=new HashMap<>();
            return new ModelAndView(payload, "/admin/adminSystemUser.hbs");
        }, new HandlebarsTemplateEngine());

        get("/material/new", (request, response) -> {
            Map<String, Object> payload = new HashMap<>();
            return new ModelAndView(payload, "/admin/adminMaterials.hbs");
        }, new HandlebarsTemplateEngine());


        get("/home", (req, res) -> {
            Map<String, Object> payload = new HashMap<>();
            List<ItemWithPartnerISP> InventoryWithISP = ItemWithPartnerISP.getAllInventoryWithISPs();
            payload.put("InventoryWithISP", InventoryWithISP);
            return new ModelAndView(payload, "home.hbs");
        }, new HandlebarsTemplateEngine());


        //show new inventory form
        get("/inventory/new", (request, response) -> {
            Map<String, Object> payload = new HashMap<>();
            List<PartnerISP> partners = PartnerISP.all();
            payload.put("partnerisps", partners);
            return new ModelAndView(payload, "new-item.hbs");
        }, new HandlebarsTemplateEngine());

        //post new users into DB
 /*       post("/users", (request, response) -> {
            Map<String, Object> payload = new HashMap<>();
            String firstName = request.queryParams("firstName");
            String lastName = request.queryParams("lastName");
            String email = request.queryParams("email");
            String company = request.queryParams("company");
            String phoneNumber = request.queryParams("phoneNumber");
            String roles = request.queryParams("roles");
            int userId = Integer.parseInt(request.queryParams("userId"));
            SystemUser newUser = new SystemUser(firstName, lastName, email, company, phoneNumber, roles);
            newUser.save();
            response.redirect("/admin/home");
            return null;
        }, new HandlebarsTemplateEngine());*/

//post new users into DB
        post("/users", (request, response) -> {
            Map<String, Object> payload = new HashMap<>();
            String firstName = request.queryParams("firstName");
            String lastName = request.queryParams("lastName");
            String email = request.queryParams("email");
            String company = request.queryParams("company");
            String phoneNumber = request.queryParams("phoneNumber");
            String roles = request.queryParams("roles");

            // Assuming userId is not a required parameter
            int userId = 0;
            try {
                // userId might not be present in the request, so handle the possibility of NumberFormatException
                String userIdParam = request.queryParams("userId");
                if (userIdParam != null && !userIdParam.isEmpty()) {
                    userId = Integer.parseInt(userIdParam);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace(); // Handle or log the exception as needed
            }

            SystemUser newUser = new SystemUser(firstName, lastName, email, company, phoneNumber, roles);
            newUser.setUserId(userId); // Set the userId

            newUser.save();
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());



//post new materials to DB
        post("/materials", (request, response) -> {
            Map<String, Object> payload = new HashMap<>();
            String itemCode = request.queryParams("firstName");
            String Description = request.queryParams("lastName");
            String UOM = request.queryParams("email");
            materialNew newMaterials = new materialNew(itemCode, Description, UOM);
            newMaterials.save();
            response.redirect("/admin/home");
            return null;
        }, new HandlebarsTemplateEngine());

        //Post new Inventory into DB
        post("/inventories", (request, response) -> {
            Map<String, Object> payload = new HashMap<>();
            String itemName = request.queryParams("itemName");
            String itemSerial = request.queryParams("itemSerial");
            String itemManufacturer = request.queryParams("itemManufacturer");
            int partnerId = Integer.parseInt(request.queryParams("partnerId"));
            InventoryItem newInventory = new InventoryItem(itemName, itemSerial, itemManufacturer, partnerId);
            newInventory.save();
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //display form to create a new partner ISP
        get("/partnerisps/new", (request, response) -> {
            Map<String, Object> payload = new HashMap<>();
//            List<Squad> squads = heroSquadDao.getAllSquads();
//            payload.put("squads", squads);
            return new ModelAndView(payload, "new-partnerisp.hbs");
        }, new HandlebarsTemplateEngine());

        //process new Partner
        post("/partnerisps", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String partnerName = request.queryParams("partnerName");
            String partnerEmail = request.queryParams("partnerEmail");
            String description = request.queryParams("description");
            PartnerISP newISP = new PartnerISP(partnerName, partnerEmail, description);
            newISP.save();
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());



    }

}