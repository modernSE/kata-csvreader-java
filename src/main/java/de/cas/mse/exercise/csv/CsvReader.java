package de.cas.mse.exercise.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import de.cas.mse.exercise.csv.ui.CsvUi;
import de.cas.mse.exercise.csv.ui.Filterable;

public class CsvReader implements Filterable {

	private CsvUi csvUi;
	private CsvData csvData;

	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

	public void run(final File file) throws Exception {
		try (FileReader reader = new FileReader(file)) {
			csvData = CsvParser.parse(reader, ",");
			present(csvData);
		}
	}

	private void present(CsvData csvData) {
		csvData.captions()
				.forEach(csvUi::addColumn);
		csvData.rows()
				.stream()
				.map(CsvRow::values)
				.forEach(csvUi::addRow);
	}

	@Override
	public List<List<String>> filter(int viewColumnIndex, String word) {
		int dataColumnIndex = applyDisplayColumnOffset(viewColumnIndex);
		return csvData.rows()
				.stream()
				.filter(it -> columnContainsValue(it, dataColumnIndex, word))
				.map(CsvRow::values)
				.collect(Collectors.toList());
	}

	private int applyDisplayColumnOffset(int viewColumnIndex) {
		return viewColumnIndex - 1;
	}

	private boolean columnContainsValue(CsvRow row, int columnIndex, String word) {
		return row.value(columnIndex).contains(word);
	}

}

final class CsvParser {
	static CsvData parse(Reader reader, String delimiter) {
		try (BufferedReader bufferedReader = new BufferedReader(reader)) {
			List<String> captions = CsvParser.parseLine(bufferedReader.readLine(), delimiter);
			List<CsvRow> rows = bufferedReader
					.lines()
					.map(rawLine -> CsvParser.parseLine(rawLine, delimiter))
					.map(CsvParser::toCsvRow)
					.collect(Collectors.toList());
			return new CsvData(captions, rows);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static List<String> parseLine(String rawLine, String delimiter) {
		return Arrays.asList(rawLine.split(delimiter));
	}

	private static CsvRow toCsvRow(List<String> values) {
		return new CsvRow(values);
	}
}

final class CsvData {

	static final CsvData EMPTY = new CsvData(List.of(), List.of());

	private final List<String> captions;
	private final List<CsvRow> rows;

	CsvData(List<String> captions, List<CsvRow> rows) {
		this.captions = captions;
		this.rows = rows;
	}

	List<String> captions() {
		return captions;
	}

	List<CsvRow> rows() {
		return rows;
	}

	int rowCount() {
		return rows.size();
	}

	int colCount() {
		return captions.size();
	}
}

final class CsvRow {

	private List<String> values;

	CsvRow(List<String> values) {
		this.values = values;
	}

	List<String> values() {
		return values;
	}

	String value(int columnIndex) {
		return values.get(columnIndex);
	}
}
