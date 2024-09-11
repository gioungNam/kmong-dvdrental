<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>レンタル管理ページ</title>
    
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
            <a class="nav-link btn btn-outline-primary" href="./">メインページ</a>
            <a class="nav-link btn btn-outline-primary" href="member">会員管理</a>
            <a class="nav-link btn btn-outline-primary" href="dvd">DVD管理</a>
        </nav>

        <!-- 렌탈 이력 테이블 -->
        <form method="post" action="return">
            <div class="table-wrapper">
                <table class="table table-bordered table-striped">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">選択</th>
                            <th scope="col">大借番号</th>
                            <th scope="col">大借日</th>
                            <th scope="col">DVDタイトル</th>
                            <th scope="col">会員ID</th>
                            <th scope="col">返却状況</th>
                            <th scope="col">返却日</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="rental" items="${rentalHistory}">
                            <tr>
                                <td>
                                    <input type="checkbox" name="rentalItemCheckbox" value="${rental.rentalItemId}"
                                           <c:if test="${rental.isReturned}">disabled="disabled"</c:if> />
                                </td>
                                <td>${rental.rentalId}</td>
                                <td>${rental.rentalDate}</td>
                                <td>${rental.dvdTitle}</td>
                                <td>${rental.memberId}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${rental.isReturned}"><span class="badge badge-success">返却済み</span></c:when>
                                        <c:otherwise><span class="badge badge-danger">未返却</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
		                            <c:if test="${rental.isReturned}">
		                                ${rental.returnDate}
		                            </c:if>
		                        </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <button type="submit" class="btn btn-success custom-button">返却する</button>
        </form>
    </div>

    <!-- Bootstrap JS 및 jQuery 추가 -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>