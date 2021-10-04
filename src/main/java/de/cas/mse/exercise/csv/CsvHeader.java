package de.cas.mse.exercise.csv;

import java.util.List;
import de.cas.mse.exercise.csv.ui.CsvUi;

public class CsvHeader {
    private final List<CsvColumn> columns;

    public CsvHeader(List<CsvColumn> columns) {
        this.columns = columns;
    }

    public void addToUi(CsvUi ui) {
        for (CsvColumn column : columns) {
            column.addToUi(ui);
        }
    }
}
