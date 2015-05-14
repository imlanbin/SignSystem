<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
 <head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta http-equiv="content-language" content="zh-CN" />  
	<title>客户信息列表</title>
	<script type="text/javascript" src='${pageContext.request.contextPath}/dwr/interface/consumerSrv.js'></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/uploadChecker.js"></script>
</head>
  <body>
  <!-- 搜索条件  开始 -->
  	  <div id="seatData">
  	  		<input id="searId" name="searId" type="hidden"value=""/>
  	  		<input id="year" name="year" type="hidden"value=""/>
  	  		<input id="deskNum" name="deskNum" type="hidden"value=""/>
  	  		<input id="seatNum" name="seatNum" type="hidden"value=""/>
  	  </div>
	  <div>
	       	单位名称缩写：
	       	<input id="CNJ" name="CNJ" type="text" value="" class="input-text" oninput="searchConsumer ()" onpropertychange="searchConsumer ()" onkeydown="entersearchConsumer()"/>
	       	联系人缩写：
	       	<input id="CPJ" name="CPJ" type="text" value="" class="input-text" oninput="searchConsumer ()" onpropertychange="searchConsumer ()" onkeydown="entersearchConsumer()"/>
	       	桌号：
	       	<input id="deskNumS" name="deskNumS" type="text" value=""  style="width:30px" oninput="searchConsumer ()" onpropertychange="searchConsumer ()" onkeydown="entersearchConsumer()"/>
	       	状态：
	       	 <select name="stateS"  id="stateS"  onchange="searchConsumer()">  
				  <option value ="0" >未到</option>  
				  <option value ="1" >已到</option>
				  <option value ="2" >不到</option>  
			</select>
	  </div>
	  <!-- 工具栏  开始 -->
	  <div id="toolbar2">  
        <a id="add" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newConsumer();">添加</a>     
        <a id="delete" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeConsumer();">删除</a>
    </div> 
    <!-- 工具栏  结束 -->
	<!-- 数据列表  开始 -->
 	<table id="con-dg" title="客户信息列表" data-options="queryParams:{str:'1=1 and state=0'},url:consumerSrv.findAllPage,onLoadSuccess:changeBtn" class="easyui-datagrid"   toolbar="#toolbar2" pagination=true   rownumbers="true" fitColumns="false" singleSelect="false" collapsible="false"  autoRowHeight="true"    pageList="[10,20,30,40,50]" sortName="deskNum"  >  
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
				<th field="state1" width="100" formatter="formatBtn1">操作</th> 
            </tr>  
        </thead>  
    </table>  
 	<!-- 数据列表  结束 --> 
 	<!-- 添加数据窗口  开始 -->  
	<div id="add-window" title="客户信息窗口" style="width:770px;height:460px;">
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
			<a href="javascript:void(0)"   id="btn-save1" icon="icon-save">保存</a>
			<a href="javascript:void(0)" onclick="closeWindow()" id="btn-cancel1" icon="icon-cancel">取消</a>
		</div>
	</div>

	<script type="text/javascript">
    
    var win1;
    var form1;
    var win2;
    var form2;
    var strSql="1=1";
    $(function(){
    	//词组窗口
	    $('#btn-save,#btn-cancel,#btn-save1,#btn-cancel1').linkbutton();
		win1 = $('#add-window').window({
			closed:true
		});
		//词组表单
		form1 = win1.find('form');
	
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
	function formatBtn1(val,row){ 
		var r = '<a href="javascript:void(0)"  onclick="setSeat(\''+row.id+'\');"  class="easyui-linkbutton opbtn" iconCls="icon-edit">分配</a>';
		// alert(r);
	   	 return  r;
	} 
	
	function setSeat(id){
		var seatId = $('#searId').val();
		seatSrv.setConsumer(seatId+"", id+"");
		$('#con-dg').datagrid('reload');
		win1.window('close');
	}
	
	function newConsumer(){
	  uuid.getID(function(data){
		 	form1.form('clear');
		 	$('#id').val(data);
		 	$("#isVIP").val(0);
		 	$("#state").val("0");
		 	win1.window('open');
		 	var id = $("#id").val();
		 	$('#btn-save1').unbind("click");
		  	$("#btn-save1").bind('click',saveConsumer);
		  	});
	}
	
	function saveConsumer(){ 
			var companyName = $('#companyName').val();
			var contactPerson = $('#contactPerson').val();
			var contactPhone = $('#contactPhone').val();
			var deskNum = $('#deskNum').val();
			var recePerson = $('#recePerson').val();
			var recePhone = $('#recePhone').val();
			
			if(contactPerson == '' || contactPerson == null){
				alert("联系人不能为空！");
				$('#contactPerson').focus();
				return;
			}

			if((deskNum == '' || deskNum == null) && deskNum != -1){
				alert("桌号不能为空！");
				$('#deskNum').focus();
				return;
			}
			
			var consumer = form.serializeObject();
			consumer.year = new Date().getFullYear();
		  	consumerSrv.add(consumer,function(data){
		  		$('#con-dg').datagrid('reload');  
				win1.window('close'); 
		  	});  
	}
	
	function removeConsumer(){ 
		var row = $('#con-dg').datagrid('getSelections');
		if (row.length>0){ 
		 $.messager.confirm('删除提示','确定删除这些客户信息吗?',function(r){  
            if (r){ 
					consumerSrv.del(row,function(data){	 
							$('#con-dg').datagrid('reload');
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
	
	
	function searchConsumer(){ 
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
		$('#con-dg').datagrid({
			url:consumerSrv.findAllPage,
			queryParams: {
				str: strSql
			}
		});
		strSql="1=1";
	}
	
     function entersearchConsumer(){
         //alert(dd);
        var event = window.event || arguments.callee.caller.arguments[0];
        if (event.keyCode == 13)
        {
            searchConsumer();
        }
    }
    
	function closeWindow(){
		win1.window('close');
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
