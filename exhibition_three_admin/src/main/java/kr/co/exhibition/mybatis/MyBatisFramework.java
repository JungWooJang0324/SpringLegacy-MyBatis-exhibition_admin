package kr.co.exhibition.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * MyBatis Framework�� �����ϰ� MyBatis Handler�� ��ȯ
 * @author user
 */
public class MyBatisFramework {
	private static MyBatisFramework mbf;
	private static SqlSessionFactory ssf;
	
	private MyBatisFramework() {
		org.apache.ibatis.logging.LogFactory.useLog4JLogging();
	}//MyBatisFramework
	
	public static MyBatisFramework getInstance() {
		if(mbf==null) {
			mbf= new MyBatisFramework();
		}//end if
		return mbf;
	}//getInstance
	
	private static SqlSessionFactory getSqlSessionFactory() throws IOException {
				if(ssf== null) {
					Reader reader = null; 
					try {
						//1.�������� ����
						reader = Resources.getResourceAsReader("kr/co/sist/dao/mybatis/SqlMapConfig.xml");
						//2. MyBatis Framework ����
						ssf = new SqlSessionFactoryBuilder().build(reader);
					}finally {
						if(reader != null) {reader.close();}//end if
					}//end finally
				}//end if
		
		return ssf;
	}//getSqlSessionFactory
	
	public SqlSession getMyBatisHandler() {
		SqlSession ss = null;
		try {
			 ss = getSqlSessionFactory().openSession();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		return ss;
	}//getMyBatisHandler
	
}//class
