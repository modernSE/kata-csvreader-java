package de.cas.mse.exercise.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.cas.mse.exercise.csv.ui.CsvUi;

public class CsvReader {

	private CsvUi csvUi;

	public void run(final File csvFile) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(csvFile));
		try {
			FileObject csvFileInfo = createFileObject(br);

			for (String caption : csvFileInfo.csvRows.get(0).rowData) {
				csvUi.addColumn(caption);
			}
			
			for (CsvRow row : csvFileInfo.csvRows) {
				csvUi.addRow(Arrays.asList(row.rowData));
			}
			

		} catch (Exception e) {

		} finally {
			br.close();
		}

	}

	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

	private FileObject createFileObject(BufferedReader br) throws IOException {
		StringBuilder sb = new StringBuilder();
		String line = br.readLine();

		List<CsvRow> allRows = new ArrayList<>();

		while (line != null) {
			allRows.add(new CsvRow(line.split(",")));
			line = br.readLine();
		}
		return new FileObject(allRows);
	}

}
