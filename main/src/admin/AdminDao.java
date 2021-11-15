package admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import cmn.ConnectionMaker;
import cmn.DTO;
import cmn.JDBCReturnReso;
import cmn.MessageVO;
import cmn.WorkDiv;

public class AdminDao implements WorkDiv {

	private final Logger LOG = Logger.getLogger(AdminDao.class);
	private ConnectionMaker  connectionMaker;
	
	private static AdminDao instance =new AdminDao();
	
	public static AdminDao getInstance(){
	return instance;
	}

	public AdminDao(){
		connectionMaker = new ConnectionMaker();
	}
    //패스워드
	public MessageVO passCheck(DTO dto) {
		AdminVO adminVO= (AdminVO) dto;
		MessageVO outVO= new MessageVO();
        int result =0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		try{
			StringBuilder sb=new StringBuilder();
			 sb.append(" SELECT COUNT(*) cnt		\n");
			 sb.append(" FROM admin                 \n");
			 sb.append(" WHERE admin = ?            \n");
			 sb.append(" AND PW= ?                  \n");
			
			 conn = connectionMaker.getConnection();
				LOG.debug("1. conn:"+conn);
				LOG.debug("2. sql:\n"+sb.toString());
			
				pstmt = conn.prepareStatement(sb.toString());
				pstmt.setString(1, adminVO.getAdmin());
				pstmt.setString(2, adminVO.getPw());
				LOG.debug("3. param:"+adminVO.getAdmin());
				LOG.debug("3. param:"+adminVO.getPw());
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getInt("cnt");
				if(result==0){//비밀번호를 확인
					outVO.setMsgId("20");
					outVO.setMsgContents("비밀번호를 확인하세요");
				}else{
					outVO.setMsgId("1");
					outVO.setMsgContents("비번ok.");
				}
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JDBCReturnReso.close(rs);
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);		
		}
		
		
		return outVO;
	}
	
	/**Admin 단건조회*/
	@Override
	public AdminVO do_selectOne(DTO dto) {
		AdminVO adminVO= (AdminVO) dto;
		AdminVO outVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		try{
			StringBuilder sb=new StringBuilder();
			sb.append(" SELECT		   \n");
			sb.append("     admin,     \n");
			sb.append("     pw         \n");
			sb.append(" FROM           \n");
			sb.append("     admin      \n");
			sb.append(" WHERE admin = ?  \n");
			
			conn = connectionMaker.getConnection();
			LOG.debug("1.============================");
			LOG.debug("1.query: \n"+sb.toString());
			LOG.debug("1.============================");
			
			pstmt=conn.prepareStatement(sb.toString());
			//query param
			pstmt.setString(1,adminVO.getAdmin());
			LOG.debug("2.============================");
			LOG.debug("2.param getAdmin="+adminVO.getAdmin());
			LOG.debug("2.============================");	
	
			rs = pstmt.executeQuery();
			if(rs.next()){
				outVO = new AdminVO();
				outVO.setAdmin(rs.getString("Admin"));
				outVO.setPw(rs.getString("Pw"));
		}
		}catch(SQLException e){
			LOG.debug("===================");
			LOG.debug("SQLException="+e.getMessage());
			LOG.debug("===================");
		}finally{
			JDBCReturnReso.close(rs);
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}
		return outVO;
	}
	
	
	//아이디
	public MessageVO idCheck(DTO dto) {
		AdminVO adminVO= (AdminVO) dto;
		MessageVO outVO= new MessageVO();
        int result =0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		try{
			StringBuilder sb=new StringBuilder();
			 sb.append(" SELECT COUNT(*) cnt		\n");
			 sb.append(" FROM admin                 \n");
			 sb.append(" WHERE admin = ?            \n");
			
			 conn = connectionMaker.getConnection();
				LOG.debug("1. conn:"+conn);
				LOG.debug("2. sql:\n"+sb.toString());
			
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1,adminVO.getAdmin());
			LOG.debug("3. param:"+adminVO.getAdmin());
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getInt("cnt");
				if(result==0){//id가 0이면 '아이디를 확인하세요'
					outVO.setMsgId("10");
					outVO.setMsgContents("id를 확인하세요");
				}else{
					outVO.setMsgId("1");
					outVO.setMsgContents("id가 있습니다.");
				}
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JDBCReturnReso.close(rs);
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);		
		}
		
		
		return outVO;
	}}