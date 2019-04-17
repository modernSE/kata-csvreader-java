package de.cas.mse.exercise.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import de.cas.mse.exercise.csv.ui.CsvUi;

public class CsvReader {
	
	private CsvUi csvUi;

	public void run(final File csvFile) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(csvFile));
		try {
			FileObject csvFileInfo = createFileObject(br);
			
		} catch(Exception e) {
			
		} finally {
			br.close();
		}
		
		
	}
	
	public void setCsvUi(final CsvUi csvUi) {
		this.csvUi = csvUi;
	}
	
	private FileObject createFileObject(BufferedReader br) throws IOException  {
	    StringBuilder sb = new StringBuilder();
		String line = br.readLine();
		FileObject fileObject = new FileObject();
		

	    while (line != null) {
	    	CsvRow row = new CsvRow(line.split(","));
	    
	    	sb.append(System.lineSeparator());
	        line = br.readLine();
	    }

		
		return fileObject;
	}

}
