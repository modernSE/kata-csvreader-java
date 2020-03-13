package de.cas.mse.exercise.csv;

import de.cas.mse.exercise.csv.ui.CsvUi;

public class CsvUiHandler { // imo unneeded wrapper class
	private CsvUi csvUi;
	
	
	void addCsvFileDataToUi(CsvFileData data) {
		data.getHeaders().forEach(csvUi::addColumn);
		data.getContent().forEach(csvUi::addRow);
	}


	void setCsvUi(CsvUi csvUi) {
		this.csvUi = csvUi;
	}
	
}
