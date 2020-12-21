package org.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ExemploJDBCGeral {

	private Connection conn = null;
	private String driver = "org.postgresql.Driver";
	private String url = "jdbc:postgresql://localhost:5432/postgres";
	private String user = "postgres";
	private String password = "master";
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private boolean getConnection() {
		try {
			Class.forName(driver);
			conn =  DriverManager.getConnection(url, 
					user, password);
		} catch (SQLException exc) {
			exc.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


	private void close() {
		try {
			if (conn != null)  {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void insertPacienteStt(int cod, String nome, String cidade, 
			String uf, String endereco, Calendar dataNasc) throws SQLException {

		Statement stt = null;
		try {
			stt = conn.createStatement();
			stt.executeUpdate("insert into paciente (cod_paciente," +
					" nome, cidade, uf, endereco, data_nasc) values" +
					"('"+cod+"','"+nome+"','"+cidade+"','"+
					uf+"','"+endereco+"','"+sdf.format(dataNasc.getTime())+"')");
		} finally {
			if (stt != null) stt.close();
		}
	}

	private void insertPacientePst(int cod, String nome, String cidade, 
			String uf, String endereco, Calendar dataNasc) throws SQLException {

		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("insert into paciente (cod_paciente," +
					" nome, cidade, uf, endereco, data_nasc) values (?,?,?,?,?,?)");
			pst.setInt(1, cod);
			pst.setString(2, nome);
			pst.setString(3, cidade);
			pst.setString(4, uf);
			pst.setString(5, endereco);
			pst.setDate(6, new java.sql.Date(dataNasc.getTime().getTime()));
			pst.executeUpdate();
		} finally {
			if (pst != null) pst.close();
		}
	}

	private void list(String cidade) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			pst = conn.prepareStatement("select cod_paciente, nome, endereco, data_nasc " +
					"from paciente where cidade = ?");
			pst.setString(1, cidade);
			rst = pst.executeQuery();
			while (rst.next()) {
				System.out.println("Código: "+rst.getInt(1)+
						" Nome: "+rst.getString(2)+
						" Endereço: "+rst.getString(3)+
						" Data Nascimento: "+sdf.format(rst.getDate(4)));
			}
		} catch (SQLException exc) {
			exc.printStackTrace();
		} finally {
			if (rst != null) rst.close();
			if (pst != null) pst.close();
		}
	}

	public static void main(String[] args) {
		boolean connected = false;

		ExemploJDBCGeral ec = new ExemploJDBCGeral();

		connected = ec.getConnection();

		if (connected) {
			System.out.println("Conexão realizada com sucesso");
			try {
				ec.insertPacienteStt(1, "Paciente 1", "Araranguá", "SC", "R. das Oliveiras", 
						new GregorianCalendar(1980,Calendar.MARCH,1));
				ec.insertPacientePst(2, "Paciente 2", "Arroio do Silva", "SC", "R. das Palmeiras", 
						new GregorianCalendar(1978,Calendar.FEBRUARY,27));
				System.out.println("Inserções realizadas com sucesso.");
				ec.list("Araranguá");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ec.close();
		} else
			System.out.println("Não foi possível " +
					" estabelecer a conexão com o banco de dados");
	}

}
