package de.cas.mse.exercise.csv.impl.ui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import de.cas.mse.exercise.csv.CsvReader;
import de.cas.mse.exercise.csv.ui.CsvUi;
import de.cas.mse.exercise.csv.ui.Filterable;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CsvApplication extends Application implements CsvUi {

	private CsvPaneController csvController;
	private CsvReader reader;

	@Override
	public void start(final Stage primaryStage) throws Exception {
		reader = new CsvReader();
		final File f;
		if (getParameters().getRaw().isEmpty()) {
			final FileChooser chooser = new FileChooser();
			chooser.setTitle("Open CSV File");
			f = chooser.showOpenDialog(primaryStage);
			if (f == null) {
				System.exit(0);
			}
		} else {
			f = new File(getParameters().getRaw().get(0));
		}
		reader.setCsvUi(this);
		initLayout(primaryStage);
		primaryStage.setTitle("CSV Reader");
		primaryStage.show();
		new Thread(() -> {
			try {
				reader.run(f);
			} catch (final Exception e) {
				throw new RuntimeException(e);
			}
		}).start();
	}

	private void initLayout(final Stage primaryStage) throws IOException {
		final URL paneUrl = determinePane();
		final FXMLLoader loader = new FXMLLoader(paneUrl);
		final AnchorPane pane = loader.load();
		primaryStage.setScene(new Scene(pane));
		csvController = loader.getController();
		if (reader instanceof Filterable) {
			csvController.setFilterData((Filterable) reader);
		}
	}

	private URL determinePane() {
		if (reader instanceof Filterable) {
			return getClass().getResource("CsvPaneFilterable.fxml");
		} else {
			return getClass().getResource("CsvPane.fxml");
		}
	}

	@Override
	public void addColumn(final String caption) {
		if (caption == null || caption.length() == 0) {
			throw new IllegalArgumentException();
		}
		Platform.runLater(() -> csvController.addColumn(caption));
	}

	@Override
	public void addRow(final List<String> data) {
		Platform.runLater(() -> csvController.addRow(data));
	}

	@Override
	public void setColumnCount(final int amount) {
		for (int i = 0; i < amount; i++) {
			Platform.runLater(() -> csvController.addColumn(null));
		}
	}

}
