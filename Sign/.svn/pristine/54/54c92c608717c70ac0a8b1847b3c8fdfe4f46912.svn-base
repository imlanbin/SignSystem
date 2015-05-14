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
 </head>
  <body>
	  <div id="consumer-window" title="客户信息窗口" style="width:770px;height:460px;"> 
			<jsp:include   page="../other/ConsumerList.jsp"  flush="false"/>
	  </div>
  <!-- 搜索条件  开始 -->
	  <div>
	  		年份：
	       	<input id="search_year" name="search_year" type="text" value="" class="input-text" oninput="search ()" onpropertychange="search ()" onkeydown="entersearch()"/>
	       	桌号：
	       	<input id="search_deskNum" name="search_deskNum" type="text" value="" class="input-text" oninput="search ()" onpropertychange="search ()" onkeydown="entersearch()"/>
	       	座位号：
	       	<input id="search_seatNum" name="search_seatNum" type="text" value="" class="input-text" oninput="search ()" onpropertychange="search ()" onkeydown="entersearch()"/>
	       	状态：
	       	 <select name="search_state"  id="search_state"  onchange="search()">
	       	   	  <option value ="1" >空位置</option>
				  <option value ="0" >已占用</option>  
			</select>
	       	<!--<input type="button" name="search"  value="查询" onclick="search()"/>-->
	  </div>
