package de.cas.mse.exercise.csv;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import de.cas.mse.exercise.csv.ui.CsvUi;

public class CsvReader {
	
	private final static String SEPARATOR = ",";

	private CsvUi csvUi;

	public void run(final File csvFile) throws IOException {
		if (csvFile == null) {
			throw new IllegalArgumentException("File parameter cannot be null!!!");
		}
		if (csvUi == null) {
			throw new IllegalStateException("csvUi not initialized!!!!!");
		}

		List<String> lines = Files.readAllLines(csvFile.toPath());
		if (lines.isEmpty()) {
			return;
		}

		List<String> dataRows = lines.subList(1, lines.size());
		handleHeader(lines.getFirst());
		for (String line : dataRows) {
			List<String> parts = getParts(line);
			csvUi.addRow(parts);
		}
	}
	
	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}


	private void handleHeader(String headerLine){
		List<String> headerParts = getParts(headerLine);
		csvUi.setColumnCount(headerParts.size());
		headerParts.forEach(csvUi::addColumn);
	}

	private List<String> getParts(String line) {
		return Arrays.asList(line.split(SEPARATOR));
	}
}
