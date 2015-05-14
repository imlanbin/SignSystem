package cn.edu.ccnu.imd.analysis.service.impl;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 

import cn.edu.ccnu.imd.analysis.common.util.SplitPageUtil;
import cn.edu.ccnu.imd.analysis.dao.BasicDao;
import cn.edu.ccnu.imd.analysis.service.BasicService;
import cn.edu.ccnu.imd.analysis.vo.User;

 

public class BasicServiceImpl implements BasicService {
	
	 
	private BasicDao dao;
 

	public void setDao(BasicDao dao) {
		this.dao = dao;
	}



	public void update(Object o) {
		// TODO Auto-generated method stub
		this.dao.update(o);
 
	}

 
 
	public Object add(Object o) {
		// TODO Auto-generated method stub
		Object oj = this.dao.save(o);
 
		return oj;
	}

 
	public Object get(Object id) {
 
		Object o =  this.dao.findById(id);
 
		return o;
	}

 
	public void del(Object o) {
		// TODO Auto-generated method stub
		this.dao.delete(o);
		 
	}

 
	public List<Object> findAll(String strSQL) {
		// TODO Auto-generated method stub	

		List<Object> l=this.dao.findAll(strSQL);
		return  l;
		
	}

	 public List<Object>  findAllPage(String strSQL,Integer page, Integer maxResults){
		 	List _l =  new ArrayList();
		    SplitPageUtil sp = new SplitPageUtil();
			Long totalcount; 
			totalcount =this.dao.count(strSQL);
			if(totalcount!=null  && totalcount>0) {			
				sp.splitpage(totalcount.intValue(), maxResults, page);
				_l =this.dao.findAllPage(strSQL, sp.getOffset(), maxResults);	
			}
			return _l;
	 }
	 
	  
	 public SplitPageUtil  getPage(String strSQL,Integer page, Integer maxResults){
		 	SplitPageUtil sp = new SplitPageUtil();
			Long totalcount; 
			totalcount =this.dao.count(strSQL);
			if(totalcount!=null  && totalcount>0) {			
				sp.splitpage(totalcount.intValue(), maxResults, page);	 
			}
			return sp;
	 }
	 
	public Map<String,Object> findAllSPPage(String strSQL, Integer page,
				Integer maxResults) {
			List _l =  new ArrayList();
			SplitPageUtil sp = new SplitPageUtil();
			Long totalcount; 
			totalcount =this.dao.count(strSQL);
			if(totalcount!=null  && totalcount>0) {			
					sp.splitpage(totalcount.intValue(), maxResults, page);
					sp.getShowPage();
					_l =this.dao.findAllPage(strSQL, sp.getOffset(), maxResults);	
			}
		    
			Map<String,Object> m = new HashMap<String,Object>();
		    
			m.put("sp", sp);
			m.put("rows", _l);
			
			return m;
		}
	public List<Object> login(User user){
		return dao.login(user);
	}
	
	public void addlog(Object o) {
		// TODO Auto-generated method stub
		Object oj = this.dao.save(o);
	}



	@Override
	public Object checkUser(String name) {
		// TODO Auto-generated method stub
		return dao.findByName(name);
	}
 
}
 