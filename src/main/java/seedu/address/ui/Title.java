package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class Title extends UiPart<Region> {

    private static final String FXML = "Title.fxml";

    @FXML
    private Label title;

    public Title() {
        super(FXML);
    }

    public Label getTitle() {
        return this.title;
    }

}
