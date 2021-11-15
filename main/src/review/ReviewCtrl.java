package review;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import cmn.MessageVO;
import cmn.SearchVO;
import cmn.StringUtil;
import notice1.NoticeVO;

@WebServlet(description = "�����ı����", urlPatterns = { "/villa/review.do" })
public class ReviewCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//do_save_move
	 protected void do_save_move(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("03.1 do_save_move");
		
		RequestDispatcher dispatcher =request.getRequestDispatcher("/villa/reviewWrite.jsp");
		dispatcher.forward(request, response); 
	 }
	// -view->Controller->Service->Dao
	// controller->service�̱� ������ ���� ��ü�� ������ �Ѵ�. 
	private ReviewService reviewService;
	
	private final Logger LOG = Logger.getLogger(ReviewDao.class);
	public ReviewCtrl(){
		reviewService = new ReviewService();
	}
	
	//doServiceHandler() do post, do get ������ �� �� ����� ����ش�. 
	protected void doServiceHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��� �б� 
		LOG.debug("02 doServiceHandler()");
		request.setCharacterEncoding("UTF-8");
		//work div�� ������ �б��ؼ� ó���Ѵ� 
		//request null ó�� 
		String workDiv = StringUtil.nvl(request.getParameter("work_div"),""); //request null �߸� ""���� �ߵ��� util�� �����ش�.
    	LOG.debug("02.1 workDiv: "+workDiv);
    
    	//case�� �б� �ϱ� 
    	/*do_retrieve : ���
    	 *do_insert: ���
    	 *do_update: ����
    	 *do_selectone:�ܰ���ȸ
    	 *do_delete:���� 
    	 */
    	//workDiv�� �����Ѵ�.
	
    	switch(workDiv){
	    	case "do_save_move":
				do_save_move(request,response);
			break;
    		case "do_insert": 
				//ȣ��Ǵ��� Ȯ�� 
				do_insert(request,response);
				break;
			case "do_update": 
				//ȣ��Ǵ��� Ȯ�� 
				do_update(request,response);
				break;
			case "do_delete": 
				//ȣ��Ǵ��� Ȯ�� 
				do_delete(request,response);
				break;
			case "do_selectone": 
				//ȣ��Ǵ��� Ȯ�� 
				do_selectone(request,response);
				break;
			case "do_retrieve": 
				//ȣ��Ǵ��� Ȯ�� 
				do_retrieve(request,response);
				break;
    	}
	}
	
	//�ؿ� �ִ� swich������ ȣ��Ǵ� �ֵ� ����
    //http://localhost:8080/WEB_EX01/member/member.do
	private void do_insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		LOG.debug("03.1 do_insert");
		ReviewVO inVO = new ReviewVO();
		String seq= StringUtil.nvl(request.getParameter("seq"), "");
    	String title = StringUtil.nvl(request.getParameter("title"),"");
        String readCnt = StringUtil.nvl(request.getParameter("read_cnt"),"");
        String contents = StringUtil.nvl(request.getParameter("contents"),"");
        String regId = StringUtil.nvl(request.getParameter("reg_id"),"");
        String regDt = StringUtil.nvl(request.getParameter("reg_dt"),"");
        String no	 = StringUtil.nvl(request.getParameter("room"),"");
        String passWd= StringUtil.nvl(request.getParameter("passwd"), "");
        
        inVO.setSeq(seq);
        inVO.setTitle(title);
        inVO.setRead_cnt(readCnt);
        inVO.setContents(contents);
        inVO.setReg_id(regId);
        inVO.setReg_dt(regDt);
        inVO.setNo(no);
        inVO.setPasswd(passWd);
        
    	LOG.debug("03.2 param:"+inVO);
   
    	int flag= this.reviewService.do_insert(inVO);
    	LOG.debug("03.3 flag:"+flag);
    	//--param
    	
    	Gson gson = new Gson();
    	response.setContentType("text/html;charset= utf-8");
    	PrintWriter out = response.getWriter();
    	
    	String msg ="";
    	String gsonString ="";
    	if(flag>0){
    		msg= "���� ����Ͽ����ϴ�.";
    	}else{
    		msg="��Ͻ���";
    	}
    	gsonString = gson.toJson(new MessageVO(String.valueOf(flag),msg));
    	LOG.debug("03.4 gsonString:"+gsonString);
    	out.print(gsonString);
    }

	private void do_update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		LOG.debug("03.1 do_update");	
		//service call : ���� 
		  ReviewVO inVO = new ReviewVO();
		//param
			String seq= StringUtil.nvl(request.getParameter("seq"), "");
	    	String title = StringUtil.nvl(request.getParameter("title"),"");
	        String contents = StringUtil.nvl(request.getParameter("contents"),"");
	        String regId = StringUtil.nvl(request.getParameter("reg_id"),"");
	        String regDt = StringUtil.nvl(request.getParameter("reg_dt"),"");
	        String no	 = StringUtil.nvl(request.getParameter("room"),"");
	        String passWd= StringUtil.nvl(request.getParameter("passwd"), "");
	        LOG.debug("no"+no);
	        
	        inVO.setSeq(seq);
	        inVO.setTitle(title);
	        inVO.setContents(contents);
	        inVO.setReg_id(regId);
	        inVO.setReg_dt(regDt);
	        inVO.setNo(no);
	        inVO.setPasswd(passWd);
	        
	        LOG.debug("03.2 param:"+inVO);
	    	
	    	int flag= this.reviewService.do_update(inVO);
	    	LOG.debug("03.3 flag:"+flag);
				
				//JSON
		    	Gson gson = new Gson();
		    	response.setContentType("text/html;charset=utf-8"); 
		    	PrintWriter out = response.getWriter();
		    	String msg = "";
		    	String gsonString ="";
		    	
		    	//�����Ǿ����ϴ�. �˸�
		    	if(flag>0){
		    		msg="�����Ǿ����ϴ�.";
		    	}else{
		    		msg="����  ����.";
		    	}
		    	gsonString=gson.toJson(new cmn.MessageVO(String.valueOf(flag),msg));
		    	LOG.debug("03.3 gsonString: "+gsonString);
		    	out.print(gsonString);
				}

	private void do_delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		LOG.debug("03.1 do_delete");
		//service call : ���� 
		ReviewVO inVO = new ReviewVO();
		//param:seq
		String seq = StringUtil.nvl(request.getParameter("seq"),"");
		inVO.setSeq(seq);
		int flag = reviewService.do_delete(inVO);
		LOG.debug("03.2 flag: "+flag);
		
		//JSON
    	Gson gson = new Gson();
    	response.setContentType("text/html;charset=utf-8"); 
    	PrintWriter out = response.getWriter();
    	String msg = "";
    	String gsonString ="";
    	
    	//msgId=flag
    	//msgContents ='�����Ǿ����ϴ�';
		
    	//�����Ǿ����ϴ�. �˸�
    	if(flag>0){
    		msg="�����Ǿ����ϴ�.";
    	}else{
    		msg="���� ����.";
    	}
    	gsonString=gson.toJson(new cmn.MessageVO(String.valueOf(flag),msg));
    	LOG.debug("03.3 gsonString: "+gsonString);
    	out.print(gsonString);
		}

	private void do_selectone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("03.1 do_selectone");
		//param ����
    	ReviewVO inVO = new ReviewVO();
    	String seq	 =StringUtil.nvl(request.getParameter("seq"), "");
    	
	    // ip ã�� �� request.getRemoteAddr('')
	 	String ip= StringUtil.nvl(request.getRemoteAddr(),"0.0.0.0");
	 	HttpSession  session= request.getSession();
	 	LOG.debug("03.2 ip:"+ip);
	 	//String userId = (String) session.getAttribute("user_id");
    	//String regId = StringUtil.nvl(userId,"admin");
	 		
    	inVO.setSeq(seq);
    	inVO.setIp(ip);
    	
    	LOG.debug("03.2 inVO:"+inVO);
    	ReviewVO outVO =reviewService.do_selectOne(inVO);
    	LOG.debug("03.3 outVO:"+outVO);
    	request.setAttribute("vo",outVO);
    	
    	//ȭ������ 
    	request.setAttribute("mode", "udlate");
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/villa/reviewPost.jsp");
    	dispatcher.forward(request, response);
	}

	private void do_retrieve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("03.1 do_retrieve");
        SearchVO inVO = new SearchVO();
        String pageNum = StringUtil.nvl(request.getParameter("page_num"),"1");
        String searchDiv = StringUtil.nvl(request.getParameter("search_div"),"");
        String searchWord = StringUtil.nvl(request.getParameter("search_word"),"");
        String pageSize = StringUtil.nvl(request.getParameter("page_size"),"10");
        inVO.setPageNum(Integer.parseInt(pageNum));
        inVO.setSearchDiv(searchDiv);
        inVO.setSearchWord(searchWord);
        inVO.setPageSize(Integer.parseInt(pageSize));
        
        LOG.debug("03.2 inVO:"+inVO);
        List<ReviewVO> list = reviewService.do_retrieve(inVO);
        LOG.debug("-----------------------");
        for(ReviewVO vo:list){
           LOG.debug(vo);
        }
        LOG.debug("-----------------------");
        //�� �ۼ�
        int totalCnt =0; //�ʱ�ȭ
        if(null !=list &&list.size()>0){
        	ReviewVO totalVO = list.get(0);
    		totalCnt = totalVO.getTotal();
    	}
        request.setAttribute("totalCnt",totalCnt);
        
        request.setAttribute("list",list);
        request.setAttribute("paramVO", inVO);
        RequestDispatcher dispacher= request.getRequestDispatcher("/villa/review.jsp");
        dispacher.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("01 doGet()");
		doServiceHandler(request,response);//doServiceHandler�� ����ش�. 
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("01 doPost()");
		LOG.debug("01.1 reviewService:"+reviewService);
		doServiceHandler(request,response);	
	}
	
	
}