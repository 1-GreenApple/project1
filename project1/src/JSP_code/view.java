package JSP_code;

public class view {
/*write.jsp ���� �� view.jsp ����
 * 
 * ��� ������ ������ 
 * <@page import="java.io.PrintWriter"%>
 * <@page import="bbs.Bbs"%>
 * <@page import="bbs.BbsDAO"%> ��� �߰�
 * 
 * <body>
 * 
 *-> bbsID�� �ʱ�ȭ ��Ű�� �����Ͱ� �Ѿ�� ���� �����ϸ� ĳ������ �Ͽ� ������ ��´�.
 * int bbsID=0;
 * if(request.getParameter("bbsID")!=null)
 * {bbsID=Integer.parseInt(request.getParameter("bbsID"));}
 * 
 * ->�Ѿ�� �����Ͱ� ���ٸ� 
 * if(bbsID==0)
 * {PrintWriter script=reponse.getWriter();
 * script.println("<script>");
 * script.println("alert('��ȿ���� ���� ���Դϴ�.')");
 * script.println("location.herf='bbs.jsp'");
 * script.println("</script>");
 * }
 * -> ��ȿ�� ���̶�� ��ü���� ������ 'bbs'��� �ν��Ͻ��� ��´�.
 * Bbs bbs = new BbsDAO().getBbs(bbsID)
 * </body>

 	
 	<tbody>
 	<tr><td style="width: 20%;">�� ����</td>
 	<td colspan="2"><%=bbs.getBbsTitle().replaceAll(" ","&nbsp;")
 	.replaceAll("<","&lt;").replaceAll(">","&gt;").replaceAll("\n","<br>")%></td>
 	</tr>
 	�ۼ���
 	�ۼ�����
 	���뵵 ������� ���������� �ۼ� �ٸ�, td style="~~" ������ ���� replaceAll�� �ش�
 	</tbody>
*/
}
