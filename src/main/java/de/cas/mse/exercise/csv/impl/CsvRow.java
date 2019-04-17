package de.cas.mse.exercise.csv.impl;

import java.util.List;

public class CsvRow {

	private final List<String> cells;
	
	public CsvRow(List<String> cells) {
		this.cells = cells;
	}
	
	public List<String> getCells() {
		return this.cells;
	}
}
