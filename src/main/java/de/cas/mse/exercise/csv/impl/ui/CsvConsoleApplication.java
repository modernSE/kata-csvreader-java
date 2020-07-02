package de.cas.mse.exercise.csv.impl.ui;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;

import de.cas.mse.exercise.csv.CsvReader;
import de.cas.mse.exercise.csv.ui.CsvUi;
import de.cas.mse.exercise.csv.ui.Filterable;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;

public class CsvConsoleApplication implements CsvUi {

    private final List<String> captions = new LinkedList<>();
    private List<List<String>> data = new LinkedList<>();

    private final CsvReader csvReader;

    public CsvConsoleApplication() {
        csvReader = new CsvReader();
    }

    public String render() {
        final AsciiTable table = new AsciiTable();
        CWC_LongestLine cwc = new CWC_LongestLine();
        table.getRenderer().setCWC(cwc);
        cwc.add(10, 60);
        if (!captions.isEmpty()) {
            table.addRule();
            table.addRow(captions);
        }
        table.addRule();
        data.forEach(row -> {
            table.addRow(row);
            table.addRule();
        });
        if(data.isEmpty()) {
            if(table.getColNumber() > 1) {
                table.addRow(Stream.concat(Collections.nCopies(table.getColNumber()-1, null).stream(), Stream.of("No Data!")).collect(Collectors.toList()));
            } else {
                table.addRow("No Data!");
            }
            table.addRule();
        }
        return table.render();
    }

    public void run(final File csvFile) throws Exception {
        csvReader.setCsvUi(this);
        csvReader.run(csvFile);
        if (!(csvReader instanceof Filterable)) {
            System.out.println(render());
        } else {
            final LineReader lineReader = LineReaderBuilder.builder().build();
            final String prompt = "filter (colIndex@wordsToFilter or !exit)>";
            System.out.println(render());
            while (true) {
                String line = null;
                try {
                    line = lineReader.readLine(prompt);
                    if ("!refresh".equalsIgnoreCase(line)) {
                        System.out.println(render());
                    } else if ("!exit".equalsIgnoreCase(line)) {
                        System.exit(0);
                    } else if(line != null && !line.isEmpty()) {
                        String[] parts = line.split("@");
                        data = ((Filterable)csvReader).filter(Integer.valueOf(parts[0].trim()), parts[1].trim());
                        System.out.println(render());
                    }
                } catch (final UserInterruptException e) {
                    // Ignore
                } catch (final EndOfFileException e) {
                    return;
                }
            }
        }
    }

    @Override
    public void setColumnCount(final int amount) {
        // not relevant for console
    }

    @Override
    public void addColumn(final String caption) {
        captions.add(caption);
    }

    @Override
    public void addRow(final List<String> row) {
        data.add(row);
    }
    
}