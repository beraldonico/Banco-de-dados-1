package org.jdbc;

import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.jdbc.connection.DatabasePool;
import org.jdbc.control.ConsultaController;
import org.jdbc.control.MedicoController;
import org.jdbc.control.PacienteController;
import org.model.Consulta;
import org.model.Medico;
import org.model.Paciente;
import org.model.PropriedadeConexao;

public class ExemploJDBC {

	private PropriedadeConexao pcon;
	private PacienteController pacCtr = new PacienteController();
	private MedicoController medCtr = new MedicoController();
	private ConsultaController consCtr = new ConsultaController();

	private void createConnection() throws Exception {
		int id = 1;
//		String driver = "com.mysql.jdbc.Driver";
//		String url = "jdbc:mysql://127.0.0.1:3306/audpublica?createDatabaseIfNotExist=true";
//		String user = "audpublica";
//		String password = "SrV2Z8xJxB3mK2qb";
		
		String driver = "org.postgresql.Driver";
		String url = "jdbc:postgresql://192.168.0.232:5432/oppcon";
		String user = "oppcon_web";
		String password = "oppcon_web";
		pcon = new PropriedadeConexao(id, driver, url, user, password);
		DatabasePool.create(pcon);
		System.out.println("Conexão executada com sucesso");
	}

	private void closeConnection() throws Exception {
		DatabasePool.close(pcon);
	}

	private void clean() throws Exception {
		Connection conn = null;
		Statement stt = null;
		try {
			conn = DatabasePool.getConnection(pcon);
			stt = conn.createStatement();
			stt.executeUpdate("delete from consulta");
			stt.executeUpdate("delete from paciente");
			stt.executeUpdate("delete from medico");
			System.out.println("Exclusões realizadas om sucesso.");
		} catch (Exception e) {
			throw new Exception("Erro durante a exclusão das tabelas", e);
		} finally {
			if (stt != null) stt.close();
		}
	}

	public void inserts() {
		pacCtr.setPropriedadeConexao(pcon);
		medCtr.setPropriedadeConexao(pcon);
		consCtr.setPropriedadeConexao(pcon);
		
		Calendar dn1 = new GregorianCalendar(1980,Calendar.MARCH,1);
		Paciente pac1 = new Paciente(1, "Paciente 1", "Araranguá", "SC", "R. das Oliveiras", dn1.getTime());
		pacCtr.insert(pac1);

		Calendar dn2 = new GregorianCalendar(1978,Calendar.FEBRUARY,27);
		Paciente pac2 = new Paciente(2, "Paciente 2", "Arroio do Silva", "SC", "R. das Palmeiras", dn2.getTime());
		pacCtr.insert(pac2);

		Medico med1 = new Medico(1, "Medico 1");
		medCtr.insert(med1);

		Medico med2 = new Medico(2, "Medico 2");
		medCtr.insert(med2);

		Medico med3 = new Medico(3, "Medico 3");
		medCtr.insert(med3);

		Consulta cons1 = new Consulta(1, pac1, med1, new Date(), 100.0);
		consCtr.insert(cons1);
		Consulta cons2 = new Consulta(2, pac1, med2, new Date(), 110.0);
		consCtr.insert(cons2);
		Consulta cons3 = new Consulta(3, pac2, med2, new Date(), 110.0);
		consCtr.insert(cons3);
		Consulta cons4 = new Consulta(4, pac1, med1, new Date(), 50.0);
		consCtr.insert(cons4);

	}

	public void print() {
		Paciente pac = pacCtr.getByCode(1);
		System.out.println("\nId: "+pac.getId()+" Nome: "+pac.getNome());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		//Lista pacientes
		System.out.println("\n-- Relação de Pacientes --");
		List<Paciente> listPaciente = pacCtr.getList();
		for (Paciente paciente : listPaciente) {
			System.out.println("Id: "+paciente.getId()+" Nome: "+paciente.getNome()+
					" Data Nascimento: "+sdf.format(paciente.getDataNasc()));
		}

		//Lista médicos
		System.out.println("\n-- Relação de Médicos --");
		List<Medico> listMedico = medCtr.getList();
		for (Medico medico : listMedico) {
			System.out.println("Id: "+medico.getId()+" Nome: "+medico.getNome());
		}

		//Lista consulta
		System.out.println("\n-- Relação de Consultas --");
		List<Consulta> listConsulta = consCtr.getList();
		for (Consulta consulta : listConsulta) {
			System.out.println("Id: "+consulta.getId()+" Paciente: "+consulta.getPaciente().getNome()+
					" Médico: "+consulta.getMedico().getNome()+ " Valor: "+consulta.getValor());
		}

	}

	public static void main(String[] args) {

		ExemploJDBC ex = new ExemploJDBC();
		try {
			ex.createConnection();
//			ex.clean();
//			ex.inserts();
//			ex.print();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ex.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}