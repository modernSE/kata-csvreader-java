package de.cas.mse.exercise.csv;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.cas.mse.exercise.csv.ui.CsvUi;
import de.cas.mse.exercise.csv.ui.Filterable;

public class CsvReader implements Filterable {
	
	private CsvUi csvUi;
	
	private List<List<String>> data = new ArrayList<>();
	

	public void run(final File csvFile) throws Exception {
	 
	    BufferedReader csvReader=new BufferedReader(new FileReader(csvFile));
	    
		this.writeHeader(csvReader);
		this.writeData(csvReader);
		
		csvReader.close();
				
	}
	
	private void writeHeader(BufferedReader csvReader) throws IOException {
		String line=csvReader.readLine();
		List<String> headerRow = Arrays.asList(line.split(","));
		for (String columnName : headerRow) {
			csvUi.addColumn(columnName);
		}

	}
	
	private void writeData(BufferedReader csvReader) throws IOException {
		String line=csvReader.readLine();
		while (line!=null) {
			List<String> csvRow = Arrays.asList(line.split(","));
			csvUi.addRow(csvRow);
			data.add(csvRow);
			line=csvReader.readLine();
		}
	}
	
	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

	@Override
	public List<List<String>> filter(int col, String word) {
		
		List<List<String>> result = new ArrayList<>();
		
		for(List<String> row: data) {
			if (row.get(col - 1).toLowerCase().contains(word.toLowerCase())){
				result.add(row);
			}
		}
		return result;
	}

}
