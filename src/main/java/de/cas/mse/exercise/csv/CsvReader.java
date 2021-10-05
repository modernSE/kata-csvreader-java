package de.cas.mse.exercise.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

import de.cas.mse.exercise.csv.ui.CsvUi;

public class CsvReader {
	
	private CsvUi csvUi;

	public void run(final File csvFile) throws Exception {
		CsvFileImporter importer = new CsvFileImporter(csvFile);
		var csvTable = importer.import();

		csvTable.getHeader().values.forEach(csvUi::addColumn);
		csvTable.getBody().stream().map(CsvRow::getValues).forEach(csvUi::addRow);
	}
	
	

	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

}
