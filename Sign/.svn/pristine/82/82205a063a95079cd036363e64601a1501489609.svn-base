package cn.edu.ccnu.imd.analysis.service;
 

import java.util.List;
import java.util.Map;
//import java.util.Map;

import cn.edu.ccnu.imd.analysis.common.util.SplitPageUtil;
import cn.edu.ccnu.imd.analysis.vo.Log;
import cn.edu.ccnu.imd.analysis.vo.User;





public interface BasicService  {
	 
	public void update (Object o);
	   
	   
	   public Object get (Object id);
	  
	   public void del (Object o);
	   
	   public List<Object> login(User user);
	   
	   public List<Object> findAll(String strSQL);
	   
	   public List<Object>  findAllPage(String strSQL,Integer page, Integer maxResults);
	   
	   
	   public SplitPageUtil  getPage(String strSQL,Integer page, Integer maxResults);
	   
 	   public Map<String,Object> findAllSPPage(String strSQL, Integer page,
		Integer maxResults) ;

 	  public void addlog (Object o);
	   
 	  	public Object checkUser(String name);
	 
	 
}
 