<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
form {border: 3px solid #f1f1f1;}

input[type=text], input[type=password],input[type=email] {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
}

button {
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 100%;
}

button:hover {
    opacity: 0.8;
}

.cancelbtn {
    width: auto;
    padding: 10px 18px;
    background-color: #f44336;
}

.imgcontainer {
    text-align: center;
    margin: 24px 0 12px 0;
}

img.avatar {
    width: 40%;
    border-radius: 50%;
}

.container {
    padding: 16px;
}

span.psw {
    float: right;
    padding-top: 16px;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
    span.psw {
       display: block;
       float: none;
    }
    .cancelbtn {
       width: 100%;
    }
}


.log-form {
    width: 40%;
    min-width: 320px;
    max-width: 475px;
    background: #fff;
    position: absolute;
    top: 50%;
    left: 50%;
    -webkit-transform: translate(-50%, -50%);
    -moz-transform: translate(-50%, -50%);
    -o-transform: translate(-50%, -50%);
    -ms-transform: translate(-50%, -50%);
    transform: translate(-50%, -50%);
    box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.25);
}
</style>
</head>
<body>
<div class="log-form">


	<form action="/registration" method="post" modelAttribute="userForm">
  
  <div class="container">
  
  
  	<label for="uname"><b>First Name</b></label>
    <input type="text" placeholder="Enter First Name" name="firstname" required>
    
    <label for="uname"><b>Last Name</b></label>
    <input type="text" placeholder="Enter Last Name" name="lastname" required>
    
    <label for="uname"><b>Email</b></label>
    <input type="email" placeholder="Enter Email" name="email" required>
    
    
    <label for="uname"><b>Username</b></label>
    <input type="text" placeholder="Enter Username" name="username" required>

    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="password" required>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <button type="submit">Register</button>
    
  </div>

  <div class="container" style="background-color:#f1f1f1">
    
    <a class="forgot" href="/">Login</a>
  </div>
</form>
</div>
</body>
</html>