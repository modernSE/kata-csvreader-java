package de.cas.mse.exercise.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import de.cas.mse.exercise.csv.ui.CsvUi;

public class CsvReader {
	private static final String SEPERATOR = ",";
	
	private CsvUi csvUi;

	public void run(final File csvFile) throws Exception {
		CsvContent content = readCsvContent(csvFile);
		displayContent(content);
	}

	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

	private CsvContent readCsvContent(final File csvFile) throws FileNotFoundException {
		CsvFileReader reader = new CsvFileReader(SEPERATOR); // nice splitting!
		CsvContent content = reader.readContent(csvFile);  // good: own data type
		return content;
	}
	
	// not correct; doesn't actually display content
	private void displayContent(CsvContent content) {
		setColumnHeaders(content);
		displayRows(content);
	}

	private void displayRows(CsvContent content) {
		for (List<String> row : content.getRows()) {
			csvUi.addRow(row);
		}
	}

	private void setColumnHeaders(CsvContent content) {
		for (String columnHeader : content.getHeader()) {
			csvUi.addColumn(columnHeader);
		}
	}

}
