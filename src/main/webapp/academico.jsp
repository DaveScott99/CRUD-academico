<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="br.edu.exemplo.model.Aluno"%>
<%@ page import="java.util.List"%>
<%
List<Aluno> alunos = (List<Aluno>) request.getAttribute("alunos");
%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="utf-8">
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

			<div class="container-new">
				<a href="./incluir.html" class="btn-new"> Novo Aluno </a>
			</div>

			<table id="table-data">

				<thead>
					<tr>
						<th>Ra</th>
						<th>Nome</th>
						<th>Endereço</th>
						<th>Email</th>
						<th>Dta. Nasc</th>
						<th>Peíodo</th>
						<th>Opções</th>
					</tr>
				</thead>

				<tbody>

					<%
					for (Aluno aluno : alunos) {
					%>
					<tr>
						<td><%=aluno.getRa()%></td>
						<td><%=aluno.getNome()%></td>
						<td><%=aluno.getEndereco()%></td>
						<td><%=aluno.getEmail()%></td>
						<td><%=aluno.getDataNascimento()%></td>
						<td><%=aluno.getPeriodo()%></td>
						<td><a href="select?raAluno=<%=aluno.getRa()%>"
							class="btn-edit">Editar</a> <a
							href="javascript: confirmation(<%=aluno.getRa()%>)"
							class="btn-delete">Excluir</a></td>
					</tr>
					<%
					}
					%>

				</tbody>

			</table>
		</main>

	</div>
	<script src="scripts/scripts.js" defer></script>
</body>
</html>