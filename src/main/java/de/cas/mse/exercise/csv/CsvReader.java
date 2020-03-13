package de.cas.mse.exercise.csv;

import java.io.File;

import de.cas.mse.exercise.csv.ui.CsvUi;

public class CsvReader {
	private CsvUiHandler csvUiHandler = new CsvUiHandler();
	
	public void run(final File csvFile) throws Exception {
		CsvFileHandler fileHandler = new CsvFileHandler(csvFile, ",");
		CsvFileData fileData = fileHandler.getFileContentWithHeaders();
		csvUiHandler.addCsvFileDataToUi(fileData);
	}
	
	public void setCsvUi(final CsvUi csvUi) {
		csvUiHandler.setCsvUi(csvUi);
		
	}

}
