package de.cas.mse.exercise.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import de.cas.mse.exercise.csv.ui.CsvUi;

public class CsvReader {
	
	private static final String SEPERATOR = ",";
	private CsvUi csvUi;

	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

	public void run(final File csvFile) throws Exception {
		List<String> lines =  Files.readAllLines(csvFile.toPath());
		publishHeader(lines.getFirst());
		publishRows(lines.subList(1, lines.size()));
	}

	private void publishHeader(String headerLine) {
		String[] headerNames = headerLine.split(SEPERATOR);
		for(String headerName : headerNames){
			csvUi.addColumn(headerName);
		}
	}

	private void publishRows(List<String> lines) {
		for(String line : lines) {
			String[] cells = line.split(SEPERATOR);
			csvUi.addRow(Arrays.asList(cells));
		}
	}
}
