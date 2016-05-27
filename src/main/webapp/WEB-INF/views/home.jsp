<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<title>홈 페이지</title>
</head>
<body>
	${students}
	<ol>
	<c:forEach items="${students}" var="data">
		<li><c:out value="${data.name}" /></li>
	</c:forEach>
	</ol> 
</body>
</html>