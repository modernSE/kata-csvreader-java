package de.cas.mse.exercise.csv;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import de.cas.mse.exercise.csv.ui.CsvUi;

public class CsvReader {

	private CsvUi csvUi;

	public void run(final File csvFile) throws Exception {
		if (csvFile.exists() && csvFile.isFile()) {
			List<String> lines = Files.readAllLines(csvFile.toPath());
			List<List<String>> csvOutput = lines.stream().map((line) -> line.split(",")).map(Arrays::asList)
					.collect(Collectors.toList());

			csvUi.setColumnCount(csvOutput.get(0).size());

			for (int i = 0; i < csvOutput.size(); i++) {
				csvUi.addRow(csvOutput.get(i));
			}

		}
	}

	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

}
