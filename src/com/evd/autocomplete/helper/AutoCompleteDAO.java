package com.evd.autocomplete.helper;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
* jQueryUI AutoComplete Plugin With Hibernate and Spring
*  
* @author CHAB SIHENG
* @version v1.0
* @email {@link chabsiheng@gmail.com}
* 
*/

public class AutoCompleteDAO extends HibernateDaoSupport implements
		AutoCompleteDAOI {
	private int maxResult;

	@SuppressWarnings("deprecation")
	@Override
	public List<?> getAutoCompleteRecord(String strQuery) throws HibernateException {
		try {
			Session session = getSession();
			Query query = session.createQuery(strQuery);
			if (this.maxResult > 0){
				query.setMaxResults(maxResult);
			}
			return query.list();
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new HibernateException("E-00005");
		}
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

}
