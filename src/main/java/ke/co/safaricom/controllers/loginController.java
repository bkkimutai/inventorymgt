package ke.co.safaricom.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class loginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle the GET request (display the login form)
        request.getRequestDispatcher("loginForm.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle the POST request (process login)
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Implement your user authentication logic here
        if (authenticateUser(email, password)) {
            // Successful login, redirect to a success page
            response.sendRedirect("loginSuccess.html");
        } else {
            // Login failed, redirect to an error page or display an error message
            response.sendRedirect("loginError.html");
        }
    }

    // Replace this method with your actual authentication logic
    private boolean authenticateUser(String email, String password) {
        // Implement user authentication logic here, e.g., check the credentials against your database.
        // Return true if authentication is successful, otherwise return false.
        return true; // For demonstration purposes; you should verify user credentials in your actual code.
    }
}


