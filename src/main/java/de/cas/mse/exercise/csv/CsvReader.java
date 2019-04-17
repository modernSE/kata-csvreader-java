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
		setHeader(lines.get(0));
		
		for(String line : lines) {
			List<String> cells = Arrays.asList(line.split(","));
			csvUi.addRow(cells);
		}
		
		
	}

	private void setHeader(String line) {
		for ( String header : line.split(",")) {
			csvUi.addColumn(header);
		}
	}
	
	public List<String> readFile(File csvFile) throws IOException  {
		 BufferedReader br = new BufferedReader(new FileReader(csvFile));
		 String line = br.readLine();
		 List<String> lines = new ArrayList<>();
		 while(line != null ) {
			 lines.add(line);
			 line = br.readLine();
		 }
		 return lines;
	}
	
	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

}
