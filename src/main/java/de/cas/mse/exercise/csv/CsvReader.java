package de.cas.mse.exercise.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import de.cas.mse.exercise.csv.impl.CsvRow;
import de.cas.mse.exercise.csv.impl.FileData;
import de.cas.mse.exercise.csv.ui.CsvUi;
import de.cas.mse.exercise.csv.ui.Filterable;

public class CsvReader implements Filterable {
	
	private CsvUi csvUi;

	public void run(final File csvFile) throws Exception {
		FileData fileInformation = readFile(csvFile);
		setUpUIData(fileInformation);		
	}
	
	private void setUpUIData(FileData fileInfo) {
		setUpHeaderData(fileInfo);			
		setUpBodyData(fileInfo);
	}
	
	private void setUpHeaderData(FileData fileData) {
		for (String headerCell : getHeaderCells(fileData)) {
			csvUi.addColumn(headerCell);
		}
	}
	
	private void setUpBodyData(FileData fileData) {
		for (CsvRow row : getBodyRows(fileData)) {
			csvUi.addRow(row.getCells());
		}
	}
	
	private List<String> getHeaderCells(FileData fileInfo) {
		return fileInfo.getRows().get(0).getCells();
	}
	
	private List<CsvRow> getBodyRows(FileData fileData) {
		return fileData.getRows().subList(1, fileData.getRows().size() - 1);
	}
	
	
	private FileData readFile(final File csvFile) throws IOException {
		
		BufferedReader reader = new BufferedReader(new FileReader(csvFile));
		List<CsvRow> rows = new ArrayList<CsvRow>();
		int columnCount = 0;
		
		String line;
		try {
			line = reader.readLine();
		    columnCount = line.split(",").length; 
			while (line != null) {
				
				CsvRow row = new CsvRow(Arrays.asList(line.split(",")));
				rows.add(row);
								
				line = reader.readLine();
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		} finally {
			reader.close();
		}
		
		return new FileData(rows, columnCount);
	}
	
	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

	@Override
	public List<List<String>> filter(int col, String word) {
		return null;
	}

}
