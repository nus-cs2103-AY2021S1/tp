package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.module.ZoomLink;

public class ZoomLinkPanel extends UiPart<Region> {
    private static final String FXML = "ZoomLinkPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ZoomLinkPanel.class);

    //@FXML
    //private ListView<ModuleName> moduleNameListView;

    @FXML
    private ListView<ZoomLink> zoomLinkListView;

    /**
     * Creates a {@code ZoomLinkPanel} with the given {@code ObservableList}.
     */
    public ZoomLinkPanel(/*ObservableList<ModuleName> moduleNameObservableList,*/
                            ObservableList<ZoomLink> zoomLinkObservableList) {
        super(FXML);
        zoomLinkListView.setItems(zoomLinkObservableList);
        zoomLinkListView.setCellFactory(listView -> new ZoomLinkViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code ZoomLink} using a {@code ZoomLinkCard}.
     */
    class ZoomLinkViewCell extends ListCell<ZoomLink> {
        @Override
        protected void updateItem(ZoomLink zoomLink, boolean empty) {
            super.updateItem(zoomLink, empty);

            if (empty || zoomLink == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ZoomLinkCard(zoomLink, getIndex() + 1).getRoot());
            }
        }
    }
}
