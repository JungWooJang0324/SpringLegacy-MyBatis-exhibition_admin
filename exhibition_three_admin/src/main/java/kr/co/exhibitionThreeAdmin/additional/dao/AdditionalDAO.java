package kr.co.exhibitionThreeAdmin.additional.dao;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import kr.co.exhibitionThreeAdmin.additional.vo.LoginVO;
import kr.co.exhibitionThreeAdmin.mybatis.MyBatisFramework;

@Component
public class AdditionalDAO {
		
	//password 체크
	public int passwordChk(LoginVO lvo) throws PersistenceException{
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		int cnt = ss.selectOne("kr.co.exhibitionThreeAdmin.additional.passChk", lvo);
		if(ss != null) {ss.close();}//end if
		return cnt;
	}
	
	//pwUpdate
	public int pwUpdate(LoginVO lvo) throws PersistenceException{
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		int cnt = ss.update("kr.co.exhibitionThreeAdmin.additional.updatePass", lvo);
		if(cnt==1) ss.commit();
		
		if(ss != null) {ss.close();}//end if
		return cnt;
	}
	
	
	//--------------------------------------------------------------------------
	//	index화면 멤버 갯수
	public int countAllMember() throws PersistenceException{
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		int cnt = ss.selectOne("kr.co.exhibitionThreeAdmin.additional.countMember");
		if(ss != null) {ss.close();}//end if
		return cnt;
	}
	public int cntTodayMember() throws PersistenceException{
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		int cnt = ss.selectOne("kr.co.exhibitionThreeAdmin.additional.cntTodayMember");
		if(ss != null) {ss.close();}//end if
		return cnt;
	}
	public int cntShownRez() throws PersistenceException{
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		int cnt = ss.selectOne("kr.co.exhibitionThreeAdmin.additional.cntShownRez");
		if(ss != null) {ss.close();}//end if
		return cnt;
	}
	public int cntAllRez() throws PersistenceException{
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		int cnt = ss.selectOne("kr.co.exhibitionThreeAdmin.additional.cntAllRez");
		if(ss != null) {ss.close();}//end if
		return cnt;
	}
	public int cntTodayRez() throws PersistenceException{
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		int cnt = ss.selectOne("kr.co.exhibitionThreeAdmin.additional.cntTodayRez");
		if(ss != null) {ss.close();}//end if
		return cnt;
	}
	public int cntTodaysBoard() throws PersistenceException{
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		int cnt = ss.selectOne("kr.co.exhibitionThreeAdmin.additional.cntTodayBoard");
		if(ss != null) {ss.close();}//end if
		return cnt;
	}
	public int cntAllExhibition() throws PersistenceException{
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		int cnt = ss.selectOne("kr.co.exhibitionThreeAdmin.additional.cntAllExhibition");
		if(ss != null) {ss.close();}//end if
		return cnt;
	}
	public int endedExhibition() throws PersistenceException{
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		int cnt = ss.selectOne("kr.co.exhibitionThreeAdmin.additional.endedExhibition");
		if(ss != null) {ss.close();}//end if
		return cnt;
	}
	public int endsTomorrow() throws PersistenceException{
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		int cnt = ss.selectOne("kr.co.exhibitionThreeAdmin.additional.endsTomorrow");
		if(ss != null) {ss.close();}//end if
		return cnt;
	}
	
	
	
}
