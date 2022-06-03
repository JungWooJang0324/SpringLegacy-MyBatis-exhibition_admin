package kr.co.exhibitionThreeAdmin.exhibition.dao;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import kr.co.exhibitionThreeAdmin.exHall.domain.AdminExHallDomain;
import kr.co.exhibitionThreeAdmin.exhibition.domain.ExhibitionDomain;
import kr.co.exhibitionThreeAdmin.exhibition.vo.ExhibitionVO;
import kr.co.exhibitionThreeAdmin.mybatis.MyBatisFramework;
import kr.co.exhibitionThreeAdmin.search.vo.SearchVO;
@Component
public class AdminExhibitionDAO {
	
	public List<ExhibitionDomain> selectExhibition(SearchVO sVO)throws PersistenceException{
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		List<ExhibitionDomain> list = ss.selectList("kr.co.exhibitionThreeAdmin.exhibition.selectEx",sVO);
		if(ss != null) {ss.close();}//end if
		return list;
	}//selectExhibition
	
	public int totalCount(SearchVO sVO)throws PersistenceException{
		int totalCnt = 0;
		//MyBatis Handler 얻기
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		//쿼리문 수행
		totalCnt = ss.selectOne("kr.co.exhibitionThreeAdmin.exhibition.totalCnt",sVO);
		
		//MyBatis Handler 끊기
		if(ss != null) {ss.close();}//end if
		
		return totalCnt;
	}//totalCount
	
	/**
	 * 전시 상세 조회
	 * @param sVO
	 * @return
	 * @throws PersistenceException
	 */
	public ExhibitionDomain selectExDetail(SearchVO sVO)throws PersistenceException{
		
		ExhibitionDomain ed = null;
		
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		ed=ss.selectOne("kr.co.exhibitionThreeAdmin.exhibition.selectEx",sVO);
		if(ss != null) {ss.close();}//end if
		
		return ed;
	}//selectExhibitionDetail
	
	/**
	 * 전시장 목록 조회
	 * @return
	 * @throws PersistenceException
	 */
	public List<AdminExHallDomain> selectExHall()throws PersistenceException{
		
		List<AdminExHallDomain> list = null;
		
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		list = ss.selectList("kr.co.exhibitionThreeAdmin.exhibition.selectExHall");
		if(ss!= null) {ss.close();}//end if
		
		return list;
	}//selectExHall
	
	public int updateExhibition(ExhibitionVO eVO)throws PersistenceException{
		int cnt = 0;
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		cnt = ss.update("updateEx",eVO);
		if(cnt>0) {
			ss.commit();
		}//end if
		if(ss!=null) {ss.close();}//end if
		return cnt;
	}//updateExhibition
	
	public int insertExhibition(ExhibitionVO eVO)throws PersistenceException{
		int cnt=0;
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		cnt = ss.insert("kr.co.exhibitionThreeAdmin.exhibition.insertEx",eVO);
		
		if(cnt > 0) {
			ss.commit();
		}//end if
		
		if(ss != null) {ss.close();}//end if
		return cnt;
	}//inserExhibition
	
}//class
