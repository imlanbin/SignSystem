package cn.edu.ccnu.imd.analysis.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.Date;


import cn.edu.ccnu.imd.analysis.common.util.CnToSpellUtil;
import cn.edu.ccnu.imd.analysis.common.util.SplitPageUtil;
import cn.edu.ccnu.imd.analysis.dao.BasicDao;
import cn.edu.ccnu.imd.analysis.dao.SeatDao;
import cn.edu.ccnu.imd.analysis.service.ConsumerService;
import cn.edu.ccnu.imd.analysis.vo.Consumer;
import cn.edu.ccnu.imd.analysis.vo.Pagination;
import cn.edu.ccnu.imd.analysis.vo.Seat;
import cn.edu.ccnu.imd.analysis.vo.User;
 
public class ConsumerServiceImpl  implements ConsumerService {

	private BasicDao dao;
	private SeatDao seatDao;
	
	public void setDao(BasicDao dao) {
		this.dao = dao;
	}
	
	public void setSeatDao(SeatDao seatDao) {
		this.seatDao = seatDao;
	}

	@Override
	public void update(Consumer o, Short deskNum, Short seatNum) {
		
		if(deskNum != o.getDeskNum()){
			Seat seat = this.dao.getSeatByDeskNumAndSeatNum(deskNum, seatNum);
			seat.setState("1");
			seat.setConsumerId("");
			this.dao.update(seat);
			
		    seat = this.dao.getSeatByDeskNum(o.getDeskNum());
			seat.setState("0");
			seat.setConsumerId(o.getId());
			this.dao.update(seat);
			o.setSeatNum(seat.getSeatNum());
		}
		
		StringBuffer sbCNJ = new StringBuffer();
		sbCNJ.append(o.getCompanyName());
		sbCNJ.append("|");
		sbCNJ.append(CnToSpellUtil.converterToSpell(o.getCompanyName()));
		sbCNJ.append("|");
		sbCNJ.append(CnToSpellUtil.converterToFirstSpell(o.getCompanyName()));
		
		StringBuffer sbCPJ = new StringBuffer();
		sbCPJ.append(o.getContactPerson());
		sbCPJ.append("|");
		sbCPJ.append(CnToSpellUtil.converterToSpell(o.getContactPerson()));
		sbCPJ.append("|");
		sbCPJ.append(CnToSpellUtil.converterToFirstSpell(o.getContactPerson()));
		
		String cNJ = sbCNJ.toString();
		String cPJ = sbCPJ.toString();
		o.setCNJ(cNJ);		
		o.setCPJ(cPJ);
		
		o.setModifyTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
		this.dao.update(o);
	}
	
