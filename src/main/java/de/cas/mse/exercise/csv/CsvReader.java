package de.cas.mse.exercise.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import de.cas.mse.exercise.csv.ui.CsvUi;
import de.cas.mse.exercise.csv.ui.Filterable;

public class CsvReader implements Filterable {

	private static final String COMMA_SEPARATOR = ",";

	private CsvUi csvUi;
	private List<String> headerRow = new ArrayList<>();
	private List<List<String>> rows = new ArrayList<>();

	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

	public void run(final File csvFile) throws Exception {
		readRowsFromFile(csvFile);

		setHeader();
		setRows();
	}

	private void readRowsFromFile(File csvFile) throws IOException {
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile))) {
			headerRow = readRowFromFile(bufferedReader.readLine());
			String currentLine;
			while ((currentLine = bufferedReader.readLine()) != null) {
				rows.add(readRowFromFile(currentLine));
			}
		}
	}

	private List<String> readRowFromFile(String currentLine) {
		String[] rowValues = currentLine.split(COMMA_SEPARATOR);
		return Arrays.asList(rowValues);
	}

	private void setHeader() {
		headerRow.forEach(colName -> csvUi.addColumn(colName));
	}

	private void setRows() {
		for(int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
			csvUi.addRow(rows.get(rowIndex));
		}
	}

	@Override
	public List<List<String>> filter(int viewColumn, String word) {
		List<List<String>> filteredRows = new ArrayList<>();
		int dataColumnIndex = viewColumnToDataColumn(viewColumn);

		for(int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
			List<String> rowContent = rows.get(rowIndex);
			if(rowContent.get(dataColumnIndex).contains(word)){
				filteredRows.add(rowContent);
			}
		}

		return filteredRows;
	}

	private int viewColumnToDataColumn(int viewColumn) {
		return viewColumn-1;
	}
}
