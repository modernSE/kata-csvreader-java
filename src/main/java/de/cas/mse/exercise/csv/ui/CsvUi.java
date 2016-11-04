package de.cas.mse.exercise.csv.ui;

import java.util.List;

/**
 * Provides access to the UI.
 */
public interface CsvUi {

	/**
	 * sets the amount of columns in the ui.
	 * 
	 * @param amount
	 *            number of columns.
	 */
	public void setColumnCount(int amount);

	/**
	 * adds a column to ui.
	 * 
	 * @param caption
	 *            of the column
	 * @throws IllegalArgumentException
	 *             if caption is null or empty.
	 * @throws IllegalStateException
	 *             if data is in the view
	 */
	public void addColumn(String caption);

	/**
	 * adds a row to the ui.
	 * 
	 * @param data
	 *            a list of data elements for each column.
	 * @throws IllegalArgumentException
	 *             if data.size() is not the size of columns in the ui.
	 */
	public void addRow(List<String> data);
}
