package de.cas.mse.exercise.csv;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class CsvFileImporter {

    private File csvFile;

    public CsvFileImporter(File csvFile) {
        this.csvFile = csvFile;
    }
    
    public CsvTable import() {
        CsvTable table = new CsvTable();
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			
			table.header = parseRow(br.readLine());

			String bodyLine;
			while ((bodyLine = br.readLine()) != null) {
			   table.addBodyLine(parseRow(bodyLine));
			}
		}
        
        return table;
    }

    private CsvRow parseRow(String row) {
        return null;
	}
}
