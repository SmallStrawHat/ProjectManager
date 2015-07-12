<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="com.business.*,java.util.Vector" %>
<!DOCTYPE html>
<html>
<head>
	<title>Detail Admin - Tables showcase</title>
    
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
        <div class="skins-nav">
            <a href="#" class="skin first_nav selected">
                <span class="icon"></span><span class="text">Default</span>
            </a>
            <a href="#" class="skin second_nav" data-file="css/skins/dark.css">
                <span class="icon"></span><span class="text">Dark skin</span>
            </a>
        </div>
        
        <div class="container-fluid">
            <div id="pad-wrapper">
            
               <!-- users table -->
                <div class="table-wrapper users-table section">
                    <div class="row-fluid head">
                        <div class="span12">
                            <h4>用户管理</h4>
                        </div>
                    </div>

                    <div class="row-fluid filter-block">
                        <div class="pull-right">
                            <a class="btn-flat pull-right success new-product add-user" href="newuser.jsp">+ 添加用户</a>
                            <input type="text" class="search user-search" placeholder="Search for users.." />
                        </div>
                    </div>

                    <div class="row-fluid">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th class="span3">
                                        Name
                                    </th>
                                    <th class="span2">
                                        <span class="line"></span>LoginID
                                    </th>
                                    <th class="span2">
                                        <span class="line"></span>UserRole
                                    </th>
                                    <th class="span2">
                                        <span class="line"></span>Phone
                                    </th>
                                    <th class="span3">
                                        <span class="line"></span>Status
                                    </th>
                                    
                                    
                                </tr>
                            </thead>
                            
                            <% MemberInformation.init();
                            	Vector user = MemberInformation.getUserList();
                            	for(int i=0;i<user.size();i++)
                            	{
                            		User tempUser = (User)user.get(i);
                            %>
                            <tbody>
                                <!-- row -->
                                <tr class="first">
                                    <td>
                                        <img src="img/contact-img.png" class="img-circle avatar hidden-phone" />
                                        <a href=<%="userProfile.jsp?targetID="+tempUser.getUserID()%> class="name">
                                        <%=tempUser.getName()%></a>
                                        <span class="subtext"><%=tempUser.getEmail()%></span>
                                    </td>
                                    <td>
                                        <%=tempUser.getUserID()%>
                                    </td>
                                    <td>
                                        <%=tempUser.getUserRole()%>
                                    </td>
                                    <td>
                                        <%=tempUser.getPhone()%>
                                    </td>
                                    <% int state = tempUser.getUserState();
                                    	if(state == 1)
                                    	{
                                    %>
                                    <td>
                                        <span class="label label-success">Active</span>
                                        <ul class="actions">
                                            <li><a href=<%="UserManager?functionMy=purseUser&accountID="+tempUser.getUserID() %>>暂停用户</a></li>
                                            
                                            <li class="last"><a data-toggle="modal" data-target="#myModal" href="" onclick="odd(<%=tempUser.getUserID()%>)">删除用户</a></li>
                                        </ul>
                                    </td>
                                    <%}else
                                    	{
                                    %>
                                    
                                    <td>
                                        <span class="label label-info">Standby</span>
                                        <ul class="actions">
                                            <li><a href=<%="UserManager?functionMy=startUser&accountID="+tempUser.getUserID() %>>开启用户</a></li>

                                            <li class="last"><a data-toggle="modal" data-target="#myModal"  href="" onclick="odd(<%=tempUser.getUserID()%>)">删除用户</a></li>
                                        </ul>
                                    </td>
                                    <%} %>
                                </tr>
                            </tbody>
                            <% }%>
                            
                            
                        </table>
                    </div>
                </div>
                <!-- end users table -->
                
               

               
             
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
        <button type="button" class="btn btn-danger" onclick="select()">确定</button>
      </div>
    </div>
  </div>
</div>
<!--  end Modal-->

	<!-- scripts -->
	
	<script type="text/javascript">
	 var userJsID = -1;
        function select()
        {
            //alert('Contacts?function=deleteContacts&accountID='+userID);
            window.location.href="http://localhost:8080/ProjectManager/UserManager?functionMy=deleteUser&accountID="+userJsID;
        }
        
        function odd(id)
        {
        	
        	userJsID = id;
        }
    </script>
    
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/theme.js"></script>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>