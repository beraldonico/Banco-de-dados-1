package org.jdbc.control;

import java.util.List;

public interface IController {

	public void insert(Object object);
	public List<?> getList();
	public Object getByCode(Integer id);
	
}
