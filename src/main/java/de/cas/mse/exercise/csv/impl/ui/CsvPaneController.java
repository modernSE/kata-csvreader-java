package de.cas.mse.exercise.csv.impl.ui;

import java.util.List;

import de.cas.mse.exercise.csv.ui.Filterable;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CsvPaneController {

	@FXML
	private TableView<RowItem> csvTable;
	@FXML
	private TextField filterText;
	@FXML
	private TextField columnText;
	@FXML
	private Button filterBtn;

	private Filterable filterData;

	@FXML
	public void initialize() {
		if (filterBtn != null) {
			filterBtn.setOnAction(e -> handleFilter());
		}
	}

	public void addColumn(final String caption) {
		if (csvTable.getItems().size() > 0) {
			throw new IllegalStateException();
		}

		String numberedCaption;
		if (caption != null) {
			numberedCaption = csvTable.getColumns().size() + 1 + ": " + caption;
		} else {
			numberedCaption = String.valueOf(csvTable.getColumns().size() + 1);
		}
		final TableColumn<RowItem, String> col = new TableColumn<>(numberedCaption);
		col.setCellValueFactory(
				e -> new ReadOnlyStringWrapper(e.getValue().rowData.get(csvTable.getColumns().indexOf(col))));
		csvTable.getColumns().add(col);

	}

	public void addRow(final List<String> data) {
		if (data.size() != csvTable.getColumns().size()) {
			throw new IllegalArgumentException();
		}
		csvTable.getItems().add(new RowItem(data));
	}

	private static final class RowItem {
		private final List<String> rowData;

		public RowItem(final List<String> rowData) {
			super();
			this.rowData = rowData;
		}
	}

	private void handleFilter() {
		final List<List<String>> data = filterData.filter(Integer.parseInt(columnText.getText()), filterText.getText());
		csvTable.getItems().clear();
		data.forEach(e -> csvTable.getItems().add(new RowItem(e)));
	}

	public void setFilterData(final Filterable filterData) {
		this.filterData = filterData;
	}
}
