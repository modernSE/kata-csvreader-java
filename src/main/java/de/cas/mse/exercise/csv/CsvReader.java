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
	
		List<String> lines = readFile(csvFile);
		addHeaderToUi(lines.get(0));
		lines.remove(0);
		addRowstoUi(lines);

	}
	
	public List<String> readFile(File csvFile) throws IOException  {
		List<String> lines = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(csvFile));
		try {
			 String line = br.readLine();
			 
			 while(line != null ) {
				 lines.add(line);
				 line = br.readLine();
			 }
		 }
		 finally {
			 br.close();
		 }
		return lines;
	}

	private void addHeaderToUi(String line) {
		for ( String header : line.split(",")) {
			csvUi.addColumn(header);
		}
	}

	private void addRowstoUi(List<String> lines ) {
		for(String line : lines) {
			List<String> cells = Arrays.asList(line.split(","));
			csvUi.addRow(cells);
		}
	}
	
	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

}
