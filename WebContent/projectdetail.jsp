<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="com.business.*,java.util.Vector"%>
<!DOCTYPE html>
<html>
<head>
<title>Project Detail</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!-- bootstrap -->
<link href="css/bootstrap/bootstrap.css" rel="stylesheet" />
<link href="css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
<link href="css/bootstrap/bootstrap-overrides.css" type="text/css"
	rel="stylesheet" />

<!-- libraries -->
<link href="css/lib/font-awesome.css" type="text/css" rel="stylesheet" />

<!-- global styles -->
<link rel="stylesheet" type="text/css" href="css/layout.css" />
<link rel="stylesheet" type="text/css" href="css/elements.css" />
<link rel="stylesheet" type="text/css" href="css/icons.css" />

<!-- this page specific styles -->
<link rel="stylesheet" href="css/compiled/projectdetail.css"
	type="text/css" media="screen" />

<!-- open sans font -->
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
	rel='stylesheet' type='text/css' />

<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>

	<%@ include file="boot.jsp"%>
	
	
	<!-- main container -->
	<div class="content">

		<!-- settings changer -->
		<!-- <div class="skins-nav">
			<a href="#" class="skin first_nav selected"> <span class="icon"></span><span
				class="text">Default</span>
			</a> <a href="#" class="skin second_nav" data-file="css/skins/dark.css">
				<span class="icon"></span><span class="text">Dark skin</span>
			</a>
		</div> -->

		<div class="container-fluid">
			<div id="pad-wrapper" class="user-profile">
				<!-- header -->
				<%  
				String str=request.getParameter("targetID");

	              Project p=ProjectManagement.searchProject(Integer.parseInt(str));
	                            
	              if(p==null)
	               {
	               	   response.sendRedirect("error.jsp");
	                }    
                  %>
				<div class="row-fluid header">
					<div class="span8">
						<!--img src="img/contact-profile.png" class="avatar img-circle" /-->
						<h3 class="name"><%=p.getName() %></h3>
						<br>
						<h4 class="name">(ID:<%=p.getId() %>)</h4>
					</div>
					<!-- <a class="btn-flat icon pull-right delete-user"
						data-toggle="tooltip" title="Delete user" data-placement="top">
						<i class="icon-trash"></i>
					</a> <a class="btn-flat icon large pull-right edit"> 编辑项目 </a> -->
				</div>

				<div class="row-fluid profile">
					<!-- bio, new note & orders column -->
					<div class="span9 bio">
						<div class="profile-box">
							<!-- biography -->
							<div class="span12 section">
								<h5>项目概述</h5>
								<textarea class="span9" type="text" readonly="readonly"><%=p.getSummary() %></textarea>
							</div>

							<h6>项目明细</h6>
							<br />
							<!-- recent orders table -->
							<table class="table table-hover">
								<thead>
									<tr>
										<th class="span2">项目ID</th>
										<td><%=p.getId() %></td>
									</tr>
									<tr>
										<th class="span2">项目名称</th>
										<td><%=p.getName() %></td>
									</tr>

									<tr>
										<th class="span2">项目预算</th>
										<td><%=p.getBudget() %>万元</td>
									</tr>
									<tr>
										<th class="span2">项目经理</th>
										<td>
										<%
										User user=(User) MemberInformation.seachUser(p.getManagerid());
										%>
										
										
                                        <%=user.getName()%>(ID:<%=p.getManagerid() %>)</a>                                       
										</td>
									</tr>
									<tr>
										<th class="span2">项目开始时间</th>
										<td><%=p.getStarttime() %></td>
									</tr>
									<tr>
										<th class="span2">预计结束时间</th>
										<td><%=p.getExpectendtime() %></td>
									</tr>
									<tr>
										<th class="span2">实际结束时间时间</th>
										<%if(p.getActualendtime()==null)
										{%>
											<td>未结束</td>
										<%}
										else
										{%>
											<td><%=p.getActualendtime() %></td>
										<%}%>
										
										
									</tr>
								
									<tr>
										<th class="span2">状态</th>
										<td><%if(p.getState().equals("进行中"))
                                    		{
                                    			%>
                                    			<span class="label label-info">进行中</span>
                                    			<%
                                    		}
                                    		if(p.getState().equals("已完成"))
                                			{
                                    			%>
                                    			<span class="label label-success">已完成</span>
                                    			<%
                                			} 
                                    		if(p.getState().equals("已提议"))
                                			{
                                    			%>
                                    			<span class="label label-important">已提议</span>
                                    			<%
                                			} 
                                    		if(p.getState().equals("暂停"))
                                			{
                                    			%>
                                    			<span class="label">暂停</span>
                                    			<%
                                			} 
                                    		if(p.getState().equals("意外终止"))
                                			{
                                    			%>
                                    			<span class="label label-warning">意外终止</span>
                                    			<%
                                			} 
                                			%>
											</td>
									</tr>
									<tr>
									<div class="field-box">
                                <label>进度:</label>
                                    <div class="progress progress-striped active span9">
                                    <%
                                    	int rateDis = (int)p.getSchedule();
                                    %>
      									<div class="bar" style="width: <%=rateDis%>%;"><%=rateDis %>%</div>
    								</div>
                            </div>
									
									</tr>
									<tr>
										<th class="span2">优先级</th>
										<td><%=p.getPriority() %></td>
									</tr>

									<tr>
										<th class="span2">已工作小时数</th>
										<td><%=p.getWorkedtime() %>小时
									<tr>
										<th class="span2">计划耗时</th>
										<td><%=p.getPlantime() %>小时</td>
									</tr>
									<tr>
										<th class="span2">项目耗时</th>
										<td><%=p.getActualtime() %>小时</td>
									</tr>


								</thead>

							</table>


							<!-- new comment form -->
							<!--div class="span12 section comment">
                                <h6>Add a quick note</h6>
                                <p>Add a note about this user to keep a history of your interactions.</p>
                                <textarea></textarea>
                                <a href="#">Attach files</a>
                                <div class="span12 submit-box pull-right">
                                    <input type="submit" class="btn-glow primary" value="Add Note" />
                                    <span>OR</span>
                                    <input type="reset" value="Cancel" class="reset" />
                                </div-->
						</div>
					</div>
					<!-- side right column -->
					<!-- <div class="span3 form-sidebar pull-right">
						<div class="btn-group toggle-inputs hidden-tablet">
							<button class="glow left active" data-input="inline">线条格式</button>
							<button class="glow right" data-input="normal">方框格式</button>
						</div>
						<div class="alert alert-info hidden-tablet">
							<i class="icon-lightbulb pull-left"></i> 点击上面的按钮，根据您的喜爱切换输入框的风格。
						</div>
						<h6>Sidebar text for instructions</h6>
						<p>Add multiple users at once</p>
						<p>Choose one of the following file types:</p>
						<ul>
							<li><a href="#">Upload a vCard file</a></li>
							<li><a href="#">Import from a CSV file</a></li>
							<li><a href="#">Import from an Excel file</a></li>
						</ul>
					</div> -->
				</div>
			</div>
		</div>
	</div>
	</div>
	<!-- end main container -->


	<!-- scripts -->
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/theme.js"></script>
	<div style="display: none">
		<script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540'
			language='JavaScript' charset='gb2312'></script>
	</div>
</body>
</html>
