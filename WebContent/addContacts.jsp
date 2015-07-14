<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="com.business.*,java.util.Vector" %>
<!DOCTYPE html>
<html>
<head>
	<title>ProjectZZY - Home</title>
    
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
                
 
                <!-- users table -->
                <div class="table-wrapper users-table section">
                    <div class="row-fluid head">
                        <div class="span12">
                            <h4>添加联系人</h4>
                        </div>
                    </div>

				
                    <div class="row-fluid filter-block">
                        <div class="pull-right">
                        
                            <a href = "javascript:;" class="btn-flat pull-right success new-product add-user" onclick="aclick();">+ 添加</a>
                            <input name="search" id="search" type="text" class="search user-search" onKeyDown="keydownEvent()" placeholder="搜索联系人.." />
                            <div class="ui-select">
                                <select id="filterName">
                                  <option value="name" />姓名
                                  <option value="role" />用户角色
                                  <option value="phone" />手机号
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="row-fluid">
                    
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                	<th class="span3">
                                	<input type="checkbox" />
                                  	Name
                               		 </th>
                                	<th class="span2">
                                    	<span class="line"></span>UserRole
                                	</th>
                                	<th class="span2">
                                    	<span class="line"></span>Phone
                                	</th>
                                    <th class="span2 ">
                                        <span class="line"></span>Status
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                            <form name="formSelect" id="formSelect" action="Contacts" method="post">
                                <!-- row -->
                                <% 
                                String searchCondition = request.getParameter("search");
                                if(searchCondition !=null && searchCondition.equals("")!=true)
                				{
                					searchCondition = new String(searchCondition.getBytes("ISO-8859-1"),"utf-8");
                				}
                                String filterMy = request.getParameter("filterMy");
                                
                                Vector user = MemberInformation.getUserList();
                                Vector contactAlready = Contacts.serachContact(Integer.parseInt((String)session.getAttribute("account")));
                            	for(int i=0;i<user.size();i++)
                            	{
                            		User tempUser = (User)user.get(i);
                            		int lag =0;
                            		if((Integer.parseInt((String)session.getAttribute("account"))) == tempUser.getUserID())
                        			{
                        				continue;
                        			}
                            		for(int j=0;j<contactAlready.size();j++)
                            		{
                            			int alreadyID=((Integer)contactAlready.get(j)).intValue();
                            			if(alreadyID == tempUser.getUserID())
                            			{
                            				lag=1;
                            				break;
                            			}
                            		}
                            		if(lag == 1)
                            		{
                            			continue;
                            		}
                            		
                            		if(searchCondition !=null && searchCondition.equals("")!=true)
                    				{
                            			if(filterMy !=null && filterMy.equals("")!=true)
                        				{
                            				if(filterMy.equals("name"))
                            				{
                            					if(tempUser.getName().indexOf(searchCondition)==-1)
                            					{
                            						continue;
                            					}
                            				}
                            				if(filterMy.equals("role"))
                            				{
                            					if(tempUser.getUserRole().indexOf(searchCondition)==-1)
                            					{
                            						continue;
                            					}
                            				}
                            				if(filterMy.equals("phone"))
                            				{
                            					if(tempUser.getPhone().indexOf(searchCondition)==-1)
                            					{
                            						continue;
                            					}
                            				}
                        					
                        				}
                    				}
                            		
                            %>
                            <input name="functionMy" value="addContacts" type="hidden" />
                                <tr class="first">
                                
                                    <td>
                                    <input type="checkbox" name="userIDList" value="<%=tempUser.getUserID()%>" />
                                        <img src="img/contact-img.png" class="img-circle avatar hidden-phone" />
                                        <a href="" class="name">
                                        <%=tempUser.getName()%></a>
                                        <span class="subtext"><%=tempUser.getEmail()%></span>
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
                                        
                                    </td>
                                    <%}else
                                    	{
                                    %>
                                    
                                    <td>
                                        <span class="label label-info">Standby</span>
                                       
                                    </td>
                                    <%} %>
                                    
                                </tr>
                               <% }
                            %>
                            
                                 </form>
                            </tbody>
                        </table>
                    </div>
               
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
    
    <script type="text/javascript">
    function keydownEvent() {

        var e = window.event || arguments.callee.caller.arguments[0];

        if (e && e.keyCode == 13 ) {

        	var name = document.getElementById("search").value;
        	var filterName = document.getElementById("filterName").value
        	window.location.href="http://localhost:8080/ProjectManager/addContacts.jsp?search="+name+"&filterMy="+filterName;
        }
    }
    
	function aclick()
	{
		document.getElementById("formSelect").submit();
	}
	</script>

<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>