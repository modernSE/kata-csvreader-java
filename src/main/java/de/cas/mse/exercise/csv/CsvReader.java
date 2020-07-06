package de.cas.mse.exercise.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import de.cas.mse.exercise.csv.ui.CsvUi;
import de.cas.mse.exercise.csv.ui.Filterable;

public class CsvReader implements Filterable {
    
    private static final String CSV_DELIMETER = ",";
    private static final int VIEW_TO_MODEL_COLUMN_OFFSET = 1;

    private CsvUi csvUi;

    private CsvData csvData;

	public void run(final File csvFile) throws Exception {
        csvData = generateCsvDataFromFile(csvFile);
        initializeCsvUi(csvData);
	}
	
	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
    }

    @Override
    public List<List<String>> filter(int viewCol, String filterText) {
        return csvData.getRows().stream() //
            .filter(row -> row.get(viewToModelColumnIndex(viewCol)).contains(filterText)) //
            .collect(Collectors.toList());
    }

    private int viewToModelColumnIndex(int viewColumnIndex) {
        return viewColumnIndex - VIEW_TO_MODEL_COLUMN_OFFSET;
    }

    private CsvData generateCsvDataFromFile(File csvFile) throws IOException {
        CsvData csvData = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            List<String> headerRow = parseCsvLine(reader.readLine());
            csvData = new CsvData(headerRow);
            String csvRow;
            while ((csvRow = reader.readLine()) != null) {
                csvData.addRow(parseCsvLine(csvRow));
            }
        }
        return csvData;
    }
    
    private List<String> parseCsvLine(String line) {
        String[] rowContent = line.split(CSV_DELIMETER);
        return Arrays.asList(rowContent);
    }

    private void initializeCsvUi(CsvData csvData) {
        csvData.getHeaders().forEach(header -> csvUi.addColumn(header));
        csvData.getRows().forEach(row -> csvUi.addRow(row));
    }

    private class CsvData {

        private final List<String> headers;
        private final List<List<String>> rows;

        public CsvData(List<String> headers) {
            this.headers = headers;
            rows = new ArrayList<>();
        }

        public List<String> getHeaders() {
            return headers;
        }

        public List<List<String>> getRows() {
            return rows;
        }

        public void addRow(List<String> row) {
            rows.add(row);
        }
    }
}
