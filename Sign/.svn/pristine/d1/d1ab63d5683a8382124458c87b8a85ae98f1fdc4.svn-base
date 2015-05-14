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
<!-- 数据列表  开始 -->
 	<table id="dg" title="客户信息列表" data-options="queryParams:{str:'1=1'},url:consumerSrv.findAllPage,onLoadSuccess:changeBtn" class="easyui-datagrid"   toolbar="#toolbar" pagination=true   rownumbers="true" fitColumns="false" singleSelect="false" collapsible="false"  autoRowHeight="true"    pageList="[10,20,30,40,50]" sortName="isVIP"  >  
        <thead>  
            <tr> 
            	<th data-options="field:'id',checkbox:true" width="100"></th>
		        <th field="companyName" sortable="true"  width="140">单位名称</th>
		        <th field="contactPerson" sortable="true"  width="90">联系人</th>
		        <th field="contactPhone" sortable="true"  width="100">联系电话</th>
		        <th field="deskNum" sortable="true"  width="40">桌号</th>
		        <th field="isVIP" sortable="true"  width="50">是否VIP</th>
		        <th field="recePerson" sortable="true"  width="90">接待人</th>
		        <th field="recePhone" sortable="true"  width="100">接待人电话</th> 
		        <th field="remarks" sortable="true"  width="200">备注</th>     
				<th field="state1" width="250" formatter="formatBtn">操作</th> 
            </tr>  
        </thead>  
    </table>  
    
    <div id="toolbar">  
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newConsumer();">添加</a>     
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeConsumer();">删除</a>    
    <!--    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="openWin('${pageContext.request.contextPath}/author.action?action=add')">执行</a>-->  
    </div> 
 <!-- 数据列表  结束 --> 
 <!-- 添加数据窗口  开始 -->  
	<div id="consumer-window" title="添加客户信息窗口" style="width:770px;height:460px;">
		<div>
			<form id="form" method="post">
				<div class="easyui-tabs" style="width:750px;height:370px">  
			        <div title="客户基本信息" style="padding:10px">  
			            <table>
					<tr>
						<td>单位名称：</td>
						<td ><input type="text"  name="companyName"  id="companyName" style="width:250px;"/><input type="hidden" name="id" id="id"/><font color="red">*</font></td>
						<td>联系人：</td>
						<td ><input type="text"  name="contactPerson"  id="contactPerson" style="width:250px;"/><font color="red">*</font></td>
					</tr> 
					<tr>
						<td>联系人电话：</td>
						<td ><input type="text"  name="contactPhone"  id="contactPhone" style="width:250px;"/><font color="red">*</font></td>
						<td>桌号：</td>
						<td ><input type="text"  name="deskNum"  id="deskNum" style="width:250px;"/><font color="red">*</font></td>
					</tr> 
					<tr>
						<td>接待人：</td>
						<td ><input type="text"  name="recePerson"  id="recePerson" style="width:250px;"/><font color="red">*</font></td>
						<td>接待人电话：</td>
						<td ><input type="text"  name="recePhone"  id="recePhone" style="width:250px;"/><font color="red">*</font></td>
					</tr> 
					<tr>
						<td>是否VIP：</td>
						<td >
							<select name="isVIP"  id="isVIP">  
							  <option value ="1">是</option>  
							  <option value ="0">否</option>  
							</select>  
						</td>
						<td>状态：</td>
						<td >
						    <select name="state"  id="state">  
							  <option value ="1">可用</option>  
							  <option value ="0">禁用</option>  
							</select>  
						</td>
					</tr> 
					<tr>
						<td>备注：</td>
						<td ><textarea cols="40" rows="8" name="remarks"  id="remarks"></textarea></td>
					</tr> 
				</table>
			        </div>  
			        
			    </div>  	 	
			</form>
		</div>
		<div style="text-align:center;padding:0px;">
			<a href="javascript:void(0)"   id="btn-save" icon="icon-save">保存</a>
			<a href="javascript:void(0)" onclick="closeWindow()" id="btn-cancel" icon="icon-cancel">取消</a>
		</div>
	</div> 
	
    <script type="text/javascript">
    
    var win;
    var form;
    var win1;
    var form1;
    var strSql="1=1";
    $(function(){
    	//词组窗口
	    $('#btn-save,#btn-cancel,#btn-save1,#btn-cancel1').linkbutton();
		win = $('#consumer-window').window({
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
		var r = '<a href="javascript:void(0)"  onclick="editConsumer(\''+row.id+'\');"  class="easyui-linkbutton opbtn" iconCls="icon-edit">编辑</a>'
		         +'<a href="javascript:void(0)"  onclick="editState1(\''+row.id+'\');"  class="easyui-linkbutton opbtn" iconCls="icon-ok">签到</a>'
		         +'<a href="javascript:void(0)"  onclick="editState0(\''+row.id+'\');"  class="easyui-linkbutton opbtn" iconCls="icon-cancel">不到</a>'; 
		// alert(r);
	   	 return  r;
	} 
	
	function newConsumer(){
	  uuid.getID(function(data){
		 	form.form('clear');
		 	$('#id').val(data);
		 	$("#isVIP").val(0);
		 	$("#state").val(1);
		 	win.window('open');
		 	var id = $("#id").val();
		 	$('#btn-save').unbind("click");
		  	$("#btn-save").bind('click',saveConsumer);
		  	});
	}
	function editConsumer(id){
		 consumerSrv.get(id+"",function(data){
		 	win.window('open');
			if(id!=''&id!=null){
			strSql = " id = '" + id + "'";
			}
			$('#dg1').datagrid({
			url:consumerSrv.findAllPage,
			queryParams: {
				str: strSql
			}
		})
			form.form('load', data);
			$('#btn-save').unbind("click");
		 	$("#btn-save").bind('click',updateConsumer);
		});
	}
	
	function updateConsumer(){ 
		  var consumer = form.serializeObject();
		  consumerSrv.update(consumer,function(data){
		  		$('#dg').datagrid('reload');  
				win.window('close'); 
		  });  
	}
	
	function saveConsumer(){ 
			var companyName = $('#companyName').val();
			var contactPerson = $('#contactPerson').val();
			var contactPhone = $('#contactPhone').val();
			var deskNum = $('#deskNum').val();
			var recePerson = $('#recePerson').val();
			var recePhone = $('#recePhone').val();
			
			if(companyName == '' || companyName == null){
				alert("单位名称不能为空！");
				$('#companyName').focus();
				return;
			}
			if(contactPerson == '' || contactPerson == null){
				alert("联系人不能为空！");
				$('#contactPerson').focus();
				return;
			}
			if(contactPhone == '' || contactPhone == null){
				alert("联系电话不能为空！");
				$('#contactPhone').focus();
				return;
			}
			if(deskNum == '' || deskNum == null){
				alert("桌号不能为空！");
				$('#deskNum').focus();
				return;
			}
			if(recePerson == '' || recePerson == null){
				alert("接待人不能为空！");
				$('#recePerson').focus();
				return;
			}
			if(recePhone == '' || recePhone == null){
				alert("接待人电话不能为空！");
				$('#phoneNumber').focus();
				return;
			}
			
			 var consumer = form.serializeObject();
		  consumerSrv.add(consumer,function(data){
		  		$('#dg').datagrid('reload');  
				win.window('close'); 
		  });  
	}
	
	function removeConsumer(){ 
		var row = $('#dg').datagrid('getSelections');
		if (row.length>0){ 
		 $.messager.confirm('删除提示','确定删除这些客户信息吗?',function(r){  
            if (r){ 
					consumerSrv.del(row,function(data){	 
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
