package cn.edu.ccnu.imd.analysis.service;

import java.util.List;
import java.util.Map;

import cn.edu.ccnu.imd.analysis.vo.Pagination;
import cn.edu.ccnu.imd.analysis.vo.Seat;

 

public interface SeatService  {
	   public void del (Seat[] o);
	   public Object get (String id);
	   public Object add (Seat o);
	   
	   public void update (Seat o);
	   public String seatInit(String year, String desk, String seat);
	   public List<Object> findAll(Pagination pagination);
	   public Map<String,Object>  findAllPage(Pagination pagination);
	   public Map<String,Object> findAllSPPage(String strSQL, Integer page,Integer maxResults) ;
	   public Map<String, Object> findDistinctYear(Pagination pagination);
	   public void setConsumer(String seatId, String id);
}
