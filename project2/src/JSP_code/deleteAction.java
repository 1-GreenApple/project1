package JSP_code;

public class deleteAction {
/*updateAction.jsp ���� �� deleteAction ����
 * 
 * <%@page import="java.io.PrintWriter"%>
<%@page import="bbs.BbsDAO"%>
<%@page import="bbs.Bbs"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP �Խ��� �� ����Ʈ</title>
</head>
<body>
	<%
		// ���� ���� ���¸� üũ�Ѵ�
		String userID = null;
		if(session.getAttribute("userID") != null){
			userID = (String)session.getAttribute("userID");
		}
		// �α����� �� ����� ���� �� �� �ֵ��� �ڵ带 �����Ѵ�
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
		} else{
			// �� ���� ������ �����Ѵ�
			BbsDAO bbsDAO = new BbsDAO();
			int result = bbsDAO.delete(bbsID);
			// �����ͺ��̽� ������ ���
			if(result == -1){
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('�� �����ϱ⿡ �����߽��ϴ�')");
				script.println("history.back()");
				script.println("</script>");
			// �� ������ ���������� ����Ǹ� �˸�â�� ���� �Խ��� �������� �̵��Ѵ�
			}else {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('�� �����ϱ� ����')");
				script.println("location.href='bbs.jsp'");
				script.println("</script>");
			}
		}
	
	%>
</body>
</html>
 * 
 * */
}
