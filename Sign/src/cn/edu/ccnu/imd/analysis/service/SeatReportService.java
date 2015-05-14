package cn.edu.ccnu.imd.analysis.service;

import java.util.List;
import java.util.Map;

import cn.edu.ccnu.imd.analysis.vo.Pagination;
import cn.edu.ccnu.imd.analysis.vo.Seat;

 

public interface SeatReportService  {
	   public Map<String,Object> get (String year);
	   public List<Object> findAll(Pagination pagination);
	   public Map<String,Object>  findAllPage(Pagination pagination);
	   public Map<String,Object> findAllSPPage(String strSQL, Integer page,
				Integer maxResults) ;
	   public List<Object> getSeatReportByYear(String year);
	   
	   public void refreashSeatReport();
}
