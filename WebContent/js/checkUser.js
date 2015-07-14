function checkCreateUser()
{
	if(!checkPassword())
	{
		return false;
	}
	return true;
}

function getID(id) {
	return document.getElementById(id);
}

function checkName() {
	var name = getID("userName");
	if (name.value == '') {
		getID("nameCss").className="span12 field-box error";
		document.getElementById("nameSpan").style.display="";
	}
	else
	{
		getID("nameCss").className="span12 field-box";
		document.getElementById("nameSpan").style.display="none";
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
	return true;
}

function checkPhone() {
	var phone = getID("userPhone");
	if (!/(\d{3}-)(\d{8})|(\d{4}-)(\d{8})|1\d{10}/.test(phone.value)) {
		// obj.focus();
		return false;
	}
	return true;
}

function checkEmail() {
	var email = getID("userEmail");
	var re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/
	if (re.test(email.value)) {
		return true;
	} else {
		return false;
	}
}
