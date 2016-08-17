   <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html >
<head>
  <title>Home page</title>
  <meta charset="utf-8">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <script src="js/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div id="header" class="well " style="background-color: #0059b3; color:white;font-size: 30px;padding-bottom: 0px;padding-top: 0px;height: 129px;border-top-width: 0px;width: 100%;">
  <div class="row">
    <div class="col-sm-9">
     <table><tr><td><img src="images/icon.png" style="height: 143px;width: 165px;"/></td>
     <td style="width: 645px; "> 
      <label style="color:white;font-size: 30px;font-style: italic;">
      MK Groceries</label></td></tr>
      </table></div>
      <input name="user" id="user"value="${pageContext.request.remoteUser}" type="hidden"/>
    <div class="col-sm-3">
    
<nav class="navbar" style="
    margin-bottom: 0px;
    width: 0px;
    border-left-width: 0px;
    border-right-width: 0px;
">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#" style="padding-left: 0px;padding-top: 0px;padding-right: 0px;
        padding-bottom: 0px;    width: 0px;    height: 0px;">
        <p style="font-family: sans-serif;font-size:medium;color:white;margin-left: 103px;margin-top: 35px;">${pageContext.request.remoteUser}</p></a>
        <ul class="dropdown-menu" style=" top: 23px;right: 0px;height: 80px;width: 60px;border-right-width: 0px;border-left-width: 0px;left: 71px;">
          <li><a href="#"><button type="button" class="btn btn-link" data-toggle="modal" data-target="#myModal">Profile</button></a></li>
          <li><a href="logout.html" style="margin-left: 0px;padding-left: 35px;">Logout</a></li>
        </ul>
      
</nav>
    <a href="/cart" target="iframe"><img src="images/cart.png" style="margin-left: 106px;" width="50px" height="50px" style=" margin-left: 72px;"/></a>
    
</div></div>
</div>
<div class="container-fluid">
  <div class="row">
    <div class="col-sm-2 sidenav">
		<ul class="list-group" >
  			<li class="list-group-item" style=" background-color:#0059b3;padding-left: 0px; padding-right: 0px;padding-top: 0px;padding-bottom: 0px;">
  				<button class="btn btn-link dropdown-toggle" type="button" data-toggle="dropdown" style="color:white;">Fruits&Vegtables</button>
    			<ul class="dropdown-menu">
       			     <li><a href="fruits.html"  target="iframe">Fruits</a></li>
    				  <li><a href="vegtables.html" target="iframe">Vegtables</a></li>
    			</ul>
    		</li>
			<li class="list-group-item" style="padding-left: 0px; padding-right: 0px;padding-top: 0px; background-color:#0059b3;padding-bottom: 0px;">
  				<button class="btn btn-link dropdown-toggle" type="button" data-toggle="dropdown" style="color:white;">Flowers&Cakes</button>
    			<ul class="dropdown-menu">
       			     <li><a href=""  target="">Flowers</a></li>
    				  <li><a href="#" target="">Cakes</a></li>
    			</ul>
		    </li>
			<li class="list-group-item" style="padding-left: 0px; background-color: #0059b3 ;padding-right: 0px;padding-top: 0px;padding-bottom: 0px;">
  				<button class="btn btn-link dropdown-toggle" style="color:white;" type="button" data-toggle="dropdown">Flowers&Cakes</button>
    			<ul class="dropdown-menu">
       			     <li><a href=""  target="">Flowers</a></li>
    				  <li><a href="#" target="">Cakes</a></li>
    			</ul>
    		</li>
    		</ul>
    	</div>
		    <div class="col-sm-10">
		   <div  class="embed-responsive embed-responsive-16by9">
          			<iframe height="400px" width="50%" src="/homePage" 
           name="iframe"></iframe>
         </div>
         </div> 
        </div>
      </div>
      <div class="container">
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h2 class="modal-title"><c:out value="${user.firstName}"/>&nbsp;<c:out value="${user.lastName}"/></h2>
        </div>
        <div class="modal-body">
          <h3>Email Id</h3><p><c:out value="${user.email}"/></p>
          <h3>Mobile Number</h3><p><c:out value="${user.phoneNumber}"/></p>
          <h3>Address</h3>
          <p>Country :<c:out value="${user.address.country}"/><br>Address:<c:out value="${user.address.address}"/><br>
          Province :<c:out value="${user.address.province}"/><br>Postal Code :<c:out value="${user.address.postalCode}"/><br>
          City :<c:out value="${user.address.city}"/><br></p>
           <h3>Password Hint</h3><p><c:out value="${user.passwordHint}"/></p>
          <h3>Roles</h3><p><c:out value="${user.roles}"/></p>                    
        </div>
        <div class="modal-footer">
          <a href="/userform"><button type="button" class="btn btn-primary">Edit</button></a>
          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>