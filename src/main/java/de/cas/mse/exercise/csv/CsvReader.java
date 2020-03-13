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
		rows = getRows(csvFile);//with or without headers?
		for(String caption : headers) {//new method "addHeader", inconsistent naming headers vs caption
			csvUi.addColumn(caption);
		}
		for(List<String> row : rows) {//new method
			csvUi.addRow(row);
		}
	}
	
	private List<List<String>> getRows(final File csvFile) throws FileNotFoundException {
		Scanner csvScanner = new Scanner(csvFile);
		List<List<String>> rows = new ArrayList<List<String>>();
		csvScanner.nextLine();//seems arbitrary (does not do what the method name says)
		while(csvScanner.hasNext()) {
			List<String> row = getNextRow(csvScanner);
			rows.add(row);
		}
		csvScanner.close(); // try with auto close
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
			if(row.get(col - 1).contains(word)) {//better readability with "cellContains"-method
				filteredRows.add(row);
			}
		}
		return filteredRows;
	}

}
