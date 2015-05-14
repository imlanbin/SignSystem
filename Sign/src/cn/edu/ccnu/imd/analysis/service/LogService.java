package cn.edu.ccnu.imd.analysis.service;

import java.util.List;
import java.util.Map;

import cn.edu.ccnu.imd.analysis.vo.Log;
import cn.edu.ccnu.imd.analysis.vo.Pagination;

 

public interface LogService  {
	   public void del (Log[] o);
	   public Object get (String id);
	   
	   public List<Object> findAll(Pagination pagination);
	   
	   public Map<String,Object>  findAllPage(Pagination pagination);
}
