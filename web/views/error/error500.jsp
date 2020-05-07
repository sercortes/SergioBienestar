<!DOCTYPE html>

<%

    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setDateHeader("Expires", 0);
    response.setHeader("Pragma", "no-cache"); 
    response.setDateHeader("Expires", 0); 

%>

<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link href="assets/img/logo/logooo.png" rel="icon">
        <title>Error 500</title>
        <link href="assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="assets/css/error.css" rel="stylesheet" type="text/css">
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
                                    <div class="login-form1 p-2">
                                        <div class="text-center">
                                            <img id="imgerror" src="assets/img/logo/error/error500.png" class="rounded img-fluid" alt="Cinque Terre" style="max-height: 240px">
                                            <hr>
                                        </div>
                                          <div class="form-group">
                                              <a href="./Start" class="btn btn-primary btn-block font-weight-bold hvr-pulse-grow"><i class="fas fa-home"></i> Ir a inicio</a>
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