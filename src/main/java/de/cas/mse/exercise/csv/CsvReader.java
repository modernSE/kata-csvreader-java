package de.cas.mse.exercise.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import de.cas.mse.exercise.csv.ui.CsvUi;

public class CsvReader {
	
	private CsvUi csvUi;

	public void run(final File csvFile) throws Exception {
		List<List<String>> lines = readFile(csvFile);
		processFile(lines);
	}

	private List<List<String>> readFile(File csvFile) throws IOException {
		try(FileReader fileReader = new FileReader(csvFile)) {
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String line = null;
			List<List<String>> result = new LinkedList<>();
			while ((line = bufferedReader.readLine()) != null) {
				String[] columns = line.split(",");
				result.add(Arrays.asList(columns));
			}
			return result;
		}
	}

	private void processFile(List<List<String>> rows) {
		int columnCount = rows.get(0).size();
		csvUi.setColumnCount(columnCount);
		for (List<String> row : rows) {
			csvUi.addRow(row);
		}
	}

	private void setNumberOfColumns() {
	}

	private void fillTable() {
	}

	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

}
