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

    private AssignmentPanel assignmentPanel;

    @FXML
    private TextArea resultDisplay;

    @FXML
    private StackPane listPlaceholder;

    @FXML
    private StackPane assignmentListPlaceHolder;


    public DetailDisplay() {
        super(FXML);
    }

    public void setDisplay(ViewCommandResult result) {
        resultDisplay.setText(result.getTextArea());

        zoomLinkPanel = new ZoomLinkPanel(FXCollections.observableArrayList(result.getZoomLinks()));
        listPlaceholder.getChildren().add(zoomLinkPanel.getRoot());
        assignmentPanel = new AssignmentPanel(FXCollections.observableArrayList(result.getAssignments()));
        assignmentListPlaceHolder.getChildren().add(assignmentPanel.getRoot());
    }
}
