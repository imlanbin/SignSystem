package cn.edu.ccnu.imd.analysis.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.ccnu.imd.analysis.common.util.SplitPageUtil;
import cn.edu.ccnu.imd.analysis.dao.BasicDao;
import cn.edu.ccnu.imd.analysis.service.LogService;
import cn.edu.ccnu.imd.analysis.vo.Log;
import cn.edu.ccnu.imd.analysis.vo.Pagination;
 
public class LogServiceImpl  implements LogService {

	private BasicDao dao;
	 
	 
	public void setDao(BasicDao dao) {
		this.dao = dao;
	}


 
	@Override
	public Object get(String id) {
		// TODO Auto-generated method stub
	 //	System.out.println("b:"+id+this.writeDao.getClassName());
		Object o=this.dao.findById(id);
			 
		return o;
	}

	@Override
	public void del(Log[] o) {
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
	
 
}
