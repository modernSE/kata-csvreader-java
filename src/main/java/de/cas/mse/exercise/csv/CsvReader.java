package de.cas.mse.exercise.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import de.cas.mse.exercise.csv.ui.CsvUi;

public class CsvReader {

	private static final String SEPARATOR = ",";

	private CsvUi csvUi;

	public void run(final File csvFile) throws Exception {
		// TODO code here
		
		FileReader fileReader = new FileReader(csvFile);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		try{
			parseFile(bufferedReader);
		}finally{
			bufferedReader.close();
			fileReader.close();
		}
	}
	
	private void parseFile(BufferedReader bufferedReader) throws Exception  {
		String firstLine = bufferedReader.readLine();
		if (firstLine == null){
			return;
		}
		
		setColumnCount(firstLine);
		
		bufferedReader.lines().forEach(this::parseLine);
	}

	private void setColumnCount(String line) {
		csvUi.setColumnCount(line.split(SEPARATOR).length );	
	}
	
	private void parseLine(String line) {
		String[] strings = line.split(SEPARATOR);
		csvUi.addRow(Arrays.asList(strings));
	}
	
	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

}
