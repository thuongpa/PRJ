
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Student" %>
<%@ page import="model.Session" %>
<%@ page import="model.Attandance" %>
<%@ page import="dal.AttandanceDBContext" %>
<%@ page import="pageNumber.*, java.util.*, java.io.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body >
         <h1><span>FPT University Academic Portal</span>
            </h1>
        </div>
        <div style="background: #f5f5f5; padding-top: 10px; padding-bottom: 10px" items="${requestScope.lecturer}" var="l">
            <div style="float: right; margin-right: 16px;">
                <a href="#">logout</a> |
                <span> CAMPUS: FPTU-Hòa Lạc</span>
            </div>
            <span style="margin-left: 16px">Home | View Schedule</span>
        </div>
        <div align="center">
        <table align="center" style="width: 100%" border="1px">
            <thead style="background: #6b90da; box-shadow: 0px 2px #f5f5f5" align="center">
        <tr>
            <th>Name</th>
            <th>Percent</th>
            <c:forEach begin="1" end="${requestScope.total}" var="i">
            <th>Slot ${i}</th>
            </c:forEach>
        </tr>
    </thead>
    <tbody align="center">
        <%
            if(request.getAttribute("studentList") != null){
                ArrayList<Student> students = new ArrayList<Student>();
                students = (ArrayList<Student>)request.getAttribute("studentList");
                Map<Integer, Double> map = (Map<Integer, Double>)request.getAttribute("map");
                for(Student s :students){
        %>

        <tr>
            <td><%=s.getName()%></td>
            <% for (Map.Entry<Integer, Double> m : map.entrySet()) {
                        if(m.getKey() == s.getId()){
                    %>
                    <td><%=m.getValue()%>%</td>

                    <%      }
                        }
                if(request.getAttribute("sessionList") != null){
                    AttandanceDBContext attDB = new AttandanceDBContext();
                    ArrayList<Session> sessionList = new ArrayList<Session>();
                    sessionList = (ArrayList<Session>) request.getAttribute("sessionList");
                    for(Session ss :sessionList){
                        Attandance att = attDB.getBySesidAndStdid(ss.getId(), s.getId());
                        if(att != null){
            %>
                <td><font color=<%=att.isPresent() == true ? "green" : "red"%>><%=att.isPresent() == true ? "P" : "A"%></font></td>    
            <%          }
                    }
                }
            %>
        </tr>
        <%
                }
            }
        %>
    </tbody>
</table>
</body>
</html>
