package admin;

import java.io.IOException;
import java.io.PrintWriter;

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
import cmn.StringUtil;


@WebServlet(description = "로그인", urlPatterns = { "/admin/admin.do","/admin/admin.json" })
public class AdiminCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Logger LOG=Logger.getLogger(AdminService.class);
	
	private AdminService adminService;
	
	public AdiminCtrl(){
		LOG.debug("0-------------------");
		adminService = new AdminService();
		LOG.debug("-adminService-"+adminService);
    	LOG.debug("0-------------------");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("01 doGet()");
		LOG.debug("01.1 adminService:"+adminService);
		doServiceHandler(request,response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("01 doPost()");
		LOG.debug("01.1 adminService:"+adminService);
		doServiceHandler(request,response);
	}

	public void doServiceHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//기능 : do_retrieve,do_insert,do_update,do_selectone,do_retrieve
    	//work_div
    	LOG.debug("02 doServiceHandler()");
    	request.setCharacterEncoding("UTF-8");
    	//work_div:read
    	String workDiv = StringUtil.nvl(request.getParameter("work_div"),"");
    	LOG.debug("02.1 workDiv:"+workDiv);
    	
    	/* do_retrieve:목록
    	 * do_insert:등록
    	 * do_update:수정
    	 * do_selectone:단건조회
    	 * do_delete:
    	 */
    	switch(workDiv){
    		case "do_logout":
    			do_logout(request,response);
    		break;
    	    case "do_login":
    	    	do_login(request,response);
    		break;
    	    case "do_move_to_main":
    	    	do_move_to_main(request,response);
    		break;   
    		     		
    	}
	}
	//do_login
		protected void do_login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  LOG.debug("03.1 do_login");
		  
		  //param 세팅
		  AdminVO inVO = new AdminVO();
		  
		  String admin =StringUtil.nvl(request.getParameter("user_id"),"");
	      String pw =StringUtil.nvl(request.getParameter("passwd"),"");
	      
	      inVO.setAdmin(admin);
	      inVO.setPw(pw);
	      LOG.debug("03.2 inVO:"+inVO);
	      
	      MessageVO checkMsg = adminService.loginCheck(inVO);
	      AdminVO outVO = new AdminVO();
	      if(checkMsg.getMsgId().equals("1")){
	    	  outVO = adminService.do_selectOne(inVO);
	    	//session:
	      	HttpSession session = request.getSession();
	      	session.setAttribute("user", outVO);
	      	session.setAttribute("admin", outVO.getAdmin()); 
	    	LOG.debug("03.3 OutVO:"+outVO); 
	      }
		    //JSON
		  	Gson gson=new Gson();
		  	response.setContentType("text/html;charset=utf-8");
		  	PrintWriter out =response.getWriter();
		  	String gsonString = "";
		  	gsonString = gson.toJson(checkMsg);
		  	LOG.debug("03.4 gsonString:"+gsonString);
		  	out.print(gsonString);
	        
	     }
		//LOGOUT
		protected void do_logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	LOG.debug("03.1 do_logout");
	    	//1.session삭제
	    	HttpSession  httpSession = request.getSession();
	    	if(null !=httpSession ){
	    		LOG.debug("03.2 httpSession"+httpSession);
	    		httpSession.removeAttribute("admin");
	    		httpSession.removeAttribute("id");
	    		
	    		httpSession.invalidate();
	    		LOG.debug("03.3 httpSession"+httpSession);
	    	}
	    	//2.login.jsp이동
			RequestDispatcher dispatcher =request.getRequestDispatcher("/villa/index.jsp");
			dispatcher.forward(request, response);     	
	    }
		private void do_move_to_main(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			LOG.debug("03.1 do_move_to_main");
			RequestDispatcher dispatcher =request.getRequestDispatcher("/villa/index.jsp");
			dispatcher.forward(request, response);	
		}
}