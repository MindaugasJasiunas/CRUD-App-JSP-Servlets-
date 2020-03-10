package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/StudentManagementAddUpdateServlet")
public class StudentManagementAddUpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        String firstName=request.getParameter("firstname");
        String lastName=request.getParameter("lastname");
        String universityGroup=request.getParameter("universityGroup");
        String email=request.getParameter("email");

        out.println("<html><body>");
        out.println(firstName+"</br>");
        out.println(lastName+"</br>");
        out.println(universityGroup+"</br>");
        out.println(email+"</br>");
        out.println("</body></html>");
    }

}
