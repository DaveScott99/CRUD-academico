package br.edu.exemplo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.exemplo.exception.DbException;
import br.edu.exemplo.model.Aluno;
import br.edu.exemplo.util.ConnectionFactory;

public class AlunoDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	private Aluno aluno;

	public AlunoDAO(Connection conn) {
		this.conn = conn;
	}

	public void salvar(Aluno aluno) {
		try {
			if (aluno != null) {
				String SQL = "INSERT INTO alunos (nome, email, endereco, datanascimento, periodo) values "
						+ "(?, ?, ?, ?, ?)";
				ps = conn.prepareStatement(SQL);
				ps.setString(1, aluno.getNome());
				ps.setString(2, aluno.getEmail());
				ps.setString(3, aluno.getEndereco());
				ps.setDate(4, new java.sql.Date(aluno.getDataNascimento().getTime()));
				ps.setString(5, aluno.getPeriodo());
				ps.executeUpdate();
			}

		} catch (SQLException sqle) {
			throw new DbException("Erro ao inserir dados " + sqle);
		} finally {
			ConnectionFactory.closeStatement(ps);
		}
	}

	public void atualizar(Aluno aluno) {
		try {
			if (aluno != null) {
				String SQL = "UPDATE alunos set nome=?, email=?, endereco=?, datanascimento=?, "
						+ "periodo=? WHERE ra=?";
				ps = conn.prepareStatement(SQL);
				ps.setString(1, aluno.getNome());
				ps.setString(2, aluno.getEmail());
				ps.setString(3, aluno.getEndereco());
				ps.setDate(4, new java.sql.Date(aluno.getDataNascimento().getTime()));
				ps.setString(5, aluno.getPeriodo());
				ps.setInt(6, aluno.getRa());
				ps.executeUpdate();
			}

		} catch (SQLException sqle) {
			throw new DbException("Erro ao alterar dados " + sqle);
		} finally {
			ConnectionFactory.closeStatement(ps);
		}
	}

	public void excluir(Integer raAluno) {
		try {
			String SQL = "DELETE FROM alunos WHERE ra = ?";
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, raAluno);
			ps.executeUpdate();
		} catch (SQLException sqle) {
			throw new DbException("Erro ao excluir dados " + sqle);
		} finally {
			ConnectionFactory.closeStatement(ps);
		}
	}

	public Aluno procurarAluno(int raAluno) {

		try {
			String SQL = "SELECT  * FROM alunos WHERE ra=?";
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, raAluno);
			rs = ps.executeQuery();
			if (rs.next()) {
				int ra = rs.getInt("ra");
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				String endereco = rs.getString("endereco");
				Date dataNascimento = rs.getDate("dataNascimento");
				String periodo = rs.getString("periodo");

				aluno = new Aluno(ra, nome, email, endereco, dataNascimento, periodo);
			}
			return aluno;
		} catch (SQLException sqle) {
			throw new DbException(sqle.getMessage());
		} finally {
			ConnectionFactory.closeResultSet(rs);
			ConnectionFactory.closeStatement(ps);
		}
	}

	public List<Aluno> todosAlunos() {
		try {
			String SQL = "SELECT * FROM alunos order by nome";
			ps = conn.prepareStatement(SQL);
			rs = ps.executeQuery();
			List<Aluno> list = new ArrayList<Aluno>();
			while (rs.next()) {
				int ra = rs.getInt("ra");
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				String endereco = rs.getString("endereco");
				Date dataNascimento = rs.getDate("dataNascimento");
				String periodo = rs.getString("periodo");
				list.add(new Aluno(ra, nome, email, endereco, dataNascimento, periodo));
			}
			return list;
		} catch (SQLException sqle) {
			throw new DbException(sqle.getMessage());
		} finally {
			ConnectionFactory.closeResultSet(rs);
			ConnectionFactory.closeStatement(ps);
		}
	}
}
