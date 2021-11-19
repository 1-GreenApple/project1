package JSP_code;

public class view {
/*write.jsp 복사 후 view.jsp 생성
 * 
 * 상단 페이지 설정에 
 * <@page import="java.io.PrintWriter"%>
 * <@page import="bbs.Bbs"%>
 * <@page import="bbs.BbsDAO"%> 요소 추가
 * 
 * <body>
 * 
 *-> bbsID를 초기화 시키고 데이터가 넘어온 것이 존재하면 캐스팅을 하여 변수에 담는다.
 * int bbsID=0;
 * if(request.getParameter("bbsID")!=null)
 * {bbsID=Integer.parseInt(request.getParameter("bbsID"));}
 * 
 * ->넘어온 데이터가 없다면 
 * if(bbsID==0)
 * {PrintWriter script=reponse.getWriter();
 * script.println("<script>");
 * script.println("alert('유효하지 않은 글입니다.')");
 * script.println("location.herf='bbs.jsp'");
 * script.println("</script>");
 * }
 * -> 유효한 글이라면 구체적인 정보를 'bbs'라는 인스턴스에 담는다.
 * Bbs bbs = new BbsDAO().getBbs(bbsID)
 * </body>

 	
 	<tbody>
 	<tr><td style="width: 20%;">글 제목</td>
 	<td colspan="2"><%=bbs.getBbsTitle().replaceAll(" ","&nbsp;")
 	.replaceAll("<","&lt;").replaceAll(">","&gt;").replaceAll("\n","<br>")%></td>
 	</tr>
 	작성일
 	작성일자
 	내용도 글제목과 마찬가지로 작성 다만, td style="~~" 내용은 없고 replaceAll만 해당
 	</tbody>
*/
}
