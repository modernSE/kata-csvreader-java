package de.cas.mse.exercise.csv;

import java.util.List;
import java.util.stream.Collectors;

import de.cas.mse.exercise.csv.ui.Filterable;

public class CsvFile implements Filterable {

    private final List<String> headers;
    private final List<List<String>> rows;

    public CsvFile(List<String> headers, List<List<String>> rows) {
        this.headers = headers;
        this.rows = rows;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public List<List<String>> getRows() {
        return rows;
    }

    @Override
    public List<List<String>> filter(int col, String word) {
        return rows.stream().filter(row -> row.get(col).contains(word)).collect(Collectors.toList());
    }

    
}