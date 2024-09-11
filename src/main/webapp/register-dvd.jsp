<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>DVD登録ページ</title>

    <!-- Bootstrap CSS 추가 -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

    <div class="container">
        <h2 class="mt-4">DVD登録</h2>
        <form method="post" action="registerDVD">
             <div class="form-group">
                <label for="title">タイトル</label>
                <input type="text" class="form-control" id="title" name="title" maxlength="30" required>
                <!-- title은 30자 제한 -->
            </div>
            <div class="form-group">
                <label for="genre">ジャンル</label>
                <input type="text" class="form-control" id="genre" name="genre" maxlength="20" required>
                <!-- genre은 20자 제한 -->
            </div>
            <div class="form-group">
                <label for="leadActor">主演俳優</label>
                <input type="text" class="form-control" id="leadActor" name="leadActor" maxlength="100" required>
                <!-- leadActor는 100자 제한 -->
            </div>
            <button type="submit" class="btn btn-primary">登録</button>
            <a href="dvd" class="btn btn-secondary ml-2">戻る</a> <!-- 뒤로가기 버튼 -->
        </form>
    </div>

    <!-- Bootstrap JS 및 jQuery 추가 -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.amazonaws.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>