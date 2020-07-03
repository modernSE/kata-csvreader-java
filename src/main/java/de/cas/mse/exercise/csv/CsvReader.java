package de.cas.mse.exercise.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

import de.cas.mse.exercise.csv.ui.CsvUi;

public class CsvReader {

	private static final String COMMA_SEPARATOR = ",";

	private CsvUi csvUi;

	public void run(final File csvFile) throws Exception {
		FileReader fileInputStream = new FileReader(csvFile);

		try(BufferedReader bufferedReader = new BufferedReader(fileInputStream)) {

			int colCount = -1;
			String s = null;
			while ((s = bufferedReader.readLine()) != null) {
				String[] split = s.split(COMMA_SEPARATOR);
				if (colCount < 0) {
					colCount = split.length;
					csvUi.setColumnCount(colCount);
				}
				csvUi.addRow(Arrays.asList(split));
			}
		}

	}

	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

}
