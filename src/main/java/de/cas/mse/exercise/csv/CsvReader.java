package de.cas.mse.exercise.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.cas.mse.exercise.csv.ui.CsvUi;
import de.cas.mse.exercise.csv.ui.Filterable;

public class CsvReader implements Filterable {
    
    private static final String COMMA_DELIMITER = ",";

    private static final int HEADER_LINE_INDEX = 0;
    private static final int DATA_LINE_INDEX = 1;

    private CsvUi csvUi;
    
    private List<List<String>> csvContent;

	public void run(final File csvFile) throws Exception {
		csvContent = extractContentFromCSVFile(csvFile);

        setHeaderLineInUI(csvContent);
        setDataLinesInUI(csvContent); 
    }

    public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
    }

    @Override
    public List<List<String>> filter(int col, String word) {
        List<List<String>> filteredData = new ArrayList<>();
        int dataColumnIndex = col - 1;

        for (int i = DATA_LINE_INDEX; i < csvContent.size(); i++) {
            if (csvContent.get(i).get(dataColumnIndex).contains(word)) {
                filteredData.add(csvContent.get(i));
            }
        }

        return filteredData;
    }

    private List<List<String>> extractContentFromCSVFile(final File csvFile) throws IOException, FileNotFoundException {
        List<List<String>> target = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                String[] currentLineContent = currentLine.split(COMMA_DELIMITER);
                target.add(Arrays.asList(currentLineContent));
            }
        }
        return target;
    }

    private void setHeaderLineInUI(List<List<String>> data) {
        for (String columnName : data.get(HEADER_LINE_INDEX)) {
            csvUi.addColumn(columnName);
        }
    }

    private void setDataLinesInUI(List<List<String>> data) {
        for (int i = DATA_LINE_INDEX; i < data.size(); i++) {
            csvUi.addRow(data.get(i));
        }
    }
}
