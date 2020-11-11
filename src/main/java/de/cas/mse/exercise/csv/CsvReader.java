package de.cas.mse.exercise.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.cas.mse.exercise.csv.ui.CsvUi;

public class CsvReader {

    private CsvUi csvUi;

    public void run(final File csvFile) throws Exception {
        // TODO: Start coding here
        // Datei parsen /auslesen
        readFile(csvFile);

        // Info an UI übergeben

    }

    private void readFile(File csvFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(csvFile));
        
        String row; 
        List<String[]> content = new ArrayList<>();

        while ((row = reader.readLine()) != null) {
            String[] columns =    row.split(",");
            content.add(columns);
        }

        
        for (String headerColumn : content.get(0)) {
            csvUi.addColumn(headerColumn);
        }
        


        


    }
	
	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}

}
