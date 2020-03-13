package de.cas.mse.exercise.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import de.cas.mse.exercise.csv.ui.CsvUi;
import de.cas.mse.exercise.csv.ui.Filterable;

public class CsvReader implements Filterable{
	
	private CsvUi csvUi;
	private List<List<String>> rows = new ArrayList<List<String>>();

	public void run(final File csvFile) throws Exception {
		List<String> headers = getHeaders(csvFile);
		rows = getRows(csvFile);
		for(String caption : headers) {
			csvUi.addColumn(caption);
		}
		for(List<String> row : rows) {
			csvUi.addRow(row);
		}
	}
	
	private List<List<String>> getRows(final File csvFile) throws FileNotFoundException {
		Scanner csvScanner = new Scanner(csvFile);
		List<List<String>> rows = new ArrayList<List<String>>();
		csvScanner.nextLine();
		while(csvScanner.hasNext()) {
			List<String> row = getNextRow(csvScanner);
			rows.add(row);
		}
		csvScanner.close();
		return rows;
	}

	private List<String> getHeaders(final File csvFile) throws FileNotFoundException {
		Scanner csvScanner = new Scanner(csvFile);
		List<String> header = getNextRow(csvScanner);
		csvScanner.close();
		return header;
	}

	private List<String> getNextRow(final Scanner csvScanner) {
		return Arrays.asList(csvScanner.nextLine().split(","));
	}

	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

	@Override
	public List<List<String>> filter(int col, String word) {
		List<List<String>> filteredRows = new ArrayList<List<String>>();
		for(List<String> row : rows) {
			if(row.get(col - 1).contains(word)) {
				filteredRows.add(row);
			}
		}
		return filteredRows;
	}

}
