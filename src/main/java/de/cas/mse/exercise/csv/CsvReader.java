package de.cas.mse.exercise.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.cas.mse.exercise.csv.ui.CsvUi;

public class CsvReader {

    private static String COMMA_SEPARATOR = ",";
    private  String currentRow = "";
	
    private CsvUi csvUi;
    
    private List<List<String>> dataRows = new ArrayList<>();

	public void run(final File csvFile) throws Exception {

        loadFile(csvFile);

        setHeader();

        setRows();
        
	}

    private void loadFile(final File csvFile) throws FileNotFoundException, IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(csvFile));
       
        while(currentRow != null) {
            currentRow = fileReader.readLine();
            String[] values = currentRow.split(COMMA_SEPARATOR);
            dataRows.add(Arrays.asList(values));
        }
        
    }

    private void setHeader() {
        csvUi.addRow(dataRows.get(0));
    }

    private void setRows() {
        while () {
            csvUi.addRow(dataRows.get(0));
        }
    }
	
	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

}
