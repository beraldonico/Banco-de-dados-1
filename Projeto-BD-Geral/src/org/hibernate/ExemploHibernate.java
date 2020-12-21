package org.hibernate;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.hibernate.control.ModelController;
import org.hibernate.control.ResultSetController;
import org.model.Consulta;
import org.model.Medico;
import org.model.Paciente;
import org.model.TotalConsultaPorPaciente;

public class ExemploHibernate {

	private ModelController modelCtr = new ModelController();

	public void close() {
		modelCtr.close();
	}

	public void clean() {
		modelCtr.deleteAll();
	}

	public void inserts() {
		Calendar dn1 = new GregorianCalendar(1980,Calendar.MARCH,1);
		Paciente pac1 = new Paciente("Paciente 1", "Araranguá", "SC", "R. das Oliveiras", dn1.getTime());
		modelCtr.insert(pac1);

		Calendar dn2 = new GregorianCalendar(1978,Calendar.FEBRUARY,27);
		Paciente pac2 = new Paciente("Paciente 2", "Arroio do Silva", "SC", "R. das Palmeiras", dn2.getTime());
		modelCtr.insert(pac2);

		Medico med1 = new Medico(1, "Medico 1");
		modelCtr.insert(med1);

		Medico med2 = new Medico(2, "Medico 2");
		modelCtr.insert(med2);

		Medico med3 = new Medico(3, "Medico 3");
		modelCtr.insert(med3);

		Consulta cons1 = new Consulta(1, pac1, med1, new Date(), 100.0);
		modelCtr.insert(cons1);
		Consulta cons2 = new Consulta(2, pac1, med2, new Date(), 110.0);
		modelCtr.insert(cons2);
		Consulta cons3 = new Consulta(3, pac2, med2, new Date(), 110.0);
		modelCtr.insert(cons3);
		Consulta cons4 = new Consulta(4, pac1, med1, new Date(), 50.0);
		modelCtr.insert(cons4);

		System.out.println("Inserções realizazadas com sucesso!!!");

	}

	@SuppressWarnings("unchecked")
	public void print() {
		int idPaciente = 73;
		//Alterar a chamada em função do último valor da sequence
		
		System.out.println("\n-- Relação de Pacientes por Id - Identificador: "+idPaciente+" --");
		Paciente pac = (Paciente)modelCtr.getByCode(Paciente.class, idPaciente);
		if (pac != null)
			System.out.println("Id: "+pac.getId()+" Nome: "+pac.getNome()+"\n");

		//Lista pacientes
		System.out.println("\n-- Relação de Pacientes --");
		List<Paciente> listPaciente = (List<Paciente>)modelCtr.list(Paciente.class);
		for (Paciente paciente : listPaciente) {
			System.out.println("Id: "+paciente.getId()+" Nome: "+paciente.getNome());
		}

		//Lista médicos
		System.out.println("\n-- Relação de Médicos --");
		List<Medico> listMedico = (List<Medico>)modelCtr.list(Medico.class);
		for (Medico medico : listMedico) {
			System.out.println("Id: "+medico.getId()+" Nome: "+medico.getNome());
		}

		//Lista consulta
		System.out.println("\n-- Relação de Consultas --");
		List<Consulta> listConsulta = (List<Consulta>)modelCtr.list(Consulta.class);
		for (Consulta consulta : listConsulta) {
			System.out.println("Id: "+consulta.getId()+" Paciente: "+consulta.getPaciente().getNome()+
					" Médico: "+consulta.getMedico().getNome()+ " Valor: "+consulta.getValor());
		}
		
		//Lista Paciente (consulta geral)
		System.out.println("\n-- Relação de Pacientes --");
		String sql = "select * from paciente where cod_paciente = :cod_paciente";
		
		Map<String, Integer> parameters = new LinkedHashMap<String, Integer>();
		parameters.put("cod_paciente", idPaciente);

		listPaciente = (List<Paciente>)modelCtr.query(sql, parameters, Paciente.class);
		for (Paciente paciente : listPaciente) {
			System.out.println("Id: "+paciente.getId()+" Nome: "+paciente.getNome());
		}
		
		//Lista do Total de Consultas por Paciente
		ResultSetController rsCtr = new ResultSetController();
		System.out.println("\n-- Total de Consultas por Paciente --");
		sql = "select p.nome, count(*) as total_consulta"
				+ " from consulta as c, paciente as p "
				+ " where c.cod_paciente = p.cod_paciente "
				+ " group by p.nome"
				+ " order by 2";
		List<TotalConsultaPorPaciente> listTCPP = 
				(List<TotalConsultaPorPaciente>)rsCtr.queryTCPP(sql);
		for (TotalConsultaPorPaciente tcpp : listTCPP) {
			System.out.println("Nome: "+tcpp.getNome()+
					" total de consultas: "+tcpp.getTotalConsulta());
		}
		
		System.out.println("\n-- Total de Consultas por Paciente --");
		List<TotalConsultaPorPaciente> listTCPP1 = 
				(List<TotalConsultaPorPaciente>)rsCtr.queryTCPP1(sql);
		for (TotalConsultaPorPaciente tcpp : listTCPP1) {
			System.out.println("Nome: "+tcpp.getNome()+
					" total de consultas: "+tcpp.getTotalConsulta());
		}
		
	}

	public static void main(String[] args) {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.INFO);
		ExemploHibernate ex = new ExemploHibernate();
		ex.clean();
		ex.inserts();
		ex.print();
		ex.close();
	}

}