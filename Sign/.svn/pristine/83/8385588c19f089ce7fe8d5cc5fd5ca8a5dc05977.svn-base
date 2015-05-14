package cn.edu.ccnu.imd.analysis.action;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.edu.ccnu.imd.analysis.service.BasicService;
import cn.edu.ccnu.imd.analysis.vo.User;
import cn.edu.ccnu.imd.analysis.vo.Log;


import com.opensymphony.xwork2.ActionSupport;

/**
 * 绠＄悊鐧诲綍淇℃�?
 * @author asus
 *
 */
public class LoginAction extends ActionSupport{

	/**
	 *	setter 注锟斤拷
	 */
	private BasicService service;

	public void setService(BasicService service) {
		this.service = service;
	}
	
	public String login() throws Exception{
			this.isLogin(); 
			HttpServletRequest request = ServletActionContext.getRequest();
			if(this.state == 1){
				return "yes";
			}else if( this.state == 2){
				request.setAttribute("message", "用户名或密码错误，请确认后重新输入或联系管理员！");
				return "no";
			}else{
				request.setAttribute("message", "账号已被禁用，请联系管理员！");
				return "no";
			}
	}	
	
	public String jsonlogin() throws Exception {
		this.isLogin();
		return SUCCESS;
	}
	
   public void loginOut(){
	   HttpServletRequest request = ServletActionContext.getRequest();
	   HttpSession session = (HttpSession)request.getSession();
	   session.invalidate();
	   session = null;
   }
   
	private User user = new User();
	
	private int state;	//1正常，2用户名或密码错误，3账户禁用	
	
	public int isState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	private void isLogin() {
		User userS = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Object> list = service.login(user);
		if (list != null && list.size() != 0) {
			userS = (User) list.get(0);
			HttpSession session = (HttpSession) request.getSession();
			if (userS.getState().equals("1")) {
				session.setAttribute("isLogin", "YES");
				session.setAttribute("userinfo", userS);
				this.state = 1;
				// 插入登陆日志
				Log log = new Log();
				log.setId(UUID.randomUUID().toString());
				log.setUserId(userS.getId());
				log.setUserName(user.getUserName());
				log.setLoginIp((String) request.getRemoteAddr());
				log.setLoginTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(Calendar.getInstance().getTime()));
				service.addlog(log);
			} else if (userS.getState().equals("0")) {
				this.state = 3;
			}
		} else {
			this.state = 2;
		}
	}	
}
