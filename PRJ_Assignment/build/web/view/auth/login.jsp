
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/loginstyle.css" rel="stylesheet" type="text/css"/>
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
        <main>
            <div class="container">
                <div class="login-form">               
                    <form name="form" onsubmit="validLogin()" action="login" method="POST"> 
                        <h1>Login</h1>
                        <div class="input-box">
                            <i ></i>
                            <input type="text" name="username" placeholder="Username">
                        </div>
                        <div class="input-box">
                            <i ></i>
                            <input type="password" name="password" placeholder="Password">
                        </div>

                        <div class="btn-box">
                            <button type="submit">
                                Login
                            </button>
                        </div>
                        <br/>

                    </form>
                </div>
            </div>
        </main>     
    </body>
</html>
