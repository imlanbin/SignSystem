package cn.edu.ccnu.imd.analysis.dao;
 
import java.util.List;

import cn.edu.ccnu.imd.analysis.vo.Leaflet;
import cn.edu.ccnu.imd.analysis.vo.Seat;
import cn.edu.ccnu.imd.analysis.vo.User;






public interface BasicDao {

	public void setClassName(String className);
	public List<Object> findAll(String strSQL);
	public List<Object> findAllPage(String strSQL,Integer firstResult, Integer maxResults);
	public void update(Object object);
	public Object save(Object object);
	public Long count(String strSQL);
	public Long countYear(String strSQL);
	public Object findById(Object id);
	public void delete(Object object);
	public List<Object> login(User user);
	public void changePass(Object id, Object pass);
	public List<Object> getSeatByYear(String year);
	public List<Object> getConsumersByYear(String year, short deskNum);
	public short getMaxDeskNumByYear(String year);
	public List<Object> getSeatReportByYear(String year);
	public List<Object> getConsumerReportByYear(String year);
	public Long getNumByState(String string);
	public List<Object> getFreeDesk();
	public Seat getSeatByDeskNum(Short deskNum);
	public int getDeskMaxSeat(Short deskNum);
	public Seat getSeatByDeskNumAndSeatNum(Short deskNum, Short seatNum);
	public void evict(Object object);
	public Object findByName(String name);
	
	public List<Object> getDesknumAndYear();
	public List<Object> findSeatInformation(String strSQL,Integer firstResult, Integer maxResults);
	public List<Object> findSumDesk(String s);
	public List<Object> findSumSeat(String s);
}
