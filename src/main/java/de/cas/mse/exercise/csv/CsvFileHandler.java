package de.cas.mse.exercise.csv;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class CsvFileHandler {
	private File csvFile;
	private String tokenDelimiter;
	
	CsvFileHandler(File csvFile, String tokenDelimiter) {
		this.csvFile = csvFile;
		this.tokenDelimiter = tokenDelimiter;
	}
	
	CsvFileData getFileContentWithHeaders() throws IOException {
		List<String> contentLines = Files.readAllLines(csvFile.toPath());
		CsvFileData csvFileData = new CsvFileData();
		
		for(int i = 0; i< contentLines.size(); i++) {
			String rowContent = contentLines.get(i);
			List<String> rowTokens = splitRowToToken(rowContent);
			if(i == 0) {
				csvFileData.addHeaders(rowTokens);
			}else {
				csvFileData.addContent(rowTokens);
			}
		}
		
		return csvFileData;
	}
	
	private List<String> splitRowToToken(String rowContent){
		return Arrays.asList(rowContent.split(tokenDelimiter));
	}

}
