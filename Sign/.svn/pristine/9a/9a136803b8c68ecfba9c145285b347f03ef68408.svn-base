package cn.edu.ccnu.imd.analysis.common.util;
 
import java.util.ArrayList;
import java.util.List;

public class SplitPageUtil {
	
	private int page = 1;
	private int pageNum = 0;
	private int count = 0;
	private int offset = 0;
	private int totalpage=0;
	private int prePage=1;
	private int nextPage=1;
	private int lastPage=1;
	private int firstPage=1;
	private int step = 3;
	private List arrayPage = null; 
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

	public int getPrePage() {
		return prePage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public List getArrayPage() {
		return arrayPage;
	}

	public void setArrayPage(List arrayPage) {
		this.arrayPage = arrayPage;
	}

	public SplitPageUtil(){
		
	}
	
	public SplitPageUtil(int count,int pageNum,int page){
		this.splitpage(count, pageNum, page);
	}
	
	public  void splitpage(int count,int pageNum,int page){
		
		this.count =count;
		this.pageNum=pageNum;
		this.page=page;
			
	 	
		this.totalpage =  (this.count + this.pageNum - 1) / this.pageNum;
		
		if (this.totalpage != 0) {
			this.lastPage =this.totalpage;
	   	}  
		
	    if (this.page< 0 || this.page > this.totalpage) {
		   this.page = 1; 
	    }
	   

	    this.prePage  = this.page - 1>0?this.page - 1:1;
	    this.nextPage = this.page + 1>this.totalpage?this.totalpage:this.page + 1;

	   
	    this.offset =(this.page-1)*this.pageNum;
		
	 //	 System.out.print(this.pageNum);
		
	}
	
 
	public  void getShowPage(){
		 
			arrayPage = new ArrayList(); 
			for(int i=page-step>0?page-step:1;i<page&&i>0;i++){
				arrayPage.add(i);
			}
	 
			for(int j=page;j<totalpage+1&&j<page+step;j++){
				arrayPage.add(j);
			}
		//	System.out.print("**:"+arrayPage.size()); 
	}
	 
}
