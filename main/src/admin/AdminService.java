package admin;

import org.apache.log4j.Logger;

import cmn.DTO;
import cmn.MessageVO;



public class AdminService {
	// -View -> Controller -> Service -> Dao  
	private final Logger LOG=Logger.getLogger(AdminService.class);
	
	private AdminDao adminDao;
	
	public AdminService(){
		LOG.debug("0-------------------");
		adminDao = new AdminDao();
		LOG.debug("-adminDao-"+adminDao);
		LOG.debug("0-------------------");	
		
	}
	public MessageVO loginCheck(DTO dto){
		MessageVO outVO =new MessageVO();
		//01:ID CHECK
		outVO = adminDao.idCheck(dto);
		if(!outVO.getMsgId().equals("1")){
			return outVO;
		}
		//O2: 비밀번호 체크
		outVO = adminDao.passCheck(dto);
		if(!outVO.getMsgId().equals("1")){
			return outVO;
		}
		return outVO;
	}
	
	public AdminVO do_selectOne(DTO dto){
		return adminDao.do_selectOne(dto);
	}
}