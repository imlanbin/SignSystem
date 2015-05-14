package cn.edu.ccnu.imd.analysis.dao.impl;
 
import java.io.Serializable;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.ccnu.imd.analysis.common.util.LybaoMd5Encrypt;
import cn.edu.ccnu.imd.analysis.dao.BasicDao;
import cn.edu.ccnu.imd.analysis.dao.SeatDao;
import cn.edu.ccnu.imd.analysis.vo.Leaflet;
import cn.edu.ccnu.imd.analysis.vo.Seat;
import cn.edu.ccnu.imd.analysis.vo.User;

public class SeatDaoImpl extends HibernateDaoSupport implements SeatDao {
	
	private static final Log log = LogFactory.getLog(SeatDaoImpl.class);
	
	private String className;
	    
	public void setClassName(String className) {
		this.className = className;
	}

	protected void initDao() {
			//do nothing
	}
	
	public List<Object> findAll(String strSQL) {
		// TODO Auto-generated method stub
		log.debug("finding "+this.className+"   "+strSQL);
		try {
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			StringBuffer queryString = new StringBuffer();
			queryString.append("  select s.ID, s.deskNum, s.seatNum, s.consumerId, c.contactPerson as consumerName, s.year," +
					" s.state from seat s left join consumer c on s.consumerId = c.ID" );
			//对查询条件做处理
			if(strSQL.contains("id") && !strSQL.contains("s.id"))
				strSQL = strSQL.replace("id", "s.id");
			if(strSQL.contains("year") && !strSQL.contains("s.year"))
				strSQL = strSQL.replace("year", "s.year");
			if(strSQL.contains("deskNum") && !strSQL.contains("s.deskNum"))
				strSQL = strSQL.replace("deskNum", "s.deskNum");
			if(strSQL.contains("seatNum") && !strSQL.contains("s.seatNum"))
				strSQL = strSQL.replace("seatNum", "s.seatNum");
			if(strSQL.contains("state") && !strSQL.contains("s.state"))
				strSQL = strSQL.replace("state", "s.state");
			//处理后，拼接到查询语句中
			if(strSQL != null && !strSQL.isEmpty())
				queryString.append(" where " + strSQL);
			SQLQuery q = session.createSQLQuery(queryString.toString());
			q.addEntity(Seat.class);
			return q.list();
		} catch (Exception re) {
			log.error("finding "+this.className+" by:", re);
			re.printStackTrace();
		}
		return null;
	}

	public List<Object> findAllPage(String strSQL, Integer firstResult,
			Integer maxResults) {
		log.debug("finding "+this.className+"   "+strSQL);
		try {
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			StringBuffer queryString = new StringBuffer();
			queryString.append("  select s.ID, s.deskNum, s.seatNum, s.consumerId, c.contactPerson as consumerName, s.year," +
					" s.state from seat s left join consumer c on s.consumerId = c.ID" );
			//对查询条件做处理
			if(strSQL.contains("id") && !strSQL.contains("s.id"))
				strSQL = strSQL.replace("id", "s.id");
			if(strSQL.contains("year") && !strSQL.contains("s.year"))
				strSQL = strSQL.replace("year", "s.year");
			if(strSQL.contains("deskNum") && !strSQL.contains("s.deskNum"))
				strSQL = strSQL.replace("deskNum", "s.deskNum");
			if(strSQL.contains("seatNum") && !strSQL.contains("s.seatNum"))
				strSQL = strSQL.replace("seatNum", "s.seatNum");
			if(strSQL.contains("state") && !strSQL.contains("s.state"))
				strSQL = strSQL.replace("state", "s.state");
			//处理后，拼接到查询语句中
			if(strSQL != null && !strSQL.isEmpty())
				queryString.append(" where " + strSQL);
			SQLQuery q = session.createSQLQuery(queryString.toString());
			q.setFirstResult(firstResult);
			q.setMaxResults(maxResults);
			q.addEntity(Seat.class);
			return q.list();
		} catch (Exception re) {
			log.error("finding "+this.className+"    by:", re);
			re.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(Object object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object save(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count(String strSQL) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long countYear(String strSQL) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object findById(Object id) {
		// TODO Auto-generated method stub
		Object instance = null;
		try {
			String strSQL = " id = '" + (Serializable)id + "'";
			instance = (Object)this.findAll(strSQL).get(0);
		 } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
		return instance;
	}

	@Override
	public void delete(Object object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Object> login(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changePass(Object id, Object pass) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Object> getSeatByYear(String year) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getConsumersByYear(String year, short deskNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public short getMaxDeskNumByYear(String year) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Object> getSeatReportByYear(String year) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getConsumerReportByYear(String year) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getNumByState(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getFreeDesk() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Seat getSeatByDeskNum(Short deskNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getDeskMaxSeat(Short deskNum) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Seat getSeatByDeskNumAndSeatNum(Short deskNum, Short seatNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void evict(Object object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getDesknumAndYear() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> findSeatInformation(String strSQL, Integer firstResult,
			Integer maxResults) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> findSumDesk(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> findSumSeat(String s) {
		// TODO Auto-generated method stub
		return null;
	}

}
 