package de.cas.mse.exercise.csv;

import de.cas.mse.exercise.csv.ui.CsvUi;

import java.util.List;
import java.util.ArrayList;

public class CsvRow {
    private final List<CsvRowEntry> entries;

    public CsvRow(List<CsvRowEntry> entries) {
        this.entries = entries;
    }

    public void addToUi(CsvUi ui) {
        List<String> rowContents = new ArrayList<>();
        for (CsvRowEntry entry : entries) {
            entry.operateOnContent(rowContents::add);
        }
        ui.addRow(rowContents);
    }
}
