package ke.co.safaricom.controllers;


import ke.co.safaricom.Models.Admin.adminLogin;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class AdminController {

    private void setupRoutes() {
        // Serve the admin login page
        Spark.get("/admin/login", (request, response) -> {
            // You can render an HTML template here or return the login form.
            return "adminLogin.hbs";
        });

        // Handle admin login form submission
        Spark.post("/admin/login", new AdminLoginRoute());

        // Protect admin routes with authentication
        Spark.before("/admin/*", (request, response) -> {
            // Check if the user is authenticated, redirect to login if not
            if (!isAdminAuthenticated(request)) {
                response.redirect("/admin/login");
            }
        });
    }

    private boolean isAdminAuthenticated(Request request) {
        // Implement your admin authentication logic here
        String email = request.queryParams("email");
        String password = request.queryParams("password");

        // You should replace this with your actual authentication logic
        adminLogin admin = new adminLogin("admin@example.com", "adminpassword");
        return admin.getEmail().equals(email) && admin.getPassword().equals(password);
    }

    private class AdminLoginRoute implements Route {
        @Override
        public Object handle(Request request, Response response) {
            // Handle the admin login form submission
            String email = request.queryParams("email");
            String password = request.queryParams("password");

            if (isAdminAuthenticated(request)) {
                // Authentication successful, redirect to admin dashboard
                response.redirect("layout.hbs");
                return null;
            } else {
                // Authentication failed, return an error message or redirect to the login page
                return "Admin Authentication Failed";
            }
        }
    }
}

