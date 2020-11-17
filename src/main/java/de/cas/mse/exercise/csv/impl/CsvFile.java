package de.cas.mse.exercise.csv.impl;

import java.util.List;

public class CsvFile {
    public List<String> headers;
    public List<List<String>> contents;
    
    public CsvFile(List<String> headers, List<List<String>> contents) {
        this.headers = headers;
        this.contents = contents;
    }
}