package cn.edu.ccnu.imd.analysis.service;

import java.util.List;
import java.util.Map;

import cn.edu.ccnu.imd.analysis.vo.Leaflet;
import cn.edu.ccnu.imd.analysis.vo.Pagination;

 

public interface LeafletService  {
	   public void del (Leaflet[] o);
	   public Object get (String id);
	   public Object add (Leaflet o);
	   
	   public void update (Leaflet o);
	   
	   public List<Object> findAll(Pagination pagination);
	   public Map<String,Object>  findAllPage(Pagination pagination);
	   public Map<String,Object> findAllSPPage(String strSQL, Integer page,
				Integer maxResults) ;
}
