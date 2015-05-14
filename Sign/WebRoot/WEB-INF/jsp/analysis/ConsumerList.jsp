<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/uploadChecker.js"></script>
	<style type="text/css">
		.tc_box {width: 400px;height: 200px;position: absolute;z-index: 2000;display: none;background: #FFE;top: 50%;left:50%;margin-top:-100px;margin-left:-200px}	
		.sp_box {width: 100%;position: absolute;z-index: 1000;background: #000;top: 0;left: 0;display: none;}
		progress {width: 200px;margin-left:100px;margin-top:70px}
	</style>
</head>
  <body>
  <!-- 搜索条件  开始 -->
	  <div>
	       	单位名称缩写：
	       	<input id="CNJ" name="CNJ" type="text" value="" class="input-text" oninput="search ()" onpropertychange="search ()" onkeydown="entersearch()"/>
	       	联系人缩写：
	       	<input id="CPJ" name="CPJ" type="text" value="" class="input-text" oninput="search ()" onpropertychange="search ()" onkeydown="entersearch()"/>
	       	桌号：
	       	<input id="deskNumS" name="deskNumS" type="text" value=""  style="width:30px" oninput="search ()" onpropertychange="search ()" onkeydown="entersearch()"/>
	       	状态：
	       	 <select name="stateS"  id="stateS"  onchange="search()">  
				  <option value ="0" >未到</option>  
				  <option value ="1" >已到</option>
				  <option value ="2" >不到</option>  
			</select>
			<form id="myForm" method="post"  enctype="multipart/form-data" action="${pageContext.request.contextPath}/admin/leaflet/importFile.action">
				<table class="detail" cellspacing="0" width="30%">
		       		<tr>
		       			<td class="fieldname"><a id="import" name="import" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="importExcel();">导入</a></td>
		       			<td class="fieldvalue">
		       				<input type="file" name="uploadFile" id="uploadFile"/>
		       				<span id="rvcd_vv" class="validateText"></span>   
		       			</td>
		       		</tr>
	       		</table>
	       	</form>
	       	<!--<input type="button" name="search"  value="查询" onclick="search()"/>-->
	       	
	  </div>
<!-- 数据列表  开始 -->
 	<table id="dg" title="客户信息列表" data-options="queryParams:{str:'1=1 and state=0'},url:consumerSrv.findAllPage,onLoadSuccess:changeBtn" class="easyui-datagrid"   toolbar="#toolbar" pagination=true   rownumbers="true" fitColumns="false" singleSelect="false" collapsible="false"  autoRowHeight="true"    pageList="[10,20,30,40,50]" sortName="deskNum"  >  
        <thead>  
            <tr> 
            	<th data-options="field:'id',checkbox:true" width="100"></th>
		        <th field="companyName" sortable="true"  width="250">单位名称</th>
		        <th field="contactPerson" sortable="true"  width="90">联系人</th>
		        <th field="contactPhone" sortable="true"  width="100">联系电话</th>
		        <th field="deskNum" sortable="true"  width="40">桌号</th>
		        <th field="isVIP" sortable="true"  width="50" formatter="formatIsVip">是否VIP</th>
		        <th field="recePerson" sortable="true"  width="90">接待人</th>
		        <th field="recePhone" sortable="true"  width="100">接待人电话</th> 
		        <th field="remarks" sortable="true"  width="200">备注</th>     
				<th field="state1" width="100" formatter="formatBtn">操作</th> 
            </tr>  
        </thead>  
    </table>  
    
    <div id="toolbar">  
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newConsumer();">添加</a>     
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeConsumer();">删除</a>
       <!-- <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="changeStateP1();">签到</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="changeStateP2();">不到</a>      
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="openWin('${pageContext.request.contextPath}/author.action?action=add')">执行</a>-->  
    </div> 
 <!-- 数据列表  结束 --> 
 <!-- 添加数据窗口  开始 -->  
	<div id="consumer-window" title="客户信息窗口" style="width:770px;height:460px;">
		<div>
			<form id="form" method="post">
				<div class="easyui-tabs" style="width:750px;height:370px">  
			        <div title="客户基本信息" style="padding:10px">  
			            <table>
					<tr>
						<td>单位名称：</td>
						<td ><input type="text"  name="companyName"  id="companyName" style="width:250px;"/><input type="hidden" name="id" id="id"/><input id="userName" value="${sessionScope.userinfo.userName}"  type="hidden"/></td>
						<td>联系人：</td>
						<td ><input type="text"  name="contactPerson"  id="contactPerson" style="width:250px;"/><font color="red">*</font></td>
					</tr> 
					<tr>
						<td>联系人电话：</td>
						<td ><input type="text"  name="contactPhone"  id="contactPhone" style="width:250px;"/></td>
						<td>桌号：</td>
						<td >
							<select name="deskNum"  id="deskNum"> 
							 
						    </select> 
						    <input type="hidden"  name="deskNum1"  id="deskNum1" style="width:250px;"/>
						    <input type="hidden"  name="seatNum"  id="seatNum" style="width:250px;"/>		 
							<font color="red">*</font>
						</td>
					</tr> 
					<tr>
						<td>接待人：</td>
						<td ><input type="text"  name="recePerson"  id="recePerson" style="width:250px;"/></td>
						<td>接待人电话：</td>
						<td ><input type="text"  name="recePhone"  id="recePhone" style="width:250px;"/></td>
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
							  <option value ="0">未到</option>  
							  <option value ="1">已到</option>
							  <option value ="2">不到</option>  
							</select>  
						</td>
					</tr> 
					<tr>
						<td>备注：</td>
						<td colspan="3"><textarea cols="90" rows="8" name="remarks"  id="remarks"></textarea></td>
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
	<div class="tc_box">
		<progress width:="160px" height:="20px"></progress>
		<br>
		<br>
		<p align="center">正在导入中，请稍后！请勿刷新或关闭本页！</p>
	</div>
	<div class="sp_box"></div>

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
    
    function formatIsVip(val,row){  
            if (val == 1){  
                return '<span style="color:red;">VIP</span>';  
            } else {  
                return '<span style="color:green;"></span>';  
            }  
    }  
	function formatBtn(val,row){ 
		var r = '<a href="javascript:void(0)"  onclick="editConsumer(\''+row.id+'\');"  class="easyui-linkbutton opbtn" iconCls="icon-edit">编辑</a>';
		         //+'<a href="javascript:void(0)"  onclick="changeState1(\''+row.id+'\');"  class="easyui-linkbutton opbtn" iconCls="icon-ok">签到</a>'
		         //+'<a href="javascript:void(0)"  onclick="changeState2(\''+row.id+'\');"  class="easyui-linkbutton opbtn" iconCls="icon-cancel">不到</a>'; 
		// alert(r);
	   	 return  r;
	} 
	
	function newConsumer(){
	  uuid.getID(function(data){
		 	form.form('clear');
		 	$('#id').val(data);
		 	$("#isVIP").val(0);
		 	$("#state").val("0");
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
			$('#deskNum1').val(data.deskNum);
			$('#seatNum').val(data.seatNum);
			$('#btn-save').unbind("click");
		 	$("#btn-save").bind('click',updateConsumer);
		});
	}
	
	function updateConsumer(){ 
		  var consumer = form.serializeObject();
		  consumer.modifyUser = $('#userName').val();
		  consumer.year = new Date().getFullYear();
		  var deskNum1 = $('#deskNum1').val();
		  var seatNum = $('#seatNum').val();
		  consumerSrv.update(consumer,deskNum1,seatNum,function(data){
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
			
//			if(companyName == '' || companyName == null){
//				alert("单位名称不能为空！");
//				$('#companyName').focus();
//				return;
//			}
			if(contactPerson == '' || contactPerson == null){
				alert("联系人不能为空！");
				$('#contactPerson').focus();
				return;
			}
//			if(contactPhone == '' || contactPhone == null){
//				alert("联系电话不能为空！");
//				$('#contactPhone').focus();
//				return;
//			}
			if((deskNum == '' || deskNum == null) && deskNum != -1){
				alert("桌号不能为空！");
				$('#deskNum').focus();
				return;
			}
//			if(recePerson == '' || recePerson == null){
//				alert("接待人不能为空！");
//				$('#recePerson').focus();
//				return;
//			}
//			if(recePhone == '' || recePhone == null){
//				alert("接待人电话不能为空！");
//				$('#recePhone').focus();
//				return;
//			}
			
			 var consumer = form.serializeObject();
			 consumer.year = new Date().getFullYear();
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
	
		function changeState1(id){
		 consumerSrv.changeState1(id+"",function(data){
		 $('#dg').datagrid('reload');
		});
	}
	
	function changeState2(id){
		 consumerSrv.changeState2(id+"",function(data){
		 $('#dg').datagrid('reload');
		});
	}
	
	function changeStateP1(){ 
		var row = $('#dg').datagrid('getSelections');
		if (row.length>1){ 
		 $.messager.confirm('签到提示','确定签到这些客户吗?',function(r){  
            if (r){ 
					consumerSrv.changeStateP1(row,function(data){	 
							$('#dg').datagrid('reload');
			  		});  
         	}
         }); 

		}else {  
	         $.messager.show({  
	             title:'警告',   
	             msg:'请先选择客户数据。'  
	         });  
     	} 
	}
	
	function changeStateP2(){ 
		var row = $('#dg').datagrid('getSelections');
		if (row.length>0){ 
		 $.messager.confirm('不到提示','确定这些客户不到吗?',function(r){  
            if (r){ 
					consumerSrv.changeStateP2(row,function(data){	 
							$('#dg').datagrid('reload');
			  		});  
         	}
         }); 

		}else {  
	         $.messager.show({  
	             title:'警告',   
	             msg:'请先选择客户数据。'  
	         });  
     	} 
	}
	
	function search(){ 
        var datagrid_rows = $('#dg').datagrid("getRows");
        if (datagrid_rows.lenth == 0) {
            $.messager.alert('提示', "查询结果区域没有数据可导出", 'info');
        } else {
            var columns = $('#dg').datagrid("options").columns;
            $.post("/Export/ExportData", { data: JSON.stringify(datagrid_rows), columns: JSON.stringify(columns) });
        }
   
		
		var CNJ = $("#CNJ").val();
		var CPJ = $("#CPJ").val();
		var deskNum = $("#deskNumS").val();
		var state = $("#stateS").val();
		if(CNJ!=''&CNJ!=null){
			strSql = strSql + " and CNJ like '%" + CNJ +"%'";
		}
		if(CPJ!=''&CPJ!=null){
			strSql = strSql + " and CPJ like '%" + CPJ + "%'";
		}if(deskNum!=''&deskNum!=null){
			strSql = strSql + " and deskNum = " + deskNum;
		}if(state!=''&state!=null){
			strSql = strSql + " and state = '" + state + "'";
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
    
	function closeWindow(){
		win.window('close');
	}
	
	function importExcel(){
		var file = $("#uploadFile").val();
     	if(check(file)){
     		$(".tc_box").show();
     		var sp_height = $(document).height();
     		$(".sp_box").css({"opacity":"0.5","height":sp_height});
     		$(".sp_box").show();
     		$("#myForm").submit();
   			$('#dg').datagrid('reload');  //刷新当前頁面
     	}
	}
	
	$(function(){
		consumerSrv.getFreeDesk(function(data){ 
					if(data != null && data.deskList.length != 0){
						for(var i = 0; i < data.deskList.length; i++){
						var desk = data.deskList[i];
						$("#deskNum").append('<option value="'+desk+'">'+desk+'</option>');
						}
					}else{
						$("#deskNum").append('<option value="-1">'+"无空位"+'</option>');
					}	
			  		});  
			  			  		
		
	});	
	
	$(function showMessage(){
		var message = '<%=request.getAttribute("message")%>';
		if(message && message != null && message != 'null' && message != ""){
			$.messager.show({  
	             title:'警告',   
	             msg:message  
	         }); 
		}else{
			$(".tc_box").hide();
			$(".sp_box").hide();
		}
	});
    </script>  
  </body>
</html>
