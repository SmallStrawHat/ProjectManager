<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="com.business.*,com.database.*,java.util.Vector"%>
<!DOCTYPE html>
<html>
<head>
<title>查看项目</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!-- bootstrap -->
<link href="css/bootstrap/bootstrap.css" rel="stylesheet" />
<link href="css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
<link href="css/bootstrap/bootstrap-overrides.css" type="text/css"
	rel="stylesheet" />
<link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="/scripts/jquery.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>

<!-- global styles -->
<link rel="stylesheet" type="text/css" href="css/layout.css" />
<link rel="stylesheet" type="text/css" href="css/elements.css" />
<link rel="stylesheet" type="text/css" href="css/icons.css" />

<!-- libraries -->
<link href="css/lib/font-awesome.css" type="text/css" rel="stylesheet" />

<!-- this page specific styles -->
<link rel="stylesheet" href="css/compiled/user-list.css" type="text/css"
	media="screen" />

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
		<div class="skins-nav">
			<a href="#" class="skin first_nav selected"> <span class="icon"></span><span
				class="text">Default</span>
			</a> <a href="#" class="skin second_nav" data-file="css/skins/dark.css">
				<span class="icon"></span><span class="text">Dark skin</span>
			</a>
		</div>

		<div class="container-fluid">
			<div id="pad-wrapper" class="displayProject">
				<div class="row-fluid header">
					<h3>查看项目</h3>
					<div class="span10 pull-right">
						<input type="text" class="span5 search" placeholder="输入查找的项目ID..." />
						<a href="newproject.jsp" class="btn-flat success pull-right">
							<span>&#43;</span> 新建项目
						</a>
					</div>
				</div>

				<!-- Users table -->
				<div class="row-fluid table">
					<table class="table table-hover">
						<thead>
							<tr>
								<th class="span2 sortable">项目</th>
								<th class="span2 sortable"><span class="line"></span>开始时间</th>
								<th class="span2 sortable"><span class="line"></span>结束时间</th>
								<th class="span2 sortable"><span class="line"></span>进度</th>
								<th class="span2 sortable"><span class="line"></span>状态</th>
							</tr>
						</thead>

						<% Vector p=new Vector();
						    p=ProjectManagement.getAllProjectList();                       
							                 
							    for(int i=0;i<p.size();i++)                       
							    {                       	
							    	Project pro=(Project)p.get(i); 
						//	    }
							%>


						<tbody>

							<!-- row 显示项目列表-->


							<tr class="first">
								<td><img src="img/contact-img.png"
									class="img-circle avatar hidden-phone" /> <a
									href=<%="projectdetail.jsp?targetID="+pro.getId()%> class="name"><%=pro.getName() %></a> <span
									class="subtext">(ID:<%=pro.getId() %>)</span></td>
								<td><%=pro.getStarttime() %></td>
								<td><%=pro.getExpectendtime() %></td>
								<td><%=pro.getSchedule() %></td>
								<td><%=pro.getState() %></td>
							</tr>
						</tbody>
						<%} %>
					</table>
				</div>
				<div class="pagination pull-right">
					<ul>
						<li><a href="#">&#8249;</a></li>
						<li><a class="active" href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#">&#8250;</a></li>
					</ul>
				</div>
				<!-- end users table -->
			</div>
		</div>
	</div>
	<!-- end main container -->


	<!-- scripts -->
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/theme.js"></script>
	<script src="/scripts/jquery.min.js"></script>
	<script src="/bootstrap/js/bootstrap.min.js"></script>
	<div style="display: none">
		<script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540'
			language='JavaScript' charset='gb2312'></script>
	</div>
</body>
</html>
