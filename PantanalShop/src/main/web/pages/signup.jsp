<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Signup</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="../resource/style/style.css" />

        <script src="https://code.jquery.com/jquery-3.2.1.min.js" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="https://kit.fontawesome.com/8fd24b2ea7.js"></script>
        <script src="../resource/script/signupScript.js" crossorigin="anonymous"></script>

    </head>
    <body class="bg-light">
        <nav class="navbar navbar-light bg-dark justify-content-between">
            <a class="navbar-brand" href="/">Pantanal Shop</a>
        </nav>
        <div class="container my-4">
            <h2 class="mb-3">Registration</h2>
            <div class="card p-3">
                <form id="registration-form" class="form-horizontal" role="form" action="/API/register" method="post">
                        <div class="form-group">
                            <label for="fullName" class="col-sm-3 control-label">Full Name*</label>
                            <div class="col-sm-9">
                                <input type="text" id="fullName" placeholder="Full Name" class="form-control"
                                       name="fullName" autofocus value="${param.fullName}" required pattern="^.{3,50}$">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="email" class="col-sm-3 control-label">Email* </label>
                            <div class="col-sm-9">
                                <input type="email" id="email" placeholder="Email" class="form-control" name= "email"
                                       value="${param.email}" required pattern="^.+@\w+\.\w+$">
                                <span class="text-danger"> ${param.E == 1 ? "Sorry this email is already taken." : null}</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-sm-3 control-label">Password*</label>
                            <div class="col-sm-9">
                                <input type="password" id="password" placeholder="Password" class="form-control"
                                       name="password" required pattern="([a-zA-Z]{4,}[0-9]{1,})">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-sm-3 control-label">Confirm Password*</label>
                            <div class="col-sm-9">
                                <input type="password" id="confirmPassword" placeholder="Password" class="form-control"
                                       name="confirmPassword" required>
                                <span id="matching" class="text-danger"></span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="password" class="col-sm-3 control-label">Address*</label>
                            <div class="col-sm-9">
                                <input type="text" id="address" placeholder="address" class="form-control"
                                       name="address" value="${param.address}" required pattern=".{8,}">
                            </div>
                        </div>

                        <div class="text-danger font-weight-bold">
                            ${param.E == 2 ? "Please enter valid fields values!" : null}
                        </div>

                        <div class="col-sm-9">
                            <button id="registerbtn" type="submit" class="btn btn-success mr-2">Register</button>
                            <input type="reset" class="btn btn-success" value="Clear">
                        </div>
                    </form>
            </div>
        </div>

        <!-- Loader -->
        <div id="loader">
            <div id="central">
                <i class="fas fa-sync-alt"></i>
            </div>
        </div>

        <!--Bottom Footer-->
        <footer class="bottom section-padding">
            <div class="container">
                <div class="row">
                    <div class="col-md-12 text-center">
                        <div class="copyright">
                            <p>&copy <span>2019</span> <a href="#" class="transition">Pantanal Shop</a> All rights reserved.</p>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
        <!--Bottom Footer-->
    </body>

</html>
