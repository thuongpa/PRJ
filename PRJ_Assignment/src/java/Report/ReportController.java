/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Report;

import dal.SessionDBContext;
import dal.StudentDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Session;
import model.Student;

/**
 *
 * @author ACER
 */
public class ReportController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("gid") != null) {
            int gid = Integer.parseInt(request.getParameter("gid"));
            SessionDBContext sesDB = new SessionDBContext();
            StudentDBContext stdDB = new StudentDBContext();
            ArrayList<Session> sessionList = sesDB.listByGid(gid);
            ArrayList<Student> stds = stdDB.listbyGid(gid);
            
            if(stds != null && sessionList != null){
                request.setAttribute("studentList", stds);
                request.setAttribute("sessionList", sessionList);
            }
            int total = sesDB.getTotalSlotByGid(gid);
            request.setAttribute("total", total);
            request.getRequestDispatcher("../view/lecturer/report.jsp").forward(request, response);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
