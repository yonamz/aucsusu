<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel = "stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <title>AUCSUSU MARKET</title>

    <style>
        .logo{
            margin-top:50px;
            float:center;
            font-size:25px;
            font-weight:bold;
            margin-left: auto;
            margin-right: auto;
            width:150px;
            height:150px;
        }

        a{
            cursor: pointer;
        }

        .navbar{
            color:yellow;
            margin-left:auto;
            margin-right: auto;
        }
        .btn btn-warning{
            margin-top:5px;
            margin-right:5px;
        }


        #dropdown-content{
            display: none;
            position: absolute;
            background-color: #f1f1f1;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            z-index: 1;
        }

        #dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }

        #dropdown-content a:hover {background-color: #ddd;}

        #dropdown:hover #dropdown-content {display: block;}

        #dropdown:hover #dropbtn {background-color: #3e8e41;}

    </style>

</head>
<body>
<button type="button" class="btn btn-warning">로그인</button>

<h2 class="logo"><a href="/">옥수수 마켓</a></h2>

<!-- 카테고리 -->
<c:if test="${user == null}">
    <nav class="navbar navbar-expand-sm navbar-dark bg-warning">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample03" aria-controls="navbarsExample03" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>


        <div class="collapse navbar-collapse" id="navbarsExample03">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/market/all">전체 상품</a>
                </li>
                <li>
                    <a class="nav-link" href="/market/addItem">상품 등록</a>
                </li>
                <li id="dropdown">
                    <a class="nav-link">마이페이지</a>
                    <div id="dropdown-content">
                        <a class="nav-link" href="#">참여 내역</a>
                        <a class="nav-link" href="#">내 정보</a>
                    </div>
                </li>
            </ul>
            <form class="form-inline my-2 my-md-0">
                <input class="form-control" type="text" placeholder="상품 검색">
            </form>
        </div>
    </nav>
</c:if>



<br>--%>

<jsp:include page="../index.jsp" flush="false"/>

item

</body>
</html>