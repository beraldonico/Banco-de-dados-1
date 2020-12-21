package org.jdbc.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jdbc.connection.DatabasePool;
import org.model.Medico;
import org.model.PropriedadeConexao;

public class MedicoController implements IController {

	private PropriedadeConexao pcon;
	private Connection conn = null;

	private static String INSERT = 
			"insert into medico " +
					" (cod_medico, nome)" +
					" values (?,?)";

	public void setPropriedadeConexao(final PropriedadeConexao cp) {
		this.pcon = cp;
	}

	public void insert(final Object medico) {
		PreparedStatement pst = null;
		Medico med = (Medico)medico;
		try {
			conn = DatabasePool.getConnection(pcon);
			pst = conn.prepareStatement(INSERT);
			pst.setInt(1, med.getId());
			pst.setString(2, med.getNome());
			pst.executeUpdate();
			System.out.println("Médico"+med.getId()+" inserido com sucesso.");
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

	public List<Medico> getList() {
		PreparedStatement pst = null;
		List<Medico> list = new ArrayList<Medico>();
		ResultSet rst = null;
		try {
			conn = DatabasePool.getConnection(pcon);

			pst = conn.prepareStatement("select cod_medico, nome from medico");
			rst = pst.executeQuery();
			while (rst.next()) {
				Medico medico = new Medico(rst.getInt(1), rst.getString(2));	
				list.add(medico);
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

	public Medico getByCode(Integer id) {
		PreparedStatement pst = null;
		Medico medico = new Medico();
		ResultSet rst = null;
		try {
			conn = DatabasePool.getConnection(pcon);
			pst = conn.prepareStatement("select cod_medico, nome from medico "
					+ "where cod_medico = ?");
			pst.setInt(1, id);
			rst = pst.executeQuery();
			rst.next();
			medico = new Medico(rst.getInt(1), rst.getString(2));	
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
		return medico;
	}

}
