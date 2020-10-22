package seedu.pivot.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.pivot.commons.core.LogsCenter;
import seedu.pivot.model.investigationcase.Document;

/**
 * Panel containing the list of persons.
 */
public class DocumentListPanel extends UiPart<Region> {
    private static final String FXML = "DocumentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(DocumentListPanel.class);

    @FXML
    private ListView<Document> documentListView;

    /**
     * Creates a {@code DocumentListPanel} with the given {@code ObservableList}.
     */
    public DocumentListPanel(ObservableList<Document> documentList) {
        super(FXML);
        documentListView.setItems(documentList);
        documentListView.setCellFactory(listView -> new DocumentListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Document} using a {@code DocumentCard}.
     */
    class DocumentListViewCell extends ListCell<Document> {
        @Override
        protected void updateItem(Document document, boolean empty) {
            super.updateItem(document, empty);

            if (empty || document == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DocumentCard(document, getIndex() + 1).getRoot());
            }
        }
    }

}
