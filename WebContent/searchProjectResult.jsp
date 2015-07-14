<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="com.business.*,java.util.Vector" %>
<!DOCTYPE html>
<html>
<head>
	<title>搜索结果</title>
    
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
   <!-- bootstrap -->
    <link href="css/bootstrap/bootstrap.css" rel="stylesheet" />
    <link href="css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
    <link href="css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet" />

    <!-- libraries -->
    <link href="css/lib/bootstrap-wysihtml5.css" type="text/css" rel="stylesheet" />
    <link href="css/lib/uniform.default.css" type="text/css" rel="stylesheet" />
    <link href="css/lib/select2.css" type="text/css" rel="stylesheet" />
    <link href="css/lib/bootstrap.datepicker.css" type="text/css" rel="stylesheet" />
    <link href="css/lib/font-awesome.css" type="text/css" rel="stylesheet" />

    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="css/layout.css" />
    <link rel="stylesheet" type="text/css" href="css/elements.css" />
    <link rel="stylesheet" type="text/css" href="css/icons.css" />
    
    <!-- this page specific styles -->
    <link rel="stylesheet" href="css/compiled/form-showcase.css" type="text/css" media="screen" />

    <!-- open sans font -->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css' />

    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<body>

     <%@ include file="boot.jsp" %>  

	<!-- main container -->
    <div class="content">
        
        <!-- settings changer -->
        
        
        <div class="container-fluid">
            <div id="pad-wrapper">
                


                <!-- orders table -->
                <div class="container-fluid">
			<div id="pad-wrapper" class="searchProjectResult">
				<div class="row-fluid header">
					<h3>搜索结果</h3>
					<div class="span10 pull-right">
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
					//	String search = response.get
							String searchName =new String(request.getParameter("searchName").getBytes("ISO-8859-1"),"utf-8");
				    	p=ProjectManagement.searchProject(searchName);   
							    for(int i=0;i<p.size();i++)                       
							    {                       	
							    	Project pro=(Project)p.get(i); 
							%>
						<tbody>

							<!-- row 显示项目列表-->


							<tr class="first">
								<td><img src="img/contact-img.png"
									class="img-circle avatar hidden-phone" /> <a
									href=<%="projectdetail.jsp?targetID="+pro.getId()%> class="name"><%=pro.getName() %></a> <span
									class="subtext">(ID:<%=pro.getId() %>)</span>

									</td>
								<td><%=pro.getStarttime() %></td>
								<td><%=pro.getExpectendtime() %></td>
								
								<td>
								<div class="field-box">
                                    <div class="progress progress-striped active">
                                    <%
                                    	int rateDis = (int)pro.getSchedule();
                                    %>

      									<div class="bar" style="width: <%=rateDis%>%;"><%=rateDis %>%</div>  
    								</div>
                            </div>
								</td>
								<td><%if(pro.getState().equals("进行中"))
                                    		{
                                    			%>
                                    			<span class="label label-info">进行中</span>
                                    			<%
                                    		}
                                    		if(pro.getState().equals("已完成"))
                                			{
                                    			%>
                                    			<span class="label label-success">已完成</span>
                                    			<%
                                			} 
                                    		if(pro.getState().equals("已提议"))
                                			{
                                    			%>
                                    			<span class="label label-important">已提议</span>
                                    			<%
                                			} 
                                    		if(pro.getState().equals("暂停"))
                                			{
                                    			%>
                                    			<span class="label">暂停</span>
                                    			<%
                                			} 
                                    		if(pro.getState().equals("意外终止"))
                                			{
                                    			%>
                                    			<span class="label label-warning">意外终止</span>
                                    			<%
                                			} 
                                			%>
                                </td>
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
                </div>
                <!-- end orders table -->

                
            </div>
        </div>
    </div>
    <!-- end main container -->

	<!-- scripts -->
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/theme.js"></script>
    <!-- scripts for this page -->
    <script src="js/wysihtml5-0.3.0.js"></script>
    <script src="js/bootstrap-wysihtml5-0.0.2.js"></script>
    <script src="js/bootstrap.datepicker.js"></script>
    <script src="js/jquery.uniform.min.js"></script>
    <script src="js/select2.min.js"></script>
    <script type="text/javascript">
        $(function () {

            // add uniform plugin styles to html elements
            $("input:checkbox, input:radio").uniform();

            // select2 plugin for select elements
            $(".select2").select2({
                placeholder: "Select a State"
            });

            // datepicker plugin
            $('.datepicker').datepicker().on('changeDate', function (ev) {
                $(this).datepicker('hide');
            });

            // wysihtml5 plugin on textarea
            $(".wysihtml5").wysihtml5({
                "font-styles": false
            });
        });
    </script>
    
    
    
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>