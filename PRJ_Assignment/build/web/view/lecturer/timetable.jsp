
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="helper" class="util.DateTimeHelper"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="util.DateTimeHelper" %>
<!DOCTYPE html>
<html>
    <head>
        <script>
            function validDate() {
                 var from = new Date(document.getElementById("date_from").value);
                 var to = new Date(document.getElementById("date_to").value);
                 if(from > to){
                      alert("Please enter From before To");
                 }
            }
        </script>
    </head>
    <body>
        <div>
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
            <h2>Timetable</h2>
        Lecturer: <input type="text" readonly="readonly" value="${requestScope.lecturer.name}"/>
        <form name="form" onsubmit="validDate()" action="timetable" method="POST">
            <input type="hidden" name="lid" value="${param.lid}"/>
            From: <input type="date" id="date_from" name="from" value="${requestScope.from}"/>
            To: <input type="date" id="date_to" name="to" value="${requestScope.to}"/>
            <input type="submit" value="View"/>
            
        </form>
        <table align="center" style="width: 100%" border="1px">
             <thead style="background: #6b90da; box-shadow: 0px 2px #f5f5f5" align="center">
            <tr>  
           
                <td> </td>
                <c:forEach items="${requestScope.dates}" var="d">
                    <td>${d}<br/>${helper.getDayNameofWeek(d)}</td>
                    </c:forEach>
            </tr><!-- comment -->
             </thead>
            <c:forEach items="${requestScope.slots}" var="slot">
                <tr>
                    <td>${slot.description}</td>
                    <c:forEach items="${requestScope.dates}" var="d">
                        <td>
                            <c:forEach items="${requestScope.sessions}" var="ses">
                                <c:if test="${helper.compare(ses.date,d) eq 0 and (ses.slot.id eq slot.id)}">
                                    <a href="takeatt?id=${ses.id}">${ses.group.name}-${ses.group.subject.name}</a>
                                    <br/>
                                    ${ses.room.name} <br>
                                    <c:if test="${ses.attanded}">
                                        (<font color=green>Attended</font>)
                                    </c:if>
                                    <c:if test="${!ses.attanded}">
                                        (<font color=red>Not yet</font>)
                                    </c:if>
                                </c:if>
                                  
                            </c:forEach>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
