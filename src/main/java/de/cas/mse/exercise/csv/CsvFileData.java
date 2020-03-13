package de.cas.mse.exercise.csv;

import java.util.ArrayList;
import java.util.List;

public class CsvFileData {
	private List<String> headers = new ArrayList<String>(); // could be public 
	private List<List<String>> content = new ArrayList<List<String>>(); // could be public
	
	
	
	public List<String> getHeaders() {
		return headers;
	}
	public void addHeaders(List<String> headers) {
		this.headers.addAll(headers);
	}
	public List<List<String>> getContent() {
		return content;
	}
	public void addContent(List<String> content) {
		this.content.add(content);
	}
	
	
}
