package seedu.address.ui;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

public class DeliveryDisplay extends UiPart<Region> {

    private static final String FXML = "DeliveryDisplay.fxml";

    @javafx.fxml.FXML
    private TextArea deliveryDisplay;

    public DeliveryDisplay() {
        super(FXML);
    }

}

