package org.hibernate.control;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.model.TotalConsultaPorPaciente;

public class ResultSetController extends ModelController {

    @SuppressWarnings("unchecked")
	public List<TotalConsultaPorPaciente> queryTCPP(String sql) {
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.setResultTransformer(new ResultTransformerTCPP());
        List<TotalConsultaPorPaciente> resultSet = query.list();
		return resultSet;
	}
	
    @SuppressWarnings("unchecked")
	public List<TotalConsultaPorPaciente> queryTCPP1(String sql) {
		SQLQuery query = this.getSession().createSQLQuery(sql);
		List<TotalConsultaPorPaciente> finalList = 
				new ArrayList<TotalConsultaPorPaciente>();
		List<Object[]> resultList = query.list();
        for (Object[] row : resultList) {
        	TotalConsultaPorPaciente tcpp = new TotalConsultaPorPaciente();
        	tcpp.setNome((String)row[0]);
        	tcpp.setTotalConsulta((BigInteger)row[1]);
        	finalList.add(tcpp);
        }
		return finalList;
	}

	
}