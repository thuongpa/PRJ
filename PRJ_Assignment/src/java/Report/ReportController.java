
package Report;

import Login.BaseAuthenticationController;
import dal.AttandanceDBContext;
import dal.SessionDBContext;
import dal.StudentDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import model.Account;
import model.Session;
import model.Student;


public class ReportController extends BaseAuthenticationController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        if (req.getParameter("gid") != null) {
            int gid = Integer.parseInt(req.getParameter("gid"));
            SessionDBContext sesDB = new SessionDBContext();
            StudentDBContext stdDB = new StudentDBContext();
            AttandanceDBContext adbc = new AttandanceDBContext();
            ArrayList<Session> sessionList = sesDB.listByGid(gid);
            ArrayList<Student> stds = stdDB.listbyGid(gid);
            
            if(stds != null && sessionList != null){
                req.setAttribute("studentList", stds);
                req.setAttribute("sessionList", sessionList);
            }
            int total = sesDB.getTotalSlotByGid(gid);
            req.setAttribute("total", total);
            Map<Integer, Double> map = adbc.getTotalAbsent(gid);
            req.setAttribute("map", map);
            req.getRequestDispatcher("../view/lecturer/report.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
          if (req.getParameter("gid") != null) {
            int gid = Integer.parseInt(req.getParameter("gid"));
            SessionDBContext sesDB = new SessionDBContext();
            StudentDBContext stdDB = new StudentDBContext();
            AttandanceDBContext adbc = new AttandanceDBContext();
            ArrayList<Session> sessionList = sesDB.listByGid(gid);
            ArrayList<Student> stds = stdDB.listbyGid(gid);
            
            if(stds != null && sessionList != null){
                req.setAttribute("studentList", stds);
                req.setAttribute("sessionList", sessionList);
            }
            int total = sesDB.getTotalSlotByGid(gid);
            req.setAttribute("total", total);
            Map<Integer, Double> map = adbc.getTotalAbsent(gid);
            req.setAttribute("map", map);
            req.getRequestDispatcher("../view/lecturer/report.jsp").forward(req, resp);
        }
}
}
