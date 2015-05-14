package cn.edu.ccnu.imd.analysis.common.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {
	
	protected FilterConfig filterConfig = null;
	
	private String redirectURL = null;
	
	private String sessionKey = null;
	
	public void destroy(){
		
		this.filterConfig = null;
		this.redirectURL = null;
		this.sessionKey = null;
		
	}
	
	public void doFilter(ServletRequest servletRequest,ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException{
			HttpServletRequest request = (HttpServletRequest) servletRequest;
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			HttpSession session = request.getSession();
			String isYes = (String)session.getAttribute(sessionKey);
			if (isYes == null || !"YES".equals(isYes)){
				response.sendRedirect(request.getContextPath() + redirectURL);
			}else{
				chain.doFilter(servletRequest, servletResponse);
			}	
	}
	
	public void init(FilterConfig filterConfig) throws ServletException {
			this.filterConfig = filterConfig;
			this.redirectURL = filterConfig.getInitParameter("redirectURL");
			this.sessionKey = filterConfig.getInitParameter("checkSessionKey");
	}

}
