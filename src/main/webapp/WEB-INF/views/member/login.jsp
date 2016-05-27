<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<title>로그인</title>
	<link href="http://www.leesangyoon.com/style/normalize.css" rel="stylesheet" type="text/css">
	<link href="http://www.leesangyoon.com/style/timetable.css" rel="stylesheet" type="text/css">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<style>
	* {font: .75em/100% dotum; }
	
	form {margin-top: 120px;}
	h3 {padding: 10px 0 !important; text-align: center; background-color: #008cba !important; color: #fff !important;}
	table {width: 100% !important;}
	th, td {text-align: center !important;}
	th {font-weight: bold !important;}
	input[type="submit"] {width: 100% !important;}
	</style>
</head>
<body>
	<form:form modelAttribute="loginStudent" accept-charset="UTF-8">
	<h3>학생 로그인</h3>
	<div class="row collapse">
	    <div class="large-12 columns">
			<label for="sid"><strong>학번</strong></label>
			<form:input path="sid" type="text" id="login_sid" name="sid" autocapitalize="off" autofocus="autofocus" tabindex="1" placeholder="학번" />
			<form:errors path="sid" cssClass="error" element="small" />
	    </div>
 	</div>
	<div class="row collapse">
	    <div class="large-12 columns">
			<label for="password"><strong>비밀번호</strong></label>
			<form:input path="password" type="password" id="login_password" name="password" tabindex="2" placeholder="비밀번호" />
			<form:errors path="password" cssClass="error" element="small" />
	    </div>
 	</div>			
	<input type="submit" name="commit" value="Sign in" tabindex="3" class="button" />
	</form:form>
</body>
</html>