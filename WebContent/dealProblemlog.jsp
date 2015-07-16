<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="com.business.*,java.util.Vector" %>
<!DOCTYPE html>
<html>
<head>
	<title>problem log list</title>
    
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
                            <h3>处理问题跟踪单</h3>
                        </div>
                    </div>

                    <div class="row-fluid filter-block">
                    	
                        <div class="pull-right">
                    
                            <!-- <input type="text" class="search order-search" placeholder="Search for a task..." /> -->
                            
                            <!-- <div class="btn-group pull-right">
                                <button class="glow left large">所有的</button>
                                <button class="glow middle large">进行中</button>
                                <button class="glow right large">中断的</button>
                            </div> -->
                        </div>
                    </div>

                    <div class="row-fluid">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th class="span2">
                                        	问题单号
                                    </th>
                                    <th class="span3">
                                    	<span class="line"></span>
                                        	所属任务
                                    </th>
                                    <th class="span3">
                                    	<span class="line"></span>
                                        	创建时间
                                    </th>
                                    <th class="span3">
                                    	<span class="line"></span>
                                        	创建人
                                    </th>
                                    <th class="span3">
                                        <span class="line"></span>
                                        	状态
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                           	<%
                           	Vector problemList = TaskManager.searchAllProblem();
                            		int accountID = Integer.parseInt((String)session.getAttribute("account"));
                            		for(int i=0;i<problemList.size();i++)
                            		{
                            			ProblemLog problem = (ProblemLog)problemList.get(i);
                            			int dealUserID = problem.getDealUserID();
                            			if(accountID != dealUserID)
                            			{
                            				continue;
                            			}
                           	%>
                    				
                    				 <!-- row -->
                                <tr class="first">
                                    <td>
                                    	<a href=<%="detailProblemlog.jsp?targetID="+problem.getProblemID()%> class="name"><%=problem.getProblemID()%></a>
                                    </td>
                                    <td>
                                    	<%
                                    		String taskName = TaskManager.searchTask(problem.getTaskID()).getTaskName();
                                    	%>
                                        <%=taskName%>
                                    </td>
                                    <td>
                                        <%=problem.getCreateTime()%>
                                    </td>
                                    <td>
                                        <%=problem.getCreateUserID()%>
                                    </td>
                                    <td>
                                    	<%
                                    		if(problem.getStatus()==1)
                                    		{
                                    			%>
                                    			<span class="label label-info">处理中</span>
                                    			<%
                                    		}
                                    		if(problem.getStatus()==2)
                                			{
                                    			%>
                                    			<span class="label label-success">处理完</span>
                                    			<%
                                			}
                                    		if(problem.getStatus()==3)
                                    		{
                                    			%>
                                    			<span class="label label-important">未解决</span>
                                    			<%
                                    		}
                                    		
                                    	%>
                                    </td>
                                </tr>
                    				
                    				
                    				<%
                    			}
                            %>
                               
                            </tbody>
                        </table>
                    </div>
                    
                   <!--  <div class="pagination pull-right">
                    	<ul>
                        	<li><a href="#">&#8249;</a></li>
                        	<li><a class="active" href="#">1</a></li>
                        	<li><a href="#">2</a></li>
                        	<li><a href="#">3</a></li>
                        	<li><a href="#">4</a></li>
                       		<li><a href="#">5</a></li>
                        	<li><a href="#">&#8250;</a></li>
                    	</ul>
                	</div> -->
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