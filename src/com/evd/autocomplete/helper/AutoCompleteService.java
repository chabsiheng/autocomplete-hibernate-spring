package com.evd.autocomplete.helper;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;

/**
* jQueryUI AutoComplete Plugin With Hibernate and Spring
*  
* @author CHAB SIHENG
* @version v1.0
* @email {@link chabsiheng@gmail.com}
* 
*/

public class AutoCompleteService implements AutoCompleteServiceI {

	@Autowired
	AutoCompleteDAO autocompleteDao;
	
	@Override
	public List<AutoComplete> getAutoCompleteResult(AutoCompleteHelper helper)
			throws HibernateException {
		String query = helper.getAutoCompleteResponseQuery();
		
		if (helper.getMaxResult() > 0){
			autocompleteDao.setMaxResult(helper.maxResult);
		}
		@SuppressWarnings("unchecked")
		List<AutoComplete> lstAutoComplete = (List<AutoComplete>) autocompleteDao.getAutoCompleteRecord(query);
		if (lstAutoComplete == null){
			return null;
		}
		return lstAutoComplete;
	}

}
