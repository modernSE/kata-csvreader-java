package de.cas.mse.exercise.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import de.cas.mse.exercise.csv.ui.CsvUi;

public class CsvReader {
	
	private CsvUi csvUi;

	public void run(final File csvFile) throws Exception {
		// TODO: Start coding here
		BufferedReader reader = readFile(csvFile);

		String line = reader.readLine();
		List<String> row = parseCells(line);
		csvUi.addRow(row);
		csvUi.setColumnCount(row.size());
		
		while((line = reader.readLine()) != null) {
			row = parseCells(line);
			csvUi.addRow(row);
		}
		
	}

	private BufferedReader readFile(final File csvFile) throws FileNotFoundException {
		return new BufferedReader(new FileReader(csvFile));
	}

	private List<String> parseCells(String line) {
		return Arrays.asList(line.split(","));
	}
	
	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

}
