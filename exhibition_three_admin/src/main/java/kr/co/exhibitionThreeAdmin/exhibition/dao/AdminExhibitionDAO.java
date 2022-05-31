package kr.co.exhibitionThreeAdmin.exhibition.dao;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import kr.co.exhibitionThreeAdmin.exhibition.domain.ExhibitionDomain;
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
	
	public ExhibitionDomain selectExDetail(SearchVO sVO)throws PersistenceException{
		ExhibitionDomain ed = null;
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		ed=ss.selectOne("kr.co.exhibitionThreeAdmin.exhibition.selectEx",sVO);
		
		if(ss != null) {ss.close();}//end if
		
		return ed;
	}//selectExhibitionDetail
	
}//class
