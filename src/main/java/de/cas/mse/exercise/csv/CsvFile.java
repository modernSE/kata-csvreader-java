package de.cas.mse.exercise.csv;

import java.io.File;
import java.util.List;

public class CsvFile {
    public CsvFile(List<String> headers, List<List<String>> rows) {
        this.headers = headers;
        this.rows = rows;
    }

    List<String> headers;
    List<List<String>> rows;
}