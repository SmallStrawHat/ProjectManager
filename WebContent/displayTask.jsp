<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="com.business.*,java.util.Vector" %>
<!DOCTYPE html>
<html>
<head>
	<title>查看任务</title>
    
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
    <!-- bootstrap -->
    <link href="css/bootstrap/bootstrap.css" rel="stylesheet" />
    <link href="css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
    <link href="css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet" />

    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="css/layout.css" />
    <link rel="stylesheet" type="text/css" href="css/elements.css" />
    <link rel="stylesheet" type="text/css" href="css/icons.css" />

    <!-- libraries -->
    <link href="css/lib/font-awesome.css" type="text/css" rel="stylesheet" />
    
    <!-- this page specific styles -->
    <link rel="stylesheet" href="css/compiled/tables.css" type="text/css" media="screen" />

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
                <div class="table-wrapper orders-table section">
                    <div class="row-fluid head">
                        <div class="span12">
                            <h3>查看任务</h3>
                        </div>
                    </div>

                    <div class="row-fluid filter-block">
                    	
                        <div class="pull-right">
                        	<a class="btn-flat success new-product" href="newTask.jsp">+ 创建任务</a>
                        	&nbsp;&nbsp;
                            <input type="text" class="search order-search" placeholder="Search for a task..." />
                            
                            <div class="btn-group pull-right">
                                <button class="glow left large">所有的</button>
                                <button class="glow middle large">进行中</button>
                                <button class="glow right large">中断的</button>
                            </div>
                        </div>
                    </div>

                    <div class="row-fluid">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th class="span2">
                                        	任务
                                    </th>
                                    <th class="span3">
                                    	<span class="line"></span>
                                        	开始时间
                                    </th>
                                    <th class="span3">
                                    	<span class="line"></span>
                                        	预计结束时间
                                    </th>
                                    <th class="span3">
                                    	<span class="line"></span>
                                        	所属项目
                                    </th>
                                    <th class="span3">
                                        <span class="line"></span>
                                        	任务进度
                                    </th>
                                    <th class="span3">
                                        <span class="line"></span>
                                        	任务状态
                                    </th>
                                    <th class="span3">
                                        <span class="line"></span>
                                        	问题跟踪单
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                            <%
                    		Vector projecList = ProjectManagement.getAllProjectList();
                    		for(int i=0;i<projecList.size();i++)
                    		{
                    			Project tempPro = (Project)projecList.get(i);
                    			Vector taskList = tempPro.getTaskList();
                    			for(int j=0;j<taskList.size();j++)
                    			{
                    				Task tempTask = (Task)taskList.get(j);
                    				
                    				%>
                    				
                    				 <!-- row -->
                                <tr class="first">
                                    <td>
                                    	<a href=<%="taskdetail.jsp?taskID="+tempTask.getTaskID()+"&projName="+tempPro.getName()%> class="name"><%=tempTask.getTaskName()%></a>
                                    </td>
                                    <td>
                                        <%=tempTask.getStartTime()%>
                                    </td>
                                    <td>
                                        <%=tempTask.getPlanEndtime()%>
                                    </td>
                                    <td>
                                        <a href=<%="projectdetail.jsp?targetID="+tempPro.getId()%>><%=tempPro.getName()%></a>
                                    </td>
                                    <td>
                                        <%=tempTask.getRate()%>%
                                    </td>
                                    <td>
                                    	<%
                                    		if(tempTask.getState().equals("进行中的"))
                                    		{
                                    			%>
                                    			<span class="label label-info">Progress</span>
                                    			<%
                                    		}
                                    		if(tempTask.getState().equals("已经完成"))
                                			{
                                    			%>
                                    			<span class="label label-success">Completed</span>
                                    			<%
                                			}
                                    		if(tempTask.getState().equals("出现问题"))
                                    		{
                                    			%>
                                    			<span class="label label-warning">Warning</span>
                                    			<%
                                    		}
                                    		
                                    	%>
                                        
                                    </td>
                                    
                                    <td>
                                        <a href=<%="createProblemlog.jsp?taskID="+tempTask.getTaskID() %> class="name">创建跟踪单</a>    

                                    </td>
                                </tr>
                    				
                    				
                    				<%
                    			}
                    		}
                            
                            %>
                               
                            </tbody>
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
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>