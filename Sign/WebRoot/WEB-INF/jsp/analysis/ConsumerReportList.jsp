<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta http-equiv="content-language" content="zh-CN" />  
	<title>客户信息列表</title>
	<jsp:include page="../easyui-dwr-js.jsp" /> 	 
	<script type="text/javascript" src='${pageContext.request.contextPath}/dwr/interface/consumerSrv.js'></script>
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
	       	<a  id="export" name="export" class="easyui-linkbutton" iconCls="icon-save" plain="true">导出</a>
	  </div>
<!-- 数据列表  开始 -->
 	<table id="dg" title="客户信息列表" data-options="queryParams:{str:'1=1'},url:consumerSrv.findAllPage" class="easyui-datagrid"   toolbar="#toolbar" pagination=true   rownumbers="true" fitColumns="false" singleSelect="false" collapsible="false"  autoRowHeight="true"    pageList="[10,20,30,40,50]" sortName="deskNum"  >  
        <thead>  
            <tr> 
            	<th data-options="field:'id',checkbox:true" width="100"></th>
		        <th field="companyName" sortable="true"  width="240">单位名称</th>
		        <th field="contactPerson" sortable="true"  width="90">联系人</th>
		        <th field="contactPhone" sortable="true"  width="100">联系电话</th>
		        <th field="deskNum" sortable="true"  width="40">桌号</th>
		        <th field="isVIP" sortable="true"  width="50" formatter="formatIsVip">是否VIP</th>
		        <th field="recePerson" sortable="true"  width="90">接待人</th>
		        <th field="recePhone" sortable="true"  width="100">接待人电话</th> 
		        <th field="remarks" sortable="true"  width="350">备注</th>     
            </tr>  
        </thead>  
    </table>  
   
    <script type="text/javascript">
    var strSql="1=1";
	function search(){
		var year = $("#year").val(); 

		if(year!=''&year!=null){
			strSql = strSql + " and year = " + year;
		}
		$('#dg').datagrid({
			url:consumerSrv.findAllPage,
			queryParams: {
				str: strSql
			}
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
    
     function formatIsVip(val,row){  
            if (val ==1){  
                return '<span style="color:red;">VIP</span>';  
            } else {  
                return '<span style="color:green;"></span>';  
            }  
    }  
    
    $(function(){
		$("#export").click(function(){
			 var year = $("#year").val(); 
			 if(year == ""){
			 	alert("请输入要导出的年份！");
			 	return;
			 }
//			location.href="${pageContext.request.contextPath}/admin/leaflet/getConsumerListExport.action?year=" + year;
   		 	$.ajax({
        		type:'post',        
       			url:'${pageContext.request.contextPath}/admin/leaflet/getConsumerListExport.action?year=' + year,    
        		data:{},    
        		cache:false,
        		async:false,
//        		dataType:'json',
				success:function(){
					 location.href="${pageContext.request.contextPath}/admin/leaflet/getConsumerListExport.action?year=" + year;
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
