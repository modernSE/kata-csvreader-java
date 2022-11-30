package de.cas.mse.exercise.csv;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import de.cas.mse.exercise.csv.ui.CsvUi;

public class CsvReader {
	
	private CsvUi csvUi;

	public void run(File file) throws Exception {

		var rows = parseRows(file);

		rows.stream()
			.findFirst()
			.map(CsvRow::columnCount)
			.ifPresent(csvUi::setColumnCount);
		
		rows.stream()
			.map(CsvRow::values)
			.forEach(csvUi::addRow);
	}

	private List<CsvRow> parseRows(File file) throws IOException {
		return Files
			.lines(file.toPath())
			.map(this::parseLine)
			.map(CsvRow::new)
			.toList();
	}

	private List<String> parseLine(String line) {
		return List.of(line.split(","));
	}
	
	public void setCsvUi(CsvUi csvUi) {
		this.csvUi = csvUi;
	}

	private record CsvRow(List<String> values) {
		int columnCount() {
			return values.size();
		}
	}

}
