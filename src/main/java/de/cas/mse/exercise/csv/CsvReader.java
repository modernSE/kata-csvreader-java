package de.cas.mse.exercise.csv;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import de.cas.mse.exercise.csv.ui.CsvUi;

public class CsvReader {
	
	private static final int HEADER_ROW_COUNT = 1;
	private static final String SEPERATOR = ",";
	private CsvUi csvUi;

	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

	public void run(final File csvFile) throws Exception {
		
		List<String> allLines = Files.readAllLines(csvFile.toPath());
		List<List<String>> rows = extractRows(allLines);
		
		addHeader(rows);
		addDataRows(rows);
	}

	private List<List<String>> extractRows(List<String> allLines) {
		return allLines
			.stream()
			.map(this::extractColumnValues)
			.toList();
	}

	private List<String> extractColumnValues(String line) {
		return Arrays.asList(line.split(SEPERATOR));
	}

	private void addHeader(List<List<String>> rows) {
		List<String> header = rows.get(0);
		header.forEach(csvUi::addColumn);
	}

	private void addDataRows(List<List<String>> rows) {
		rows.stream()
			.skip(HEADER_ROW_COUNT)
			.forEach(csvUi::addRow);
	}

	

}
