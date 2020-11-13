package seedu.bagel.ui;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class SideBar extends UiPart<Region> {

    private static final String FXML = "SideBar.fxml";

    @FXML
    private VBox buttonBar;

    /**
     * Constructs a SideBar.
     */
    public SideBar() {
        super(FXML);
    }

    /**
     * Adds a new button to SideBar.
     * @param button Button to be added.
     */
    public void addButton(Button button) {
        this.buttonBar.getChildren().add(button);
    }

    /**
     * Deletes an existing button from SideBar.
     * @param buttonNode Node of the button to be deleted
     */
    public void deleteButton(Node buttonNode) {
        this.buttonBar.getChildren().remove(buttonNode);
    }

    public List<Node> getButtons() {
        return this.buttonBar.getChildren();
    }

    public void clearAllButtons() {
        this.buttonBar.getChildren().clear();
    }

}
