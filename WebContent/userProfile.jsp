<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="com.business.*,java.util.Vector" %>

<!DOCTYPE html>
<html>
<head>
	<title>Detail Admin - User Profile</title>
    
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
    <!-- bootstrap -->
    <link href="css/bootstrap/bootstrap.css" rel="stylesheet" />
    <link href="css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
    <link href="css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet" />

    <!-- libraries -->
    <link href="css/lib/font-awesome.css" type="text/css" rel="stylesheet" />

    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="css/layout.css" />
    <link rel="stylesheet" type="text/css" href="css/elements.css" />
    <link rel="stylesheet" type="text/css" href="css/icons.css" />
    
    <!-- this page specific styles -->
    <link rel="stylesheet" href="css/compiled/user-profile.css" type="text/css" media="screen" />

    <!-- open sans font -->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css' />

    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<body>
<% 
   User localUser = MemberInformation.seachUser(Integer.parseInt(request.getParameter("targetID")));
   if(localUser == null)
   {
	   response.sendRedirect("index.jsp");
   }
%>
<%@ include file="boot.jsp" %>


	<!-- main container -->
    <div class="content">
        
        <!-- settings changer -->
        <!-- <div class="skins-nav">
            <a href="#" class="skin first_nav selected">
                <span class="icon"></span><span class="text">Default</span>
            </a>
            <a href="#" class="skin second_nav" data-file="css/skins/dark.css">
                <span class="icon"></span><span class="text">Dark skin</span>
            </a>
        </div> -->
        
        <div class="container-fluid">
            <div id="pad-wrapper" class="user-profile">
                <!-- header -->
                <div class="row-fluid header">
                    <div class="span8">
                        <img src="img/contact-profile.png" class="avatar img-circle" />
                        <h3 class="name"><%=localUser.getName()%></h3>
                        <span class="area"><%=localUser.getEmail()%></span>
                    </div>
                    <a class="btn-flat icon pull-right delete-user" data-toggle="modal" data-target="#myModal" title="Delete user" data-placement="top">
                        <i class="icon-trash"></i>
                    </a>
                     <a class="btn-flat icon large pull-right edit" href=<%="editUserDetail.jsp?actionID="+localUser.getUserID()%> >
                        	编辑用户信息
                    </a>
                </div>

                <div class="row-fluid profile">
                    <!-- bio, new note & orders column -->
                    <div class="span9 bio">
                        <div class="profile-box">
                            <!-- biography -->
                            <div class="span12 section">
                                <h6>简介</h6>
                                <p><%=localUser.getSummary()%></p>
                            </div>

                            <h6>正在参与的任务</h6>
                            <br />
                            <!-- recent orders table -->
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
                                            	状态
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                
                         <%
                         
                        
                         
                 		Vector projecList = ProjectManagement.getAllProjectList();
                        int accountID = Integer.parseInt((String)session.getAttribute("account"));
                 		for(int i=0;i<projecList.size();i++)
                 		{
                 			Project tempPro = (Project)projecList.get(i);
                 			Vector taskList = tempPro.getTaskList();
                 			for(int j=0;j<taskList.size();j++)
                 			{
                 				Task tempTask = (Task)taskList.get(j);
                 				Vector userList = tempTask.getUserList();
                    			
                    			int k;
        						for(k=0;k<userList.size();k++)
        						{
        							if(accountID == ((User)userList.get(k)).getUserID())
        							{
        								break;
        							}
        						}
        						if(k == userList.size())
        						{
        							continue;
        						}
                    			
        					
                    %>
                                    <!-- row -->
                                    <tr class="first">
                                        <td>
                                            <a href=<%="taskdetail.jsp?taskID="+tempTask.getTaskID()+"&projName="+tempPro.getName()%> ><%=tempTask.getTaskName()%></a>
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
                                    </tr>
                                   
                                   <%} }%>
                                </tbody>
                            </table>
                      
                      <%
                        	User loginUser = MemberInformation.seachUser(Integer.parseInt(request.getParameter("targetID")));
                      		String logInformation = FileOperation.ReadFileToString(loginUser.getUserLogpath());
                        %>
                            <!-- new comment form -->
                            <div class="span12 section comment">
                                <h6>用户日志</h6>
                                
                                <textarea><%=logInformation %></textarea>
                                <p>记录了对此用户的所有操作。</p>
                              
                            </div>
                        </div>
                    </div>

                    <!-- side address column -->
                    <div class="span3 address pull-right">
                        <h6>基本信息</h6>
                        
                        <ul>
                            <li class="ico-li">
                                <i class="icon-user"></i>
                                <%=loginUser.getUserRole() %>
                            </li>
                            
                            <li class="ico-li">
                                <i class="ico-phone"></i>
                                 <%=loginUser.getPhone() %>
                            </li>
                             <li class="ico-li">
                                <i class="ico-mail"></i>
                                <a href="#"> <%=loginUser.getEmail() %></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- end main container -->


<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">删除用户</h4>
      </div>
      <div class="modal-body">    
       		这个操作将会删除用户的所有信息，真的要删除这个用户吗？
      </div>
      <div class="modal-footer">
      
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <a href=<%="UserManager?functionMy=deleteUser&accountID="+localUser.getUserID() %> ><button type="button" class="btn btn-danger">确定</button></a>
      </div>
    </div>
  </div>
</div>
<!--  end Modal-->

	<!-- scripts -->
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/theme.js"></script>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>