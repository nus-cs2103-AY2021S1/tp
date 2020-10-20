package seedu.address.ui;

import java.io.File;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.currentpath.CurrentPath;

/**
 * Panel of the file explorer.
 */
public class FileExplorerPanel extends UiPart<Region> {
    private static final String FXML = "FileExplorerPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TagListPanel.class);

    @FXML
    private TitledPane currentPath;
    @FXML
    private ListView<File> fileListView;

    /**
     * Creates a {@code FileExplorerPanel} with the given {@code ObservableList}.
     */
    public FileExplorerPanel(CurrentPath currentPath, ObservableList<File> fileList) {
        super(FXML);
        this.currentPath.setText(currentPath.getAddress().value);
        fileListView.setItems(fileList);
        fileListView.setCellFactory(listView -> new FileListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code File} using a {@code FileCard}.
     */
    class FileListViewCell extends ListCell<File> {
        @Override
        protected void updateItem(File file, boolean empty) {
            super.updateItem(file, empty);

            if (empty || file == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new FileCard(file).getRoot());
            }
        }
    }

}
