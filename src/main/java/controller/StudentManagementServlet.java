package controller;

import model.Student;
import model.StudentDAO;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;

@WebServlet("/studentManagement")
public class StudentManagementServlet extends HttpServlet {
/*    @Resource(name="jdbc/student-management")
    private DataSource dataSource;*/


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Setup print writer
        PrintWriter out=response.getWriter();
        response.setContentType("text/plain");


        StudentDAO.getInstance().connect();
        List<Student> studentList= StudentDAO.getInstance().getStudents();
        for(Student s:studentList){
            out.println(s.getFirstName());
        }

        //add students attribute and forward to jsp
/*        request.setAttribute("students", studentList);
        RequestDispatcher dispatcher=request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);*/
    }

}
