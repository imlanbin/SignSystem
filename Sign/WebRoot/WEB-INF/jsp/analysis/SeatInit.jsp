<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta http-equiv="content-language" content="zh-CN" />  
	<title>客户信息列表</title>
	<jsp:include page="../easyui-dwr-js.jsp" /> 	 
	<script type="text/javascript" src='${pageContext.request.contextPath}/dwr/interface/seatSrv.js'></script>
	<script type="text/javascript" src='${pageContext.request.contextPath}/dwr/interface/uuid.js'></script>
	<script type="text/javascript" src='${pageContext.request.contextPath}/dwr/interface/picSrv.js'></script>   
	<script type="text/javascript" src='${pageContext.request.contextPath}/ckfinder/ckfinder.js'></script>
	<script type="text/javascript" >var path="${pageContext.request.contextPath}";</script>
	<script type="text/javascript" src='${pageContext.request.contextPath}/static/js/public.js'> </script>
	<style type="text/css">
		.tc_box {width: 400px;height: 200px;position: absolute;z-index: 2000;display: none;background: #FFE;top: 50%;left:50%;margin-top:-100px;margin-left:-200px}	
		.sp_box {width: 100%;position: absolute;z-index: 1000;background: #000;top: 0;left: 0;display: none;}
		progress {width: 200px;margin-left:100px;margin-top:70px}
	</style>
 </head>
  <body>

 <!-- 数据列表  结束 --> 
 <!-- 添加数据窗口  开始 -->  
	
		<div>
	       	年份：
	       	<input id="year" name="year" type="text" value="" class="input-text" maxlength="4"/>
	       	桌数：
	       	<input id="desk" name="desk" type="text" value="" class="input-text" maxlength="3"/>
	       	座位数：
	       	<input id="seat" name="seat" type="text" value=""  style="width:30px" maxlength="2"/>
	       	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="initSeat();">初始化</a>
	  </div>
	  <!-- 数据列表  开始 -->
 	<table id="dg" title="历年座位信息列表" data-options="queryParams:{str:'1=1'},url:seatSrv.findDistinctYear" class="easyui-datagrid"    pagination=true   rownumbers="true" fitColumns="false" singleSelect="false" collapsible="false"  autoRowHeight="true"    sortName="year,sumDesk,sumSeat"     >  
        <thead>  
            <tr> 
            	<th field="year" sortable="true"  width="100" sortable="true">年份</th>
		        <th field="sumDesk" sortable="true"  width="100" sortable="true">总桌数</th>
		        <th field="sumSeat" sortable="true"  width="100" sortable="true">总座位数</th>
            </tr>  
        </thead>  
    </table>  
    
    <div class="tc_box">
		<progress width:="160px" height:="20px"></progress>
		<br>
		<br>
		<p align="center">正在初始化中，请稍后！请勿刷新或关闭本页！</p>
	</div>
	<div class="sp_box"></div>
    
 <!--   <div id="toolbar">  
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeSeat();">删除</a>
    </div>  -->
 <!-- 数据列表  结束 -->
	  
    <script type="text/javascript">
	function initSeat(){ 
			var year = $('#year').val();
			var desk = $('#desk').val();
			var seat = $('#seat').val();
			
			if(year == '' || year == null){
				alert("年份不能为空！");
//				$.messager.alert('系统提示', '年份不能为空！', 'warning');
				$('#year').focus();
				return;
			}
			if(desk == '' || desk == null){
				alert("桌数不能为空！");
//				$.messager.alert('系统提示', '桌数不能为空！', 'warning');
				$('#desk').focus();
				return;
			}
			if(seat == '' || seat == null){
				alert("座位数不能为空！");
//				$.messager.alert('系统提示', '座位数不能为空！', 'warning');
				$('#seat').focus();
				return;
			}
			if(isNaN(year)){
				alert("不合法的年份！");
//				$.messager.alert('系统提示', '不合法的年份！', 'warning');
				$('#year').focus();
				return;
			}
			if(isNaN(desk)){
				alert("不合法的桌数！");
//				$.messager.alert('系统提示', '不合法的桌数！', 'warning');
				$('#year').focus();
				return;
			}
			if(isNaN(seat)){
				alert("不合法的座位数！");
//				$.messager.alert('系统提示', '不合法的座位数！', 'warning');
				$('#year').focus();
				return;
			}
			$(".tc_box").show();
     		var sp_height = $(document).height();
     		$(".sp_box").css({"opacity":"0.5","height":sp_height});
     		$(".sp_box").show();
		    seatSrv.seatInit(year,desk,seat,function(data){
		    	if(data == null){
		    		$.messager.alert('系统提示', '初始化成功！', 'info');
		    		$('#dg').datagrid('reload');
		    	}else{
		    		$.messager.alert('系统提示', '该年份之前已经被初始化1次，无法再次初始化！', 'error');
		    	}		 
		    	$(".tc_box").hide();
		    	$(".sp_box").hide();  
		  });  
//		    $("#myForm").submit();
//   			$('#dg').datagrid('reload');
	}
	$(function showMessage(){
		var message = '<%=request.getAttribute("message")%>';
		if(message && message != null && message != 'null' && message != "")
			 $.messager.show({  
	             title:'警告',   
	             msg:message  
	         }); 
	});
    </script>  
  </body>
</html>
