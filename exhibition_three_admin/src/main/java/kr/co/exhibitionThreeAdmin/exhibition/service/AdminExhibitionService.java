package kr.co.exhibitionThreeAdmin.exhibition.service;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.exhibitionThreeAdmin.exhibition.dao.AdminExhibitionDAO;
import kr.co.exhibitionThreeAdmin.exhibition.domain.ExhibitionDomain;
import kr.co.exhibitionThreeAdmin.search.vo.SearchVO;
@Component
public class AdminExhibitionService {
	@Autowired(required = false)
	AdminExhibitionDAO aDAO;
	
	public List<ExhibitionDomain> searchExhibition(SearchVO sVO){
		List<ExhibitionDomain> list = null;
		try {
			list = aDAO.selectExhibition(sVO);
		}catch(PersistenceException pe) {
			pe.printStackTrace();
		}//end catch
		return list;
	}//searchExhibition
}//class
