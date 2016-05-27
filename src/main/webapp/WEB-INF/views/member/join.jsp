<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<title>회원 가입</title>
	<link href="https://github.global.ssl.fastly.net/assets/github-4fb1bd5b1cfc52208aadd90424bff1dd908b943a.css" media="all" rel="stylesheet" type="text/css">
	<link href="https://github.global.ssl.fastly.net/assets/github2-3754bb8b15b2f667994498f624683f627d3bb6af.css" media="all" rel="stylesheet" type="text/css">
</head>
<body>
	<form:form modelAttribute="joinStudent" accept-charset="UTF-8">
	<div class="boxed-group">
    <h3>Sign Up</h3>
	<div class="boxed-group-inner seamless">
		<table class="boxed-group-table">
		<colgroup>
			<col width="150" />
			<col width="*" />
		</colgroup>
		<tbody>
			<tr>
				<th><label for="sid">Student Number</label></th>
				<td><form:input path="sid" type="text" size="30" /></td>
			</tr>
			<tr>
				<th><label for="password">Password</label></th>
				<td><form:input path="password" type="password" size="30" /></td>
			</tr>
			<tr>
				<th><label for="name">Name</label></th>
				<td><form:input path="name" type="text" size="30" /></td>
			</tr>
			<tr>
				<th><label for="email">Email</label></th>
				<td><form:input path="email" type="email" size="30" /></td>
			</tr>
			<tr>
				<th><label>Grade</label></th>
				<td>
					<select name="grade" id="grade">
					<c:forEach items="${gradeList}" var="data">
						<option value="${data}">${data} 학년</option>
					</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th><label>Major</label></th>
				<td>
					<select name="major" id="major">
					<c:forEach items="${majorList}" var="data">
						<option value="${data}">${data}</option>
					</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" name="commit" value="Create an account" class="button primary" />
					<input type="reset" name="reset" value="Reset" class="button" />
					</td>
			</tr>
			</tbody>
		</table>
	</div>
	</div>
	</form:form>
</body>
</html>