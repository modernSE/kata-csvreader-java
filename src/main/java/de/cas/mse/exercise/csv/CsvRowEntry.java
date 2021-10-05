package de.cas.mse.exercise.csv;

import java.util.function.Consumer;

public class CsvRowEntry {
    private final String content;

    public CsvRowEntry(String content) {
        this.content = content;
    }

    public void operateOnContent(Consumer<String> consumer) {
        consumer.accept(content);
    }
}
