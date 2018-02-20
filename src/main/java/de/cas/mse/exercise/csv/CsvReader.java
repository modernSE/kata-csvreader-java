package de.cas.mse.exercise.csv;

import java.io.File;

import de.cas.mse.exercise.csv.ui.CsvUi;

public class CsvReader {

	private CsvUi csvUi;

	public void run(final File csvFile) throws Exception {
		CsvParser parser = new CsvParser(csvFile);
		csvUi.setColumnCount(parser.getColumnCount());
		for(int i= 0; i < parser.getRowCount(); i++) {
			csvUi.addRow(parser.getRow(i));
		}
	}

	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

}
