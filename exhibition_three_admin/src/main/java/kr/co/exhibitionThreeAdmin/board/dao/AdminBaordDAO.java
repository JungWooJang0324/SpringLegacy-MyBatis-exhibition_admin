package kr.co.exhibitionThreeAdmin.board.dao;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import kr.co.exhibitionThreeAdmin.board.domain.AdminBoardDomain;
import kr.co.exhibitionThreeAdmin.board.vo.AdminBoardVO;
import kr.co.exhibitionThreeAdmin.mybatis.MyBatisFramework;
import kr.co.exhibitionThreeAdmin.search.vo.BHSearchVO;

@Component
public class AdminBaordDAO {
	
	//전체 글 수
	public int getTotalRows(BHSearchVO sVO) {
		int cntRows =0;
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		cntRows = ss.selectOne("kr.co.exhbitionThreeAdmin.board.totalRows", sVO); 	
		if(ss!=null) {ss.close();}
		return cntRows;
	}
	
	//게시글 조회
	public List<AdminBoardDomain> selectBoard(BHSearchVO sVO) throws PersistenceException{
		List<AdminBoardDomain> list = null;
			
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		list = ss.selectList("kr.co.exhbitionThreeAdmin.board.selectBoard", sVO); 	
		if(ss!=null) {ss.close();}
		
		return list;
	}
	
	/**
	 * 게시글 수정
	 * @param abVO
	 * @return
	 */
	public int updateBoard(AdminBoardVO abVO) throws PersistenceException{
		int cnt =0;
		
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		cnt = ss.update("kr.co.exhbitionThreeAdmin.board.updateBoard", abVO);
		if(cnt>0) {
			ss.commit();
		}
		if(ss!=null) {ss.close();}
		return cnt;
	}
	
	/**
	 * 게시글 삭제
	 * @param bdId
	 * @return
	 */
	public int deleteBoard(int bdId) throws PersistenceException{
		int cnt =0;
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		cnt = ss.update("kr.co.exhbitionThreeAdmin.board.deleteBoard", bdId);
		if(cnt>0) {
			ss.commit();
		}
		if(ss!=null) {ss.close();}
		return cnt;
	}
	
	/**
	 * 게시글 추가
	 * @param abVO
	 * @return
	 */
	public int insertBoard(AdminBoardVO abVO) throws PersistenceException{
		int cnt =0;
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		cnt = ss.insert("kr.co.exhbitionThreeAdmin.board.insertBoard", abVO);
		if(cnt>0) {
			ss.commit();
		}
		if(ss!=null) {ss.close();}
		return cnt;
	}
	
	//전시장 상세
	public AdminBoardDomain selectBoardDetail(int bdId) throws PersistenceException{
		AdminBoardDomain abDomain = null;
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		abDomain = ss.selectOne("kr.co.exhbitionThreeAdmin.board.boardDetail", bdId); 	
		if(ss!=null) {ss.close();}
		return abDomain;
	}
	
	//카테고리 전체
	public List<AdminBoardDomain> selectCategory() throws PersistenceException{
		List<AdminBoardDomain> list = null;
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		list = ss.selectList("kr.co.exhbitionThreeAdmin.board.selectCategory"  );
		if(ss!=null) {ss.close();}
		return list;
	}
	
}
