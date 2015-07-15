var eg={};
var xmlhttp;
eg.$ = function(id)
{
	return document.getElementById(id);
}

function noDisplay()
{
	document.getElementById("notiseMy").style.display="none";
}

function myFilter()
{
	
}

function check()
{
	var account = eg.$("account");
	var password = eg.$("password");
	if(account.value == '' || password.value=='')
	{
		document.getElementById("notiseMy").style.display="";
		return false;
	}
	
	var jarge =  /^[0-9]*$/ ;
	if(!jarge.test(account.value))
	{
		document.getElementById("notiseMy").style.display ="";
		return false;
	}
	
	
	//非IE浏览器创建XmlHttpRequest对象
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    }
    //IE浏览器创建XmlHttpRequest对象
    if (window.ActiveXObject) {
        try {
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        catch (e){
            try {
                xmlhttp = new ActiveXObject("msxml2.XMLHTTP");
            }
            catch (x){
            }
        }
    }
	var url = "LogCheck?account="+account.value+"&password="+password.value;
	xmlhttp.open("GET",url,true);
	xmlhttp.setRequestHeader("Content-Type", "text/html;charset=UTF-8");
	xmlhttp.onreadystatechange  = function complate(){
		if(xmlhttp.readyState == 4){
			var msg = xmlhttp.responseText;
			if(msg == '0'){
				document.getElementById("notiseMy").style.display="";
				return false;
			}else{
				window.location.href="Login?account="+account.value+"&password="+password.value;
				return true;
			}
		}
	}
	xmlhttp.send(null);
	
	return false;
}