	@Override
	public Object add(Consumer o) {
		Seat seat = this.dao.getSeatByDeskNum(o.getDeskNum());
		seat.setState("0");
		seat.setConsumerId(o.getId());
		this.dao.update(seat);
		
		o.setSeatNum(seat.getSeatNum());
		
		StringBuffer sbCNJ = new StringBuffer();
		sbCNJ.append(o.getCompanyName());
		sbCNJ.append("|");
		sbCNJ.append(CnToSpellUtil.converterToSpell(o.getCompanyName()));
		sbCNJ.append("|");
		sbCNJ.append(CnToSpellUtil.converterToFirstSpell(o.getCompanyName()));
		
		StringBuffer sbCPJ = new StringBuffer();
		sbCPJ.append(o.getContactPerson());
		sbCPJ.append("|");
		sbCPJ.append(CnToSpellUtil.converterToSpell(o.getContactPerson()));
		sbCPJ.append("|");
		sbCPJ.append(CnToSpellUtil.converterToFirstSpell(o.getContactPerson()));
		
		String cNJ = sbCNJ.toString();
		String cPJ = sbCPJ.toString();
		o.setCNJ(cNJ);		
		o.setCPJ(cPJ);
		
		o.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
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
	public void del(Consumer[] o) {
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
	
	public Map<String, Object> getNumByState(){
		Map<String,Object> map = new HashMap<String,Object>();
		Long wdNum = 0l;//未到人数
		Long ydNum = 0l;//已到人数
		Long bdNum = 0l;//不到人数
		wdNum = this.dao.getNumByState("0");
		ydNum = this.dao.getNumByState("1");
		bdNum = this.dao.getNumByState("2");
		
		map.put("wdNum", wdNum);
		map.put("ydNum", ydNum);
		map.put("bdNum", bdNum);
		return map;
	}
	@Override 
	public Map<String,Object> changeStateP1(Consumer[] o) {
		// TODO Auto-generated method stub
		for(int i=0;i<o.length;++i){
			Consumer consumer=(Consumer)this.dao.findById(o[i].getId());
			if("1".equals(consumer.getState())){
				Map<String,Object> map = new HashMap<String,Object>();
				map = getNumByState();
				map.put("message", "hascome");
				map.put("index", i);
				return map;
			}
			if("2".equals(consumer.getState())){
				Map<String,Object> map = new HashMap<String,Object>();
				map = getNumByState();
				map.put("message", "hasnotcome");
				map.put("index", i);
				return map;
			}
		}
		Consumer con = new Consumer();
		for(int i=0;i<o.length;i++){
			con = o[i];
			this.changeState1(con.getId());
			con = null;
		} 
		Map<String,Object> map = new HashMap<String,Object>();
		return map = getNumByState();
	}
	
	@Override
	public Map<String,Object> changeStateP2(Consumer[] o) {
		// TODO Auto-generated method stub
		for(int i=0;i<o.length;++i){
			Consumer consumer=(Consumer)this.dao.findById(o[i].getId());
			if("1".equals(consumer.getState())){
				Map<String,Object> map = new HashMap<String,Object>();
				map = getNumByState();
				map.put("message", "hascome");
				map.put("index", i);
				return map;
			}
			if("2".equals(consumer.getState())){
				Map<String,Object> map = new HashMap<String,Object>();
				map = getNumByState();
				map.put("message", "hasnotcome");
				map.put("index", i);
				return map;
			}
		}		
		Consumer consumer = null;
		for(int i=0;i<o.length;i++){
			consumer=(Consumer)this.dao.findById(o[i].getId());
			String strSQL = "seatNum = " +consumer.getSeatNum() +" and deskNum = " +consumer.getDeskNum() + " and year =" + Calendar.getInstance().get(Calendar.YEAR);
			Seat seat = (Seat)seatDao.findAll(strSQL).get(0);
			seat.setState("1");
			seat.setConsumerId(null);
			this.dao.update(seat);
			consumer.setState("2");
			consumer.setDeskNum(null);
			consumer.setSeatNum(null);
			this.dao.update(consumer);
			consumer = null;
		} 
		Map<String,Object> map = new HashMap<String,Object>();
		return map = getNumByState();
	}
	
	@Override
	public Map<String,Object> changeState1(String id) {
		// TODO Auto-generated method stub
		Consumer consumer=(Consumer)this.dao.findById(id);
		if("1".equals(consumer.getState())){
			Map<String,Object> map = new HashMap<String,Object>();
			map = getNumByState();
			map.put("message", "hascome");
			return map;
		}else if("2".equals(consumer.getState())){
			Map<String,Object> map = new HashMap<String,Object>();
			map = getNumByState();
			map.put("message", "hasnotcome");
			return map;
		}else{
			consumer.setState("1");
			this.dao.update(consumer);
			Map<String,Object> map = new HashMap<String,Object>();
			return map = getNumByState();
		}		
	}
	
	@Override
	public Map<String,Object> changeState2(String id) {
		// TODO Auto-generated method stub
		Consumer consumer=(Consumer)this.dao.findById(id);
		if("1".equals(consumer.getState())){
			Map<String,Object> map = new HashMap<String,Object>();
			map = getNumByState();
			map.put("message", "hascome");
			return map;
		}else if("2".equals(consumer.getState())){
			Map<String,Object> map = new HashMap<String,Object>();
			map = getNumByState();
			map.put("message", "hasnotcome");
			return map;
		}else{			
			String strSQL = "seatNum = " +consumer.getSeatNum() +" and deskNum = " +consumer.getDeskNum() + " and year =" + Calendar.getInstance().get(Calendar.YEAR);
			Seat seat = (Seat)seatDao.findAll(strSQL).get(0);
			seat.setState("1");
			seat.setConsumerId(null);
			this.dao.update(seat);
			consumer.setState("2");
			consumer.setDeskNum(null);
			consumer.setSeatNum(null);
			this.dao.update(consumer);
			Map<String,Object> map = new HashMap<String,Object>();
			return map = getNumByState();
		}
	}
	
	
	public Map<String,Object> changeState3(String id) {
		// TODO Auto-generated method stub
		Consumer consumer=(Consumer)this.dao.findById(id);		
		if("1".equals(consumer.getState())){
			consumer.setState("0");
			this.dao.update(consumer);
		}else if("2".equals(consumer.getState())){
			String strSQL = "state = '1' and year =" + Calendar.getInstance().get(Calendar.YEAR);
			Seat seat = (Seat)seatDao.findAll(strSQL).get(0);
			consumer.setState("0");		
			consumer.setDeskNum(seat.getDeskNum());
			consumer.setSeatNum(seat.getSeatNum());
			this.dao.update(consumer);
			seat.setState("0");
			seat.setConsumerId(consumer.getId());
			this.dao.update(seat);
		}		
		Map<String,Object> map = new HashMap<String,Object>();
		return map = getNumByState();
	}
	
	public List<Object> getConsumerReportByYear(String year){
		List<Object> l = this.dao.getConsumerReportByYear(year);
		return l;
	}
	
	public Map<String,Object> getFreeDesk(){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Object> deskList = this.dao.getFreeDesk();
		map.put("deskList", deskList);
		return map;
	}
	
	@Override
	public Seat getSeatByDeskNum(Short deskNum) {
		// TODO Auto-generated method stub
		return this.dao.getSeatByDeskNum(deskNum);
	}

	@Override
	public int getDeskMaxSeat(Short deskNum) {
		// TODO Auto-generated method stub
		return this.dao.getDeskMaxSeat(deskNum);
	}
}
