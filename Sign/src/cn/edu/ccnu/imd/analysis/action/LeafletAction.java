package cn.edu.ccnu.imd.analysis.action;

 

import java.util.List;
import java.util.Map;

import cn.edu.ccnu.imd.analysis.service.LeafletService;
import cn.edu.ccnu.imd.analysis.vo.Leaflet;
import com.opensymphony.xwork2.ActionSupport;
 

public class LeafletAction extends  ActionSupport {
	
		public LeafletService service;
		
		public void setService(LeafletService service) {
			this.service = service;
		}
		
		
		public String jsp(){
			return SUCCESS;
		}
		
		public String missionList(){
			return SUCCESS;
		}
		
		public String search(){
			String strSQL = " 1=1 ";
			if(pinYin != null && !"".equals(pinYin)){
				strSQL = " pinYin =  '"+pinYin+"'";
			}
			this.stauts = true;
		 	this.mapLeaflet = this.service.findAllSPPage(strSQL, page, maxResults);
			if(null == this.mapLeaflet || this.mapLeaflet.size()==0){
				this.stauts = false;
			} 
			return SUCCESS ;
		}
		
		
		
		//问题列表
		private Map<String ,Object> mapLeaflet;
		
		//说明书列表
		private List<Leaflet> leaflet ;
		
		private boolean stauts = false;	

		//每次条数
		private int maxResults = 10;
		//页码
		private int page=1;
		
		private String pinYin;
		
		

		public String getPinYin() {
			return pinYin;
		}

		public void setPinYin(String pinYin) {
			this.pinYin = pinYin;
		}
		
		public boolean isStauts() {
			return stauts;
		}


		public void setStauts(boolean stauts) {
			this.stauts = stauts;
		}
		
		public List<Leaflet> getLeaflet() {
			return leaflet;
		}


		public void setLeaflet(List<Leaflet> leaflet) {
			this.leaflet = leaflet;
		}


		public Map<String, Object> getMapLeaflet() {
			return mapLeaflet;
		}


		public void setMapLeaflet(Map<String, Object> mapLeaflet) {
			this.mapLeaflet = mapLeaflet;
		}

}