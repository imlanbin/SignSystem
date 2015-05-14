<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta http-equiv="content-language" content="zh-CN" />  
	<title>日志列表</title>
	<jsp:include page="../easyui-dwr-js.jsp" /> 	 
	<script type="text/javascript" src='${pageContext.request.contextPath}/dwr/interface/logSrv.js'></script>  
	
 </head>
  <body>
<!-- 数据列表  开始 -->
 	<table id="dg" title="用户日志列表" data-options="queryParams:{str:'1=1'},url:logSrv.findAllPage" class="easyui-datagrid"   toolbar="#toolbar" pagination=true   rownumbers="true" fitColumns="false" singleSelect="false" collapsible="false"  autoRowHeight="true"    pageList="[10,20,30,40,50]" sortName="loginTime" sortOrder="desc" >  
        <thead>  
            <tr> 
            	<th data-options="field:'id',checkbox:true" width="100"></th>
		        <th field="userName"   width="200">用户名</th>
		        <th field="loginTime" sortable="true"  width="200">登录时间</th>   
				<th field="loginIp"  width="200" >登录IP</th> 
            </tr>  
        </thead>  
    </table>  
    
    <div id="toolbar">     
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeLog();">删除</a>    
    <!--    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="openWin('${pageContext.request.contextPath}/author.action?action=add')">执行</a>-->  
    </div> 
	</div> 
 <!-- 添加数据窗口  结束 -->  
 
 	
    
    <script type="text/javascript">
    
    
    
	
	function removeLog(){ 
		var row = $('#dg').datagrid('getSelections');
		if (row.length>0){ 
		 $.messager.confirm('删除提示','确定删除这些日志吗?',function(r){  
            if (r){ 
					logSrv.del(row,function(data){	 
							$('#dg').datagrid('reload');
			  		});  
         	}
         }); 

		}else {  
	         $.messager.show({  
	             title:'警告',   
	             msg:'请先选择要删除的数据。'  
	         });  
     	} 
	}
	
	
    </script>  
  </body>
</html>
