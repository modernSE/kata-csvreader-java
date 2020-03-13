package de.cas.mse.exercise.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CsvFileReader {
	private String seperator;

	public CsvFileReader(String seperator) {
		this.seperator = seperator;
	}
	
	public CsvContent readContent(File file) throws FileNotFoundException {
		try(Scanner scanner = new Scanner(file)) {
			List<String> header = readHeader(scanner);
			List<List<String>> rows = readContentRows(scanner);
			return new CsvContent(header, rows);
		}
	}

	private List<String> readHeader(Scanner scanner) {
		return readRow(scanner);
	}

	private List<List<String>> readContentRows(Scanner scanner) {
		List<List<String>> rows = new LinkedList<List<String>>();
		while (scanner.hasNextLine()) {
			rows.add(readHeader(scanner));
		}
		return rows;
	}

	private List<String> readRow(Scanner scanner) {
		String row = scanner.nextLine();
		return Arrays.asList(row.split(seperator));
	}
}
