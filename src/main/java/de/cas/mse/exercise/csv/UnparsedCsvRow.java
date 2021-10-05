package de.cas.mse.exercise.csv;

import java.util.List;
import java.util.ArrayList;

public class UnparsedCsvRow {
    private final String content;

    public UnparsedCsvRow(String content) {
        this.content = content;
    }

    public CsvRow parse() {
        List<CsvRowEntry> entries = new ArrayList<>();
        for (String entry : content.split(",")) {
            entries.add(new CsvRowEntry(entry));
        }
        return new CsvRow(entries);
    }
}
