package de.cas.mse.exercise.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import de.cas.mse.exercise.csv.ui.CsvUi;
import de.cas.mse.exercise.csv.ui.Filterable;

public class CsvReader implements Filterable {
	
	private CsvUi csvUi;

	private static final String DELIMETER = ",";

	private List<List<String>> lines = new LinkedList<>();

	public void run(final File csvFile) throws Exception {
		// TODO: Start coding here
		BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile));

		bufferedReader.lines().limit(1).forEach(this::setUpUiSkeleton);
		bufferedReader.lines().skip(1).forEach(this::addLine);
	}

	private void addLine(String line) {
		String[] splitLine = line.split(DELIMETER);
		csvUi.addRow(Arrays.asList(splitLine));
		lines.add(Arrays.asList(splitLine));
	}

	private void setUpUiSkeleton(String headLine) {
		String[] splitLine = headLine.split(DELIMETER);
		Arrays.stream(splitLine).forEach(csvUi::addColumn);
	}

	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

	@Override
	public List<List<String>> filter(int col, String word) {
		return lines.stream().filter(l -> filterLine(l, col, word)).collect(Collectors.toList());
	}

	private boolean filterLine(List<String> l, int col, String word) {
		if (word != null && !word.equals("")) {
			return l.get(col-1).toLowerCase().contains(word.toLowerCase());
		}
		return true;
	}
}
