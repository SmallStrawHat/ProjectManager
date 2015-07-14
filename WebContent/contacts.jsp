<%@ page import="com.business.*,java.util.Vector" %>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Detail Admin - User list</title>
    
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!-- bootstrap -->
    <link href="css/bootstrap/bootstrap.css" rel="stylesheet" />
    <link href="css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
    <link href="css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet" />

    <!-- libraries -->
    <link href="css/lib/jquery-ui-1.10.2.custom.css" rel="stylesheet" type="text/css" />
    <link href="css/lib/font-awesome.css" type="text/css" rel="stylesheet" />

    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="css/layout.css" />
    <link rel="stylesheet" type="text/css" href="css/elements.css" />
    <link rel="stylesheet" type="text/css" href="css/icons.css" />

    <!-- this page specific styles -->
    <link rel="stylesheet" href="css/compiled/user-list.css" type="text/css" media="screen" />    

    <!-- open sans font -->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css' />

    <!-- lato font -->
    <link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900,300italic,400italic,700italic,900italic' rel='stylesheet' type='text/css' />

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
            <div id="pad-wrapper" class="users-list">
                <div class="row-fluid header">
                    <h3>联系人</h3>
                    <div class="span10 pull-right">
                        <input name="search" id="search" type="text" class="span5 search" onKeyDown="keydownEvent()" placeholder="输入联系人的名字..." />
                        
                        <!-- custom popup filter -->
                        <!-- styles are located in css/elements.css -->
                        <!-- script that enables this dropdown is located in js/theme.js -->

                        <a href="addContacts.jsp" class="btn-flat success pull-right">
                            <span>&#43;</span>
                            	添加联系人
                        </a>
                    </div>
                </div>

                <!-- Users table -->
                <div class="row-fluid table">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th class="span4 sortable">
                                  Name
                                </th>
                                <th class="span2 sortable">
                                    <span class="line"></span>UserID
                                </th>
                                <th class="span3 sortable">
                                    <span class="line"></span>Phone
                                </th>
                                <th class="span3 sortable">
                                    <span class="line "></span>UserRole
                                </th>
                                <th class="span1 sortable">
                                    
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                        <%
                        String searchCondition = request.getParameter("search");
                        if(searchCondition !=null && searchCondition.equals("")!=true)
        				{
        					searchCondition = new String(searchCondition.getBytes("ISO-8859-1"),"utf-8");
        				}
                        
                        Vector contactList = Contacts.serachContact(Integer.parseInt((String)session.getAttribute("account")));
                        for(int i=0;i<contactList.size();i++)
                        {
                        	User tempUser = MemberInformation.seachUser(((Integer)contactList.get(i)).intValue());
                        	if(searchCondition !=null && searchCondition.equals("")!=true)
            				{
            					if(tempUser.getName().indexOf(searchCondition)==-1)
            					{
            						continue;
            					}
            				}
                       
                        %>
                        <!-- row -->
                        <tr class="first">
                            <td>
                                <img src="img/contact-img.png" class="img-circle avatar hidden-phone" />
                                <a class="name"><%=tempUser.getName()%></a>
                                <span class="subtext"><%=tempUser.getEmail()%></span>
                            </td>
                            <td>
                                <%=tempUser.getUserID()%>
                            </td>
                            <td>
                                <%=tempUser.getPhone()%>
                            </td>
                            <td >
                                <%=tempUser.getUserRole()%>
                            </td>
                            <td class=" align-right">
                                <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#myModal" onclick="odd(<%=tempUser.getUserID()%>)"><i class="icon-trash"></i></button>
                            </td>
                            
                            
                        </tr>
                        <%} %>
                        </tbody>
                    </table>
                </div>
                <!-- <div class="pagination pull-right">
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
        <h4 class="modal-title" id="myModalLabel">删除联系人</h4>
      </div>
      <div class="modal-body">    
       		这个操作将会删除该联系人，真的要删除吗？
      </div>
      <div class="modal-footer">
      
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-danger" onclick="select()">确定</button>
        <%-- <a href=<%="Contacts?functionMy=deleteContacts&accountID="+selectID %> ><button type="button" class="btn btn-danger" onclick="select()">确定</button></a> --%>
      </div>
    </div>
  </div>
