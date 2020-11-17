package de.cas.mse.exercise.csv;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import de.cas.mse.exercise.csv.ui.CsvUi;
import de.cas.mse.exercise.csv.ui.Filterable;

public class CsvReader implements Filterable {
	
    private CsvUi csvUi;
    private CsvFile csvFile;

    // should be renamed: read()
	public void run(final File csvFile) throws Exception {
        this.csvFile = parseFile(csvFile);
        fillUi();
    }
    
    private CsvFile parseFile(final File csvFile) throws IOException {
        List<String> lines = Files.readAllLines(csvFile.toPath());
        if (lines.size() > 1) {
            List<String> headers = getCells(lines.get(0));
            List<List<String>> rows = lines.stream().skip(1).map(line -> getCells(line)).collect(Collectors.toList());
            return new CsvFile(headers, rows);
        } else {
            throw new IllegalArgumentException("The csv file was corrupt or empty.");
        }
    }

    private List<String> getCells(String commaSeparatedLine) {
        return Arrays.asList(commaSeparatedLine.split(","));
    }

    private void fillUi() {
        csvFile.getHeaders().forEach(header -> csvUi.addColumn(header));
        csvFile.getRows().forEach(row -> csvUi.addRow(row));
    }
	
	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

    @Override
    public List<List<String>> filter(int col, String word) {
        return csvFile.filter(col, word);
    }

}
