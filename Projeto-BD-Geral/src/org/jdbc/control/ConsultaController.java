package org.jdbc.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.jdbc.connection.DatabasePool;
import org.model.Consulta;
import org.model.Medico;
import org.model.Paciente;
import org.model.PropriedadeConexao;

public class ConsultaController implements IController {

	private PropriedadeConexao pcon;
	private Connection conn = null;

	private static String INSERT = 
			"insert into consulta " +
					" (cod_consulta, cod_paciente, cod_medico, data, valor)" +
					" values (?,?,?,?,?)";

	public void setPropriedadeConexao(final PropriedadeConexao cp) {
		this.pcon = cp;
	}

	public void insert(final Object consulta) {
		PreparedStatement pst = null;
		Consulta cons = (Consulta)consulta;
		try {
			conn = DatabasePool.getConnection(pcon);
			pst = conn.prepareStatement(INSERT);
			pst.setInt(1, cons.getId());
			pst.setInt(2, cons.getPaciente().getId());
			pst.setInt(3, cons.getMedico().getId());
			pst.setTimestamp(4, new Timestamp(cons.getData().getTime()));
			pst.setDouble(5, cons.getValor());
			pst.executeUpdate();
			System.out.println("Consulta "+cons.getId()+" inserido com sucesso.");
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

	public List<Consulta> getList() {
		PreparedStatement pst = null;
		List<Consulta> list = new ArrayList<Consulta>();
		ResultSet rst = null;
		try {
			conn = DatabasePool.getConnection(pcon);

			pst = conn.prepareStatement("select cod_consulta, cod_paciente, cod_medico, data, valor from consulta");
			rst = pst.executeQuery();
			while (rst.next()) {
				PacienteController pacCtr = new PacienteController();
				pacCtr.setPropriedadeConexao(pcon);
				Paciente paciente = pacCtr.getByCode(rst.getInt(2));
				MedicoController medCtr = new MedicoController();
				medCtr.setPropriedadeConexao(pcon);
				Medico medico = medCtr.getByCode(rst.getInt(3));
				
				Consulta consulta = new Consulta(rst.getInt(1), paciente, medico,
						rst.getDate(4), rst.getDouble(5));	
				list.add(consulta);
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

	public Consulta getByCode(Integer id) {
		PreparedStatement pst = null;
		Consulta consulta = new Consulta();
		ResultSet rst = null;
		try {
			conn = DatabasePool.getConnection(pcon);
			pst = conn.prepareStatement("select cod_consulta, cod_paciente, cod_medico, data, valor "
					+ "from consulta where cod_consulta = ?");
			pst.setInt(1, id);
			rst = pst.executeQuery();
			rst.next();
			
			PacienteController pacCtr = new PacienteController();
			pacCtr.setPropriedadeConexao(pcon);
			Paciente paciente = pacCtr.getByCode(rst.getInt(2));
			MedicoController medCtr = new MedicoController();
			medCtr.setPropriedadeConexao(pcon);
			Medico medico = medCtr.getByCode(rst.getInt(3));

			consulta = new Consulta(rst.getInt(1), paciente, medico,
					rst.getDate(4), rst.getDouble(5));	
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
		return consulta;
	}

}
