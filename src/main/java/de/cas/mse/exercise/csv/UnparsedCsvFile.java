package de.cas.mse.exercise.csv;

import java.util.List;
import java.util.ArrayList;

public class UnparsedCsvFile {
    private final UnparsedCsvHeader header;
    private final List<UnparsedCsvRow> body;

    public UnparsedCsvFile(List<String> lines) {
        this.header = new UnparsedCsvHeader(lines.get(0));
        this.body = new ArrayList<>(lines.size() - 1);
        for (int i = 1; i < lines.size(); i++) {
            body.add(new UnparsedCsvRow(lines.get(i)));
        }
    }

    public CsvFile parse() {
        List<CsvRow> rows = new ArrayList<>(body.size());
        for (UnparsedCsvRow unparsedRow : body) {
            rows.add(unparsedRow.parse());
        }
        return new CsvFile(header.parse(), rows);
    }
}
