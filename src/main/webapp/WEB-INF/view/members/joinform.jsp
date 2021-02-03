<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 가입</title>
</head>
<body>
<form method="post" action="/members/join">
    <div>
        <label>이름</label>
        <input type="text" name="name">
    </div>
    <div>
        <label>비밀번호</label>
        <input type="password" name="password">
    </div>
    <div>
        <label>이메일</label>
        <input type="text" name="email">
    </div>
    <div>
        <label></label>
        <input type="submit" value="회원가입">
    </div>
</form>
</body>
</html>

