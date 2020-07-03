package de.cas.mse.exercise.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import de.cas.mse.exercise.csv.ui.CsvUi;

public class CsvReader {
	
    private CsvUi csvUi;
    List<List<String>> rows = new LinkedList<>();

	public void run(final File csvFile) throws Exception {

        FileReader fileReader = new FileReader(csvFile);
        BufferedReader bReader = new BufferedReader(fileReader);
        LinkedList<String> lines = new LinkedList<>();
        
       try(bReader) {
            bReader.lines().forEach(line -> lines.add(line));
        }
        String headers = lines.get(0);
        lines.stream().forEach(line -> rows.add(splitRows(line)));
        
    }
    
    private List<String> splitRows(String line) {
       return new LinkedList(Arrays.asList(line.split(",")));
    }

	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
    }
    
    public List<List<String>> filter(int col, String word) {
        List<List<String>> filterResult = new LinkedList<>();
        
        for(List<String> row : rows) {
            if(row.get(col-1).contains(word)) {
                filterResult.add(row);
            }
        }
        return filterResult;
    }


}
