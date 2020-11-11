package de.cas.mse.exercise.csv;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import de.cas.mse.exercise.csv.ui.CsvUi;
import de.cas.mse.exercise.csv.ui.Filterable;

public class CsvReader implements Filterable {

    private CsvUi csvUi;
    private List<String> headers;
    private List<List<String>> rows;

    public void run(final File csvFile) throws Exception {
        List<String> allLines = Files.readAllLines(csvFile.toPath());
        if (allLines.isEmpty()) {
            return;
        }

        headers = parseLine(allLines.get(0));
        rows = getRows(allLines);

        render();
    }

    public void setCsvUi(final CsvUi csvUi) {
        this.csvUi = csvUi;
    }

    @Override
    public List<List<String>> filter(int columnIndexForFiltering, String wordToContain) {
        String filterRegex = String.format(".*\\b%s\\b.*", wordToContain);
        return rows.stream()
            .filter(row -> filterApplies(row, columnIndexForFiltering, filterRegex))
            .collect(Collectors.toList());
    }

    private List<List<String>> getRows(List<String> allLines) {
        // First line contains the header
        return allLines.stream().skip(1).map(this::parseLine).collect(Collectors.toList());
    }

    private List<String> parseLine(String line) {
        return Arrays.asList(line.split(","));
    }

    private void render() {
        headers.forEach(csvUi::addColumn);
        rows.forEach(csvUi::addRow);
    }

    private boolean filterApplies(List<String> row, int columnIndexForFiltering, String filterRegex) {
        return row.get(columnIndexForFiltering).matches(filterRegex);
    }
}
