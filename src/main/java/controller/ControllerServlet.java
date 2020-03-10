package controller;

import model.Student;
import model.StudentDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param=request.getParameter("action");
        if(param!=null){
            if(param.equalsIgnoreCase("addNewStudent")){
                request.setAttribute("AddOrUpdate","Add new");
                request.setAttribute("addupdate", "addnew");
                RequestDispatcher dispatcher=request.getRequestDispatcher("/addUpdateStudent.jsp");
                dispatcher.forward(request, response);
                return;
            }else if(param.equalsIgnoreCase("updateStudent")){
                request.setAttribute("addupdate", "updateexisting");
                String id=request.getParameter("id");
                Student student=StudentDAO.getInstance().getStudentById(Integer.parseInt(id));
                request.setAttribute("AddOrUpdate","Update existing");
                request.setAttribute("id",student.getId());
                request.setAttribute("firstName",student.getFirstName());
                request.setAttribute("lastName",student.getLastName());
                request.setAttribute("universityGroup",student.getUniversityGroup());
                request.setAttribute("email",student.getEmail());
                RequestDispatcher dispatcher=request.getRequestDispatcher("/addUpdateStudent.jsp");
                dispatcher.forward(request, response);
                return;
            }else if(param.equalsIgnoreCase("deleteStudent")){
                StudentDAO.getInstance().deleteStudent(Integer.parseInt(request.getParameter("id")));
                displayStudents(request, response);
                return;
            }
        }
        displayStudents(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=0;
        try{
            //when updating
            id=Integer.parseInt(request.getParameter("id"));
        }catch (NumberFormatException e){
            //adding new, pass
        }
        String firstName=request.getParameter("firstname");
        String lastName=request.getParameter("lastname");
        String universityGroup=request.getParameter("universityGroup");
        String email=request.getParameter("email");

        String param=request.getParameter("action");
        boolean created=false;
        if(param!=null){
            if(param.equalsIgnoreCase("addnew")){
                created=StudentDAO.getInstance().createStudent(firstName, lastName, universityGroup, email);
            }else if(param.equalsIgnoreCase("updateexisting")){
                Student tempStudent=StudentDAO.getInstance().getStudentById(id);
                created=StudentDAO.getInstance().updateStudent(tempStudent, firstName, lastName, universityGroup, email);
            }
            if(!created){
                request.setAttribute("AddOrUpdate","Update existing");
                request.setAttribute("addupdate", "updateexisting");
                request.setAttribute("errorCode", "Error when editing student. Check values and try again.");
                request.setAttribute("id",id);
                request.setAttribute("firstName",firstName);
                request.setAttribute("lastName",lastName);
                request.setAttribute("universityGroup",universityGroup);
                request.setAttribute("email",email);
                errorUpdating(request, response);
            }else{
                doGet(request, response);
            }
        }

    }


    public void displayStudents(ServletRequest request, ServletResponse response) throws ServletException, IOException{
        PrintWriter out=response.getWriter();
        response.setContentType("text/plain");
        List<Student> studentList= StudentDAO.getInstance().getStudents();
        for(Student s:studentList){
            out.println(s.getFirstName());
        }

        //add students attribute and forward to jsp
        request.setAttribute("students", studentList);
        RequestDispatcher dispatcher=request.getRequestDispatcher("/studentManagement.jsp");
        dispatcher.forward(request, response);
    }

    public void errorUpdating(ServletRequest request, ServletResponse response) throws ServletException, IOException{
        //if not updated, return to update page with values
        RequestDispatcher dispatcher=request.getRequestDispatcher("/addUpdateStudent.jsp");
        dispatcher.forward(request, response);
    }

    public void updateStudent(ServletRequest request, ServletResponse response) throws ServletException, IOException{

    }

}
