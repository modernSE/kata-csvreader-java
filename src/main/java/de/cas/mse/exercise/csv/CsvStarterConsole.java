package de.cas.mse.exercise.csv;

import java.io.File;

import de.cas.mse.exercise.csv.impl.ui.CsvConsoleApplication;

public class CsvStarterConsole {
    
    public static void main(String[] args) throws Exception {
        new CsvConsoleApplication().run(new File(args[0]));
    }

}