<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>.:Sistema acadêmico:.</title>
<link rel="stylesheet" type="text/css" href="./styles.css"
	media="screen" />
</head>
<body>

	<div class="container">
	
		<header class="header">

			<div class="header-content">
				<h1 class="brand">CRUD Acadêmico</h1>

				<nav>
					<ul class="menu">
						<a href="main">
							<li class="item-menu">Inicio</li>
						</a>
					</ul>
				</nav>
			</div>

		</header>


		<main>

			<h2>Editar aluno</h2>

			<form class="form-registry" name="frmAluno" action="update">

				<div class="field">
					<label>Ra: </label> <input type="number" name="ra"
						class="text-field ra" min="1" readonly="readonly"
						value="<%out.print(request.getAttribute("ra"));%>" /> <label>Nome:
					</label> <input type="text" name="name" class="text-field"
						value="<%out.print(request.getAttribute("nome"));%>" />
				</div>

				<div class="field">
					<label>Email: </label> <input type="email" name="email"
						class="text-field"
						value="<%out.print(request.getAttribute("email"));%>" /> <label>Endereço:
					</label> <input type="text" name="address" class="text-field"
						value="<%out.print(request.getAttribute("endereco"));%>" />
				</div>

				<div class="field">

					<label>Data de nascimento: </label> <input type="date"
						name="birthday" class="field-date"
						value="<%out.print(request.getAttribute("birthday"));%>"
						readonly="readonly" /> <label>Período: </label> <select
						name="cmbPeriodo" size="1" class="cmbPeriodo">
						<option selected="selected">
							<%
							out.print(request.getAttribute("cmbPeriodo"));
							%>
						</option>
					</select>

				</div>

			</form>

			<div class="buttons">
				<button class="btn-save" onclick="validate()">Salvar</button>
			</div>
		</main>
	</div>

	<script src="scripts/validator.js" defer></script>
</body>
</html>