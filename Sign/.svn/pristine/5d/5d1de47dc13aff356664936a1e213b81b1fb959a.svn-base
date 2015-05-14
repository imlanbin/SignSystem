package cn.edu.ccnu.imd.analysis.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hibernate.sql.Delete;

import cn.edu.ccnu.imd.analysis.common.util.LybaoMd5Encrypt;
import cn.edu.ccnu.imd.analysis.common.util.SplitPageUtil;
import cn.edu.ccnu.imd.analysis.dao.BasicDao;
import cn.edu.ccnu.imd.analysis.service.UserService;
import cn.edu.ccnu.imd.analysis.vo.User;
import cn.edu.ccnu.imd.analysis.vo.Pagination;
 
public class UserServiceImpl  implements UserService {

	private BasicDao dao;
	 
	 
	public void setDao(BasicDao dao) {
		this.dao = dao;
	}

	@Override
	public void update(User o) {
		// TODO Auto-generated method stub
//		User tmp=(User) this.dao.findById(o.getId());
//		o.setPassword(tmp.getPassword());
		User tmp = (User) dao.findById(o.getId());
		o.setPassword(tmp.getPassword());
		this.dao.evict(tmp);
		this.dao.update(o);
		 
	}
	@Override
	public Object add(User o) {
		// TODO Auto-generated method stub
		o.setId(UUID.randomUUID().toString());
		o.setPassword(LybaoMd5Encrypt.MyMD5(o.getPassword()));
		System.out.print(o.getPassword());
		Object oj = this.dao.save(o); 
		
		return oj;
	}

	@Override
	public void changePass(String id,String pass) {
		// TODO Auto-generated method stub
	 //	System.out.println("b:"+id+this.writeDao.getClassName());
		this.dao.changePass(id,pass);
	}
	
	@Override
	public Object get(String id) {
		// TODO Auto-generated method stub
	 //	System.out.println("b:"+id+this.writeDao.getClassName());
		Object o=this.dao.findById(id);
			 
		return o;
	}

	@Override
	public void del(User[] o) {
		// TODO Auto-generated method stub
		for(int i=0;i<o.length;i++){
			this.dao.delete(o[i]);
		}
		
		 
	}

	 
 

	@Override
	public List<Object> findAll(Pagination pagination){
		// TODO Auto-generated method stub
		List<Object> l=this.dao.findAll(pagination.getStr());
		
		return l;
	}

	@Override
	public Map<String,Object>  findAllPage(Pagination pagination) {
	    List _l =  new ArrayList();
	    SplitPageUtil sp = new SplitPageUtil();

		Long totalcount;
	 
		totalcount =this.dao.count(pagination.getStr());
			 
		if(totalcount!=null  && totalcount>0) {			
			
		//	System.out.println(pagination.getPage()+" "+pagination.getRows());
			
			sp.splitpage(totalcount.intValue(), pagination.getRows(), pagination.getPage());
			String sql = pagination.getStr()+" order by "+pagination.getSort()+" "+pagination.getOrder()+" ";
			_l =this.dao.findAllPage(sql, sp.getOffset(), pagination.getRows());	 
		}
	    
		Map<String,Object> m = new HashMap<String,Object>();
	    
		m.put("total", totalcount);
		m.put("rows", _l);
		
		return m;
	}

	@Override
	public Object checkUserName(String name) {
		// TODO Auto-generated method stub
		return this.dao.findByName(name);
	}
	
 
}
