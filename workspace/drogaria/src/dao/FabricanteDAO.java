package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Fabricante;
import factory.ConexaoFactory;

public class FabricanteDAO {

	public void salvar(Fabricante f) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fabricante ");
		sql.append("(descricao) ");
		sql.append("VALUES (?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, f.getDescricao());

		comando.executeUpdate();
	}

	public void excluir(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fabricante ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setLong(1, f.getCodigo());

		comando.executeUpdate();
	}

	public void editar(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fabricante ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, f.getDescricao());
		comando.setLong(2, f.getCodigo());

		comando.executeUpdate();

	}

	public Fabricante buscarPorCodigo(Fabricante f) throws SQLException {

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fabricante ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());

		ResultSet resultado = comando.executeQuery();

		Fabricante retorno = null;

		if (resultado.next()) {
			retorno = new Fabricante();
			retorno.setCodigo(resultado.getLong("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));
		}

		return retorno;

	}

	public static void main(String[] args) {

		/*
		 * Fabricante f1 = new Fabricante(); f1.setDescricao("DESCRICAO 1");
		 * Fabricante f2 = new Fabricante(); f2.setDescricao("DESCRICAO 2");
		 * 
		 * FabricanteDAO fdao = new FabricanteDAO();
		 * 
		 * try { fdao.salvar(f1); fdao.salvar(f2);
		 * System.out.println("Salvo com sucesso"); } catch (SQLException e) {
		 * System.out.println("N�o salvo...."); e.printStackTrace(); }
		 * 
		 * Fabricante f1 = new Fabricante(); f1.setCodigo(2L);
		 * 
		 * Fabricante f2 = new Fabricante(); f2.setCodigo(3L);
		 * 
		 * FabricanteDAO fdao = new FabricanteDAO(); try { fdao.excluir(f1);
		 * fdao.excluir(f2); System.out.println("Sucesso...."); } catch
		 * (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); System.out.println("Erro....."); }
		 * 
		 * 
		 * Fabricante f1 = new Fabricante(); f1.setCodigo(5L);
		 * f1.setDescricao("DESCRICAO 3");
		 * 
		 * 
		 * FabricanteDAO fdao = new FabricanteDAO(); try { fdao.editar(f1);
		 * System.out.println("SUCESSO..."); } catch (SQLException e) {
		 * System.out.println("Sem Conex�o...."); e.printStackTrace();
		 * 
		 * }
		 */

		Fabricante f1 = new Fabricante();
		f1.setCodigo(1L);

		Fabricante f2 = new Fabricante();
		f2.setCodigo(4L);

		FabricanteDAO fdao = new FabricanteDAO();

		try {
			Fabricante f3 = fdao.buscarPorCodigo(f1);
			Fabricante f4 = fdao.buscarPorCodigo(f2);

			System.out.println("Resultado 1: " + f3);
			System.out.println("Resultado 2: " + f4);

		} catch (SQLException e) {

			System.out.println("Erro....");
			e.printStackTrace();
		}
	}
}
