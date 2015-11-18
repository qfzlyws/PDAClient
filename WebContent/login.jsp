<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>歡迎登錄</title>
		<link rel="stylesheet" type="text/css" href="css/css1.css">
	</head>
	<body>
		<div align="center">
			<form action="login.do" method="post">
				<input type="hidden" value="checkuser" name="function">
				<table border="1">
					<tr>
						<td align="right">系統:</td>
						<td align="left">
							<select name="system" class="input">
								<option value="B071-物流管理系統">B071-物流管理系統</option>
								<option value="B071-物流管理系統(Test)">B071-物流管理系統(Test)</option>
							</select>
						</td>
					</tr>
	
					<tr>
						<td align="right">用戶名:</td>
						<td align="left"><input type="text" name="username" class="input" /></td>
					</tr>
	
					<tr>
						<td align="right">密碼:</td>
						<td align="left"><input type="password" name="password" class="input" /></td>
					</tr>
	
					<tr>
						<td align="center" colspan="2"><input type="submit" value="提交">
							<input type="reset" value="重置"></td>
					</tr>
				</table>
			</form>
		</div>
	
	</body>
</html>