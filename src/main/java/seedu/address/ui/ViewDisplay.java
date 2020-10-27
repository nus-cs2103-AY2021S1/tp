package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;


public class ViewDisplay extends UiPart<Region>{
    private static final String FXML = "viewDisplay.fxml";

    @FXML
    private TextArea viewDisplay;

    public ViewDisplay() {
        super(FXML);
        viewDisplay.setText("MODULES");
    }

    public void setCurrentView(String view) {
        viewDisplay.setText(view);
    }
}
