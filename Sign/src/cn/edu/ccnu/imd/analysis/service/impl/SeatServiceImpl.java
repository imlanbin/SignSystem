package cn.edu.ccnu.imd.analysis.service.impl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;


import cn.edu.ccnu.imd.analysis.common.util.CnToSpellUtil;
import cn.edu.ccnu.imd.analysis.common.util.SplitPageUtil;
import cn.edu.ccnu.imd.analysis.dao.BasicDao;
import cn.edu.ccnu.imd.analysis.dao.SeatDao;
import cn.edu.ccnu.imd.analysis.service.ConsumerService;
import cn.edu.ccnu.imd.analysis.service.SeatService;
import cn.edu.ccnu.imd.analysis.vo.Consumer;
import cn.edu.ccnu.imd.analysis.vo.Pagination;
import cn.edu.ccnu.imd.analysis.vo.Seat;
import cn.edu.ccnu.imd.analysis.vo.User;
import cn.edu.ccnu.imd.analysis.vo.YearSeat;;
 
public class SeatServiceImpl  implements SeatService {

	private static final String SUCCESS = null;
	private BasicDao dao;
	private SeatDao seatDao;
	
	public void setDao(BasicDao dao) {
		this.dao = dao;
	}

	public void setSeatDao(SeatDao seatDao) {
		this.seatDao = seatDao;
	}
	
	@Override
	public void update(Seat o) {
		// TODO Auto-generated method stub
		this.dao.update(o);
		 
	}
	@Override
	public Object add(Seat o) {
		// TODO Auto-generated method stub
		//o.setId(UUID.randomUUID().toString());
		Object oj = this.dao.save(o); 
		return oj;
	}

 
	@Override
	public Object get(String id) {
		// TODO Auto-generated method stub
	 //	System.out.println("b:"+id+this.writeDao.getClassName());
		Object o=this.seatDao.findById(id);
			 
		return o;
	}

	@Override
	public void del(Seat[] o) {
		// TODO Auto-generated method stub
		for(int i=0;i<o.length;i++){
			this.dao.delete(o[i]);
		}
		
		 
	}

	@Override
	public List<Object> findAll(Pagination pagination){
		// TODO Auto-generated method stub
		List<Object> l=this.seatDao.findAll(pagination.getStr());
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
			_l =this.seatDao.findAllPage(sql, sp.getOffset(), pagination.getRows());	 
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
	
	public String seatInit(String year, String desk, String seat){
		List _l = dao.getSeatByYear(year);
		if(_l != null && _l.size() != 0){
//				for(int i=0; i<_l.size(); i++){
//					this.dao.delete(_l.get(i));
//				}
			return "fail";
		}
		
		for(Short j=1; j<=Integer.parseInt(desk); j++){
			for(Short k=1; k<=Integer.parseInt(seat); k++){
				Seat s = new Seat();
				s.setId(UUID.randomUUID().toString());
				s.setDeskNum(j);
				s.setSeatNum(k);
				s.setYear(year);
				s.setState("1");
				dao.save(s);
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> findDistinctYear(Pagination pagination) {
		List _l =  new ArrayList();
	    SplitPageUtil sp = new SplitPageUtil();
		Long totalcount;
		totalcount =this.dao.countYear(pagination.getStr());
		if(totalcount!=null  && totalcount>0) {
			sp.splitpage(totalcount.intValue(), pagination.getRows(), pagination.getPage());
			String sql = pagination.getStr()+" order by "+pagination.getSort()+" "+pagination.getOrder()+" ";
			_l =this.dao.findSeatInformation(sql, sp.getOffset(), pagination.getRows());	 
		}
		List result =  new ArrayList();
		if(_l != null && _l.size() > 0){
			for(int i=0;i<_l.size();++i){
				YearSeat ys = new YearSeat();
				String tmp_year = _l.get(i).toString();
				ys.setYear(tmp_year);
				List _lsD =  new ArrayList();//list of sumDesk
				_lsD =this.dao.findSumDesk(tmp_year);
				if(_lsD != null && _lsD.size() > 0){
					int sumD = Integer.parseInt(_lsD.get(0).toString());
					ys.setSumDesk(sumD);
				}
				List _lsS =  new ArrayList();//list of sumSeat
				_lsS = this.dao.findSumSeat(tmp_year);
				if(_lsS != null && _lsS.size() > 0){
					int sumS = Integer.parseInt(_lsS.get(0).toString());
					ys.setSumSeat(sumS);
				}
				result.add(ys);
			}			
		}
		
		Map<String,Object> m = new HashMap<String,Object>();
	    
		m.put("total", totalcount);
		m.put("rows", result);
		
		return m;
	}

	@Override
	public void setConsumer(String seatId, String id) {
		// TODO Auto-generated method stub
	    StringBuffer query = new StringBuffer();
	    query.append(" 1=1 and  id = \""+ seatId + "\"");
		try{
			Seat seat1 = (Seat)seatDao.findAll(query.toString()).get(0);
			dao.setClassName("cn.edu.ccnu.imd.analysis.vo.Consumer");
			Consumer con = (Consumer)this.dao.findById(id);
			dao.setClassName("cn.edu.ccnu.imd.analysis.vo.Seat");
			query = new StringBuffer();
			query.append(" 1=1 and year = \"" + con.getYear() + "\" and deskNum = " + con.getDeskNum() 
					+ " and seatNum = " + con.getSeatNum());
			Seat seat2 = (Seat)seatDao.findAll(query.toString()).get(0);
			seat1.setConsumerId(id);
			seat1.setState("0");
			seat2.setConsumerId(null);
			seat2.setState("1");
			con.setDeskNum(seat1.getDeskNum());
			con.setSeatNum(seat1.getSeatNum());
			this.dao.update(seat1);
			this.dao.update(seat2);
			this.dao.update(con);
		} catch(Exception e){
			System.out.println("setConsumer fali for: "+ e.toString());
		}
	}
	
}
