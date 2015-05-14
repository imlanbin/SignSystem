package cn.edu.ccnu.imd.analysis.service;

import java.util.List;
import java.util.Map;

import cn.edu.ccnu.imd.analysis.vo.User;
import cn.edu.ccnu.imd.analysis.vo.Pagination;

 

public interface UserService  {
	   public void del (User[] o);
	   public Object get (String id);
	   public Object add (User o);
	   
	   public void update (User o);
	   
	   public List<Object> findAll(Pagination pagination);
	   
	   public Map<String,Object>  findAllPage(Pagination pagination);
	   void changePass(String id, String pass);
	   public Object checkUserName(String name);
}
