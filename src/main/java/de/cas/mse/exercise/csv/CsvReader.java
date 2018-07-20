package de.cas.mse.exercise.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import de.cas.mse.exercise.csv.ui.CsvUi;

public class CsvReader {

	private CsvUi csvUi;

	public void run(final File csvFile) throws Exception {

		try (Scanner sc = new Scanner(csvFile)) {
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				List<String> data = Arrays.asList(line.split(","));
				csvUi.setColumnCount(data.size());

				csvUi.addRow(data);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

}
