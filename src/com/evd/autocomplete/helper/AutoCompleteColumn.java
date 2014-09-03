package com.evd.autocomplete.helper;

/**
* jQueryUI AutoComplete Plugin With Hibernate and Spring
*  
* @author CHAB SIHENG
* @version v1.0
* @email {@link chabsiheng@gmail.com}
* 
*/

public class AutoCompleteColumn {
	private String columnId;
	private String columnLabel;
	private String columnValue;

	public AutoCompleteColumn() {
		super();
	}

	public AutoCompleteColumn(String columnId, String columnLabel,
			String columnValue) {
		super();
		this.columnId = columnId;
		this.columnLabel = columnLabel;
		this.columnValue = columnValue;
	}

	public String getColumnId() {
		return columnId;
	}

	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

	public String getColumnLabel() {
		return columnLabel;
	}

	public void setColumnLabel(String columnLabel) {
		this.columnLabel = columnLabel;
	}

	public String getColumnValue() {
		return columnValue;
	}

	public void setColumnValue(String columnValue) {
		this.columnValue = columnValue;
	}
}
