package cn.edu.ccnu.imd.analysis.action;

 

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.struts2.ServletActionContext;

import cn.edu.ccnu.imd.analysis.common.util.RegexUtils;
import cn.edu.ccnu.imd.analysis.service.ConsumerService;
import cn.edu.ccnu.imd.analysis.vo.Consumer;
import cn.edu.ccnu.imd.analysis.vo.Seat;

import com.opensymphony.xwork2.ActionSupport;
 

public class ConsumerAction extends  ActionSupport {
	
		public ConsumerService consumerService;
		private Consumer consumer = new Consumer();
		private List<Object> consumerReportList;
		private String fileName;
		private InputStream excelStream; // 这个输入流对应上面struts.xml中配置的那个excelStream，两者必须一致
		private String year;
		private List deskList = new ArrayList();
		
		public File uploadFile;

		public void setConsumerService(ConsumerService consumerService) {
			this.consumerService = consumerService;
		}
		
		
		public String jsp(){
			return SUCCESS;
		}
		
		public String missionList(){
			return SUCCESS;
		}
		
		public String getConsumerSignExport(){
			try {
				if(year != "" && year != null ){
					consumerReportList = this.consumerService.getConsumerReportByYear(year);
				}
				if(consumerReportList != null && consumerReportList.size() != 0){
					HSSFWorkbook workbook = getWorkbook(this.consumerReportList);
					this.workbook2InputStream(workbook, year + " ConsumersReport");
					return SUCCESS;
				}else{
					return ERROR;
				}
			} 
			catch (Exception e) {
				return SUCCESS;
			}
		}
		
		
		public String getConsumerListExport() {
			try {
				if(year != "" && year != null ){
					consumerReportList = this.consumerService.getConsumerReportByYear(year);
				}
				if(consumerReportList != null && consumerReportList.size() != 0){
					HSSFWorkbook workbook = getWorkbook(this.consumerReportList);
					this.workbook2InputStream(workbook, year + " ConsumerSignReport");
					return SUCCESS;
				}else{
//					PrintWriter writer = ServletActionContext.getResponse().getWriter();
//					writer.println("该年份没有可导出的数据!");
//					writer.flush();
//					writer.close();
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
			String titles[] = { "单位名称", "联系人", "联系电话", "桌号", "是否VIP", "接待人", "接待人电话", "备注" };
			int[] sort = new int[] { 0, 1, 2, 3, 4, 5, 6, 7 };
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
				Consumer con = (Consumer) it.next();

				cell = dataRow.createCell(sort[0]);
				cell.setCellValue(con.getCompanyName() + "");//单位名称

				cell = dataRow.createCell(sort[1]);
				cell.setCellValue(con.getContactPerson() + "");// 联系人

				cell = dataRow.createCell(sort[2]);
				cell.setCellValue(con.getContactPhone() + "");// 联系电话
				
				cell = dataRow.createCell(sort[3]);
				cell.setCellValue(con.getDeskNum() + "");// 桌号
				
				cell = dataRow.createCell(sort[4]);
				if(con.getIsVIP() != null && con.getIsVIP() != ""){
					if(con.getIsVIP().equals("1")){
						cell.setCellValue("是" + "");// 是否VIP
					}else {
						cell.setCellValue("否" + "");// 是否VIP
					}	
				}else{
					cell.setCellValue("否" + "");// 是否VIP
				}
				
				cell = dataRow.createCell(sort[5]);
				cell.setCellValue(con.getRecePerson() + "");// 接待人

				cell = dataRow.createCell(sort[6]);
				cell.setCellValue(con.getRecePhone() + "");// 接待人电话
				
				cell = dataRow.createCell(sort[7]);
				cell.setCellValue(con.getRemarks() + "");// 备注
				
				i++;
			}
			return workbook;
		}
		
		
		//导入excel文件批量添加客户数据
		public String addConsumerByUploadFile(){
			//Map<String, String> param = new HashMap<String, String>();
			//初始化客户队列
			List<Consumer> consumerList = new ArrayList<Consumer>();
			
			//日期时间格式化
//					DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			DateFormat formatYear = new SimpleDateFormat("yyyy");
//					DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
//					String sDtae1 = format1.format(new Date());
//					String sDtae2 = format2.format(new Date());
			
			HttpServletRequest request = ServletActionContext.getRequest();
			try{
				if(uploadFile == null || uploadFile.getName() == null 
						|| "".equals(uploadFile.getName())){
					//this.message = "err.upload.null";
					request.setAttribute("message", "没有上传文件");
					return SUCCESS;
				}
				
				InputStream is = new FileInputStream(uploadFile);  
		        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);  
		        // 循环工作表Sheet  
		        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {  
		            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);  
		            if (hssfSheet == null) {  
		            	request.setAttribute("message", "上传文件没有内容");
		                return SUCCESS;  
		            }  
		            // 循环行Row
		            for (int rowNum = 2; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
		                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
		                if (hssfRow == null) {
		                	request.setAttribute("message", "第"+(rowNum+1)+"行没有数据");
		                    return SUCCESS;
		                }
		                consumer = new Consumer();
		                // 循环列Cell  
		                // 0单位   1联系人   2联系电话   3桌号   4 座号  5VIP 6接待人 7接待电话 8备注  
		                HSSFCell companyName = hssfRow.getCell(0);
		                if (companyName != null && !"".equals(companyName.getStringCellValue().trim())) {
		                	consumer.setCompanyName(companyName.getStringCellValue().trim());
		                }
		                
		                HSSFCell contactPerson = hssfRow.getCell(1);
		                if (contactPerson != null && !"".equals(contactPerson.getStringCellValue().trim())){		                	
		                	consumer.setContactPerson(contactPerson.getStringCellValue().trim());
		                }
		                
		                if((companyName == null || "".equals(companyName.getStringCellValue().trim())) && (contactPerson == null || "".equals(contactPerson.getStringCellValue().trim()))){
		                	request.setAttribute("message", "第"+(rowNum+1)+"行的单位名称和联系人不能同时为空");
		                	return SUCCESS;
		                }
		                
		                HSSFCell contactPhone = hssfRow.getCell(2);
		                if(contactPhone != null){
		                	if(contactPhone.getCellType() != Cell.CELL_TYPE_BLANK){
		                		if(contactPhone.getCellType() == Cell.CELL_TYPE_NUMERIC){		                			
		                			DecimalFormat dataformat = new DecimalFormat("#");
		                			String phone = String.valueOf(dataformat.format(contactPhone.getNumericCellValue()));
		 							consumer.setContactPhone(phone.trim());
		                		}
		                		if(contactPhone.getCellType() == Cell.CELL_TYPE_STRING){
		                			consumer.setContactPhone(contactPhone.getStringCellValue().trim());
		                		}
		                	}
		                }		                
		                
		                HSSFCell deskNum = hssfRow.getCell(3);		                
		                HSSFCell seatNum = hssfRow.getCell(4);
		                int curSeat = 999;
	                	
					if (deskNum != null) {
						if (deskNum.getCellType() == Cell.CELL_TYPE_BLANK) {
							request.setAttribute("message", "第" + (rowNum + 1) + "行的桌号没有填写");
							return SUCCESS;
						} else {
							if(seatNum != null){
								if (seatNum.getCellType() != Cell.CELL_TYPE_BLANK) {
									curSeat = seatNum.getCellType() == Cell.CELL_TYPE_NUMERIC ? (int) seatNum.getNumericCellValue() : Integer.parseInt(seatNum.getStringCellValue());
								} else {
									request.setAttribute("message", "第" + (rowNum + 1) + "行的座位号没有填写");
									return SUCCESS;
								}
							}							
							if (deskNum.getCellType() == Cell.CELL_TYPE_STRING) {
								if (deskNum.getStringCellValue().trim()
										.equals("")) {
									request.setAttribute("message", "第" + (rowNum + 1) + "行的桌号没有填写");
									return SUCCESS;
								} else {
									if (!RegexUtils.checkDigit(deskNum
											.getStringCellValue().trim())) {
										int num = Integer.valueOf(deskNum
												.getStringCellValue().trim());
										if (checkDesk(num)) {
											request.setAttribute("message", "第" + (rowNum + 1) + "行的" + num + "号桌子不存在,请联系管理员");
											return SUCCESS;
										} else if (checkDeskMaxSeat(num,
												curSeat)) {
											request.setAttribute("message", "第" + (rowNum + 1) + "行的" + num + "号桌子已满,请联系管理员添加座位");
											return SUCCESS;
										} else {
											consumer.setDeskNum((short) num);
										}
									} else {
										request.setAttribute("message", "第" + (rowNum + 1) + "行的桌号格式有误,请输入整数");
										return SUCCESS;
									}
								}
							} else if (deskNum.getCellType() == Cell.CELL_TYPE_NUMERIC) {
								long longVal = Math.round(deskNum
										.getNumericCellValue());
								double doubleVal = Math.round(deskNum
										.getNumericCellValue());
								if (Double.parseDouble(longVal + ".0") == doubleVal) {
									if (checkDesk((int) longVal)) {
										request.setAttribute("message", "第" + (rowNum + 1) + "行的" + (int) longVal + "号桌子不存在,请联系管理员");
										return SUCCESS;
									} else if (checkDeskMaxSeat((int) longVal,
											curSeat)) {
										request.setAttribute("message", "第" + (rowNum + 1) + "行的" + (int) longVal + "号桌子已满,请联系管理员添加座位");
										return SUCCESS;
									} else {
										consumer.setDeskNum((short) longVal);
									}
								} else {
									request.setAttribute("message", "第" + (rowNum + 1) + "行的桌号格式有误,请输入整数");
									return SUCCESS;
								}
							}
						}
					}
		               
		                
//		                HSSFCell seatNum = hssfRow.getCell(4);  
//		                if (seatNum == null || seatNum.equals("")) {
//		                	request.setAttribute("message", "第"+(rowNum+1)+"行的座位号没有填写");
//	                		return SUCCESS; 
//		                }
//		                else{
//		                	if(seatNum.getCellType() == Cell.CELL_TYPE_NUMERIC){
//		                		long longVal = Math.round(seatNum.getNumericCellValue());
//			                	double doubleVal = Math.round(seatNum.getNumericCellValue());
//			                	if(Double.parseDouble(longVal + ".0") == doubleVal){
//			                		consumer.setSeatNum((short)longVal);
//			                	}else{
//			                		request.setAttribute("message", "第"+(rowNum+1)+"行的座号格式有误,请输入整数");
//									return SUCCESS;
//			                	}
//		                	}else{
//			                	request.setAttribute("message", "第"+(rowNum+1)+"行的桌号格式有误,请设置为文本格式");
//								return SUCCESS;
//		                	}
//		                }
		                
		                HSSFCell isVIP = hssfRow.getCell(5);  
		                if(isVIP != null){
		                	String str = isVIP.getStringCellValue();
		                	if("VIP".equals(str))
		                		consumer.setIsVIP("1");
		                	else
		                		consumer.setIsVIP("0");
		                }
		                
		                HSSFCell recePerson = hssfRow.getCell(6); 
		                if (recePerson != null) {
		                	consumer.setRecePerson(recePerson.getStringCellValue());
		                }		                	
		                
		                HSSFCell recePhone = hssfRow.getCell(7);
		                if(recePhone != null){
		                	if(recePhone.getCellType() != Cell.CELL_TYPE_BLANK){
		                		if(recePhone.getCellType() == Cell.CELL_TYPE_NUMERIC){		                			
		                			DecimalFormat dataformat = new DecimalFormat("#");
		                			String recPhone = String.valueOf(dataformat.format(recePhone.getNumericCellValue()));
		 							consumer.setRecePhone(recPhone.trim());
		                		}
		                		if(recePhone.getCellType() == Cell.CELL_TYPE_STRING){
		                			consumer.setRecePhone(recePhone.getStringCellValue().trim());
		                		}
			                }
		                }
		                
		                HSSFCell remarks = hssfRow.getCell(8);  
		                if (remarks != null && !remarks.equals("")) 
		                	consumer.setRemarks(remarks.getStringCellValue());
		                
		                consumer.setId(UUID.randomUUID().toString());
		                consumer.setState("0");//新增客户默认状态为0，表示还没到
						consumer.setYear(formatYear.format(new Date()));
		                
		                consumerList.add(consumer);  
		            }
		        }
				
				//插入客户数据
				for(int i = 0; i < consumerList.size(); i++){
					consumer = consumerList.get(i);
					consumerService.add(consumer);
				}
					
			}catch(Exception e){
//						log.error("ContactAction-addContactByUploadFile==>", e);
				System.out.print(e.toString());
				return SUCCESS;
			}
			return SUCCESS;
		}
		
