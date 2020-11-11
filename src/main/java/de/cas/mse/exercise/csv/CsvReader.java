package de.cas.mse.exercise.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import de.cas.mse.exercise.csv.ui.CsvUi;

public class CsvReader {

    private CsvUi csvUi;

    public void run(final File csvFile) throws Exception {

        CsvFile contents = parseTheFile(csvFile);

        publishToUi(contents);
    }

    private void publishToUi(CsvFile contents) {
        
        for (String headerCell : contents.headers) {
            csvUi.addColumn(headerCell);
        } 
        for (List<String> row : contents.rows) {
            csvUi.addRow(row);
        }
    }


    private CsvFile parseTheFile(final File csvFile) throws IOException {
        
        List<String> lineInTheFile = Files.readAllLines(csvFile.toPath());
        String firstLine = lineInTheFile.get(0);
        List<String> headers = parseLine(firstLine);
        List<List<String>> rows = lineInTheFile.stream().skip(1).map(line -> parseLine(line)).collect(Collectors.toList());

        return new CsvFile(headers, rows);
    }

    private List<String> parseLine(String line) {
        return Arrays.asList(line.split(","));
    }

    public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

}
