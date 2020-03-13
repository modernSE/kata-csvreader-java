package de.cas.mse.exercise.csv;

import java.util.List;

public class CsvContent {
	private List<String> header;
	private List<List<String>> rows;
	
	public CsvContent(List<String> header, List<List<String>> rows) {
		super();
		this.header = header;
		this.rows = rows;
	}

	public List<String> getHeader() {
		return header;
	}

	public List<List<String>> getRows() {
		return rows;
	}
}
