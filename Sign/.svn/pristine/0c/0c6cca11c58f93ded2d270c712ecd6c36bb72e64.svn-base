<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://ckfinder.com" prefix="ckfinder" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta http-equiv="content-language" content="zh-CN" />  
	<title>词语列表</title>
	<jsp:include page="../easyui-dwr-js.jsp" /> 	 
	<script type="text/javascript" src='${pageContext.request.contextPath}/dwr/interface/userSrv.js'></script>
	<script type="text/javascript" src="./ckfinder/ckfinder.js"></script>
	
 </head>
  <body>
<!-- 数据列表  开始 -->
 	<table id="dg" title="用户列表" data-options="queryParams:{str:'1=1'},url:userSrv.findAllPage,onLoadSuccess:changeBtn" class="easyui-datagrid"   toolbar="#toolbar" pagination=true   rownumbers="true" fitColumns="false" singleSelect="false" collapsible="false"  autoRowHeight="true"    pageList="[10,20,30,40,50]" sortName="userName"  >  
        <thead>  
            <tr> 
            	<th data-options="field:'id',checkbox:true" width="100"></th>
		        <th field="userName" sortable="true"  width="200">用户名</th>
		        <th field="level" sortable="true"  width="200" formatter="formatLevel">用户级别</th>
		        <th field="state" sortable="true"  width="200" formatter="formatState">状态</th>  
				<th field="state1" width="200" formatter="formatBtn">操作</th> 
            </tr>  
        </thead>  
    </table>  
    
    <div id="toolbar">  
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser();">添加</a>     
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeUser();">删除</a>    
    <!--    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="openWin('${pageContext.request.contextPath}/author.action?action=add')">执行</a>-->  
    </div> 
 <!-- 数据列表  结束 --> 
 <!-- 添加数据窗口  开始 -->  
	<div id="User-window" title="用户窗口" style="width:500px;height:400px;">
		<div style="padding:20px 20px 15px 20px;">
			<div style="padding:20px 20px 15px 20px;">
			<form method="post">
				<table>
					<tr>
						<td>用户名：</td>
						<td >
						<input type="text"  name="userName"  id="userName" style="width:250px;"onkeyup="checkEditName()"/>
						<input type="hidden" name="id" id="id"/>
						<!-- <input type="hidden" name="password" id="password"/> -->
						<input id="level" value="${sessionScope.userinfo.level}"  type="hidden"/>
						
						</td>
						<td><div id="result1" style="float:left"></div></td>
					</tr>
					<tr>
						<td>状态：</td>
						<td>
						<select name="state" id="state" style="width:260px;">
						<option value="0">禁用</option>
						<option value="1">可用</option>
						</select>
						</td>
					</tr> 
					<tr>
						<td>级别：</td>
						<td>
						<select name="level" id="level" style="width:260px;">
						<option value="0">普通用户</option>
						<option value="1">超级管理员</option>
						</select>
						</td>
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
 <!-- 添加用户窗口  开始 -->  
	<div id="addUser-window" title="添加用户窗口" style="width:500px;height:400px;">
		<div style="padding:20px 20px 15px 20px;">
			<div style="padding:20px 20px 15px 20px;">
			<form method="post">
				<table>
					<tr>
						<td>用户名：</td>
						<td >
						<input type="text"  name="newUserName"  id="newUserName" style="width:250px;" onblur="checkName()"/>
						<input type="hidden" name="id" id="id"/>
						<input id="level" value="${sessionScope.userinfo.level}"  type="hidden"/>
						</td>
						<td><div id="result" style="float:left"></div></td>
					</tr>
					<tr>
						<td>密码：</td>
						<td>
						<input type="text"  name="newUpassword"  id="newUpassword" style="width:250px;"/>
						</td>
					</tr>
					<tr>
						<td>状态：</td>
						<td>
						<select name="newUstate" id="newUstate" style="width:260px;">
						<option value="0">禁用</option>
						<option value="1">可用</option>
						</select>
						</td>
					</tr> 
					<tr>
						<td>级别：</td>
						<td>
						<select name="newUlevel" id="newUlevel" style="width:260px;">
						<option value="0">普通用户</option>
						<option value="1">超级管理员</option>
						</select>
						</td>
					</tr>					
				</table>
			</form>
		</div>
		<div style="text-align:center;padding:5px;">
			<a href="javascript:void(0)"   id="add-btn-save" icon="icon-save">保存</a>
			<a href="javascript:void(0)" onclick="closeAddWindow()" id="add-btn-cancel" icon="icon-cancel">取消</a>
		</div>
	</div> 
 <!-- 添加数据窗口  结束 -->
 <!--修改密码窗口 开始-->
    <div id="changePw" class="easyui-window" title="修改密码" collapsible="false" minimizable="false"
        maximizable="false" icon="icon-save"  style="width: 300px; height: 150px; padding: 5px;
        background: #fafafa;">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            <form method="post">
                <table cellpadding=3>
                    <tr>
                        <td>新密码：</td>
                        <td><input id="newPass" type="password" class="txt01" /></td>
                    </tr>
                    <tr>
                        <td>确认密码：</td>
                        <td>
						<input id="rePass" type="password" class="txt01" />
						<input type="hidden" name="id" id="Id"/>
						</td>
                    </tr>
                </table>
                </form>
            </div>
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" onclick="doChangePass()">确定</a>
				<a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)" onclick="closeChangePassWindow()">取消</a>
            </div>
        </div>
    </div>
