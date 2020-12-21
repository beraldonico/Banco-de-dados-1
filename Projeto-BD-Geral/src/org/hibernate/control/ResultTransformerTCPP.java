package org.hibernate.control;

import java.math.BigInteger;
//import java.util.ArrayList;
import java.util.List;

import org.hibernate.transform.ResultTransformer;
import org.model.TotalConsultaPorPaciente;

public class ResultTransformerTCPP implements ResultTransformer {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	public List transformList(List arg0) {
		return arg0;
	}

	public TotalConsultaPorPaciente transformTuple(Object[] valores, String[] alias) {
		TotalConsultaPorPaciente object = new TotalConsultaPorPaciente();
		object.setNome((String) valores[0]);
		object.setTotalConsulta((BigInteger) valores[1]);
		return object;
	}
	
	
	
//	public List transformList(List arg0) {
////		List<TotalConsultaPorPaciente> list = new ArrayList<TotalConsultaPorPaciente>();
////		for (Object tcpp : arg0) {
////			list.add((TotalConsultaPorPaciente) tcpp);
////		}
////		return list;
//		return arg0;
//	}

}