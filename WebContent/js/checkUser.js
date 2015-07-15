
	function getID(id) {
		return document.getElementById(id);
	}
	
	function checkFilter()
	{
		if(!checkName())
		{
			return false;
		}
		if(!checkPassword())
		{
			return false;
		}
		if(!checkPhone())
		{
			return false;
		}
		if(!checkEmail())
		{
			return false;
		}
		return true;
	}


	function checkName() {
		var name = getID("userName");
		if (name.value == '') {
			getID("nameCss").className="span12 field-box error";
			document.getElementById("nameSpan").style.display="";
			return false;
		}
		else
		{
			getID("nameCss").className="span12 field-box";
			document.getElementById("nameSpan").style.display="none";
			return true;
		}
	}

	function checkPassword() {
		var password = getID("userPassword");
		var ensurePassword = getID("ensurePassword");

		if (password.value != ensurePassword.value) {
			getID("ensureCss").className="span12 field-box error";
			document.getElementById("ensureSpan").style.display="";
			return false;
		}
		else
		{
			getID("ensureCss").className="span12 field-box";
			document.getElementById("ensureSpan").style.display="none";
			return true;
		}
	}

	function checkPhone() {
		var phone = getID("userPhone");
		if (!/(\d{3}-)(\d{8})|(\d{4}-)(\d{8})|1\d{10}/.test(phone.value)) {
			getID("phoneCss").className="span12 field-box error";
			document.getElementById("phoneSpan").style.display="";
			return false;
		}
		else
		{
			getID("phoneCss").className="span12 field-box";
			document.getElementById("phoneSpan").style.display="none";
			return true;
		}
	}

	function checkEmail() {
		var email = getID("userEmail");
		var re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/
		if (!re.test(email.value)) {
			getID("emailCss").className="span12 field-box error";
			document.getElementById("emailSpan").style.display="";
			return false;
		} else {
			getID("emailCss").className="span12 field-box";
			document.getElementById("emailSpan").style.display="none";
			return true;
		}
	}