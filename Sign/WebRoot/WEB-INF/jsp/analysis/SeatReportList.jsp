<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta http-equiv="content-language" content="zh-CN" />  
	<title>排位信息列表</title>
	<jsp:include page="../easyui-dwr-js.jsp" /> 	 
	<script type="text/javascript" src='${pageContext.request.contextPath}/dwr/interface/seatReportSrv.js'></script>
	<script type="text/javascript" src='${pageContext.request.contextPath}/dwr/interface/uuid.js'></script>
	<script type="text/javascript" src='${pageContext.request.contextPath}/dwr/interface/picSrv.js'></script>   
	<script type="text/javascript" src='${pageContext.request.contextPath}/ckfinder/ckfinder.js'></script>
	<script type="text/javascript" >var path="${pageContext.request.contextPath}";</script>
	<script type="text/javascript" src='${pageContext.request.contextPath}/static/js/public.js'> </script>
 </head>
  <body>
  <!-- 搜索条件  开始 -->
	  <div>
	  		年份：
	       	<input id="year" name="year" type="text" value="" class="input-text"  onkeydown="entersearch()"/>
	       	桌号：
	       	<input id="deskNum" name="deskNum" type="text" value="" class="input-text"  onkeydown="entersearch()"/>
	       	<a  id="export" name="export" class="easyui-linkbutton" iconCls="icon-save" plain="true" >导出</a>
	  </div>
<!-- 数据列表  开始 -->
 	<table id="dg" title="排位信息列表" data-options="queryParams:{str:'1=1'},url:seatReportSrv.findAllPage" class="easyui-datagrid"   toolbar="#toolbar" pagination=true   rownumbers="true" fitColumns="false" singleSelect="false" collapsible="false"  autoRowHeight="true"    pageList="[10,20,30,40,50]" sortName="year,deskNum"  >  
        <thead>  
            <tr> 
            	<th data-options="field:'id',checkbox:true" width="100"></th>
            	<th field="year" sortable="true"  width="100">年份</th>
		        <th field="deskNum" sortable="true"  width="100">桌号</th>
		        <th field="consumers" sortable="true"  width="300">客户</th>
		        <th field="number" sortable="true"  width="100" >人数</th>
            </tr>  
        </thead>  
    </table>  
  
    <script type="text/javascript">

    var strSql="1=1";
	function search(){
		var year = $("#year").val(); 
		var deskNum = $("#deskNum").val();
		
		seatReportSrv.get(year+"",function(data){
			if(data.message == "未初始化该年份座位信息！"){
				alert(data.message);
				return;
			}
			if(year!=''&year!=null){
				strSql = strSql + " and year = " + year;
			}
			if(deskNum!=''&deskNum!=null){
				strSql = strSql + " and deskNum = " + deskNum;
			}
			$('#dg').datagrid({
				url:seatReportSrv.findAllPage,
				queryParams: {
					str: strSql
				}
			});
		});
		strSql="1=1";
	}
	 	
     function entersearch(){
         //alert(dd);
        var event = window.event || arguments.callee.caller.arguments[0];
        if (event.keyCode == 13)
        {
            search();
        }
    }
    
    $(function(){
		$("#export").click(function(){
			 var year = $("#year").val(); 
			 if(year == ""){
			 	alert("请输入要导出的年份！");
			 	return;
			 }
//			location.href="${pageContext.request.contextPath}/admin/leaflet/getSeatReportListExport.action?year=" + year;
			$.ajax({
        		type:'post',        
       			url:'${pageContext.request.contextPath}/admin/leaflet/getSeatReportListExport.action?year=' + year,    
        		data:{},    
        		cache:false,
        		async:false,
//        		dataType:'json',
				success:function(){
					 location.href="${pageContext.request.contextPath}/admin/leaflet/getSeatReportListExport.action?year=" + year;
				},
        		error:function(){
//        			 alert(year+'年没有可导出的数据！');
        			 $.messager.alert('错误',year+'年没有可导出的数据！','error');
        		}
   		 	});
		});
	});	
    </script>  
  </body>
</html>
