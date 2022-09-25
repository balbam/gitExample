<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memo_list</title>
<%@ include file="../include/header.jsp" %>
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<script type="text/javascript">
$(function(){
	$("#memo").summernote({
		width : 500,
		height : 200
	});
});

function memo_view(idx){
	location.href="${path}/memo/view/"+idx;
	//REST(Representaional state transfer) 방식, 또는 RESTful한 URI방식
	//게시물로 고유한 주소값을 가짐, 게시물하나가 주소1개임.
	//http://localhost/spring02/memo/view/1 번게시물
	//http://localhost/spring02/memo/view/2
	//스프링에서는 이런방식으로 요청이 들어오면 @PathVariable것을 적용해야함.
	
	//jsp방식에서는 파라미터가 바뀌는것 뿐이지 주소가 바뀌는것은 아니었음
	//http://localhost/spring02/memo/view.do?idx=1
	//http://localhost/spring02/memo/view.do?idx=2		
}
</script>

</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>메모장</h2>
<form method="post" action="${path}/memo/insert.do">
 이름 : <input name="writer" size="10"> <br>
 메모 : <!-- <input id="memo" name="memo" size="40">  -->
 <textarea rows="5" cols="60" name="memo" id="memo"></textarea>
 
 
<input type="submit" value="확인">
</form>
<table border="1">
 <tr>
  <th>번호</th>
  <th>이름</th>
  <th>메모</th>
  <th>날짜</th>
 </tr>
<c:forEach var="row" items="${list}"> 
 <tr>
  <td>${row.idx}</td>
  <td>${row.writer}</td>
  <td><a href="#" onclick="memo_view('${row.idx}')">
  ${row.memo}</a></td>
  <td><fmt:formatDate value="${row.post_date}" 
  pattern="yyyy-MM-dd HH:mm:ss"/>   </td>
 </tr>
</c:forEach>
</table>
</body>
</html>