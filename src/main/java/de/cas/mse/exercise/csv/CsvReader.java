package de.cas.mse.exercise.csv;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import de.cas.mse.exercise.csv.ui.CsvUi;

public class CsvReader {

	private static final String COLUMN_SEPARATOR = ",";
	private CsvUi csvUi;

	public void run(final File csvFile) throws Exception {
		List<String> lines = Files.readAllLines(csvFile.toPath());
		setCaptions(lines);
		setRows(lines);
	}

	private void setRows(List<String> lines) {
		for (int i=1; i<lines.size(); i++) {
			String line = lines.get(i);
			csvUi.addRow(getColumns(line));
		}
	}

	private List<String> getColumns(String line) {
		return Arrays.asList(line.split(COLUMN_SEPARATOR));
	}

	private List<String> getCaptions(List<String> lines) {
		return getColumns(lines.get(0));
	}

	private void setCaptions(List<String> lines) {
		for (String caption : getCaptions(lines)) {
			csvUi.addColumn(caption);
		}
	}

	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

}
