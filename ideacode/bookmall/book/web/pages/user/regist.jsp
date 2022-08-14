<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
	<%-- 静态包含base标签，css样式和jQery文件--%>
	<%@include file="/pages/common/head.jsp"%>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
</style>
	<script type="text/javascript">
		//页面加载完成之后
		$(()=>{
			//给注册绑定单击事件
			$("#sub_btn").click(function (){
				//验证用户名：必须由字母数字下划线组成，并且长度为5-12位
				//1.获取用户名输入框中的内容
				let username = $("#username").val();
				//2.创建正则表达式对象
				let usernamePatt=/^\w{5,12}$/;
				//3.使用test方法验证
				if(!usernamePatt.test(username)){
					$(".errorMsg").text("用户名不合法");
				}
				//验证密码
				//1.获取密码输入框中的内容
				let password = $("#password").val();
				//2.创建正则表达式对象
				let passwordPatt=/^\w{5,12}$/;
				//3.使用test方法验证
				if(!passwordPatt.test(password)){
					$(".errorMsg").text("密码不合法");
				}
				//验证确认密码：和密码相同
				//1.获取确认密码
				let repwd = $("#repwd").val();
				//2.和密码比较
				//3.提示用户
				if(password!==repwd){
					$(".errorMsg").text("密码不一致");
				}
				//验证邮箱
				//1.获取邮箱内容
				let email = $("#email").val();
				//2.创建正则表达式
				let emailPatt=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
				//3.验证
				if(!emailPatt.test(email)){
					$(".errorMsg").text("邮箱错误");
				}
				//验证码，现在只需要验证输入了，还没将到服务器
				let code = $("#code").val();
				//去前后空格
				code=$.trim(code);

				//去后空格*/
				/*return false;*/
				/*注意注意注意，禁止点击后跳转回影响表单提交*/
			});
		});
	</script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg"></span>
							</div>
							<div class="form">
								<form action="helloServlet" method="post">
									<input type="hidden" name="action" value="regist"/>
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username" />
									<br/>
									<br/>
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" id="email" />
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" name="code" style="width: 150px;" id="code"/>
									<img alt="" src="static/img/code.bmp" style="float: right; margin-right: 40px">
									<br/>
									<br/>
									<input type="submit" value="注册" id="sub_btn"/>
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%--静态包含页脚--%>
		<%@include file="/pages/common/foot.jsp"%>
</body>
</html>