<!-- 数据列表  开始 -->
 	<table id="dg" title="座位信息列表" data-options="queryParams:{str:'1=1'},url:seatSrv.findAllPage,onLoadSuccess:changeBtn" class="easyui-datagrid"   toolbar="#toolbar" pagination=true   rownumbers="true" fitColumns="false" singleSelect="false" collapsible="false"  autoRowHeight="true"    pageList="[10,20,30,40,50]" sortName="year,deskNum,seatNum"  >  
        <thead>  
            <tr> 
            	<th data-options="field:'id',checkbox:true" width="100"></th>
            	<th field="year" sortable="true"  width="100">年份</th>
		        <th field="deskNum" sortable="true"  width="100">桌号</th>
		        <th field="seatNum" sortable="true"  width="100">座位号</th>
		        <th field="consumerName" sortable="true"  width="100">客户</th>
		        <th field="state" sortable="true"  width="100" formatter="formatState">状态</th>
				<th field="state1" width="250" formatter="formatBtn">操作</th> 
            </tr>  
        </thead>  
    </table>  
    
    <div id="toolbar">  
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newSeat();">添加</a>     
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeSeat();">删除</a>
    <!--    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="openWin('${pageContext.request.contextPath}/author.action?action=add')">执行</a>-->  
    </div> 
 <!-- 数据列表  结束 --> 
 <!-- 添加数据窗口  开始 -->  
	<div id="seat-window" title="座位信息窗口" style="width:770px;height:460px;">
		<div>
			<form id="form" method="post">
				<div class="easyui-tabs" style="width:750px;height:370px">  
			        <div title="座位基本信息" style="padding:10px">  
			            <table>
							<tr>
								<td>年份：</td>
								<td ><input type="text"  name="year"  id="year" style="width:250px;" maxlength="4"/><input type="hidden" name="id" id="id"/><input id="userName" value="${sessionScope.userinfo.userName}"  type="hidden"/><font color="red">*</font></td>
								<td>桌号：</td>
								<td ><input type="text"  name="deskNum"  id="deskNum" style="width:250px;" maxlength="3"/><font color="red">*</font></td>
							</tr> 
							<tr>
								<td>座位号：</td>
								<td ><input type="text"  name="seatNum"  id="seatNum" style="width:250px;" maxlength="2"/><font color="red">*</font></td>
								<td>状态：</td>
								<td >
									<select name="state"  id="state" >
	       	   	  						<option value ="1" >空位置</option>
				  						<option value ="0" >已占用</option>  
									</select><font color="red">*</font>
								</td>
								<!-- 
								<td>客户名称：</td>
								<td ><input type="text"  name="consumerName"  id="consumerName" style="width:250px;"/><font color="red">*</font></td>
								 -->
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
    var win2;
    var form;
 	var state;
 	
    var strSql="1=1";
    $(function(){
    	//词组窗口
	    $('#btn-save,#btn-cancel,#btn-save1,#btn-cancel1').linkbutton();
		win = $('#seat-window').window({
			closed:true
		});
		win2 = $('#consumer-window').window({
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
    		state = val;
            if (val ==0){  
                return '<span style="color:red;">已占用</span>';  
            } else {  
                return '<span style="color:green;">空位置</span>';  
            }  
    }  
	function formatBtn(val,row){ 
		var r = '<a href="javascript:void(0)"  onclick="editSeat(\''+row.id+'\');"  class="easyui-linkbutton opbtn" iconCls="icon-edit">编辑</a>';
		if(state == 1)
			r = r + '<a href="javascript:void(0)"  onclick="setConsumer(\''+row.id+'\');"  class="easyui-linkbutton opbtn" iconCls="icon-edit">分配客户</a>';
		// alert(r);
	   	return  r;
	} 
	
	function newSeat(){
	  uuid.getID(function(data){
		 	form.form('clear');
		 	$('#id').val(data);
		 	win.window('open');
		 	var id = $("#id").val();
		 	$('#btn-save').unbind("click");
		  	$("#btn-save").bind('click',saveSeat);
		  	});
	}
	
	function setConsumer(id){
		 	win2.window('open');
		 	$("#searId").val(id);
		 	$('#btn-save').unbind("click");
		  	$("#btn-save").bind('click',saveSeat);
	}
	
	function editSeat(id){
		 seatSrv.get(id+"",function(data){
		 	win.window('open');
			if(id!=''&id!=null){
			strSql = " id = '" + id + "'";
			}
			$('#dg1').datagrid({
				url:seatSrv.findAllPage,
				queryParams: {
					str: strSql
				}
			});
			form.form('load', data);
			$('#btn-save').unbind("click");
		 	$("#btn-save").bind('click',updateSeat);
		});
	}
	
	function updateSeat(){ 
		  var seat = form.serializeObject();
		  seat.state = $('#state').val();
		  //seat.consumerName = $('#consumerName').val();
		  seatSrv.update(seat,function(data){
		  		$('#dg').datagrid('reload');  
				win.window('close'); 
		  });  
	}
	
	function saveSeat(){ 
			var year = $('#year').val();
			var deskNum = $('#deskNum').val();
			var seatNum = $('#seatNum').val();
			
		
			if(year == '' || year== null){
				alert("年份不能为空！");
				$('#year').focus();
				return;
			}
			if(deskNum == '' || deskNum == null){
				alert("桌号不能为空！");
				$('#deskNum').focus();
				return;
			}
			if(seatNum == '' || seatNum == null){
				alert("座位号不能为空！");
				$('#seatNum').focus();
				return;
			}
			if(isNaN(year)){
				alert("不合法的年份！");
				$('#year').focus();
				return;
			}
			if(isNaN(deskNum)){
				alert("不合法的桌数！");
				$('#deskNum').focus();
				return;
			}
			if(isNaN(seatNum)){
				alert("不合法的座位数！");
				$('#seatNum').focus();
				return;
			}	
			
			var seat = form.serializeObject();
			seat.state = "1";
			seatSrv.add(seat,function(data){
				$('#dg').datagrid('reload');  
				win.window('close'); 
			});  
	}
	
	function removeSeat(){ 
		var row = $('#dg').datagrid('getSelections');
		if (row.length>0){ 
		 	$.messager.confirm('删除提示','确定删除这些座位信息吗?',function(r){  
	            if (r){ 
					seatSrv.del(row,function(data){	 
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
	
	
	function search(){
		var year = $("#search_year").val(); 
		var deskNum = $("#search_deskNum").val();
		var seatNum = $("#search_seatNum").val();
		var state = $("#search_state").val();
		
		if(year!=''&year!=null){
			strSql = strSql + " and year = " + year;
		}
		if(deskNum!=''&deskNum!=null){
			strSql = strSql + " and deskNum = " + deskNum;
		}
		if(seatNum!=''&seatNum!=null){
			strSql = strSql + " and seatNum = " + seatNum;
		}
		if(state!=''&state!=null){
			strSql = strSql + " and state = '" + state + "'";
		}
		$('#dg').datagrid({
			url:seatSrv.findAllPage,
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
	
	
	
    </script>  
  </body>
</html>
