package de.cas.mse.exercise.csv;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.cas.mse.exercise.csv.ui.CsvUi;
import de.cas.mse.exercise.csv.ui.Filterable;

public class CsvReader implements Filterable {
	
	private static final String SEPARATOR = ",";
	
	private CsvUi csvUi;
	private List<String> headerValues;
	private List<List<String>> cellValues;

	public void run(final File csvFile) throws Exception {
		parseCsv(csvFile);
		
		setHeaderData();
		setColumnData();
	}

	private void parseCsv(File csvFile) throws IOException {
		List<String> lines = Files.readAllLines(csvFile.toPath());
		headerValues = Arrays.asList(lines.get(0).split(SEPARATOR));
		
		cellValues = new ArrayList<>();
		for (int i = 1; i < lines.size(); i++) {
			cellValues.add(Arrays.asList(lines.get(i).split(SEPARATOR)));
		}
	}

	private void setHeaderData() {
		for (String columnName : headerValues) {
			csvUi.addColumn(columnName);
		}
	}
	
	private void setColumnData() {
		for (List<String> row : cellValues) {
			csvUi.addRow(row);
		}
	}
	
	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

	@Override
	public List<List<String>> filter(int col, String word) {
		for (List<String> list : cellValues) {
			cellValues.contains(word);
		}
		return null;
	}
}
