<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<title>빈 강의실 검색</title>
	<link href="http://www.leesangyoon.com/style/normalize.css" rel="stylesheet" type="text/css">
	<link href="http://www.leesangyoon.com/style/timetable.css" rel="stylesheet" type="text/css">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<style>
	* {font: .75em/100% dotum;}
	h3 {text-align: center;}
	table {width: 100% !important;}
	th, td {text-align: center !important;}
	th {font-weight: bold !important;}
	input[type="submit"] {width: 100% !important;}
	</style>
</head>
<body>
    <h3>시간표 목록</h3>
	<table>
	<thead>
		<tr style="text-align: center;">
			<th>과목번호</th>
			<th>교과목명</th>
			<th>강의요시/강의실</th>
			<th>담당교수</th>
		</tr>
	</thead>
	<tfoot>
		<tr>
			<td colspan="4" style="text-align: right;">
				<form action="" method="post">
					<input type="text" id="subject" name="subject" placeholder="교과목 명" value="${keyword}" />
					<input type="submit" value="검색" class="button radius" />
				</form>
			</td>
		</tr>
	</tfoot>
	<tbody>
	<c:choose>
	<c:when test="${not empty timetables}">
	<c:forEach items="${timetables}" var="data">
		<tr style="text-align: center;">
			<td>${data[0]}</td>
			<td>${data[1]}</td>
			<td>${data[2]} ${data[3]} (${data[4]})</td>
			<td>${data[5]}</td>
		</tr>
	</c:forEach>
	</c:when>
	<c:otherwise>
		<tr>
			<td colspan="4">데이터가 존재하지 않습니다.</td>
		</tr>
	</c:otherwise>
	</c:choose>
	</tbody>
	</table>
</body>
</html>