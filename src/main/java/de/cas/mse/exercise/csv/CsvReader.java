package de.cas.mse.exercise.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import de.cas.mse.exercise.csv.ui.CsvUi;
import de.cas.mse.exercise.csv.ui.Filterable;

public class CsvReader implements Filterable {

    private CsvUi csvUi;
    private List<String> headerRow;
    private List<List<String>> rows = new ArrayList<>();
    private final static String CELL_DELIMITER = ",";

    public void run(final File csvFile) throws Exception {
        readRows(csvFile);
        buildTable();
    }

    private void readRows(final File csvFile) throws Exception {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] cells = line.split(CELL_DELIMITER);
                rows.add(Arrays.asList(cells));
            }
        }

        headerRow = rows.remove(0);
    }

    private void buildTable() {
        headerRow.stream().forEach(column -> csvUi.addColumn(column));
        rows.stream().forEach(row -> csvUi.addRow(row));
    }

    @Override
    public List<List<String>> filter(int uiColumn, String filteredWord) {
        int filteredColumn = transformToColumnIndex(uiColumn);

        return rows.stream()
            .filter(row -> row.get(filteredColumn).matches(filteredWord))
            .collect(Collectors.toList());
    }

    private int transformToColumnIndex(int uiColumn) {
        return uiColumn + 1;
    }
	
	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

}
