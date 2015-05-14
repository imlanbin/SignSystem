<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta http-equiv="content-language" content="zh-CN" />  
	<title>词语列表</title>
	<jsp:include page="../easyui-dwr-js.jsp" /> 	 
	<script type="text/javascript" src='${pageContext.request.contextPath}/dwr/interface/leafletSrv.js'></script>  
	
 </head>
  <body>
  	 <div>
       	药品名称缩写：
       	<input id="pinYin" name="pinYin" type="text" value="" class="input-text" />
       	<input type="button" name="search"  value="查询" onclick="search()"/>
     	</div>
<!-- 数据列表  开始 -->
 	<table id="dg" title="药品说明书列表"  class="easyui-datagrid"   toolbar="#toolbar" pagination=true   rownumbers="true" fitColumns="false" singleSelect="false" collapsible="false"  autoRowHeight="true"    pageList="[10,20,30,40,50]" sortName="commoName" >  
       
        <thead>  
            <tr> 
            	<th data-options="field:'id',checkbox:true" width="100"></th>
		        <th field="commoName" sortable="true"  width="100">药物名称</th>
		        <th field="pinYin" sortable="true"  width="100">药品名称缩写</th>
		        <th field="produceDate" sortable="true"  width="100">生产日期</th>
		        <th field="expiryDate" sortable="true"  width="100">保质期</th>
		        <th field="companyName" sortable="true"  width="200">生产企业</th>
		        <th field="phoneNumber" sortable="true"  width="100">联系电话</th>    
				<th field="state1" width="150" formatter="formatBtn" >操作</th> 
            </tr>  
        </thead>  
    </table>  
    
   
 <!-- 数据列表  结束 --> 
 <!-- 添加数据窗口  开始 -->  
	<div id="leaflet-window" title="说明书窗口" style="width:800px;height:450px;">
		<div style="padding:20px 20px 15px 20px;">
			<form method="post">
				<table >
					<tr>
						<td>药品通用名称：</td>
						<td ><input type="text"  name="commoName"  id="commoName" style="width:250px;"/><input type="hidden" name="id" id="id"/><input value="0" type="hidden" name="state" id="state"/><font color="red">*</font></td>
						<td>首字母大写缩写：</td>
						<td ><input type="text"  name="pinYin"  id="pinYin" style="width:250px;"/><font color="red">*</font></td>
					</tr> 
					<tr>
						<td>药品成份：</td>
						<td ><input type="text"  name="ingredient"  id="ingredient" style="width:250px;"/></td>
						<td>药品性状：</td>
						<td ><input type="text"  name="properties"  id="properties" style="width:250px;"/></td>
					</tr> 
					<tr>
						<td>主治功能：</td>
						<td ><input type="text"  name="functions"  id="functions" style="width:250px;"/><font color="red">*</font></td>
						<td>规格：</td>
						<td ><input type="text"  name="standard"  id="standard" style="width:250px;"/></td>
					</tr> 
					<tr>
						<td>用法用量：</td>
						<td ><input type="text"  name="dosage"  id="dosage" style="width:250px;"/><font color="red">*</font></td>
						<td>不良反应：</td>
						<td ><input type="text"  name="adverseReaction"  id="adverseReaction" style="width:250px;"/><font color="red">*</font></td>
					</tr> 
					<tr>
						<td>禁忌：</td>
						<td ><input type="text"  name="taboo"  id="taboo" style="width:250px;"/><font color="red">*</font></td>
						<td>注意事项：</td>
						<td ><input type="text"  name="attention"  id="attention" style="width:250px;"/><font color="red">*</font></td>
					</tr> 
					<tr>
						<td>贮藏：</td>
						<td ><input type="text"  name="reposit"  id="reposit" style="width:250px;"/></td>
						<td>包装：</td>
						<td ><input type="text"  name="pack"  id="pack" style="width:250px;"/></td>
					</tr>
					<tr>
						<td>生产日期：</td>
						<td ><input type="text"  name="produceDate"  id="produceDate" style="width:250px;"/></td>
						<td>保质期：</td>
						<td ><input type="text"  name="expiryDate"  id="expiryDate" style="width:250px;"/><font color="red">*</font></td>
					</tr> 
					<tr>
						<td>执行标准：</td>
						<td ><input type="text"  name="carriedStandard"  id="carriedStandard" style="width:250px;"/></td>
						<td>批准文号：</td>
						<td ><input type="text"  name="approvalNumber"  id="approvalNumber" style="width:250px;"/></td>
					</tr> 
					<tr>
						<td>生产企业名称：</td>
						<td ><input type="text"  name="companyName"  id="companyName" style="width:250px;"/><font color="red">*</font></td>
						<td>生产地址：</td>
						<td ><input type="text"  name="address"  id="address" style="width:250px;"/></td>
					</tr>  
					<tr>
						<td>邮政编码：</td>
						<td ><input type="text"  name="postalcode"  id="postalcode" style="width:250px;"/></td>
						<td>联系电话：</td>
						<td ><input type="text"  name="phoneNumber"  id="phoneNumber" style="width:250px;"/><font color="red">*</font></td>
					</tr> 
					<tr>
						<td>传真号码：</td>
						<td ><input type="text"  name="faxNumber"  id="faxNumber" style="width:250px;"/></td>
						<td>企业网址：</td>
						<td ><input type="text"  name="website"  id="website" style="width:250px;"/></td>
					</tr> 
				</table>
			</form>
		</div>
		<div style="text-align:center;padding:5px;">
			<a href="javascript:void(0)" onclick="closeWindow()" id="btn-cancel" icon="icon-cancel">取消</a>
		</div>
	</div> 
 <!-- 添加数据窗口  结束 -->  
 	
    
    <script type="text/javascript">
    
    var win;
    var form;
    var strSql="1=1";
    $(function(){
    	//词组窗口
	    $('#btn-cancel').linkbutton();
		win = $('#leaflet-window').window({
			closed:true
		});
		//词组表单
		form = win.find('form');
		$('#dg').datagrid({
			queryParams: {
				str: strSql
			},
			url:leafletSrv.findAllPage,
			onLoadSuccess:changeBtn
		});
		
    }); 
    //格式化操作按钮
    function changeBtn(data){
		$('.opbtn').linkbutton();
    }
    function formatState(val,row){  
            if (val ==0){  
                return '<span style="color:red;">等待</span>';  
            } else {  
                return '<span style="color:green;">完成</span>';  
            }  
    }  
	function formatBtn(val,row){ 
		var r = '<a href="javascript:void(0)"  onclick="detailLeaflet(\''+row.id+'\');"  class="easyui-linkbutton opbtn" iconCls="icon-edit">查看详情</a>'; 
		// alert(r);
	   	 return  r;
	} 
	
	function detailLeaflet(id){
		 leafletSrv.get(id+"",function(data){
		 	win.window('open');
			form.form('load', data);
		});
	}
	
	
	function search(){ 
		var pinYin = $("#pinYin").val();
		if(pinYin!=''&pinYin!=null){
			strSql = strSql + " and pinYin like " + pinYin;
		}
		$('#dg').datagrid({
			url:leafletSrv.findAllPage,
			queryParams: {
				str: strSql
			}
		});
		
		strSql="1=1";
	}
	
	
	function closeWindow(){
		win.window('close');
	}
	
    </script>  
  </body>
</html>
