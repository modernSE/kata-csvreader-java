# CsvReader-Kata
Clean Code Kata

Under the path `/samples` you can find CSV-Files, which we want to read in and display to the user.
A very kind front-end-team has already implemented two user interfaces for us, yay =)
Our job now is to breathe in some life to the GUI with the contents of the CSV-files.

For the sake of simlicity and educational character of this exercise we can assume well formed CSV-Files. Our goal is **not** to create a perfect almighty CSV-Reader!


## Task 1 - Read and display a CSV-File without column headers
Input file: input-1-no-header.csv

For this task relevant GUI-APIs:
```java
public interface CsvUi {

	/**
	 * sets the amount of columns in the ui.
	 * 
	 * @param amount
	 *            number of columns.
	 */
	public void setColumnCount(int amount);
  
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
```

## Task 2 - Read and display a CSV-File with column headers
Input file: input-2-with-header.csv

For this task relevant GUI-APIs:
```java
public interface CsvUi {

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
```

## Task 3 - Read, display and filter a CSV-File with column headers
Input file: input-2-with-header.csv

Hint: Your `CsvReader` class needs to implement the provided and untouchable interface `Filterable`.

For this task relevant GUI-APIs:
```java
 
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

public interface CsvUi {

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
 
```
