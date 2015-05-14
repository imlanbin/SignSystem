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
import cn.edu.ccnu.imd.analysis.vo.Leaflet;
import cn.edu.ccnu.imd.analysis.vo.Seat;
import cn.edu.ccnu.imd.analysis.vo.User;

public class BasicDaoImpl extends HibernateDaoSupport implements BasicDao {
	
	 private static final Log log = LogFactory.getLog(BasicDaoImpl.class);
	 
 
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
			queryString.append("  from " );
			queryString.append(this.className);
			queryString.append(" where ");
			queryString.append(strSQL );
			Query q = session.createQuery(queryString.toString());

			return q.list();
			 
		} catch (Exception re) {
			log.error("finding "+this.className+"    by:", re);
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
			queryString.append("  from " );
			queryString.append(this.className);
			queryString.append(" where ");
			queryString.append(strSQL );
			Query q = session.createQuery(queryString.toString());
			q.setFirstResult(firstResult);
			q.setMaxResults(maxResults);
			
		//	System.out.println(firstResult+":"+maxResults);
			return q.list();
			 
		} catch (Exception re) {
			log.error("finding "+this.className+"    by:", re);
			re.printStackTrace();
		}
		return null;
	 
	}
	
 
	public void update(Object instance) {
		log.debug("update "+this.className+" instance");
		try {	
			getHibernateTemplate().update(instance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}

	}

 
	public Object save(Object transientInstance) {
		log.debug("saving "+this.className+" instance");
        try {      
        	getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
            return transientInstance;
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
	}

 
	
	public Long count(String strSQL) {
		log.debug("finding "+this.className+" count "+strSQL);
		try {
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		 
			StringBuffer queryString = new StringBuffer();
			queryString.append("select count(*) from " );
			queryString.append(this.className);
			queryString.append(" where ");
			queryString.append(strSQL );

			Query q = session.createQuery(queryString.toString());
			List lc=q.list();
    		Iterator iter=lc.iterator();
			Long count = 0l;
			while (iter.hasNext()) {
				count = (Long) iter.next();
			}
			return count;
			 
		} catch (Exception re) {
			log.error("finding "+this.className+"  count by:", re);
			re.printStackTrace();
		}
		
		return null;
	}
	
	public void delete(Object persistentInstance) {
        log.debug("deleting "+this.className+"instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
	
 
    public Object findById(Object id) {
        log.debug("getting "+this.className+" instance with id: " + id);
        Object instance = null;
        try {
        	instance =  (Object)getHibernateTemplate().get(this.className, (Serializable)id);	
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
        return instance;
    }
    
   
    public void changePass(Object id,Object pass) {
    	try{
    			Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
				StringBuffer sb = new StringBuffer(" update User set id=:id ");
				sb.append(",password=:password ");
				sb.append(" where id=:id ");
				Query q = session.createQuery(sb.toString());
				q.setParameter("id", id);
				q.setParameter("password",LybaoMd5Encrypt.MyMD5(pass.toString()));	
				q.executeUpdate();
		    }catch(Exception e){
				e.printStackTrace();
			}
    }
    
    public List<Object> login(User user){
		try{
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			String hql = " From User where 1=1 and userName=:name and password=:pass ";
			Query q = session.createQuery(hql);
			q.setParameter("name", user.getUserName());
			q.setParameter("pass", LybaoMd5Encrypt.MyMD5(user.getPassword()));
			System.out.print(LybaoMd5Encrypt.MyMD5(user.getPassword()));
			return  q.list();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
    
    public List<Object> getSeatByYear(String year){
		try{
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			StringBuffer sql = new StringBuffer();
			sql.append("select distinct year from seat where year = '");
			sql.append(year);
			sql.append("'");
			SQLQuery q = session.createSQLQuery(sql.toString());
			return q.list();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
    
    public short getMaxDeskNumByYear(String year){
    	Short maxValue;
    	short maxDeskNum = 0;
		try{
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			Query q = session.createQuery("select max(m.deskNum) from Seat m where year=:year ");
			q.setParameter("year", year);
			Object oj = q.uniqueResult();
			if(oj != null){
				maxValue = (Short)oj;
				maxDeskNum = (short)maxValue.intValue();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return maxDeskNum;
	}
    
    public List<Object> getConsumersByYear(String year, short deskNum){
    	try{
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			String hql = " From Consumer where 1=1 and year=:year and deskNum=:deskNum";
			Query q = session.createQuery(hql);
			q.setParameter("year", year);
			q.setParameter("deskNum", deskNum);
			return q.list();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
    }
    
    @Override
	public List<Object> getDesknumAndYear() {
    	try{
    		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			Query q = session.createQuery("select distinct year,deskNum From Seat where 1=1");
			return q.list();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return null;
	}


	public List<Object> getSeatReportByYear(String year){
    	try{
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			String hql = " From SeatReport where 1=1 and year=:year order by deskNum";
			Query q = session.createQuery(hql);
			q.setParameter("year", year);
			return q.list();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
    }
    
    public List<Object> getConsumerReportByYear(String year){
    	try{
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			String hql = " From Consumer where 1=1 and year=:year order by deskNum";
			Query q = session.createQuery(hql);
			q.setParameter("year", year);
			return q.list();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
    }
    
    public Long getNumByState(String strSQL) {
		log.debug("finding "+this.className+" count "+strSQL);
		try {
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		 
			StringBuffer queryString = new StringBuffer();
			queryString.append("select count(*) from " );
			queryString.append(this.className);
			queryString.append(" where state =");
			queryString.append(strSQL );

			Query q = session.createQuery(queryString.toString());
			List lc=q.list();
    		Iterator iter=lc.iterator();
    		Long count = 0l;
			while (iter.hasNext()) {
				count =(Long) iter.next();
			}
			return count;
			 
		} catch (Exception re) {
			log.error("finding "+this.className+"  count by:", re);
			re.printStackTrace();
		}
		return null;
    }
    
    public List<Object> getFreeDesk(){
    	try {
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		 
			StringBuffer queryString = new StringBuffer();
			queryString.append("select distinct deskNum from Seat where state ='1' and year=:year order by deskNum asc" );
			Query q = session.createQuery(queryString.toString());
			q.setParameter("year", String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
			List<Object> deskList = q.list();
  
			return deskList;
			 
		} catch (Exception re) {
			log.error("finding "+this.className+"  count by:", re);
			re.printStackTrace();
		}
		return null;
    }
    
    public Seat getSeatByDeskNum(Short deskNum){
    	Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		 
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT s.ID, s.deskNum, s.seatNum, s.consumerId, c.contactPerson AS consumerName, " +
				"s.year, s.state from seat s LEFT JOIN consumer c ON s.consumerId = c.ID " +
				"where s.state ='1' and s.year=:year and s.deskNum=:deskNum order by s.seatNum asc" );
		SQLQuery q = session.createSQLQuery(queryString.toString());
		q.setParameter("year", String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
		q.setParameter("deskNum", deskNum);
		q.addEntity(Seat.class);
		List<Object> deskList = q.list();
		if(deskList.size() > 0){
			Seat seat = (Seat)deskList.get(0);
			return seat;
		}
		return null;
    }
    
    public Seat getSeatByDeskNumAndSeatNum(Short deskNum, Short seatNum){
    	Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		 
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT s.ID, s.deskNum, s.seatNum, s.consumerId, c.contactPerson AS consumerName, " +
				"s.year, s.state from seat s LEFT JOIN consumer c ON s.consumerId = c.ID " +
				"where s.year=:year and s.deskNum=:deskNum and s.seatNum = :seatNum" +
				" order by s.seatNum asc" );
		SQLQuery q = session.createSQLQuery(queryString.toString());
		q.setParameter("year", String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
		q.setParameter("deskNum", deskNum);
		q.setParameter("seatNum", seatNum);
		q.addEntity(Seat.class);
		List<Object> deskList = q.list();
		if(deskList.size() > 0){
			Seat seat = (Seat)deskList.get(0);
			return seat;
		}
		return null;
    }



	@Override
	public void evict(Object object) {
		// TODO Auto-generated method stub
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.evict(object);
	}



	@Override
	public Object findByName(String name) {
		// TODO Auto-generated method stub
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql = " From User where 1=1 and userName=:name";
		Query q = session.createQuery(hql);
		q.setParameter("name", name);
		return  q.list().size() == 0 ? null : q.list().get(0);
	}



	@Override
	public int getDeskMaxSeat(Short deskNum) {
		// TODO Auto-generated method stub
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT s.ID, s.deskNum, s.seatNum, s.consumerId, c.contactPerson AS consumerName, " +
				"s.year, s.state from seat s LEFT JOIN consumer c ON s.consumerId = c.ID " +
				"where s.state ='1' and s.year=:year and s.deskNum=:deskNum order by s.seatNum desc" );
		SQLQuery q = session.createSQLQuery(queryString.toString());
		q.setParameter("year", String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
		q.setParameter("deskNum", deskNum);
		q.addEntity(Seat.class);
		List<Object> deskList = q.list();
		if(deskList.size() > 0){
			Seat seat = (Seat)deskList.get(0);
			return seat.getSeatNum();
		}
		return 0;
	}



	@Override
	public Long countYear(String strSQL) {
		log.debug("finding "+this.className+" count "+strSQL);
		try {
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		 
			StringBuffer queryString = new StringBuffer();
			queryString.append("select count(DISTINCT year) from " );
			queryString.append(this.className);
			queryString.append(" where ");
			queryString.append(strSQL );

			Query q = session.createQuery(queryString.toString());
			List lc=q.list();
    		Iterator iter=lc.iterator();
			Long count = 0l;
			while (iter.hasNext()) {
				count = (Long) iter.next();
			}
			return count;
			 
		} catch (Exception re) {
			log.error("finding "+this.className+"  count by:", re);
			re.printStackTrace();
		}
		
		return null;
	}



	@Override
	public List<Object> findSeatInformation(String strSQL,Integer firstResult, Integer maxResults) {
		try {
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		 
			StringBuffer queryString = new StringBuffer();
//			queryString.append("select new cn.edu.ccnu.imd.analysis.vo.YearSeat(year,COUNT(DISTINCT deskNum),COUNT(*)) from " );
//			queryString.append(this.className);
//			queryString.append(" GROUP BY year ORDER BY year DESC" );
			queryString.append("select DISTINCT year from " );
			queryString.append(this.className);
			queryString.append(" GROUP BY year ORDER BY year DESC" );
			Query q = session.createQuery(queryString.toString());
			
		//	System.out.println(firstResult+":"+maxResults);
			return q.list();
			 
		} catch (Exception re) {
			log.error("finding "+this.className+"    by:", re);
			re.printStackTrace();
		}
		return null;
	}



	@Override
	public List<Object> findSumDesk(String s) {
		log.debug("finding "+this.className+"   "+s);
		try {
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		 
			StringBuffer queryString = new StringBuffer();
//			queryString.append("select DISTINCT year,COUNT(DISTINCT deskNum),COUNT(*) from " );
//			queryString.append(this.className);
//			queryString.append(" GROUP BY year ORDER BY year DESC" );
			queryString.append("select COUNT(DISTINCT deskNum) from " );
			queryString.append(this.className);
			queryString.append(" where year = '" );
			queryString.append(s);
			queryString.append("'");
			Query q = session.createQuery(queryString.toString());
			
		//	System.out.println(firstResult+":"+maxResults);
			return q.list();
			 
		} catch (Exception re) {
			log.error("finding "+this.className+"    by:", re);
			re.printStackTrace();
		}
		return null;
	}



	@Override
	public List<Object> findSumSeat(String s) {
		log.debug("finding "+this.className+"   "+s);
		try {
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		 
			StringBuffer queryString = new StringBuffer();
//			queryString.append("select DISTINCT year,COUNT(DISTINCT deskNum),COUNT(*) from " );
//			queryString.append(this.className);
//			queryString.append(" GROUP BY year ORDER BY year DESC" );
			queryString.append("select COUNT(*) from " );
			queryString.append(this.className);
			queryString.append(" where year = '" );
			queryString.append(s);
			queryString.append("'");
			Query q = session.createQuery(queryString.toString());
			
		//	System.out.println(firstResult+":"+maxResults);
			return q.list();
			 
		} catch (Exception re) {
			log.error("finding "+this.className+"    by:", re);
			re.printStackTrace();
		}
		return null;
	}
    
}
 