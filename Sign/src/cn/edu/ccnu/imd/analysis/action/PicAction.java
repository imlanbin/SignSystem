package cn.edu.ccnu.imd.analysis.action;

 

import java.util.Map;

import cn.edu.ccnu.imd.analysis.service.LeafletService;
import cn.edu.ccnu.imd.analysis.service.PicService;

import com.opensymphony.xwork2.ActionSupport;
 

public class PicAction extends  ActionSupport {
	
	    public PicService service;
		
		public PicService getService() {
			return service;
		}

		public void setService(PicService service) {
			this.service = service;
		}

		public String jsp(){
			return SUCCESS;
		}
		
		public String missionList(){
			return SUCCESS;
		}
 
		public String searchPic(){
			String strSQL = "";
			if(id != null && !"".equals(id)){
				strSQL = " leafletId =  '"+id+"'";
			}
			this.stauts = true;
		 	this.mapPic = this.service.findAllSPPage(strSQL, page, maxResults);
			if(null == this.mapPic || this.mapPic.size()==0){
				this.stauts = false;
			} 
			return SUCCESS ;
		}
		
		private boolean stauts = false;	
		
		private String id;
		
		private Map<String ,Object> mapPic;
		
		//每次条数
		private int maxResults = 10;
		//页码
		private int page=1;

		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
		public boolean isStauts() {
			return stauts;
		}

		public void setStauts(boolean stauts) {
			this.stauts = stauts;
		}

		public Map<String, Object> getMapPic() {
			return mapPic;
		}


		public void setMapPic(Map<String, Object> mapPic) {
			this.mapPic = mapPic;
		}

}