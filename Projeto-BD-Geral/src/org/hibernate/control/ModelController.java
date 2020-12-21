package org.hibernate.control;

import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;


public class ModelController implements IController {

	private Session session;

	public ModelController() {
		this.session = GeneralController.getSession();
	}
	
	public Session getSession() {
		return session;
	}

	public void insert(Object object) {		
		this.session.beginTransaction();
		this.session.persist(object);
		this.session.getTransaction().commit();
	}

	public void update(Object object) {
		this.session.beginTransaction();
		this.session.merge(object);
		this.session.getTransaction().commit();
	}

	public void delete(Object object) {
		this.session.beginTransaction();
		this.session.delete(object);
		this.session.getTransaction().commit();
	}
	
	public List<?> list(Class<?> iClass) {
		return (List<?>) this.session.createCriteria(iClass).list();
	}

	public Object getByCode(Class<?> iClass, Integer id) {
		Object object = null;
		try {
			object = this.session.get(iClass, id);
		} catch (Exception exc) {
			return object;
		}
		return object;
	}
	
	public void deleteAll() {
		this.session.beginTransaction();
		this.session.createSQLQuery("delete from consulta").executeUpdate();
		this.session.createSQLQuery("delete from paciente").executeUpdate();
		this.session.createSQLQuery("delete from medico").executeUpdate();
		this.session.getTransaction().commit();
	}
	
	public void close() {
		this.session.close();
	}
	
	public List<?> query(String sql, Map<String,Integer> parameters,  Class<?> iClass) {
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(iClass);
		Integer value;
		for (String key : parameters.keySet()) {
			value = parameters.get(key);
			query.setParameter(key, value);
		}
		List<?> resultSet = query.list();
		return resultSet;
	}
	
/*	
	public List<?> querySQL(String idSql, Map<String,Integer> parameters) {
		SQLQuery query = session.crecreateSQLQuery(idSql);
//		query.addEntity(iClass);
		Integer value;
		for (String key : parameters.keySet()) {
			value = parameters.get(key);
			query.setParameter(key, value);
		}
		List<?> resultSet = query.list();
		return resultSet;
	}
*/


}