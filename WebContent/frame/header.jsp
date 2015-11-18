<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 用於頁面的頭部顯示公司logo，歡迎用戶，登出等信息 -->
<html>
<body>
	<a href="/PDAClient/systemtree.jsp">返回系統菜單</a>
	<%if(session.getAttribute("username") != null){
		out.println(session.getAttribute("username"));
	%>
		歡迎 <%=session.getAttribute("username")%>&nbsp;&nbsp;
		<a href="/PDAClient/loginout.do">登出</a>
	<%}else {%>
		歡迎
	<%}%>
</body>
</html>