# CsvReader-Kata
Clean Code Kata

Unter `/samples` findet ihr Beispiel CSV-Dateien, die wir Einlesen und Anzeigen wollen.
Ein liebes Front-End-Team hat dies bereits vorab implementiert und uns zwei Interfaces bereitgestellt.
Unsere Aufgabe ist es nun die GUI mit Inhalten zu füllen, diese Inhalten müssen wir aus den CSV-Dateien lesen.

Wir gehen von validen schönen CSV-Dateien aus. Ziel ist es nicht den prefekten und robustesten CSV-Reader zu schreiben!

## Aufgabe 1 - CSV-Datei ohne Spaltenüberschriften
Eingabedatei: input-1-no-header.csv

Aufgabe: Die CSV-Datei ohne Spaltenüberschriften importieren.

Dafür relevante API-Funktionen:
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

## Aufgabe 2 - CSV-Datei mit Spaltenüberschriften
Eingabedatei: input-2-with-header.csv

Aufgabe: Die CSV-Datei mit Spaltenüberschriften importieren.

Dafür relevante API-Funktionen:
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

## Aufgabe 3 - CSV-Datei mit Spaltenüberschriften filtern
Eingabedatei: input-2-with-header.csv

Aufgabe: Die CSV-Datei mit Spaltenüberschriften importieren und das neue Interface `Filterable` implementieren.

Dafür relevante API-Funktionen:
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
