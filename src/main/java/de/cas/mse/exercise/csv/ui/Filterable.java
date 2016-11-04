package de.cas.mse.exercise.csv.ui;

import java.util.List;

/**
 * Defines a filterable table source.
 */
public interface Filterable {

	/**
	 * filters the datasource based on the given word.
	 * 
	 * @param col
	 *            the column which shall contain the word
	 * @param word
	 *            the word will be search as contains
	 * @return a list of rows fullfilling the filter
	 */
	public List<List<String>> filter(int col, String word);
}
