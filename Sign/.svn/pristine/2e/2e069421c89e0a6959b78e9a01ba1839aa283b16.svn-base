package cn.edu.ccnu.imd.analysis.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import cn.edu.ccnu.imd.analysis.common.util.SplitPageUtil;
import cn.edu.ccnu.imd.analysis.dao.BasicDao;
import cn.edu.ccnu.imd.analysis.service.SeatReportService;
import cn.edu.ccnu.imd.analysis.vo.Consumer;
import cn.edu.ccnu.imd.analysis.vo.Pagination;
import cn.edu.ccnu.imd.analysis.vo.Seat;
import cn.edu.ccnu.imd.analysis.vo.SeatReport;
import java.io.InputStream;

public class SeatReportServiceImpl implements SeatReportService {

	private BasicDao dao;

	public void setDao(BasicDao dao) {
		this.dao = dao;
	}

	@Override
	public Map<String, Object> get(String year) {
		// TODO Auto-generated method stub

		String message = "";
		List<Object> l = this.dao.getSeatReportByYear(year);
		if (l.size() != 0) {
			for (int i = 0; i < l.size(); i++) {
				this.dao.delete(l.get(i));
			}
		}

		short maxDeskNum = this.dao.getMaxDeskNumByYear(year);
		if (maxDeskNum != 0) {
			for (short i = 1; i <= maxDeskNum; i++) {
				List<Object> _l = this.dao.getConsumersByYear(year, i);
				String str = "";
				short j = 0;
				SeatReport sp = new SeatReport();
				sp.setId(UUID.randomUUID().toString());
				sp.setDeskNum(i);
				sp.setYear(year);
				if (_l != null && _l.size() != 0) {
					for (j = 0; j < (_l.size() - 1); j++) {
						Consumer con = (Consumer) _l.get(j);
						str = str + con.getContactPerson() + "、";
					}
					str = str + ((Consumer) _l.get(j)).getContactPerson();
					j++;
				}
				sp.setConsumers(str);
				sp.setNumber((short) j);
				this.dao.save(sp);
			}
		} else {
			message = "未初始化该年份座位信息！";
		}

		Map<String, Object> m = new HashMap<String, Object>();
		m.put("message", message);
		return m;
	}

	@Override
	public List<Object> findAll(Pagination pagination) {
		// TODO Auto-generated method stub
		List<Object> l = this.dao.findAll(pagination.getStr());
		return l;
	}

	@Override
	public Map<String, Object> findAllPage(Pagination pagination) {
		List _l = new ArrayList();
		SplitPageUtil sp = new SplitPageUtil();
		Long totalcount;
		totalcount = this.dao.count(pagination.getStr());
		if (totalcount != null && totalcount > 0) {

			// System.out.println(pagination.getPage()+" "+pagination.getRows());

			sp.splitpage(totalcount.intValue(), pagination.getRows(),
					pagination.getPage());
			String sql = pagination.getStr() + " order by "
					+ pagination.getSort() + " " + pagination.getOrder() + " ";
			_l = this.dao
					.findAllPage(sql, sp.getOffset(), pagination.getRows());
		}

		Map<String, Object> m = new HashMap<String, Object>();

		m.put("total", totalcount);
		m.put("rows", _l);

		return m;
	}

	public Map<String, Object> findAllSPPage(String strSQL, Integer page,
			Integer maxResults) {
		List _l = new ArrayList();
		SplitPageUtil sp = new SplitPageUtil();
		Long totalcount;
		totalcount = this.dao.count(strSQL);
		if (totalcount != null && totalcount > 0) {
			sp.splitpage(totalcount.intValue(), maxResults, page);
			sp.getShowPage();
			_l = this.dao.findAllPage(strSQL, sp.getOffset(), maxResults);
		}

		Map<String, Object> m = new HashMap<String, Object>();

		m.put("sp", sp);
		m.put("rows", _l);

		return m;
	}

	public List<Object> getSeatReportByYear(String year) {

		List<Object> l = this.dao.getSeatReportByYear(year);

		return l;
	}

	@Override
	public void refreashSeatReport() {
		// TODO Auto-generated method stub
		SeatReport sReport = new SeatReport();
		String contactPersons = null;
		int countNumber = 0;
		boolean has = false;

		String strSQL = "1=1";
		List<Object> reportList = this.dao.findAll(strSQL);
		List<String> oldReportList = new ArrayList<String>();
		List<String> oldReportList_Id = new ArrayList<String>();
		if (reportList != null && reportList.size() != 0) {
			for (int i = 0; i < reportList.size(); i++) {
				sReport = (SeatReport) reportList.get(i);
				String s = sReport.getYear() + sReport.getDeskNum().toString();
				oldReportList.add(i, s);
				oldReportList_Id.add(i, sReport.getId());
			}
		}

		List<Object> desknumList = this.dao.getDesknumAndYear();
		if (reportList != null && reportList.size() != 0) {
			for (int i = 0; i < desknumList.size(); i++) {

				contactPersons = "";
				countNumber = 0;
				has = false;
				SeatReport sReport1 = new SeatReport();
				Object[] obj = (Object[]) desknumList.get(i);
				short shortNum = (Integer.valueOf(obj[1].toString()))
						.shortValue();
				List<Object> consumerList = this.dao.getConsumersByYear(
						obj[0].toString(), shortNum);

				for (int j = 0; j < consumerList.size(); j++) { // 获取固定桌号的客户们
					Consumer cs = (Consumer) consumerList.get(j);
					contactPersons += cs.getContactPerson().toString();
					if (j != (consumerList.size() - 1)) {
						contactPersons += "、";
					}
					countNumber++;
				}

				String s1 = obj[0].toString() + obj[1].toString(); // 根据年和桌号判断是否已经存在此条数据
				String Id = "";
				for (int num = 0; num < oldReportList.size(); num++) {
					if (s1.equals(oldReportList.get(num))) {
						has = true;
						if (oldReportList_Id != null
								&& oldReportList_Id.size() != 0) {
							Id = oldReportList_Id.get(num);
						}
						return;
					}
				}
				if (has == false) {
					sReport1.setId(UUID.randomUUID().toString());
					sReport1.setYear(obj[0].toString());
					sReport1.setDeskNum(shortNum);
					sReport1.setConsumers(contactPersons.toString());
					sReport1.setNumber((Integer.valueOf(countNumber))
							.shortValue());
					this.dao.save(sReport1);
				} else if (has == true) {
					SeatReport seatReport2 = (SeatReport) this.dao.findById(Id);
					seatReport2.setYear(obj[0].toString());
					seatReport2.setDeskNum(shortNum);
					seatReport2.setConsumers(contactPersons.toString());
					seatReport2.setNumber((Integer.valueOf(countNumber))
							.shortValue());
					this.dao.update(seatReport2);
				}
			}
		}
	}
}