//		public String getFreeDesk(){
//			Map<String, Object> jsonMap = new HashMap<String, Object>();
//	    	try{
//	    		List<Object> deskList = this.consumerService.getFreeDesk();
//	    		jsonMap.put("deskList", JSONArray.fromObject(deskList));
//	    		System.out.print(1213);
//	    	}catch(Exception e){
//	    		
//	    	}
//			return ajaxJsonObject(jsonMap);
//		}
		
		public static String ajaxJsonObject(Map<String, Object> jsonMap) {
			JSONObject jsonObject = JSONObject.fromObject(jsonMap);
			return ajax(jsonObject.toString(), "text/html");
		}
		
		// AJAX输出，返回null
		public static String ajax(String content, String type) {
			try {
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType(type + ";charset=UTF-8");
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				response.getWriter().write(content);
				response.getWriter().flush();
			} catch (IOException e) {
			}
			return null;
		}
		
		public boolean checkDesk(int deskNum){
			try{
				//判断桌号是否存在，若桌子存在则同时查询该桌是否有空位
				Seat seat = consumerService.getSeatByDeskNum((short)deskNum);
				if(seat == null || seat.getId() == null)
					return true;
			}catch(Exception e){
				System.out.println(e);
			}
			return false;
		}
		
		public boolean checkDeskMaxSeat(int deskNum,int curSeat){
			try{
				//判断桌号是否存在，若桌子存在则同时查询该桌是否有空位
				int maxSeat = consumerService.getDeskMaxSeat((short)deskNum);
				if(maxSeat < curSeat)
					return true;
			}catch(Exception e){
				System.out.println(e);
			}
			return false;
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


		public Consumer getConsumer() {
			return consumer;
		}


		public void setConsumer(Consumer consumer) {
			this.consumer = consumer;
		}


		public List<Object> getConsumerReportList() {
			return consumerReportList;
		}


		public void setConsumerReportList(List<Object> consumerReportList) {
			this.consumerReportList = consumerReportList;
		}


		public File getUploadFile() {
			return uploadFile;
		}


		public void setUploadFile(File uploadFile) {
			this.uploadFile = uploadFile;
		}

		

		public List getDeskList() {
			return deskList;
		}


		public void setDeskList(List deskList) {
			this.deskList = deskList;
		}
		

}