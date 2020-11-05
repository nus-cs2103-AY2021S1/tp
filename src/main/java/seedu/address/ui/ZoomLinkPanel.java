package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;

public class ZoomLinkPanel extends UiPart<Region> {
    private static final String FXML = "ZoomLinkPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ZoomLinkPanel.class);

    @FXML
    private ListView<DisplayZoomLink> zoomLinkListView;

    /**
     * Creates a {@code ZoomLinkPanel} with the given {@code ObservableList}.
     */
    public ZoomLinkPanel(ObservableList<DisplayZoomLink> zoomLinkObservableList) {
        super(FXML);
        zoomLinkListView.setItems(zoomLinkObservableList);
        zoomLinkListView.setCellFactory(listView -> new ZoomLinkViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code DisplayZoomLink} using a {@code ZoomLinkCard}.
     */
    class ZoomLinkViewCell extends ListCell<DisplayZoomLink> {
        @Override
        protected void updateItem(DisplayZoomLink displayZoomLink, boolean empty) {
            super.updateItem(displayZoomLink, empty);

            if (empty || displayZoomLink == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ZoomLinkCard(displayZoomLink, getIndex() + 1).getRoot());
            }
        }
    }


}
