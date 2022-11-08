<%-- 
    Document   : login
    Created on : Oct 17, 2022, 3:08:08 PM
    Author     : sonnt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script>
            function validLogin() {
                if (document.form.username.value == "") {
                    alert("Please enter Login Name.");
                    document.loginform.userName.focus();
                    return;
                }
                if (document.form.password.value == "") {
                    alert("Please enter password.");
                    document.userform.password.focus();
                    return;
                }
                alert("Welcome User");
                return;
            }
        </script>
    </head>
    <body>
        <form name="form" onsubmit="validLogin()" action="login" method="POST">
            Username: <input type="text" name="username" /> <br/>
            Password: <input type="password" name="password" /> <br/>
            <input type="submit" value="Login"/>
        </form>
    </body>
</html>
