package cn.edu.ccnu.imd.analysis.vo;

public class Pagination {
	private String str;
	private int page;
	private int rows;
	private String sort;
	private String order;
 
	

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public String getOrder() {
		return order;
	}

 
	
}
