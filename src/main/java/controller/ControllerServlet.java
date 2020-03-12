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
    private enum AddOrUpdate{ ADD, UPDATE }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param=request.getParameter("action");
        if(param!=null){
            if(param.equalsIgnoreCase("addNewStudent")){
                addUpdateStudentPage(request,response, AddOrUpdate.ADD );
                return;
            }else if(param.equalsIgnoreCase("updateStudent")){
                addUpdateStudentPage(request,response, AddOrUpdate.UPDATE );
                return;
            }else if(param.equalsIgnoreCase("deleteStudent")){
                StudentDAO.getInstance().deleteStudent(Integer.parseInt(request.getParameter("id")));
                displayStudentsPage(request, response);
                return;
            }
        }
        displayStudentsPage(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=0;
        try{
            //when updating
            id=Integer.parseInt(request.getParameter("id"));
        }catch (NumberFormatException e){
            //adding new student(doesn't have ID yet), use default id=0 and continue
        }
        String firstName=request.getParameter("firstname");
        String lastName=request.getParameter("lastname");
        String universityGroup=request.getParameter("universityGroup");
        String email=request.getParameter("email");

        String param=request.getParameter("action");
        boolean created=false;
        if(param!=null){
            if(param.equalsIgnoreCase("addnew")){
                //set attributes for case of error when creating/updating
                request.setAttribute("AddOrUpdate","Add new");
                request.setAttribute("addupdate", "addnew");
                created=StudentDAO.getInstance().createStudent(firstName, lastName, universityGroup, email);
            }else if(param.equalsIgnoreCase("updateexisting")){
                //set attributes for case of error when creating/updating
                request.setAttribute("AddOrUpdate","Update existing");
                request.setAttribute("addupdate", "updateexisting");
                Student tempStudent=StudentDAO.getInstance().getStudentById(id);
                created=StudentDAO.getInstance().updateStudent(tempStudent, firstName, lastName, universityGroup, email);
            }
            if(!created){ //if error
                //set attributes: id needed when creating and run into error, other attributes-when updating
                request.setAttribute("errorCode", "Error when editing student. Check values and try again.");
                request.setAttribute("id",id);
                request.setAttribute("firstName",firstName);
                request.setAttribute("lastName",lastName);
                request.setAttribute("universityGroup",universityGroup);
                request.setAttribute("email",email);
                errorUpdatingPage(request, response);
            }else{
                //to prevent adding new student on refresh - redirect to main page(same page)
                response.sendRedirect(request.getContextPath() + "/ControllerServlet");
//                doGet(request, response);
            }
        }

    }


    public void displayStudentsPage(ServletRequest request, ServletResponse response) throws ServletException, IOException{
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

    public void errorUpdatingPage(ServletRequest request, ServletResponse response) throws ServletException, IOException{
        //if not updated, return to update page with values
        RequestDispatcher dispatcher=request.getRequestDispatcher("/addUpdateStudent.jsp");
        dispatcher.forward(request, response);
    }


    public void addUpdateStudentPage(ServletRequest request, ServletResponse response, AddOrUpdate addOrUpdate) throws ServletException, IOException{
        if(addOrUpdate==AddOrUpdate.ADD){
            request.setAttribute("AddOrUpdate","Add new");
            request.setAttribute("addupdate", "addnew");
        }else if(addOrUpdate==AddOrUpdate.UPDATE){
            request.setAttribute("AddOrUpdate","Update existing");
            request.setAttribute("addupdate", "updateexisting");
            String id=request.getParameter("id");
            Student student=StudentDAO.getInstance().getStudentById(Integer.parseInt(id));
            request.setAttribute("id",student.getId());
            request.setAttribute("firstName",student.getFirstName());
            request.setAttribute("lastName",student.getLastName());
            request.setAttribute("universityGroup",student.getUniversityGroup());
            request.setAttribute("email",student.getEmail());
        }
        RequestDispatcher dispatcher=request.getRequestDispatcher("/addUpdateStudent.jsp");
        dispatcher.forward(request, response);
    }

}