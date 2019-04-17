package de.cas.mse.exercise.csv.ui;

import java.util.List;

public class FileData {

	private final List<String> columnHeaders;

	private final List<CharRow> rows;

	public FileData(List<String> columnHeaders, List<CharRow> rows) {
		super();
		this.columnHeaders = columnHeaders;
		this.rows = rows;
	}

	public List<String> getColumnHeaders() {
		return columnHeaders;
	}

	public List<CharRow> getRows() {
		return rows;
	}
}
