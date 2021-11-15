package review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import cmn.ConnectionMaker;
import cmn.DTO;
import cmn.JDBCReturnReso;
import cmn.SearchVO;
import cmn.WorkDiv;

import read_cnt2.ReadCntVO2;

public class ReviewDao implements WorkDiv {
	private final Logger LOG = Logger.getLogger(ReviewDao.class);
	private ConnectionMaker  connectionMaker;
	
	public ReviewDao() {
		connectionMaker = new ConnectionMaker();
	}
    @Override
	public int do_insert(DTO dto) {
		ReviewVO vo = (ReviewVO) dto;
		int flag  = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			StringBuilder sb=new StringBuilder();
			sb.append(" INSERT INTO board2 (		\n");
			sb.append("     seq,                    \n");
			sb.append("     title,                  \n");
			sb.append("     contents,               \n");
			sb.append("     reg_id,                 \n");
			sb.append("     reg_dt,                 \n");
			sb.append("     no,                	    \n");
			sb.append("     passwd                  \n");
			sb.append(" ) VALUES (                  \n");
			sb.append("     SEQ_BOARD.nextval,      \n");
			sb.append("     ?,                      \n");
			sb.append("     ?,                      \n");
			sb.append("     ?,                      \n");
			sb.append("     SYSDATE,                \n");
			sb.append("     ?,                       \n");
			sb.append("     ?                       \n");
			sb.append(" )                           \n");


			
			LOG.debug("1.============================");
			LOG.debug("1.query\n:"+sb.toString());
			LOG.debug("1.============================");
			
			conn = connectionMaker.getConnection();
			LOG.debug("2.============================");
			LOG.debug("1.conn\n:"+conn);
			LOG.debug("1.============================");
			pstmt = conn.prepareStatement(sb.toString());
			
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setString(3, vo.getReg_id());
			pstmt.setString(4, vo.getNo());
			pstmt.setString(5, vo.getPasswd());
			
			LOG.debug("2.============================");
			LOG.debug("2.param\n:"+vo.toString());
			LOG.debug("2.============================");			
			
			flag = pstmt.executeUpdate();
			LOG.debug("3.============================");
			LOG.debug("3.flag\n:"+flag);
			LOG.debug("3.============================");	
			
		}catch(SQLException e){
			LOG.debug("======================");
			LOG.debug("SQLException:"+e.toString());
			LOG.debug("======================");
			
		}finally{
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}
		return flag;
	}

	@Override
	public int do_update(DTO dto) {
		ReviewVO vo= (ReviewVO) dto;
		int flag = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = connectionMaker.getConnection();
			
			LOG.debug("1.conn:"+conn);
			StringBuilder sb=new StringBuilder();
			
			sb.append(" UPDATE board2	        \n");
			sb.append(" SET title =  ?,         \n");
			sb.append("     contents =  ?,      \n");
			sb.append("     reg_id =  ?,        \n");
			sb.append("     reg_dt = sysdate,   \n");
			sb.append("     no = ?,		        \n");
			sb.append("     passwd = ?          \n");
			sb.append(" WHERE seq = ?           \n");
			
			
			LOG.debug("1.======================");
			LOG.debug("1.query \n"+sb.toString());
			LOG.debug("1.======================");
			
			
			pstmt = conn.prepareStatement(sb.toString());
			
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setString(3, vo.getReg_id());
			pstmt.setString(4, vo.getNo());
			pstmt.setString(5, vo.getPasswd());
			pstmt.setString(6, vo.getSeq());
			
			LOG.debug("2.======================");
			LOG.debug("2.param :"+vo);
			LOG.debug("2.======================");			
			
			
			flag = pstmt.executeUpdate();
			LOG.debug("3.======================");
			LOG.debug("3.flag :"+flag);
			LOG.debug("3.======================");	
			
		}catch(SQLException s){
			LOG.debug("================");
			LOG.debug("SQLException="+s.toString());
			LOG.debug("================");
		}finally{
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}
		return flag;
	}

	@Override
	public int do_delete(DTO dto) {
		ReviewVO vo = (ReviewVO) dto;
		int flag = 0;
		Connection conn = null;
		
		PreparedStatement pstmt = null;
		
		
		StringBuilder sb=new StringBuilder();
		sb.append("	DELETE FROM board2		  \n");
		sb.append(" WHERE seq = ?     \n");
		
		try {
			conn = connectionMaker.getConnection();
			//transaction개발자가 관리한다.
			conn.setAutoCommit(false);
			
			LOG.debug("1======================");
			LOG.debug("query:\n"+sb.toString());
			LOG.debug("1======================");
			
			pstmt = conn.prepareStatement(sb.toString());
			//query param
			pstmt.setString(1, vo.getSeq());
			LOG.debug("2======================");
			LOG.debug("param, seq="+vo.getSeq());
			LOG.debug("2======================");	
			
			flag = pstmt.executeUpdate();
			//-transaction
			if(flag>0){
				LOG.debug("3======================");
				LOG.debug("transaction="+conn);
				LOG.debug("3======================");
				//conn.rollback();
				conn.commit();
			}else{
				conn.rollback();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);						
	    }
		
		LOG.debug("3=====================");
		LOG.debug("flag:"+flag);
		LOG.debug("3=====================");
		
		
		return flag;
	} 
	@Override
	public ReviewVO do_selectOne(DTO dto) {
		ReviewVO vo =(ReviewVO) dto;
		ReviewVO outVO = null;
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		try{
			StringBuilder sb=new StringBuilder();
			sb.append(" SELECT			 \n");
			sb.append("     seq,         \n");
			sb.append("     title,       \n");
			sb.append("     read_cnt,    \n");
			sb.append("     contents,    \n");
			sb.append("     reg_id,      \n");
			sb.append("     TO_CHAR(reg_dt,'YYYY-MM-DD') reg_dt,   \n");
			sb.append("     no,           \n");
			sb.append("     passwd       \n");
			sb.append(" FROM             \n");
			sb.append("     board2      \n");			
			sb.append(" WHERE seq = ?    \n");
			conn = connectionMaker.getConnection();
			LOG.debug("1.============================");
			LOG.debug("1.query: \n"+sb.toString());
			LOG.debug("1.============================");
			
			pstmt = conn.prepareStatement(sb.toString());
			//query param
			pstmt.setString(1, vo.getSeq());
			LOG.debug("2.============================");
			LOG.debug("2.param seq="+vo.getSeq());
			LOG.debug("2.============================");			
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				outVO = new ReviewVO();
				outVO.setSeq(rs.getString("seq"));
				outVO.setTitle(rs.getString("title"));
				outVO.setRead_cnt(rs.getString("read_cnt"));
				outVO.setContents(rs.getString("contents"));
				outVO.setReg_id(rs.getString("reg_id"));
				outVO.setReg_dt(rs.getString("reg_dt"));
				outVO.setNo(rs.getString("no"));
				outVO.setPasswd(rs.getString("passwd"));
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
	@Override
	public List<?> do_retrieve(DTO dto) {
		SearchVO vo = (SearchVO) dto;
		
		List<ReviewVO> list=new ArrayList<>();
		Connection conn = null;//db 연결
		PreparedStatement pstmt = null;//query수행
		ResultSet rs = null;//결과처리 
		
		//검색 query
				StringBuilder sbWhere=new StringBuilder();
				//제목=10,내용=20,ID=30
				if(null != vo.getSearchDiv() ){
					
					if("10".equals(vo.getSearchDiv())){
						sbWhere.append("WHERE title like ?||'%' \n");
					}else if("20".equals(vo.getSearchDiv())){
						sbWhere.append("WHERE contents like ?||'%' \n");
					}else if("30".equals(vo.getSearchDiv())){
						sbWhere.append("WHERE reg_id like ?||'%' \n");
					}
				}
				
				//main query
				StringBuilder sb=new StringBuilder();
				sb.append(" SELECT T1.*,T2.*                                            				\n");
				sb.append(" FROM                                                      			 	    \n");
				sb.append(" (                                                        				    \n");
				sb.append("     SELECT b.rnum num,                                    				    \n");
				sb.append("            b.seq,                                         				    \n");
				sb.append("            b.title,                                       				    \n");
				sb.append("           (SELECT COUNT(*) FROM read_cnt2 BR WHERE BR.SEQ2=B.SEQ  ) read_cnt, \n"); 
				sb.append("            b.contents,                                   				    \n");
				sb.append("            b.reg_id,                                       				    \n");
				sb.append("            DECODE(TO_CHAR(b.reg_dt,'YYYY-MM-DD')           				    \n");
				sb.append("                  ,TO_CHAR(SYSDATE,'YYYY-MM-DD')            				    \n");
				sb.append("                  ,TO_CHAR(b.reg_dt,'HH24:mi')             				    \n");
				sb.append("                  ,TO_CHAR(b.reg_dt,'YYYY-MM-DD')) as reg_dt, 				\n"); 
				sb.append("            b.no,                                            			    \n");
				sb.append("            b.passwd                                         				\n");
				sb.append("     FROM(                                                 				    \n");
				sb.append("         SELECT ROWNUM rnum,A.*                             				    \n");
				sb.append("         FROM(                                              				    \n");
				sb.append("             SELECT a.*                                     				    \n");
				sb.append("             FROM board2 a                                    				\n");
				sb.append("             --SEARCH CONDITION                             				    \n");
				//-------------------------------------------------------------------------------
				if(null != vo.getSearchDiv() ){//검색구분
					if(null != vo.getSearchWord() && vo.getSearchWord().length()>0){//검색어가 있는냐
						sb.append(sbWhere.toString());
					}
				}		
				//-------------------------------------------------------------------------------
				sb.append("             ORDER BY a.reg_dt desc                          \n");
				sb.append("         )A                                                  \n");
				sb.append("         WHERE ROWNUM <=( ? * ( ?-1)+ ?)                                 \n");
				sb.append("     )B                                                      \n");
				sb.append("     WHERE B.rnum>= ( ? * ( ?-1)+1)                                      \n");
				sb.append(" )T1 CROSS JOIN                                              \n");
				sb.append(" (                                                           \n");
				sb.append("     SELECT COUNT(*) total_cnt                               \n");
				sb.append("     FROM board2 a                                            \n");
				sb.append("     --SEARCH CONDITION                                      \n");
				//-------------------------------------------------------------------------------
				if(null != vo.getSearchDiv() ){//검색구분
					if(null != vo.getSearchWord() && vo.getSearchWord().length()>0){//검색어가 있는냐
						sb.append(sbWhere.toString());
					}
				}		
				//-------------------------------------------------------------------------------		
				sb.append(" )T2                                                         \n");		
				LOG.debug("2 sql \n:"+sb.toString());
				try{
					conn = connectionMaker.getConnection();
					pstmt = conn.prepareStatement(sb.toString());
					//param
					if(null != vo.getSearchDiv() && !"".equals(vo.getSearchDiv()) ){
						//검색어
						//PAGE_SIZE
						//PAGE_NUM
						//PAGE_SIZE
						//PAGE_SIZE
						//PAGE_NUM
						//검색어
						pstmt.setString(1, vo.getSearchWord());
						pstmt.setInt(2, vo.getPageSize());
						pstmt.setInt(3, vo.getPageNum());
						pstmt.setInt(4, vo.getPageSize());
						pstmt.setInt(5, vo.getPageSize());
						pstmt.setInt(6, vo.getPageNum());	
						pstmt.setString(7, vo.getSearchWord());
					}else{//검색이 없는 경우
						//PAGE_SIZE
						//PAGE_NUM
						//PAGE_SIZE
						//PAGE_SIZE
						//PAGE_NUM
						pstmt.setInt(1, vo.getPageSize());
						pstmt.setInt(2, vo.getPageNum());
						pstmt.setInt(3, vo.getPageSize());
						pstmt.setInt(4, vo.getPageSize());
						pstmt.setInt(5, vo.getPageNum());				
					}
					LOG.debug("3 param \n:"+vo);
					rs = pstmt.executeQuery();
					while(rs.next()){
						ReviewVO outVO=new ReviewVO();
						
						outVO.setNum(rs.getInt("num"));
						outVO.setSeq(rs.getString("seq"));
						outVO.setTitle(rs.getString("title"));
						outVO.setRead_cnt(rs.getString("read_cnt"));
						outVO.setContents(rs.getString("contents"));
						outVO.setReg_id(rs.getString("reg_id"));
						outVO.setReg_dt(rs.getString("reg_dt"));
						outVO.setNo(rs.getString("no"));
						outVO.setPasswd(rs.getString("passwd"));
						outVO.setTotal(rs.getInt("total_cnt"));
						
						list.add(outVO);
					}
					
					
					
				}catch(SQLException e){
					LOG.debug("===============================");
					LOG.debug("SQLException="+e.toString());
					LOG.debug("===============================");
				}finally{
					JDBCReturnReso.close(rs);
					JDBCReturnReso.close(pstmt);
					JDBCReturnReso.close(conn);					
				}		
				return list;
			}
}