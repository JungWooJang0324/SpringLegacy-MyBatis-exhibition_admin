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
}//class
