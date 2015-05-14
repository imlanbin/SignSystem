/**   
* @Title: SeatDao.java 
* @Package cn.edu.ccnu.imd.analysis.dao 
* @Description: TODO(用一句话描述该文件做什么) 
* @author cenhaoqiang   
* @date 2014-7-9 上午10:24:05 
* @version V1.0   
*/

package cn.edu.ccnu.imd.analysis.dao;

import java.util.List;

public interface SeatDao extends BasicDao {
	public List<Object> findAll(String strSQL);
	public List<Object> findAllPage(String strSQL, Integer firstResult, Integer maxResults) ;
}

