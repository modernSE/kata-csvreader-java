import java.util.ArrayList;
import java.util.List;

public class CsvTable {

    public CsvRow header;
    public List<CsvRow> body = new ArrayList<>();

    public CsvRow getHeader() {
        return header;
    }

    public void addBodyLine(CsvRow row) {
        body.add(row);
    }

}