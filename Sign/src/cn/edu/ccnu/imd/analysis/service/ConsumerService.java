package cn.edu.ccnu.imd.analysis.service;

import java.util.List;
import java.util.Map;

import cn.edu.ccnu.imd.analysis.vo.Consumer;
import cn.edu.ccnu.imd.analysis.vo.Pagination;
import cn.edu.ccnu.imd.analysis.vo.Seat;

 

public interface ConsumerService  {
	   public void del (Consumer[] o);
	   public Object get (String id);
	   public Object add (Consumer o);
	   
	   public void update (Consumer o, Short deskNum ,Short seatNum);
	   
	   public List<Object> findAll(Pagination pagination);
	   public Map<String,Object>  findAllPage(Pagination pagination);
	   public Map<String,Object> findAllSPPage(String strSQL, Integer page,
				Integer maxResults) ;
	   public Map<String, Object> changeStateP1 (Consumer[] o);
	   
	   public Map<String, Object> changeStateP2 (Consumer[] o);
	   
       public Map<String, Object> changeState1 (String id);
	   
	   public Map<String, Object> changeState2 (String id);
	   
	   public Map<String, Object> changeState3 (String id);
	
	   public List<Object> getConsumerReportByYear(String year);
	   
	   public Map<String, Object> getNumByState();
	   
	   public Map<String, Object> getFreeDesk();
	   
	   public Seat getSeatByDeskNum(Short deskNum);
	   
	   public int getDeskMaxSeat(Short deskNum);
}
