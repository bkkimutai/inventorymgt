package ke.co.safaricom.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ke.co.safaricom.Models.Admin.SystemUser;
import ke.co.safaricom.Models.Admin.*;

@WebServlet("/adminSystemUser")
public class SystemUserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle the GET request (display the registration form)
        request.getRequestDispatcher("userRegistrationForm.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle the POST request (process form submission)
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String company = request.getParameter("company");
        String roles = request.getParameter("roles");
        String phoneNumber = request.getParameter("phoneNumber");

        // Create a SystemUser object and save the data to the database
        SystemUser systemUser = new SystemUser(firstname, lastname, email, company, roles, phoneNumber);
        systemUser.save(); // Implement the save method as per your requirements

        // Redirect to a success page or display a success message
        response.sendRedirect("registrationSuccess.html");
    }
}
