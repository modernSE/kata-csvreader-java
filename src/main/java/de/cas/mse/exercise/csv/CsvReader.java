package de.cas.mse.exercise.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.cas.mse.exercise.csv.ui.CsvUi;
import de.cas.mse.exercise.csv.ui.Filterable;

public class CsvReader implements Filterable {

    private CsvUi csvUi;
    private List<List<String>> csvData = new ArrayList<>();
    private static final String DELIMITER_COMMA = ",";
    private static final int OFFSET = 1;
    private static final int FIRST_ROW_INDEX = 0;

    public void run(final File csvFile) throws Exception {
        readFile(csvFile);
        setColumnCount();
        addDataRows();
    }

    private void setColumnCount() {
        csvUi.setColumnCount(csvData.get(FIRST_ROW_INDEX).size());
    }

    private void addDataRows() {
        for (List<String> row : csvData) {
            csvUi.addRow(row);
        }
    }

    private void readFile(File csvFile) throws FileNotFoundException, IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile))) {
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] cells = line.split(DELIMITER_COMMA);
                csvData.add(Arrays.asList(cells));
            }
        }
    }

    public void setCsvUi(final CsvUi csvUi) {
        this.csvUi = csvUi;
    }

    @Override
    public List<List<String>> filter(int col, String word) {
        List<List<String>> filteredRows = new ArrayList<>();
        for (List<String> row : csvData) {
            String currColValue = row.get(col - OFFSET);
            if (currColValue.contains(word)) {
                filteredRows.add(row);
            }
        }
        return filteredRows;
    }

}
