package com.evd.autocomplete.helper;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

/**
* jQueryUI AutoComplete Plugin With Hibernate and Spring
*  
* @author CHAB SIHENG
* @version v1.0
* @email {@link chabsiheng@gmail.com}
* 
*/

@Service
@Configurable
public abstract class AutoCompleteHelper {

	public enum WhereMatch {
		ALL, START, END
	}

	@Autowired
	AutoCompleteServiceI autocompleteService;

	HttpServletRequest request;
	String searchValue;
	private String searchValueName;
	AutoCompleteColumn column;
	WhereMatch where;
	int maxResult;

	public AutoCompleteHelper(HttpServletRequest request,
			AutoCompleteColumn column, WhereMatch where) throws HibernateException {
		super();
		this.request = request;
		this.column = column;
		this.where = where;
		this.searchValueName = "term";
	}

	public AutoCompleteHelper(HttpServletRequest request, String column,
			WhereMatch where) throws HibernateException {
		super();
		this.request = request;
		this.column = new AutoCompleteColumn(column, column, column);
		this.where = where;
		this.searchValueName = "term";
	}

	public AutoCompleteHelper(HttpServletRequest request,
			AutoCompleteColumn column, WhereMatch where, int maxResult)
			throws HibernateException {
		super();
		this.request = request;
		this.column = column;
		this.where = where;
		this.searchValueName = "term";
		this.maxResult = maxResult;
	}

	public AutoCompleteHelper(HttpServletRequest request, String column,
			WhereMatch where, int maxResult) throws HibernateException {
		super();
		this.request = request;
		this.column = new AutoCompleteColumn(column, column, column);
		this.where = where;
		this.searchValueName = "term";
		this.maxResult = maxResult;
	}

	public AutoCompleteHelper(HttpServletRequest request,
			AutoCompleteColumn column, WhereMatch where, String valueName)
			throws HibernateException {
		super();
		this.request = request;
		this.column = column;
		this.where = where;
		this.searchValueName = valueName;
	}

	public AutoCompleteHelper(HttpServletRequest request, String column,
			WhereMatch where, String valueName) throws HibernateException {
		super();
		this.request = request;
		this.column = new AutoCompleteColumn(column, column, column);
		this.where = where;
		this.searchValueName = valueName;
	}

	public AutoCompleteHelper(HttpServletRequest request,
			AutoCompleteColumn column, WhereMatch where, String valueName,
			int maxResult) throws HibernateException {
		super();
		this.request = request;
		this.column = column;
		this.where = where;
		this.searchValueName = valueName;
		this.maxResult = maxResult;
	}

	public AutoCompleteHelper(HttpServletRequest request, String column,
			WhereMatch where, String valueName, int maxResult)
			throws HibernateException {
		super();
		this.request = request;
		this.column = new AutoCompleteColumn(column, column, column);
		this.where = where;
		this.searchValueName = valueName;
		this.maxResult = maxResult;
	}

	/**
	 * @return FROM Query for Hibernate Ex. FROM evd_users
	 * */
	public abstract String populateAutoComplete();

	public void getAutoCompleteRequest() throws HibernateException {
		searchValue = request.getParameter(searchValueName);
	}

	public String getAutoCompleteResponseQuery() throws HibernateException {
		this.getAutoCompleteRequest();
		if (searchValue == null || searchValue.length() <= 0) {
			return null;
		}

		if (column == null) {
			return null;
		}

		String baseQuery = "SELECT new com.evd.autocomplete.helper.AutoComplete(";
		baseQuery += column.getColumnId() + ", ";
		baseQuery += column.getColumnLabel() + ", ";
		baseQuery += column.getColumnValue() + ") ";
		baseQuery += this.populateAutoComplete().trim();

		String likeQuery = "";
		switch (this.where) {
		case ALL:
			likeQuery = " LIKE \'%" + this.searchValue + "%\'";
			break;
		case START:
			likeQuery = " LIKE \'" + this.searchValue + "%\'";
			break;
		case END:
			likeQuery = " LIKE \'%" + this.searchValue + "\'";
			break;
		}
		baseQuery += " WHERE " + this.column.getColumnLabel() + " " + likeQuery;

		return baseQuery;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}
}