</div>
<!--  end Modal-->


	<!-- scripts -->
	
	 <script type="text/javascript">
	 function keydownEvent() {

         var e = window.event || arguments.callee.caller.arguments[0];

         if (e && e.keyCode == 13 ) {

         	var name = document.getElementById("search").value;
         	window.location.href="http://localhost:8080/ProjectManager/contacts.jsp?search="+name;
         }
     }
	 
	 var userID = -1;
        function select()
        {
            //alert('Contacts?function=deleteContacts&accountID='+userID);
            window.location.href='http://localhost:8080/ProjectManager/Contacts?functionMy=deleteContacts&accountID='+userID;
        }
        
        function odd(id)
        {
        	
        	userID = id;
        }
    </script>
	
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery-ui-1.10.2.custom.min.js"></script>
    <!-- knob -->
    <script src="js/jquery.knob.js"></script>
    <!-- flot charts -->
    <script src="js/jquery.flot.js"></script>
    <script src="js/jquery.flot.stack.js"></script>
    <script src="js/jquery.flot.resize.js"></script>
    <script src="js/theme.js"></script>

    <script type="text/javascript">
        $(function () {

            // jQuery Knobs
            $(".knob").knob();



            // jQuery UI Sliders
            $(".slider-sample1").slider({
                value: 100,
                min: 1,
                max: 500
            });
            $(".slider-sample2").slider({
                range: "min",
                value: 130,
                min: 1,
                max: 500
            });
            $(".slider-sample3").slider({
                range: true,
                min: 0,
                max: 500,
                values: [ 40, 170 ],
            });

            

            // jQuery Flot Chart
            var visits = [[1, 50], [2, 40], [3, 45], [4, 23],[5, 55],[6, 65],[7, 61],[8, 70],[9, 65],[10, 75],[11, 57],[12, 59]];
            var visitors = [[1, 25], [2, 50], [3, 23], [4, 48],[5, 38],[6, 40],[7, 47],[8, 55],[9, 43],[10,50],[11,47],[12, 39]];

            var plot = $.plot($("#statsChart"),
                [ { data: visits, label: "Signups"},
                 { data: visitors, label: "Visits" }], {
                    series: {
                        lines: { show: true,
                                lineWidth: 1,
                                fill: true, 
                                fillColor: { colors: [ { opacity: 0.1 }, { opacity: 0.13 } ] }
                             },
                        points: { show: true, 
                                 lineWidth: 2,
                                 radius: 3
                             },
                        shadowSize: 0,
                        stack: true
                    },
                    grid: { hoverable: true, 
                           clickable: true, 
                           tickColor: "#f9f9f9",
                           borderWidth: 0
                        },
                    legend: {
                            // show: false
                            labelBoxBorderColor: "#fff"
                        },  
                    colors: ["#a7b5c5", "#30a0eb"],
                    xaxis: {
                        ticks: [[1, "JAN"], [2, "FEB"], [3, "MAR"], [4,"APR"], [5,"MAY"], [6,"JUN"], 
                               [7,"JUL"], [8,"AUG"], [9,"SEP"], [10,"OCT"], [11,"NOV"], [12,"DEC"]],
                        font: {
                            size: 12,
                            family: "Open Sans, Arial",
                            variant: "small-caps",
                            color: "#697695"
                        }
                    },
                    yaxis: {
                        ticks:3, 
                        tickDecimals: 0,
                        font: {size:12, color: "#9da3a9"}
                    }
                 });

            function showTooltip(x, y, contents) {
                $('<div id="tooltip">' + contents + '</div>').css( {
                    position: 'absolute',
                    display: 'none',
                    top: y - 30,
                    left: x - 50,
                    color: "#fff",
                    padding: '2px 5px',
                    'border-radius': '6px',
                    'background-color': '#000',
                    opacity: 0.80
                }).appendTo("body").fadeIn(200);
            }

            var previousPoint = null;
            $("#statsChart").bind("plothover", function (event, pos, item) {
                if (item) {
                    if (previousPoint != item.dataIndex) {
                        previousPoint = item.dataIndex;

                        $("#tooltip").remove();
                        var x = item.datapoint[0].toFixed(0),
                            y = item.datapoint[1].toFixed(0);

                        var month = item.series.xaxis.ticks[item.dataIndex].label;

                        showTooltip(item.pageX, item.pageY,
                                    item.series.label + " of " + month + ": " + y);
                    }
                }
                else {
                    $("#tooltip").remove();
                    previousPoint = null;
                }
            });
        });
    </script>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>