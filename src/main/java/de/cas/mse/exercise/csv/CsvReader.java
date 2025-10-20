package de.cas.mse.exercise.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import de.cas.mse.exercise.csv.ui.CsvUi;

public class CsvReader {
	
	private CsvUi csvUi;

	public void run(final File csvFile) throws Exception {
		
		CsvFile parsedCsvFile = CsvFile.parseCsvFile(csvFile);

		parsedCsvFile.columnNames().forEach(csvUi::addColumn);
		parsedCsvFile.rows().forEach(row -> csvUi.addRow(row.cells()));
	}

	record CsvFile(List<CsvRow> rows, List<String> columnNames){

		static CsvFile parseCsvFile(final File csvFile) throws FileNotFoundException {
			try (Scanner csvScanner = new Scanner(csvFile)){

				CsvRow columnRow = CsvRow.of(csvScanner.nextLine());

				List<CsvRow> rows = new ArrayList<>();
				while(csvScanner.hasNextLine()){
					CsvRow row = CsvRow.of(csvScanner.nextLine());				
					rows.add(row);
				}
				
				return new CsvFile(rows, columnRow.cells());
			}
		}
	}

	public record CsvRow(List<String> cells) {
		static CsvRow of(String csvRow){
			return new CsvRow(getCellsForRow(csvRow));
		}

		private static List<String> getCellsForRow(String csvRow){
			return Arrays.asList(csvRow.split(","));
		}
	}

	
	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

}
