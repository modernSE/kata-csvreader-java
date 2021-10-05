package de.cas.mse.exercise.csv;

import java.util.List;

import de.cas.mse.exercise.csv.ui.CsvUi;

public class CsvFile {
    private final CsvHeader header;
    private final List<CsvRow> rows;

    public CsvFile(CsvHeader header, List<CsvRow> rows) {
        this.header = header;
        this.rows = rows;
    }

    public void addToUi(CsvUi ui) {
        header.addToUi(ui);
    
        for (CsvRow row : rows) {
            row.addToUi(ui);
        }
        
    }
}
