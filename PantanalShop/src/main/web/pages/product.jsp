<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 987109
  Date: 6/14/2019
  Time: 2:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Product</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="../resource/style/style.css" />
    <link rel="stylesheet" href="../resource/style/productStyle.css">

    <script src="https://code.jquery.com/jquery-3.2.1.min.js"  crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/8fd24b2ea7.js"></script>
    <script src="../resource/script/productScript.js" crossorigin="anonymous"></script>
    ${user != null ? null : "<script src='resource/script/checkCookies.js'></script>"}
</head>
<body class="bg-light">
<nav class="navbar navbar-light bg-dark justify-content-between">
    <a class="navbar-brand" href="/">Pantanal Shop</a>
    <div class="" id="smallbar">
        <form class="form-inline my-2 my-lg-0 mr-auto ${cookie.user.value != null ? "dnone" : null}">
            <a data-toggle="modal" data-target="#cart-modal" class="btn btn-success my-2 my-sm-0 btn-sm" >
                <i class="fas fa-shopping-cart"></i> Cart
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
                    <a data-toggle="modal" data-target="#cart-modal" class="dropdown-item" ><i class="fas fa-shopping-cart"></i> Cart</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="/logout"><i class="fas fa-sign-out-alt"></i> logout</a>
                </div>
            </li>
        </form>
    </div>
</nav>
<div class="container product-details">
    <div id="msg-error" class="card bg-danger text-light custom-msg my-4 ${param.E == 1 ? null : "dnone"}">
        <div class="card-body">
            <h4><i class="fas fa-times"></i> You must to be logged in to add products to cart!</h4>
        </div>
    </div>
    <div class="row" id="addClass">
        <!-- Display block of product-->
    </div>
</div>

<!--ogin Modal Content-->
<div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">

    <div class="modal-dialog" role="document">
        <div class="modal-content loginmodal-container">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Login</h5>

            </div>
            <div class="modal-body">
                <form action="/auth" method="post">
                    <input type="text" name="user" placeholder="Username">
                    <input type="password" name="pass" placeholder="Password">
                    <div class="dropdown-divider"></div>
                    <input type="submit" name="login" class="login btn btn-success" value="Login">

                </form>
                <div class="login-help">
                    <label>Please click  </label><a href="pages/signup.jsp"> Register </a><span> If you dont have an Account.</span>
                </div>
            </div>

        </div>
    </div>
</div>

<!--Cart Modal Content-->
<div class="modal fade" id="cart-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg">
        <div class="modal-content card-body text-left">
            <h3><i class="fas fa-shopping-cart"></i> Cart (${items > 0 ? items : 0})</h3>
            <div class="dropdown-divider"></div>

            <!-- repeat this for each product -->
            <c:forEach items="${cart}" var="product">
                <div id="cartshow" class="row">
                    <div class="col-sm-3 text-right">
                        <img src="<c:url value="${product[3]}"  />"  alt="<c:out value="${product[0]}" />">
                    </div>
                    <div class="col-sm-5">
                        <h6 class="mt-0"><c:out value="${product[0]}" /></h6>
                    </div>
                    <div class="col-sm-4 text-left">
                        <span>Quantity <strong><c:out value="${product[6]}" /></strong></span>
                    </div>
                </div>
            </c:forEach>

            <div class="my-4 text-center emptyCart ${items > 0 ? "dnone" : null}">
                <h3><i class="far fa-frown fa-lg"></i></h3>
                <h3>Your cart is empty!</h3>
            </div>

            <div class="dropdown-divider"></div>
            <div class="text-right">
                <a href="/checkout" class="btn btn-danger checkoutbtn">Checkout</a>
            </div>
        </div>
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
