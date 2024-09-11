<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>DVD管理ページ</title>

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
        
        /* 선택된 탭에 대한 스타일 추가 */
        .selected {
            background-color: #007bff;
            color: white;
        }
    </style>
</head>
<body>

    <div class="container">
        <!-- 상단 탭 -->
        <nav class="nav tabs">
            <a class="nav-link btn btn-outline-primary" href="./">メインページ</a>
            <a class="nav-link btn btn-outline-primary" href="member">会員管理</a>
            <a class="nav-link btn btn-outline-primary" href="rent">レンタル管理</a>
            <a class="nav-link btn btn-outline-primary selected" href="dvd">DVD管理</a>
        </nav>

        <!-- DVD 등록 버튼 -->
        <div class="mb-3">
            <a href="registerDVD" class="btn btn-primary">DVD登録</a> <!-- DVD 등록 페이지로 이동 -->
        </div>

        <!-- DVD 삭제 폼 -->
        <form method="post" action="dvd">
            <div class="table-wrapper">
                <table class="table table-bordered table-striped">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">選択</th>
                            <th scope="col">タイトル</th>
                            <th scope="col">ジャンル</th>
                            <th scope="col">主演俳優</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dvd" items="${dvdList}">
                            <tr>
                                <td><input type="checkbox" name="dvdCheckbox" value="${dvd.id}" /></td>
                                <td>${dvd.title}</td>
                                <td>${dvd.genre}</td>
                                <td>${dvd.leadActor}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <button type="submit" class="btn btn-danger custom-button">選択されたDVDを削除</button> <!-- 삭제 버튼 -->
        </form>
    </div>

    <!-- Bootstrap JS 및 jQuery 추가 -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>