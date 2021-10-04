package de.cas.mse.exercise.csv;

import de.cas.mse.exercise.csv.ui.CsvUi;

public class CsvColumn {
    private final String name;

    public CsvColumn(String name) {
        this.name = name;
    }

    public void addToUi(CsvUi ui) {
        ui.addColumn(name);
    }
}
