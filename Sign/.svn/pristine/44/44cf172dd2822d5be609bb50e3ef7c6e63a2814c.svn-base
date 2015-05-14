package cn.edu.ccnu.imd.analysis.common.util;

import java.util.LinkedHashMap;

import cn.edu.ccnu.imd.analysis.vo.Admin;

public class SessionContext {
	
	// 封装登录用户信息的链�?
	private static LinkedHashMap<String, Admin> userMap = new LinkedHashMap<String, Admin>();
	
	/**
	 * 删除用户
	 * @param sessionId
	 * @return
	 */
	public static boolean removeUser(String sessionId) {
		boolean bRemove = false;
		try {
			userMap.remove(sessionId);
			bRemove = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bRemove;
	}

	// 用户登录
	public static boolean addUser(String sessionId, Admin user) {
		boolean bAdd = false;
		try {
			userMap.put(sessionId, user);
			bAdd = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bAdd;
	}

	/**
	 * 获取�?��的用户信�?
	 * @return
	 */
	public static LinkedHashMap<String, Admin> getUserMap() {
		return userMap;
	}
	
	/**
	 * 通过当前sessionId 获取�?��用户对象
	 * @param sessionId
	 * @return
	 */
	public static Admin getUser(String sessionId){
		return userMap.get(sessionId);
	}
}
