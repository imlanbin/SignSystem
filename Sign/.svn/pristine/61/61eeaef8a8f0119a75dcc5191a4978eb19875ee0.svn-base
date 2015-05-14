package cn.edu.ccnu.imd.analysis.action;

 

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.edu.ccnu.imd.analysis.service.BasicService;
import cn.edu.ccnu.imd.analysis.service.UserService;

import com.opensymphony.xwork2.ActionSupport;
 

public class UserAction extends  ActionSupport {
	
		private UserService userService;
		
		public void setUserService(UserService userService){
			this.userService = userService;
		}
		
		public String jsp(){
			return SUCCESS;
		}
		
		public String missionList(){
			return SUCCESS;
		}
		
		public String checkUserName() throws IOException {
			HttpServletRequest request = ServletActionContext.getRequest();
			String userName = request.getParameter("userName");
			Object o = userService.checkUserName(userName);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			if(o != null){				
				out.print("用户名已存在!");
				out.flush();
				out.close();
			}else{
				out.print("用户名可用!");
				out.flush();
				out.close();
			}
			return null;
		}
 
}