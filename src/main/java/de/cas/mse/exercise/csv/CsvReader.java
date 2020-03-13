package de.cas.mse.exercise.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import de.cas.mse.exercise.csv.ui.CsvUi;

public class CsvReader {

	private CsvUi csvUi;

	public void run(final File csvFile) throws Exception {
		List<List<String>> csvRows = readCSV(csvFile, ",");
		
		//könnte in eigene Methode ausgelagert werden, um künftige Anpassungen an den Headern zu erleichtern
		List<String> csvHeader = csvRows.get(0);
		addHeaderCaptions(csvHeader);
		
		//wie oben, nur für den content
		List<List<String>> csvContents = csvRows.subList(1, csvRows.size());
		addRowContents(csvContents);
	}

	private List<List<String>> readCSV(File csvFile, final String delimiter) throws FileNotFoundException {
		List<List<String>> rows = new ArrayList<>();
		try (Scanner fileScanner = new Scanner(csvFile)) {
			while (fileScanner.hasNext()) {
				String row = fileScanner.nextLine();
				List<String> rowCells = Arrays.asList(row.split(delimiter));
				rows.add(rowCells);
			}
		}
		return rows;
	}

	private void addRowContents(List<List<String>> csvRows) {
		for (List<String> row : csvRows) {
			csvUi.addRow(row);
		}
	}

	private void addHeaderCaptions(List<String> headerCaptions) {
		for (String caption : headerCaptions) {
			csvUi.addColumn(caption);
		}
	}

	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

}
