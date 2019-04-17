package de.cas.mse.exercise.csv;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import de.cas.mse.exercise.csv.ui.CharRow;
import de.cas.mse.exercise.csv.ui.CsvUi;
import de.cas.mse.exercise.csv.ui.FileData;

public class CsvReader {

	private CsvUi csvUi;

	public void run(final File file) throws Exception {
		FileData fileData = parseFile(file);
		setUiData(fileData);
	}

	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

	private FileData parseFile(File file) throws IOException {
		List<String> lines = Files.readAllLines(file.toPath());

		List<CharRow> rows = lines.stream() //
				.map(this::splitLineIntoCells) //
				.map(x -> new CharRow(x)) //
				.collect(Collectors.toList());

		if (rows.isEmpty()) {
			throw new IllegalArgumentException("File is empty");
		}

		CharRow headerRow = extractHeaderRowAndRemoveFromBody(rows);
		FileData fileData = new FileData(headerRow.getCells(), rows);

		return fileData;
	}

	private List<String> splitLineIntoCells(String line) {
		return Arrays.asList(line.split(",", 0));
	}

	private CharRow extractHeaderRowAndRemoveFromBody(List<CharRow> rows) {
		CharRow header = rows.get(0);
		rows.remove(0);
		return header;
	}

	private void setUiData(FileData fileData) {
		fileData.getColumnHeaders().forEach(header -> csvUi.addColumn(header));
		fileData.getRows().forEach(row -> csvUi.addRow(row.getCells()));
	}

}
