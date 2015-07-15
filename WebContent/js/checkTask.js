
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
		return true;
	}


	function checkName() {
		var taskName = getID("taskName");
		if (taskName.value == '') {
			getID("taskNameCss").className="field-box error";
			document.getElementById("taskNameSpan").style.display="";
			return false;
		}
		else
		{
			getID("taskNameCss").className="field-box";
			document.getElementById("taskNameSpan").style.display="none";
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
