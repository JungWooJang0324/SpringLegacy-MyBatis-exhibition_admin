package kr.co.exhibitionThreeAdmin.member.dao;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import kr.co.exhibitionThreeAdmin.exHall.domain.AdminExHallDomain;
import kr.co.exhibitionThreeAdmin.member.domain.MemberDomain;
import kr.co.exhibitionThreeAdmin.member.vo.MemberVO;
import kr.co.exhibitionThreeAdmin.mybatis.MyBatisFramework;
import kr.co.exhibitionThreeAdmin.search.vo.SearchVO;

@Component
public class AdminMemberDAO {
	
	
	public int selectTotalCnt(SearchVO sVO)throws PersistenceException{
		int totalCnt = 0;
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		totalCnt = ss.selectOne("kr.co.exhibitionThreeAdmin.member.totalCnt",sVO);
		if(ss != null) {ss.close();}//end if
		return totalCnt;
	}//selectTotalCount
	
	public List<MemberDomain> selectMember(SearchVO sVO) throws PersistenceException {
		List<MemberDomain> list = null;
		//핸들러 얻기
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		//쿼리문 수행
	
		list = ss.selectList("kr.co.exhibitionThreeAdmin.member.selectMember",sVO);
		if(ss != null) {ss.close();}//end if
		return list;
	}//selectMember
	
	public MemberDomain selectDetail(SearchVO sVO) throws PersistenceException {
		MemberDomain md = null;
		//핸들러 얻기
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		//쿼리문 수행
		
		md = ss.selectOne("kr.co.exhibitionThreeAdmin.member.selectMember",sVO);
		if(ss != null) {ss.close();}//end if
		return md;
	}//selectMember
	
	public int updateMember(MemberVO mVO)throws PersistenceException{
		int cnt = 0;
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		cnt = ss.update("updateMember", mVO);
		if(cnt>0) {ss.commit();}//end if
		if(ss != null) {ss.close();}//end if
		
		return cnt;
	}//updateMember
	
}//class
