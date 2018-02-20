package de.cas.mse.exercise.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CsvParser {
	
	private static String separtor = ",";
	
	private List<String> rows;
	
	public CsvParser(final File csvFile) {
		readLinesFromCSVFile(csvFile);
	}
	
	public List<String> getRow(int index){
		return Arrays.asList(rows.get(index).split(separtor));
	}
	
	public int getRowCount(){
		return rows.size();
	}
	
	public int getColumnCount(){
		return rows.size() > 0 ? getRow(0).size() : 0;
	}
	
	private void readLinesFromCSVFile(final File csvFile) {
		try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
			this.rows = reader.lines().collect(Collectors.toList());
		} catch (Exception e) {
			this.rows = Collections.emptyList();
		}
	}
	
}
