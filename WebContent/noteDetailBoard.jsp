<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쪽지 상세조회</title>
</head>
<body>
보낸사람 : ${requestScope.note.counterpart_nickname} <br>
보낸날짜 : ${requestScope.note.sendDate }<br>
내용 : ${ requestScope.note.content }

<form action = "${pageContext.request.contextPath}/deleteNote.do" method = "POST">
<input type="hidden" name="noteNo" value = "${requestScope.note.note_no}" />
<input type="hidden" name = "counterpartNo" value = "${requestScope.note.counterpart_no }" />
<input type="hidden" name="isRecieve" value = "${requestScope.note.sendrecieve}" />
<button type = "submit">삭제</button>
</form>
</body>
</html>