<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>南储年会签到系统V1.0</title>
    <link href="${pageContext.request.contextPath}/static/css/default.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/js/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/js/themes/icon.css" /> 
	<jsp:include page="../easyui-dwr-js.jsp" /> 
	<script type="text/javascript" src='${pageContext.request.contextPath}/static/js/outlook2.js'> </script>
	<script type="text/javascript" src='${pageContext.request.contextPath}/dwr/interface/userSrv.js'></script>
   

</head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
<input id="level" name="level" value="${sessionScope.userinfo.level}" type="hidden"/>
<noscript>
<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
    <img src="${pageContext.request.contextPath}/static/images/noscript.gif" alt='抱歉，请开启脚本支持！' />
</div></noscript>
    <div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
        background: url(${pageContext.request.contextPath}/static/images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
        <span style="float:right; padding-right:20px;" class="head">hi ${sessionScope.userinfo.userName} <a href="#" id="editpass">修改密码</a> <a href="#" id="loginOut">安全退出</a></span>
        <span style="padding-left:10px; font-size: 16px; "><img src="${pageContext.request.contextPath}/static/images/blocks.gif" width="20" height="20" align="absmiddle" />南储年会签到系统V1.0</span>
    </div>
    <div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
        <div class="footer">技术支持：南储仓储管理集团有限公司&nbsp;&nbsp;信息科技部</div>
    </div>
    <div region="west" hide="true" split="true" title="导航菜单" style="width:180px;" id="west">
<div id="nav" class="easyui-accordion" fit="true" border="false">
		<!--  导航内容 -->
				
			</div>

    </div>
    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
			<div title="南储年会签到系统V1.0" style="padding:20px;overflow:hidden;  " >
				<h1 style="font-size:24px;">欢迎使用南储年会签到系统！</h1>
				
			</div>
		</div>
    </div>
    
    
    <!--修改密码窗口-->
    <div id="w" class="easyui-window" title="修改密码" collapsible="false" minimizable="false"
        maximizable="false" icon="icon-save"  style="width: 300px; height: 150px; padding: 5px;
        background: #fafafa;">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
                <table cellpadding=3>
                    <tr>
                        <td>新密码：</td>
                        <td><input id="newPass" type="password" class="txt01" /></td>
                    </tr>
                    <tr>
                        <td>确认密码：</td>
                        <td><input id="rePass" type="password" class="txt01" /><input id="Id" value="${sessionScope.userinfo.id}"  type="hidden"/></td>
                    </tr>
                </table>
            </div>
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" >
                    确定</a> <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
            </div>
        </div>
    </div>

	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>

 <script type="text/javascript">
     var level = $('#level').val();
	 if(level == 1){
	  var _menus = {"menus":[
	 					{"menuid":"011","icon":"icon-sys","menuname":"用户管理",
							"menus":[{"menuid":"0101","menuname":"用户日志","icon":"icon-log","url":"${pageContext.request.contextPath}/admin/leaflet/logList.action"},
									{"menuid":"0102","menuname":"用户列表","icon":"icon-users","url":"${pageContext.request.contextPath}/admin/leaflet/userList.action"}
								]
						},{"menuid":"02","icon":"icon-sys","menuname":"座位信息管理",
							"menus":[{"menuid":"0201","menuname":"座位初始化","icon":"icon-log","url":"${pageContext.request.contextPath}/admin/leaflet/seatInit.action"},
									{"menuid":"0202","menuname":"座位信息列表","icon":"icon-log","url":"${pageContext.request.contextPath}/admin/leaflet/seatList.action"}
								]
						},{"menuid":"03","icon":"icon-sys","menuname":"客户信息管理",
							"menus":[{"menuid":"0201","menuname":"客户信息列表","icon":"icon-log","url":"${pageContext.request.contextPath}/admin/leaflet/consumerList.action"}
								]
						},{"menuid":"04","icon":"icon-sys","menuname":"签到管理",
							"menus":[{"menuid":"0201","menuname":"客户签到","icon":"icon-log","url":"${pageContext.request.contextPath}/admin/leaflet/consumerSign.action"}
								]
						},{"menuid":"05","icon":"icon-sys","menuname":"数据统计管理",
							"menus":[{"menuid":"0201","menuname":"排位报表","icon":"icon-log","url":"${pageContext.request.contextPath}/admin/leaflet/seatReportList.action"},
									{"menuid":"0202","menuname":"客户信息报表","icon":"icon-log","url":"${pageContext.request.contextPath}/admin/leaflet/consumerReportList.action"}
								]
						}
						/*,{"menuid":"02","icon":"icon-sys","menuname":"模板管理",
							"menus":[{"menuid":"0201","menuname":"说明书列表","icon":"icon-log","url":"${pageContext.request.contextPath}/admin/leaflet/medicineList.action"},
									{"menuid":"0202","menuname":"查询说明书","icon":"icon-log","url":"${pageContext.request.contextPath}/admin/leaflet/medicineListS.action"}
								]
						}*/
				]};
	 }else{
	 		var _menus = {"menus":[
	 					{"menuid":"04","icon":"icon-sys","menuname":"签到管理",
							"menus":[{"menuid":"0201","menuname":"客户签到","icon":"icon-log","url":"${pageContext.request.contextPath}/admin/leaflet/consumerSign.action"}
								]
						}
				]};
	 }
	
        //设置登录窗口
        function openPwd() {
            $('#w').window({
                title: '修改密码',
                width: 300,
                modal: true,
                shadow: true,
                closed: true,
                height: 160,
                resizable:false
            });
        }
        //关闭登录窗口
        function closePwd() {
            $('#w').window('close');
        }

        

        //修改密码
        function serverLogin() {
            var $newpass = $('#newPass');
            var $rePass = $('#rePass');
			var $Id = $('#Id');
            if ($newpass.val() == '') {
                msgShow('系统提示', '请输入密码！', 'warning');
                return false;
            }
            if ($rePass.val() == '') {
                msgShow('系统提示', '请在一次输入密码！', 'warning');
                return false;
            }

            if ($newpass.val() != $rePass.val()) {
                msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
                return false;
            }
		    userSrv.changePass($Id.val()+"",$newpass.val()+"",function(data){
		    	if(data != ''){
		    	msgShow('系统提示', '修改密码成功', 'warning');
		    	}else{
		    	msgShow('系统提示', '修改密码失败', 'warning');
		    	}
		    	$('#w').window('close');
		    });	
        }

        $(function() {
           
            openPwd();

            $('#editpass').click(function() {
                $('#w').window('open');
            });

            $('#btnEp').click(function() {
                serverLogin();
            })

			$('#btnCancel').click(function(){closePwd();})

            $('#loginOut').click(function() {
                $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {

                    if (r) {
                        location.href = '../login.jsp';
                    }
                });
            })
        });
		
		

    </script>

</body>
</html>