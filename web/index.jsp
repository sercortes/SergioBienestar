<!DOCTYPE html>

<%

    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setDateHeader("Expires", 0);
    response.setHeader("Pragma", "no-cache"); 
    response.setDateHeader("Expires", 0); 

%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty USER.perfil}">
    <c:redirect url="/Home"/>
</c:if>

<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link href="assets/img/logo/logooo.png" rel="icon">
        <title>Login</title>
        <link href="assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="assets/css/login.css" rel="stylesheet" type="text/css">
        <link href="assets/css/ruang-admin.min.css" rel="stylesheet">
        <link href="assets/css/hover-min.css" rel="stylesheet">
        <link href="assets/css/style.css" rel="stylesheet">

    </head>

    <body class="bg-gradient-login">
        <!-- Login Content -->
        <div class="container-login">
            <div class="row justify-content-center">
                <div class="col-xl-10 col-lg-12 col-md-9">
                    <div class="card shadow-sm my-5">
                        <div class="card-body p-0">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="login-form">
                                        <div class="text-center">
                                            <img src="assets/img/logo/logooo.png" class="rounded img-fluid" alt="Cinque Terre">
                                            <hr>
                                        </div>
                                        <form class="user needs-validation" action="Login" method="post" novalidate>
                                            <div class="form-group">
                                                <input type="email" class="form-control" id="" name="email" aria-describedby="emailHelp"
                                                       placeholder="sdcortes6@misena.edu.co" required>
                                                <div class="invalid-feedback">
                                                    Ingrese un correo válido
                                                </div>
                                                <div class="valid-feedback">
                                                    Todo en orden :D
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <input type="password" class="form-control" id="pass" name="pass" placeholder="**********" minlength="6" required>
                                                <div class="invalid-feedback">
                                                    Ingrese la contraseña
                                                </div>
                                                <div class="valid-feedback">
                                                    Todo en orden :D
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <button type="submit" class="btn btn-primary btn-block font-weight-bold hvr-pulse-grow"><i class="fas fa-smile-wink"></i> Ingresar</button>
                                            </div>
                                            <hr>
                                            <c:if test="${not empty MESSAGE}">

                                                <div class="alert alert-warning alert-dismissible fade show" role="alert">
                                                    <strong>=( </strong> ${MESSAGE}
                                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>

                                            </c:if>
                                        </form>
                                        <div class="text-center">
                                            <a class="font-weight-bold small" href="#">Olvido la contraseña</a>
                                        </div>
                                        <div class="text-center">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Login Content -->
        <script src="assets/vendor/jquery/jquery.min.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="assets/vendor/jquery-easing/jquery.easing.min.js"></script>
        <script src="assets/js/project/login/start.js"></script>
        <script src="assets/js/ruang-admin.min.js"></script>
    </body>

</html>