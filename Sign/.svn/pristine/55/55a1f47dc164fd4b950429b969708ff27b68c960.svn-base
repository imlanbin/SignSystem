package cn.edu.ccnu.imd.analysis.action;

 

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import cn.edu.ccnu.imd.analysis.service.SeatReportService;
import cn.edu.ccnu.imd.analysis.vo.Consumer;
import cn.edu.ccnu.imd.analysis.vo.Pagination;
import cn.edu.ccnu.imd.analysis.vo.Seat;
import cn.edu.ccnu.imd.analysis.vo.SeatReport;

import com.opensymphony.xwork2.ActionSupport;





public class SeatReportAction extends  ActionSupport {
	
		public SeatReportService seatReportervice;
		private SeatReport seatReport = new SeatReport();
		private List<Object> seatReportList;
		private String fileName;
		private InputStream excelStream; // 这个输入流对应上面struts.xml中配置的那个excelStream，两者必须一致
		private String year;
		
		public void setSeatReportervice(SeatReportService seatReportervice) {
			this.seatReportervice = seatReportervice;
		}
		
		public String missionList(){
			return SUCCESS;
		}
		
		public String getSeatReportListExport() {
			try {
				if(year != "" && year != null ){
					seatReportList = this.seatReportervice.getSeatReportByYear(year);
				}
				if(seatReportList != null && seatReportList.size() != 0){
					HSSFWorkbook workbook = getWorkbook(this.seatReportList);
					this.workbook2InputStream(workbook, year + " SeatReport");
					return SUCCESS;
				}else{
					return ERROR;
				}
				
			} catch (Exception e) {
				return SUCCESS;
			}
			
		}

		// 将Workbook写入到InputStream
		private void workbook2InputStream(HSSFWorkbook workbook, String fileName) throws Exception {
			this.fileName = fileName; // 设置fileName
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			workbook.write(baos);
			baos.flush();
			byte[] aa = baos.toByteArray();
			excelStream = new ByteArrayInputStream(aa, 0, aa.length);
			baos.close();
		}

		/* 下面这个方法是将list转换为Excel工作表的 */
		private HSSFWorkbook getWorkbook(List<Object> list) throws Exception {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("sheet1");
			// 创建数据表头
			String titles[] = { "年份", "桌号", "客户", "人数" };
			int[] sort = new int[] { 0, 1, 2, 3 };
			HSSFRow row = sheet.createRow((short) 0);
			HSSFCellStyle items_style = workbook.createCellStyle();
			items_style.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);

			HSSFFont celltbnamefont = workbook.createFont();
			celltbnamefont.setFontHeightInPoints((short) 10);
			celltbnamefont.setColor((short) (HSSFFont.COLOR_NORMAL));
			items_style.setFont(celltbnamefont);
			items_style.setWrapText(true);

			for (int i = 0; i < titles.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellValue(titles[i]);
				cell.setCellStyle(items_style);
			}

			HSSFCellStyle datestyle = workbook.createCellStyle();
			HSSFDataFormat df = workbook.createDataFormat();
			datestyle.setAlignment((short) HSSFCellStyle.ALIGN_LEFT);
			datestyle.setDataFormat(df.getFormat("yyyy-mm-dd hh:mm:ss"));

			HSSFCellStyle intdatestyle = workbook.createCellStyle();
			intdatestyle.setAlignment((short) HSSFCellStyle.ALIGN_LEFT);

			HSSFCellStyle floatdatestyle = workbook.createCellStyle();
			floatdatestyle.setAlignment((short) HSSFCellStyle.ALIGN_LEFT);

			HSSFCellStyle longdatestyle = workbook.createCellStyle();
			longdatestyle.setAlignment((short) HSSFCellStyle.ALIGN_LEFT);
			
			HSSFCellStyle setBorder  = workbook.createCellStyle();
			setBorder.setWrapText(true);

			int i = 1;
			HSSFCell cell;
			Iterator<Object> it = list.iterator();
			while (it.hasNext()) {
				HSSFRow dataRow = sheet.createRow(i);
				SeatReport sp = (SeatReport) it.next();

				cell = dataRow.createCell(sort[0]);
				cell.setCellValue(sp.getYear() + "");// 年份

				cell = dataRow.createCell(sort[1]);
				cell.setCellValue(sp.getDeskNum() + "");// 桌号

				cell = dataRow.createCell(sort[2]);
				cell.setCellValue(sp.getConsumers() + "");// 客户
				
				cell = dataRow.createCell(sort[3]);
				cell.setCellValue(sp.getNumber() + "");// 人数
				
				i++;
			}
			return workbook;
		}
		
		public String seatReportListInit() throws Exception{
			System.out.println("this is seatReportListInit");
			
			this.seatReportervice.refreashSeatReport();

			return SUCCESS;
		}

		public SeatReport getModel() {
			return seatReport;
		}

		public SeatReport getSeatReport() {
			return seatReport;
		}

		public void setSeatReport(SeatReport seatReport) {
			this.seatReport = seatReport;
		}

		
		public void setSeatReportList(List<Object> seatReportList) {
			this.seatReportList = seatReportList;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public InputStream getExcelStream() {
			return excelStream;
		}

		public void setExcelStream(InputStream excelStream) {
			this.excelStream = excelStream;
		}


		public String getYear() {
			return year;
		}


		public void setYear(String year) {
			this.year = year;
		}


		public List<Object> getSeatReportList() {
			return seatReportList;
		}

		
}