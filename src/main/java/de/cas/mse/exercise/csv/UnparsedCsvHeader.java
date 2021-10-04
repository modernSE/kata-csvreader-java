package de.cas.mse.exercise.csv;

import java.util.List;
import java.util.ArrayList;

public class UnparsedCsvHeader {
    private final String header;

    public UnparsedCsvHeader(String header) {
        this.header = header;
    }

    public CsvHeader parse() {
        List<CsvColumn> columns = new ArrayList<>();
        for (String entry : header.split(",")) {
            columns.add(new CsvColumn(entry));
        }
        return new CsvHeader(columns);
    }
}
