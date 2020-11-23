package seedu.address.ui.panels;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

public class StartPanel extends UiPart<Region> {

    private static final String FXML = "StartPanel.fxml";

    @FXML
    private ImageView greenTeaIcon;

    @FXML
    private Label headerMessage;

    @FXML
    private Label commandMessage;

    /**
     * Constructor for the start panel
     */
    public StartPanel() {
        super(FXML);
        Image greenTea = new Image(this.getClass().getResourceAsStream("/images/green_tea_icon.png"));
        greenTeaIcon.setImage(greenTea);
        headerMessage.setText("Welcome to Green Tea!\n");
        commandMessage.setText("Here are some commands you can try:\n"
                + "-----------------------------------------\n"
                + "                new deck\n"
                + "                remove deck \n"
                + "                select deck \n");
    }
}
