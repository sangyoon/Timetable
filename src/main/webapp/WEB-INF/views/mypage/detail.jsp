<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--[if IE 9]><html class="lt-ie10" lang="en" > <![endif]-->
<html class="no-js" lang="ko">
<head>
	<meta charset="utf-8">
	<title>빈 강의실 검색</title>
	<link href="http://www.leesangyoon.com/style/normalize.css" rel="stylesheet" type="text/css">
	<link href="http://www.leesangyoon.com/style/timetable.css" rel="stylesheet" type="text/css">
	<style>
	* {font: .75em/100% dotum;}
	h3 {text-align: center;}
	table {width: 100% !important;}
	th, td {text-align: center !important;}
	th {font-weight: bold !important;}
	td.bordered {border: 1px solid #666 !important;}
	td.highlight {background-color: #007ba1 !important;}
	input[type="submit"] {width: 100% !important;}
	</style>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<script>
		var usable = ${usable};
		
		if( usable == true )
			alert( "강의실 이용 가능합니다.\n\n열심히 공부하세요!" );
		else
			alert( "수업이 진행되는 강의실이 있습니다.\n\n시간을 변경하시거나 다른 강의실을 찾아주세요!" );
	</script>
</head>
<body>
    <h3>${place}</h3>
	<table>
	<thead>
		<tr>
			<th width="120"></th>
			<th width="120">월요일</th>
			<th width="120">화요일</th>
			<th width="120">수요일</th>
			<th width="120">목요일</th>
			<th width="120">금요일</th>
		</tr>
	</thead>
	<tfoot>
	<tr>
		<td colspan="6">
			<form action="" method="post">
				<select id="week" name="week">
					<option value="월"<c:if test="${week eq 0}"> selected</c:if>>월요일</option>
					<option value="화"<c:if test="${week eq 1}"> selected</c:if>>화요일</option>
					<option value="수"<c:if test="${week eq 2}"> selected</c:if>>수요일</option>
					<option value="목"<c:if test="${week eq 3}"> selected</c:if>>목요일</option>
					<option value="금"<c:if test="${eek eq 4}"> selected</c:if>>금요일</option>
				</select>
				<select id="start" name="start">
				<c:forEach items="${start}" var="data" varStatus="i">
					<option value="${i.count}"<c:if test="${s eq i.count}"> selected</c:if>>${data}</option>
				</c:forEach>
				</select>
				<select id="end" name="end">
				<c:forEach items="${end}" var="data" varStatus="i">
					<option value="${i.count + 1}"<c:if test="${e eq i.count + 1}"> selected</c:if>>${data}</option>
				</c:forEach>
				</select>
				<input type="submit" value="검색" class="button radius" />
		</form>
		</td>
	</tr>
	</tfoot>
	<tbody>
	<c:forEach items="${timetable}" var="data">
		<tr>
			<th>${data[0]}</th>
			${data[1]}
			${data[2]}
			${data[3]}
			${data[4]}
			${data[5]}
		</tr>
	</c:forEach>
	</tbody>
	</table>
</body>
</html>