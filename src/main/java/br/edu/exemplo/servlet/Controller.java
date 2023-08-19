package br.edu.exemplo.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.exemplo.dao.AlunoDAO;
import br.edu.exemplo.exception.DbException;
import br.edu.exemplo.model.Aluno;
import br.edu.exemplo.util.ConnectionFactory;

@WebServlet(urlPatterns = { "/MeuServlet", "/main", "/insert", "/select", "/update", "/delete" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	AlunoDAO alunoDao = new AlunoDAO(ConnectionFactory.getConnection());
	Aluno aluno = new Aluno();

	public Controller() throws Exception {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		if (action.equals("/main")) {
			alunos(request, response);
		} 
		else if (action.equals("/insert")) {
			novoAluno(request, response);
		} 
		else if (action.equals("/select")) {
			selecionarAluno(request, response);
		}
		else if (action.equals("/update")) {
			editarAluno(request, response);
		}
		else if (action.equals("/delete")) {
			deletarAluno(request, response);
		}
	}

	private void novoAluno(HttpServletRequest request, HttpServletResponse response) {
		try {
			aluno.setNome(request.getParameter("name"));
			aluno.setEmail(request.getParameter("email"));
			aluno.setEndereco(request.getParameter("address"));
			aluno.setPeriodo(request.getParameter("cmbPeriodo"));

			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
			Date birthday = formatDate.parse(request.getParameter("birthday"));

			aluno.setDataNascimento(birthday);

			alunoDao.salvar(aluno);
			response.sendRedirect("main");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private void editarAluno(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		try {
			aluno.setRa(Integer.parseInt(request.getParameter("ra")));
			aluno.setNome(request.getParameter("name"));
			aluno.setEndereco(request.getParameter("address"));
			aluno.setEmail(request.getParameter("email"));
			
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
			Date birthday = formatDate.parse(request.getParameter("birthday"));
			
			aluno.setDataNascimento(birthday);
			aluno.setPeriodo(request.getParameter("cmbPeriodo"));
			
			alunoDao.atualizar(aluno);
			
			response.sendRedirect("main");
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void selecionarAluno(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException  {
		String raAluno = request.getParameter("raAluno");
		aluno = alunoDao.procurarAluno(Integer.parseInt(raAluno));
		
		request.setAttribute("ra", aluno.getRa());
		request.setAttribute("nome", aluno.getNome());
		request.setAttribute("email", aluno.getEmail());
		request.setAttribute("endereco", aluno.getEndereco());
		request.setAttribute("birthday", aluno.getDataNascimento());
		request.setAttribute("cmbPeriodo", aluno.getPeriodo());
		
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}

	protected void alunos(HttpServletRequest request, HttpServletResponse response) {

		try {
			List<Aluno> alunos = alunoDao.todosAlunos();
			request.setAttribute("alunos", alunos);
			RequestDispatcher rd = request.getRequestDispatcher("academico.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		}

	}
	
	private void deletarAluno(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String raAluno = request.getParameter("raAluno");
		alunoDao.excluir(Integer.parseInt(raAluno));
		response.sendRedirect("main");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
