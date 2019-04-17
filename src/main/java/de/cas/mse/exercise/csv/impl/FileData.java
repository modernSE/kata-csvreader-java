package de.cas.mse.exercise.csv.impl;

import java.util.List;

public class FileData {
	
	private final int columnCount;
	
	private final List<CsvRow> rows;
	
	public FileData(List<CsvRow> rows, int columnCount) {
		this.rows = rows;
		this.columnCount = columnCount;
	}
	
	public List<CsvRow> getRows() {
		return this.rows;
	}
	
	public int getColumnCount() {
		return this.columnCount;
	}

}
