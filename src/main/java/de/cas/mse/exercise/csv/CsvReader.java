package de.cas.mse.exercise.csv;

import java.io.File;
import java.nio.file.Files;

import de.cas.mse.exercise.csv.ui.CsvUi;

public class CsvReader {
	
	private CsvUi csvUi;

	// No primitive type
	// No getter/setter
	public void run(final File csvFile) throws Exception {
		UnparsedCsvFile unparsedFile = new UnparsedCsvFile(Files.readAllLines(csvFile.toPath()));
		CsvFile parsedFile = unparsedFile.parse();
		parsedFile.addToUi(csvUi);
	}
	
	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

}
