/**
 * Validação de formulário
 * @author Davi
 */

 function validate() {
	let name = frmAluno.name.value;
	let email = frmAluno.email.value;
	let address = frmAluno.address.value;
	let birthday = frmAluno.birthday.value;

	if (name === "") {
		window.alert("Preencha o campo Nome");
		frmAluno.name.focus();
		return false;
	}
	else if (email === "") {
		window.alert("Preencha o campo Email");
		frmAluno.email.focus();
		return false;
	}
	else if (address === "") {
		window.alert("Preencha o campo Endereço");
		frmAluno.address.focus();
		return false;
	}
	else if (birthday === "") {
		window.alert("Preencha o campo Data de nascimento");
		frmAluno.birthday.focus();
		return false;
	}
	else {
		document.forms["frmAluno"].submit();
	}
 }
 
function confirmation(raAluno) {
	 let response = confirm("Confirma a exclusão deste aluno ?");
	 
	 if (response === true) {
		window.location.href = "delete?raAluno=" + raAluno;
	 }
 }