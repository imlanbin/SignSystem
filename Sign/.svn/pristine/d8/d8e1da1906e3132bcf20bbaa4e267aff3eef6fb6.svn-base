package cn.edu.ccnu.imd.analysis.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


import cn.edu.ccnu.imd.analysis.common.util.SplitPageUtil;
import cn.edu.ccnu.imd.analysis.dao.BasicDao;
import cn.edu.ccnu.imd.analysis.service.LeafletService;
import cn.edu.ccnu.imd.analysis.vo.Leaflet;
import cn.edu.ccnu.imd.analysis.vo.Pagination;
import cn.edu.ccnu.imd.analysis.vo.User;
 
public class DWRServiceImpl  implements LeafletService {

	private BasicDao dao;
	
	public void setDao(BasicDao dao) {
		this.dao = dao;
	}

	@Override
	public void update(Leaflet o) {
		// TODO Auto-generated method stub
		this.dao.update(o);
		 
	}
	@Override
	public Object add(Leaflet o) {
		// TODO Auto-generated method stub
		//o.setId(UUID.randomUUID().toString());
		Object oj = this.dao.save(o); 
		return oj;
	}

 
	@Override
	public Object get(String id) {
		// TODO Auto-generated method stub
	 //	System.out.println("b:"+id+this.writeDao.getClassName());
		Object o=this.dao.findById(id);
			 
		return o;
	}

	@Override
	public void del(Leaflet[] o) {
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
}
