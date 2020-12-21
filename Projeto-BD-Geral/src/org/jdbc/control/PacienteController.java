package org.jdbc.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jdbc.connection.DatabasePool;
import org.model.Paciente;
import org.model.PropriedadeConexao;

public class PacienteController implements IController {

	private PropriedadeConexao pcon;
	private Connection conn = null;

	private static String INSERT = 
			"insert into paciente " +
					" (cod_paciente, nome, cidade, uf, endereco, data_nasc)" +
					" values (?,?,?,?,?,?)";

	public void setPropriedadeConexao(final PropriedadeConexao cp) {
		this.pcon = cp;
	}

	public void insert(final Object paciente) {
		PreparedStatement pst = null;
		Paciente pac = (Paciente)paciente;
		try {
			conn = DatabasePool.getConnection(pcon);
			pst = conn.prepareStatement(INSERT);
			pst.setInt(1, pac.getId());
			pst.setString(2, pac.getNome());
			pst.setString(3, pac.getCidade());
			pst.setString(4, pac.getUf());
			pst.setString(5, pac.getEndereco());
			pst.setDate(6, new java.sql.Date(pac.getDataNasc().getTime()));
			pst.executeUpdate();
			System.out.println("Cliente "+pac.getId()+" inserido com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pst != null)
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public List<Paciente> getList() {
		PreparedStatement pst = null;
		List<Paciente> list = new ArrayList<Paciente>();
		ResultSet rst = null;
		try {
			conn = DatabasePool.getConnection(pcon);

			pst = conn.prepareStatement("select cod_paciente, nome, cidade, uf, endereco, data_nasc "
					+ "from paciente");
			rst = pst.executeQuery();
			while (rst.next()) {
				Paciente paciente = new Paciente(rst.getInt(1), rst.getString(2),
						rst.getString(3), rst.getString(4), rst.getString(5),
						rst.getDate(6));	
				list.add(paciente);
			}
		} catch (SQLException exc) {
			exc.printStackTrace();
		} finally {
			try {
				if (rst != null) rst.close();
				if (pst != null) pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public Paciente getByCode(Integer id) {
		PreparedStatement pst = null;
		Paciente paciente = new Paciente();
		ResultSet rst = null;
		try {
			conn = DatabasePool.getConnection(pcon);
			pst = conn.prepareStatement("select cod_paciente, nome, cidade, uf, endereco, data_nasc "
					+ "from paciente "
					+ "where cod_paciente = ?");
			pst.setInt(1, id);
			rst = pst.executeQuery();
			rst.next();
			paciente = new Paciente(rst.getInt(1), rst.getString(2),
					rst.getString(3), rst.getString(4), rst.getString(5),
					rst.getDate(6));	
		} catch (SQLException exc) {
			exc.printStackTrace();
		} finally {
			try {
				if (rst != null) rst.close();
				if (pst != null) pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return paciente;
	}

}
