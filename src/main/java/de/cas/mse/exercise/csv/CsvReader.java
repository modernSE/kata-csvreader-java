package de.cas.mse.exercise.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import de.cas.mse.exercise.csv.ui.CsvUi;

public class CsvReader {

	private static final String SEPARATOR = ",";

	private CsvUi csvUi;

	public void run(final File csvFile) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(csvFile));
		try {
			String headLine = reader.readLine();
			if (headLine == null) {
				return;
			}
			setHeaderRow(headLine);
			String line = null;
			while((line = reader.readLine()) != null){
				addRow(line);
			}
		} finally {
			reader.close();
		}
	}

	private void setHeaderRow(String headLine) {
		List<String> columnTitles = separateLine(headLine);
		for (String columnTitle : columnTitles) {
			csvUi.addColumn(columnTitle);
		}
	}

	private void addRow(String line) {
		csvUi.addRow(separateLine(line));
	}

	private List<String> separateLine(String line) {
		return Arrays.asList(line.split(SEPARATOR));
	}

	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

}
