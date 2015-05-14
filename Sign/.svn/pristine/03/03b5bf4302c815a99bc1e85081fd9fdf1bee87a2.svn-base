<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta http-equiv="content-language" content="zh-CN" />  
	<title>词语列表</title>
	<jsp:include page="../easyui-dwr-js.jsp" /> 	 
	<script type="text/javascript" src='${pageContext.request.contextPath}/dwr/interface/atermSrv.js'></script>  
	<script type="text/javascript" src='${pageContext.request.contextPath}/dwr/interface/uuid.js'></script>
	
 </head>
  <body>
<!-- 数据列表  开始 -->
 	<table id="dg" title="词语列表" data-options="queryParams:{str:'1=1'},url:atermSrv.findAllPage" class="easyui-datagrid"   toolbar="#toolbar" pagination=true   rownumbers="true" fitColumns="false" singleSelect="false" collapsible="true"  autoRowHeight="true"    pageList="[10,20,30,40,50]" sortName="terms"  >  
        <thead>  
            <tr> 
            	<th data-options="field:'atid',checkbox:true" width="100"></th>
		        <th field="terms" sortable="true"  width="200">词语</th> 
				<th field="state" width="100"  formatter="formatState">状态</th> 
				<th field="state1" width="100" formatter="formatBtn">操作</th> 
            </tr>  
        </thead>  
    </table>  
    
    <div id="toolbar">  
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newTerm()"">添加</a>     
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="editTerm()"">修改</a>  
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeTerm()">删除</a>   
    <!--    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="openWin('${pageContext.request.contextPath}/author.action?action=add')">执行</a>-->  
    </div> 
 <!-- 数据列表  结束 --> 
 
 <div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px" closed="true" buttons="#dlg-buttons" title="new users">  
        <form id="fm" method="post" novalidate>  
            <div class="fitem">  
                <label>语词:</label>  
                <input type="text"  name="terms"  id="terms" style="width:250px;"/><input type="hidden" name="atid" id="atid"/><input value="0" type="hidden" name="state" id="state"/>  
            </div>   
        </form>  
  
    
    <div id="dlg-buttons">  
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" id="btn-save">Save</a>  
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>  
    </div>  
</div> 

 <!-- 添加数据窗口  结束 -->  
 	
 	<script type="text/javascript">  
        var url;  
        function newTerm(){  
            uuid.getID(function(data){
            $('#dlg').dialog('open').dialog('setTitle','New User');  
            $('#fm').form('clear'); 
            $('#atid').val(data);
		 	$("#state").val(0);
            $('#btn-save').unbind("click");
		  	$("#btn-save").bind('click',saveTerm);  
		  	 });
        }  
        function editTerm(){  
            var row = $('#dg').datagrid('getSelected');  
            if (row){  
                $('#dlg').dialog('open').dialog('setTitle','Edit User');  
                $('#fm').form('load',row);  
  				$('#btn-save').unbind("click");
		  	    $("#btn-save").bind('click',updateTerm);  
  			
            }  
        }  
        
        function updateTerm(){ 
		  var termm = {
		  	terms:$("#terms").val(),
		 	state:$("#state").val(),
		 	atid:$("#atid").val()
		 }; 
		  alert(termm.terms);
		  atermSrv.update(termm,function(data){
		  		$('#dg').datagrid('reload');  
				$('#dlg').dialog('close');  
		  });  
	}
	
        function saveTerm(){  
          var termm = {
		  	terms:$("#terms").val(),
		 	state:$("#state").val(),
		 	atid:$("#atid").val()
		 }; 
		  alert(termm.terms);
		  atermSrv.add(termm,function(data){
		  		$('#dg').datagrid('reload');  
				$('#dlg').dialog('close'); 
		  });  
        }  
        
         function formatState(val,row){  
            if (val ==0){  
                return '<span style="color:red;">等待</span>';  
            } else {  
                return '<span style="color:green;">完成</span>';  
            }  
    }  
	
	function formatBtn(val,row){ 
		var r = '<a href="javascript:void(0)"  onclick="editTerm(\''+row.atid+'\');"  class="easyui-linkbutton opbtn" iconCls="icon-edit">编辑</a>'; 
		// alert(r);
	   	 return  r;
	} 
	
            
        function removeTerm(){
        alert("123"); 
		var row = $('#dg').datagrid('getSelections'); 
		alert(row.length); 
		if (row.length>0){ 
		 $.messager.confirm('删除提示','确定删除这些词语吗?',function(r){  
            if (r){ 
					atermSrv.del(row,function(data){	 
							$('#dg').datagrid('reload');
			  		});  
         	}
         }); 

		}else {  
	         $.messager.show({  
	             title:'警告',   
	             msg:'请先选择删除的数据。'  
	         });  
     	} 
	}
 
        
        
    </script>  
    <style type="text/css">  
        #fm{  
            margin:0;  
            padding:10px 30px;  
        }  
       
        .fitem{  
            margin-bottom:5px;  
        }  
        .fitem label{  
            display:inline-block;  
            width:80px;  
        }  
    </style>  
 	
 	
    
     
  </body>
</html>
