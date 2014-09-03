package com.evd.autocomplete.helper;

import java.util.List;

import org.hibernate.HibernateException;

/**
* jQueryUI AutoComplete Plugin With Hibernate and Spring
*  
* @author CHAB SIHENG
* @version v1.0
* @email {@link chabsiheng@gmail.com}
* 
*/

public interface AutoCompleteDAOI {

	public List<?> getAutoCompleteRecord(String strQuery) throws HibernateException;
}
