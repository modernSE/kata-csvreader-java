package de.cas.mse.exercise.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

import de.cas.mse.exercise.csv.ui.CsvUi;

public class CsvReader {
	
	private CsvUi csvUi;

	private static final String DELIMETER = ",";

	public void run(final File csvFile) throws Exception {
		// TODO: Start coding here
		BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile));

		bufferedReader.lines().limit(1).forEach(this::setUpUiSkeleton);
		bufferedReader.lines().skip(1).forEach(this::addLine);
	}

	private void addLine(String line) {
		String[] splitLine = line.split(DELIMETER);
		csvUi.addRow(Arrays.asList(splitLine));
	}

	private void setUpUiSkeleton(String headLine) {
		String[] splitLine = headLine.split(DELIMETER);
		Arrays.stream(splitLine).forEach(csvUi::addColumn);
	}

	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

}
