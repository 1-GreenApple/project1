package JSP_code;

public class update {
/*write.jsp���� �� update.jsp����
 * 
 *  <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="bbs.Bbs" %>
<%@ page import="bbs.BbsDAO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- ȭ�� ����ȭ -->
<meta name="viewport" content="width-device-width", initial-scale="1">
<!-- ��Ʈ ������ ��Ʈ��Ʈ���� �����ϴ� ��ũ -->
<link rel="stylesheet" href="css/bootstrap.css">
<title>JSP �Խ��� �� ����Ʈ</title>
</head>
<body>
	<%
		// ���ǿ� ���� ����ִ��� üũ
		String userID = null;
		if(session.getAttribute("userID") != null){
			userID = (String)session.getAttribute("userID");
		}
		if(userID == null){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('�α����� �ϼ���')");
			script.println("location.href='login.jsp'");
			script.println("</script>");
		}
		int bbsID = 0;
		if(request.getParameter("bbsID") != null){
			bbsID = Integer.parseInt(request.getParameter("bbsID"));
		}
		if(bbsID == 0){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('��ȿ���� ���� ���Դϴ�')");
			script.println("location.href='bbs.jsp'");
			script.println("</script>");
		}
		//�ش� 'bbsID'�� ���� �Խñ��� ������ ���� ������ ���Ͽ� �ۼ��� ������ �´��� üũ�Ѵ�
		Bbs bbs = new BbsDAO().getBbs(bbsID);
		if(!userID.equals(bbs.getUserID())){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('������ �����ϴ�')");
			script.println("location.href='bbs.jsp'");
			script.println("</script>");
		}
	%>
	<nav class="navbar navbar-default"> <!-- �׺���̼� -->
		<div class="navbar-header"> 	<!-- �׺���̼� ��� �κ� -->
			<!-- �׺���̼� ��� �ڽ� ���� -->
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<!-- �� ���� ��ư�� ȭ���� �������� ������ ��Ÿ���� -->
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<!-- ��� �ٿ� ������ ��Ÿ���� Ŭ���ϸ� main �������� �̵��Ѵ� -->
			<a class="navbar-brand" href="main.jsp">JSP �Խ��� �� ����Ʈ</a>
		</div>
		<!-- �Խ��� ���� �̸� ���� ��Ÿ���� �޴� ���� -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="main.jsp">����</a></li>
				<li class="active"><a href="bbs.jsp">�Խ���</a></li>
			</ul>
			<!-- ��� ������ ��Ÿ���� ����ٿ� ���� -->
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">ȸ������<span class="caret"></span></a>
					<!-- ����ٿ� ������ ���� -->	
					<ul class="dropdown-menu">
						<li><a href="logoutAction.jsp">�α׾ƿ�</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</nav>
	<!-- �׺���̼� ���� �� -->
	
	<!-- �Խ��� �� ���� ��� ���� ���� -->
	<div class="container">
		<div class="row">
			<form method="post" action="updateAction.jsp?bbsID=<%= bbsID %>">
				<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
					<thead>
						<tr>
							<th colspan="2" style="background-color: #eeeeee; text-align: center;">�Խ��� �� ���� ���</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="text" class="form-control" placeholder="�� ����" name="bbsTitle" maxlength="50"
								value="<%=bbs.getBbsTitle() %>"></td>
						</tr>
						<tr>
							<td><textarea class="form-control" placeholder="�� ����" name="bbsContent" maxlength="2048"
								style="height: 350px;"><%=bbs.getBbsContent() %></textarea></td>
						</tr>
					</tbody>
				</table>
				<!-- �۾��� ��ư ���� -->
				<input type="submit" class="btn btn-primary pull-right" value="�����ϱ� ">
			</form>
		</div>
	</div>
	<!-- �Խ��� �� ���� ��� ���� �� -->
	
	<!-- ��Ʈ��Ʈ�� ���� ���� -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>*/
}
