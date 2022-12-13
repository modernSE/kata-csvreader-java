package de.cas.mse.exercise.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import de.cas.mse.exercise.csv.ui.CsvUi;

public class CsvReader {
	
	private CsvUi csvUi;

	public void run(final File csvFile) throws Exception {

		BufferedReader reader = new BufferedReader(new FileReader(csvFile));
		
		parseCsv(reader);
		reader.close();
	}

	private void parseCsv(BufferedReader reader) throws IOException {
		String captionLine = reader.readLine();
		setColumnCaptions(captionLine);

		reader.lines().forEach(line -> addLineToCsvUi(line));
	}

	private void setColumnCaptions(String captionLine){
		String[] captions = splitCsvLine(captionLine);
		for (String caption : captions) {
			csvUi.addColumn(caption);
		}
	}

	private String[] splitCsvLine(String oneLine) {
		return oneLine.split(",");
	}

	private void addLineToCsvUi(String oneLine){
		String[] cells = splitCsvLine(oneLine);
		csvUi.addRow(Arrays.asList(cells));
	}
	
	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

}
