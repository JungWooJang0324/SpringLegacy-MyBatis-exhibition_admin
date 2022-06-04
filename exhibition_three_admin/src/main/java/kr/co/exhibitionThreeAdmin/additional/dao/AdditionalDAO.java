package kr.co.exhibitionThreeAdmin.additional.dao;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import kr.co.exhibitionThreeAdmin.additional.vo.LoginVO;
import kr.co.exhibitionThreeAdmin.mybatis.MyBatisFramework;

@Component
public class AdditionalDAO {
		
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
	
	
	
}
