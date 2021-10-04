package de.cas.mse.exercise.csv;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import de.cas.mse.exercise.csv.ui.CsvUi;

public class CsvReader {
	
	private CsvUi csvUi;

	public void run(final File csvFile) throws Exception {
		
		List<String> allRows = Files.readAllLines(csvFile.toPath());
		List<String> columnNames = parseRowOrHeader(allRows.get(0));
		Stream<String> dataRows = allRows.stream().skip(1);

		columnNames
			.forEach(csvUi::addColumn);
		dataRows
			.map(this::parseRowOrHeader)
			.forEach(csvUi::addRow);
	}

	private List<String> parseRowOrHeader(String row) {
		List<String> columns = Arrays.asList(row.split(","));
		return columns;
	}

	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

}
