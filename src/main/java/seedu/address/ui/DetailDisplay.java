package seedu.address.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.logic.commands.ViewCommandResult;

public class DetailDisplay extends UiPart<Region> {

    private static final String FXML = "DetailsDisplay.fxml";

    private ZoomLinkPanel zoomLinkPanel;

    @FXML
    private TextArea resultDisplay;

    @FXML
    private StackPane listPlaceholder;

    public DetailDisplay() {
        super(FXML);
    }

    public void setDisplay(ViewCommandResult result) {
        resultDisplay.setText(result.getTextArea());

        zoomLinkPanel = new ZoomLinkPanel(FXCollections.observableArrayList(result.getZoomLinks()));
        listPlaceholder.getChildren().add(zoomLinkPanel.getRoot());
    }
}
