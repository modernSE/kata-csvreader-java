package de.cas.mse.exercise.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import de.cas.mse.exercise.csv.ui.CsvUi;
import de.cas.mse.exercise.csv.ui.Filterable;

public class CsvReader implements Filterable {

	private static final String DELIMITER = ",";

	private CsvUi csvUi;

	private List<List<String>> contentRows;

	public void run(final File csvFile) throws Exception {
		List<List<String>> rows = readRowsFromFile(csvFile);
		List<String> headerRow = getHeader(rows);
		contentRows = getContent(rows);
		setColumnHeadings(headerRow);
		addRowsToUi(contentRows);
	}

	private List<List<String>> getContent(List<List<String>> rows) {
		return rows.subList(1, rows.size());
	}

	private List<String> getHeader(List<List<String>> rows) {
		return rows.get(0);
	}

	private void addRowsToUi(List<List<String>> rows) {
		for (List<String> row : rows) {
			csvUi.addRow(row);
		}
	}

	private List<List<String>> readRowsFromFile(final File csvFile) throws FileNotFoundException {
		List<List<String>> rows = new LinkedList<>();
		try (Scanner csvScanner = new Scanner(csvFile)) {

			while (csvScanner.hasNext()) {
				List<String> row = readColumnsOfRow(csvScanner);
				rows.add(row);
			}
		}
		return rows;
	}

	private List<String> readColumnsOfRow(Scanner csvScanner) {
		String csvRow = csvScanner.nextLine();
		String[] columnsOfRow = csvRow.split(DELIMITER);
		return Arrays.asList(columnsOfRow);
	}

	private void setColumnHeadings(List<String> headerRow) {
		for (String caption : headerRow) {
			csvUi.addColumn(caption);
		}
	}

	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

	@Override
	public List<List<String>> filter(int col, String word) {
		List<List<String>> relevantRows = new ArrayList<List<String>>();
		for (List<String> row : contentRows) {
			if (isRelevant(col, word, row)) {
				relevantRows.add(row);
			}
		}
		return relevantRows;
	}

	private boolean isRelevant(int col, String word, List<String> row) {
		return row.get(col - 1).contains(word);
	}

}
