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
    private List<List<String>> data;

    public void run(final File csvFile) throws Exception {
        List<String> allLines = Files.readAllLines(csvFile.toPath());
        if (allLines.isEmpty()) {
            return;
        }

        addHeadersToUi(parseLine(allLines.get(0)));
        data = allLines.stream().skip(1).map(this::parseLine).collect(Collectors.toList());
    }

    private List<String> parseLine(String line) {
        return Arrays.asList(line.split(","));
    }

    private void addHeadersToUi(List<String> headers) {
        headers.forEach(csvUi::addColumn);
    }

    public void setCsvUi(final CsvUi csvUi) {
        this.csvUi = csvUi;
    }

    @Override
    public List<List<String>> filter(int col, String word) {
        return data.stream().filter(row -> filterApplies(row, col, word)).collect(Collectors.toList());
    }

    private boolean filterApplies(List<String> row, int col, String word) {
        return row.get(col).matches(String.format(".*\\b%s\\b.*", word));
    }

}
