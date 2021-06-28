<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
    </script>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
          crossorigin="anonymous">


    <style>
        html, body{
    	height : 100%;
    }
    body{
    	display: -ms-flexbox;
    	display: flex;
    	-ms-flex-align:center;
    	align-items: center;
    	padding-top:40px;
    	padding-bottom: 40px;
    	margin-left:auto;
    	margin-right:auto;
    }
    form{
        margin-left:auto;
    	margin-right:auto;
    }
    .form-signin{
    	width:50%;
    	max-width: 330px;
    	padding: 15px;
    	margin: auto;
    }
    .form-signin .checkbox{
    	font-weight: 400;
    }
    .form-signin .form-control{
    	position: relative;
    	box-sizing: border-box;
    	height:auto;
    	padding:10px;
    	font-size:16px;
    }
    .form-signin .form-control:focus{
    	z-index: 2;
    }
    .form-signin input[id="uid"]{
    	margin-bottom: -1px;
    	border-bottom-right-radius: 0;
    	border-bottom-left-radius: 0;
    }
    .form-signin input[id="password"]{
    	margin-bottom: 10px;
    	border-top-left-radius: 0;
    	border-bottom-right-radius: 0;
    }


        #title{
            cursor:pointer;
        }
        #signupBtn{
            margin-top : 15px;
            margin-bottom : 15px;
        }
    </style>
    <script>
    		function signup(){
    			location.href="/signup";
    		}
    		function home(){
    		    location.href="/";
    	    }
    </script>


    <title>AUCSUSU MARKET</title>
</head>
<body>

<h2><a href="/">메인 페이지</a></h2>
<br>

<form class="form-signin" name="form" id="form" role="form" method="post" action="/user">
    <div>
        <h2 id="title" onclick="return home();">HELLO WORLD</h2>
    </div>
    <div class="form-label-group">
        <label for="uid" class="sr-only">User ID</label>
        <input type="text" id="uid" name="uid" class="form-control" placeholder="User ID" required autofocus/>
    </div>
    <div class="form-label-group">
        <label for="password" class="sr-only">User Password</label>
        <input type="password" id="password" name="password" class="form-control" placeholder="User Password" required />
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
    <!--<a href="/oauth2/authorization/google" class="btn btn-success active" role="button">GOOGLE LOGIN</a>-->
    <button class="btn btn-lg btn-primary btn-block" type="button" onclick="return signup();">JOIN</button>
    <c:if test="${error}">
        <p style="color:red; align:center;">로그인 에러</p>
        <p style="color:red; align:center;">아이디와 비밀번호를 다시 입력해주세요</p>
    </c:if>
    <br>
</form>
<script src="login.js"></script>
</body>
</html>