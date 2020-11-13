package seedu.studybananas.ui.commons;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.studybananas.ui.UiPart;

public class CommandHelpLabel extends UiPart<Region> {
    private static final String FXML = "CommandHelpLabel.fxml";

    @FXML
    private HBox labelPane;
    @FXML
    private Label command;
    @FXML
    private Label argument;
    @FXML
    private Label description;

    /**
     * Constructs CommandHelpLabel with command, argument and description.
     */
    public CommandHelpLabel(String command, String argument, String description) {
        super(FXML);
        this.command.setText(command);
        this.description.setText(description);

        // remove argument label when argument does not exist
        if (argument == null) {
            labelPane.getChildren().remove(this.argument);
        } else {
            this.argument.setText(argument);
        }


    }
}
