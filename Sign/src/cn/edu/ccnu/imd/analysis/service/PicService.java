package cn.edu.ccnu.imd.analysis.service;

import java.util.List;
import java.util.Map;

import cn.edu.ccnu.imd.analysis.vo.Pic;
import cn.edu.ccnu.imd.analysis.vo.Pagination;

 

public interface PicService  {
	   public void del (Pic[] o);
	   public Object get (String id);
	   public Object add (Pic o);
	   
	   public void update (Pic o);
	   
	   public List<Object> findAll(Pagination pagination);
	   
	   public Map<String,Object>  findAllPage(Pagination pagination);
	   
	   public Map<String,Object> findAllSPPage(String strSQL, Integer page,
				Integer maxResults) ;
}
