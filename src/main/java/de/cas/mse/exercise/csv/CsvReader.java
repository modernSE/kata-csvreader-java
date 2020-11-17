package de.cas.mse.exercise.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import de.cas.mse.exercise.csv.impl.CsvFile;
import de.cas.mse.exercise.csv.ui.CsvUi;
import de.cas.mse.exercise.csv.ui.Filterable;

public class CsvReader implements Filterable {

    private CsvUi csvUi;
    private CsvFile csvFile;

    public List<List<String>> filter(int col, String word) {
        return csvFile.contents.stream().filter(row -> row.get(col).contains(word)).collect(Collectors.toList());
    }


    public void run(final File csvFile) throws Exception {
        this.csvFile = parseFile(csvFile);
        publishCsvFile(this.csvFile);
    }

    private CsvFile parseFile(final File csvFile) throws FileNotFoundException {
        BufferedReader input = new BufferedReader(new FileReader(csvFile));

        String headerLine = input.lines().findFirst().get();
        List<String> headers = parseLine(headerLine);

        List<List<String>> contents = input.lines().skip(1).map(line -> parseLine(line)).collect(Collectors.toList());
        
        return new CsvFile(headers, contents);
    }

    private List<String> parseLine(String line) {
        return Arrays.asList(line.split(","));
    }
    
    private void publishCsvFile(CsvFile csvFile) {
        for (String caption : csvFile.headers) {
            this.csvUi.addColumn(caption);
        }
        for (List<String> row : csvFile.contents) {
            this.csvUi.addRow(row);
        }
    }
    
	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

}
