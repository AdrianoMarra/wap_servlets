<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Thank you</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="resource/style/style.css" />
    <link rel="stylesheet" href="resource/style/homepageStyle.css" />

    <script src="https://code.jquery.com/jquery-3.2.1.min.js"  crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/8fd24b2ea7.js"></script>
</head>
<body>
    <nav class="navbar navbar-light justify-content-between">
    <a class="navbar-brand" href="/">Pantanal Shop</a>
    <div class="" id="smallbar">
        <form class="form-inline my-2 my-lg-0 mr-auto ${cookie.user.value != null ? "dnone" : null}">
            <a data-toggle="modal" data-target="#cart-modal" class="btn btn-success my-2 my-sm-0 btn-sm" >
                <i class="fas fa-shopping-cart"></i> Cart (${items > 0 ? items : 0})
            </a> &nbsp;
            <a href="/signup" class="btn btn-success my-2 my-sm-0 btn-sm" >
                <i class="fas fa-sign-in-alt"></i> Signup
            </a> &nbsp;
            <a href="#" data-toggle="modal" data-target="#login-modal" class="btn btn-outline-success my-2 my-sm-0 btn-sm" >
                <i class="fas fa-user-circle"></i> Login
            </a> &nbsp;
        </form>
        <form class="form-inline my-2 my-lg-0 mr-auto ${cookie.user.value == null ? "dnone" : null}">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fas fa-user-circle"></i> ${cookie.user.value}
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a data-toggle="modal" data-target="#cart-modal" class="dropdown-item" ><i class="fas fa-shopping-cart"></i> Cart (${items > 0 ? items : 0})</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="/logout"><i class="fas fa-sign-out-alt"></i> logout</a>
                </div>
            </li>
        </form>
    </div>
</nav>

    <div class="container mt-5">
        <h1 style="color: #909294">Thank you for shopping with us! <i class="far fa-smile"></i></h1>
    </div>


    <!--Bottom Footer-->
    <footer class="bottom section-padding fixed-bottom">
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
