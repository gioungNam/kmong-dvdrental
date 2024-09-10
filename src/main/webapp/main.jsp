<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>DVDレンタルシステム - メイン</title>
    
     <!-- Bootstrap CSS 추가 -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    
    <style>
        .table-wrapper {
            margin-top: 20px;
        }
        .tabs {
            margin-bottom: 20px;
        }
        .tabs a {
            margin-right: 10px;
        }
        .custom-button {
            margin-top: 10px;
        }
    </style>
</head>
<body>

    <div class="container">
        <!-- 상단 탭 -->
        <nav class="nav tabs">
            <a class="nav-link btn btn-outline-primary" href="member-management.jsp">会員管理</a>
            <a class="nav-link btn btn-outline-primary" href="rental-management.jsp">レンタル管理</a>
        	<a class="nav-link btn btn-outline-primary" href="dvd-management.jsp">DVD管理</a>
        </nav>

        <!-- DVD 검색 -->
        <form method="get" action="main" class="form-inline">
            <div class="form-group mb-2">
                <input type="text" class="form-control" name="searchTitle" placeholder="DVDのタイトルを検索">
            </div>
            <button type="submit" class="btn btn-primary mb-2 ml-2">検索</button>
        </form>
        

        <!-- DVD 리스트 테이블 -->
        <form method="post" action="main">
            <div class="table-wrapper">
                <table class="table table-bordered table-striped">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">選択</th>
                            <th scope="col">タイトル</th>
                            <th scope="col">ジャンル</th>
                            <th scope="col">主演俳優</th>
                            <th scope="col">レンタル状態</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dvd" items="${dvdList}">
                            <tr>
                                <td><input type="checkbox" name="dvdCheckbox" value="${dvd.id}" 
                                      <c:if test="${dvd.isRented}">disabled="disabled"</c:if> />
                                </td>
                                <td>${dvd.title}</td>
                                <td>${dvd.genre}</td>
                                <td>${dvd.leadActor}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${dvd.isRented}"><span class="badge badge-danger">レンタル中</span></c:when>
                                        <c:otherwise><span class="badge badge-success">レンタル可能</span></c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <button type="submit" class="btn btn-success custom-button">レンタル</button>
        </form>
    </div>

    <!-- Bootstrap JS 및 jQuery 추가 -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>