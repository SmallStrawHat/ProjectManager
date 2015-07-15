
	function getID(id) {
		return document.getElementById(id);
	}

	function checkFilter()
	{
		if(!checkName())
		{
			return false;
		}
		if(!checkBudget())
		{
			return false;
		}
		if(!checkPlantime())
		{
			return false;
		}
		return true;
	}

	function checkName() {
		var name = getID("pname");
		if (name.value == '') {
			getID("pnameCss").className="field-box error";
			document.getElementById("pnameSpan").style.display="";
			return false;
		}
		else
		{
			getID("pnameCss").className="field-box";
			document.getElementById("pnameSpan").style.display="none";
			return true;
		}
	}
	
	function checkBudget()
	{
		var budget = getID("budget");
		var re = /^\d+(\.\d+)?$/ ;
		if (!re.test(budget.value)) {
			getID("budgetCss").className="field-box error";
			document.getElementById("budgetSpan").style.display="";
			return false;
		}
		else
		{
			getID("budgetCss").className="field-box";
			document.getElementById("budgetSpan").style.display="none";
			return true;
		}
	}

	function checkPlantime() {
		var plantime = getID("plantime");
		var re = /^[0-9]*[1-9][0-9]*$/ ; 
		if (!re.test(plantime.value)) {
			getID("plantimeCss").className="field-box error";
			document.getElementById("plantimeSpan").style.display="";
			return false;
		}
		else
		{
			getID("plantimeCss").className="field-box";
			document.getElementById("plantimeSpan").style.display="none";
			return true;
		}
	}
