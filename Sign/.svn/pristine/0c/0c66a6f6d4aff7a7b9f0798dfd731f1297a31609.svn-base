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
 	<table id="dg" title="词语列表" data-options="queryParams:{str:'1=1'},url:atermSrv.findAllPage,onLoadSuccess:changeBtn" class="easyui-datagrid"   toolbar="#toolbar" pagination=true   rownumbers="true" fitColumns="false" singleSelect="false" collapsible="false"  autoRowHeight="true"    pageList="[10,20,30,40,50]" sortName="terms"  >  
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
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newTerm();">添加</a>     
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeTerm();">删除</a>    
    <!--    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="openWin('${pageContext.request.contextPath}/author.action?action=add')">执行</a>-->  
    </div> 
 <!-- 数据列表  结束 --> 
 <!-- 添加数据窗口  开始 -->  
	<div id="term-window" title="词语窗口" style="width:400px;height:200px;">
		<div style="padding:20px 20px 15px 20px;">
			<form method="post">
				<table>
					<tr>
						<td>词语：</td>
						<td ><input type="text"  name="terms"  id="terms" style="width:250px;"/><input type="hidden" name="atid" id="atid"/><input value="0" type="hidden" name="state" id="state"/></td>
					</tr>
					 
				</table>
			</form>
		</div>
		<div style="text-align:center;padding:5px;">
			<a href="javascript:void(0)"   id="btn-save" icon="icon-save">保存</a>
			<a href="javascript:void(0)" onclick="closeWindow()" id="btn-cancel" icon="icon-cancel">取消</a>
		</div>
	</div> 
 <!-- 添加数据窗口  结束 -->  
 
 	
    
    <script type="text/javascript">
    
    var win;
    var form;
    
    $(function(){
    	//词组窗口
	    $('#btn-save,#btn-cancel').linkbutton();
		win = $('#term-window').window({
			closed:true
		});
		//词组表单
		form = win.find('form');
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
		var r = '<a href="javascript:void(0)"  onclick="editTerm(\''+row.atid+'\');"  class="easyui-linkbutton opbtn" iconCls="icon-edit">编辑</a>'; 
		// alert(r);
	   	 return  r;
	} 
	function newTerm(){
		 uuid.getID(function(data){
		 	form.form('clear');
		 	$('#atid').val(data);
		 	$("#state").val(0);
		 	win.window('open');
		 	 $('#btn-save').unbind("click");
		  	$("#btn-save").bind('click',saveTerm);
		 	
		 });
	}
	function editTerm(id){
		 atermSrv.get(id+"",function(data){
		 	win.window('open');
			form.form('load', data);
			 $('#btn-save').unbind("click");
		 	$("#btn-save").bind('click',updateTerm);
		});
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
				win.window('close'); 
		  });  
	}
	function saveTerm(){ 
		var termm = {
		  	terms:$("#terms").val(),
		 	state:$("#state").val(),
		 	atid:$("#atid").val()
		 };
		  atermSrv.add(termm,function(data){
		  		$('#dg').datagrid('reload');  
				win.window('close'); 
		  });  
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
	function closeWindow(){
		win.window('close');
	}
	
    </script>  
  </body>
</html>