<!--修改密码窗口 结束-->
 	
    
    <script type="text/javascript">
    
    var win;
    var addwin;
    var changePwin;
    var form;
    var addform;
    var changeform;
    var oldUserName;
    
    $(function(){
    	//词组窗口
	    $('#btn-save,#btn-cancel,#add-btn-save,#add-btn-cancel').linkbutton();
		win = $('#User-window').window({
			closed:true
		});
		addwin = $('#addUser-window').window({
			closed:true
		});
		changePwin = $('#changePw').window({
			closed:true
		});
		//词组表单
		form = win.find('form');
		addform = addwin.find('form');
		changeform = changePwin.find('form');
		
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
    
    function formatLevel(val,row){  
            if (val ==0){  
                return '<span style="color:red;">普通用户</span>';  
            } else {  
                return '<span style="color:green;">超级管理员</span>';  
            }  
    }  
    function formatState(val,row){  
            if (val ==0){  
                return '<span style="color:red;">禁用</span>';  
            } else {  
                return '<span style="color:green;">可用</span>';  
            }  
    }  
	function formatBtn(val,row){ 
		var r = '<a href="javascript:void(0)"  onclick="editUser(\''+row.id+'\');"  class="easyui-linkbutton opbtn" iconCls="icon-edit">编辑</a>'
		         +'<a href="javascript:void(0)"  onclick="changePw(\''+row.id+'\');"  class="easyui-linkbutton opbtn" iconCls="icon-edit">修改密码</a>'; 
		// alert(r);
	   	 return  r;
	} 
	function newUser(){

		 	addform.form('clear');
			$("#result").empty();
		 	addwin.window('open');

		 	$('#add-btn-save').unbind("click");
		 	
		  	$("#add-btn-save").bind('click',saveUser);

	}	
	function editUser(id){
		 userSrv.get(id+"",function(data){
		 	win.window('open');
			form.form('load', data);
			oldUserName = data.userName;
			$("#result1").empty();
			$('#btn-save').unbind("click");
		 	$("#btn-save").bind('click',updateUser);
		});
	}
	
	function updateUser(){ 
		  var result1 = $("#result1").html();
		  var user = form.serializeObject();
		  if(result1 == "用户名已存在!"){
				alert("用户名已存在，请换个用户名再添加！");
				$('#userName').focus();
				return;
		 }
		 if(result1 != null && result1 != "")
		 		alert($("#result1").html());
		 userSrv.update(user,function(data){
		  		$('#dg').datagrid('reload');  
				win.window('close'); 
		 }); 
	}
	function saveUser(){ 
		    var userName = $('#newUserName').val();
			var password = $('#newUpassword').val();
			var state = $('#newUstate').val();
			var level = $('#newUlevel').val();
			var result = $("#result").html();
			if(userName == '' || userName == null){
				alert("用户名不能为空！");
				$('#newUserName').focus();
				return;
			}
			if(password == '' || password == null){
				alert("密码不能为空！");
				$('#newUpassword').focus();
				return;
			}
			if(state == '' || state == null){
				alert("状态不能为空！");
				$('#newUstate').focus();
				return;
			}
			if(level == '' || level == null){
				alert("级别不能为空！");
				$('#newUlevel').focus();
				return;
			}
			if(result == "用户名已存在!"){
				alert("用户名已存在，请换个用户名再添加！");
				$('#newUserName').focus();
				return;
			}
		 //var user = addform.serializeObject();
		 var user = new Object();
		 user.userName = userName;
		 user.password = password;
		 user.state = state;
		 user.level = level;
		 userSrv.add(user,function(data){
		  		$('#dg').datagrid('reload');  
				addwin.window('close'); 
		  });  
	}
	
	function removeUser(){ 
		var row = $('#dg').datagrid('getSelections');
		//alert(row.length); 
		if (row.length>0){ 
		 $.messager.confirm('删除提示','确定删除这些用户信息吗?',function(r){  
            if (r){ 
					userSrv.del(row,function(data){	 
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
	function changePw(id){
		 userSrv.get(id+"",function(data){
		 	
		 	$('#changePw').window('open');
		 	changeform.form('clear');
			changeform.form('load', data);
		});
	}
	function doChangePass(){
			var $newpass = $('#newPass');
            var $rePass = $('#rePass');
			var $Id = $('#Id');
            if ($newpass.val() == '') {
                $.messager.alert('系统提示', '请输入密码！', 'warning');
                return false;
            }
            if ($rePass.val() == '') {
                $.messager.alert('系统提示', '请在一次输入密码！', 'warning');
                return false;
            }

            if ($newpass.val() != $rePass.val()) {
                $.messager.alert('系统提示', '两次密码不一至！请重新输入', 'warning');
                return false;
            }
		    userSrv.changePass($Id.val()+"",$newpass.val()+"",function(data){
		    	if(data != ''){
		    	$.messager.alert('系统提示', '修改密码成功', 'warning');
		    	}else{
		    	$.messager.alert('系统提示', '修改密码失败', 'warning');
		    	}
		    	$('#changePw').window('close');
		    });	
	}
	function checkName(){
		var userName = $('#newUserName').val();
		if(userName != '' && userName != null){
			$.get("checkUserName.action?userName="+userName, function(data){  
        		$("#result").html(data);  
    		}); 
		}		
	}

	function checkEditName(){
		var userName= $('#userName').val();
		if(userName != '' && userName != null){
			if(userName != oldUserName)
			{
			$.get("checkUserName.action?userName="+userName, function(data){  
        		$("#result1").html(data);  
    			}); 
    		}
		}		
	}
	function closeWindow(){
		win.window('close');
	}
	function closeAddWindow(){
		addwin.window('close');
	}
	function closeChangePassWindow(){
		changePwin.window('close');
	}
	
    </script>  
  </body>
</html>
