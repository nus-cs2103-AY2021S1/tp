package seedu.address.ui;

import java.util.Comparator;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.module.Module;


/**
 * A UI component that displays information of a {@code Module}.
 */
public class ModuleCard extends UiPart<Region> {
    private static final String FXML = "ModuleListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Module module;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label moduleCode;
    @FXML
    private Label moduleName;
    @FXML
    private FlowPane instructors;

    /**
     * Creates a {@code ModuleCard} with the given {@code Module} and index to display.
     */
    public ModuleCard(Module module, int displayedIndex) {
        super(FXML);
        this.module = module;
        moduleCode.setText(module.getModuleCode().moduleCode);
        moduleName.setText(module.getModuleName().moduleName);

        module.getInstructors().stream()
            .sorted(Comparator.comparing(instructor -> instructor.getName().fullName))
            .forEach(person -> instructors.getChildren().add(new Label(person.getName().fullName)));

        cardPane.widthProperty().addListener((observable, oldValue, newValue) ->
            Platform.runLater(() -> resizeInstructors(newValue.doubleValue())));
    }

    private void resizeInstructors(double width) {
        instructors.setMaxWidth(width * 0.9);
        instructors.setPrefWrapLength(width);
        instructors.getChildren()
            .filtered(x -> x instanceof Label)
            .forEach(label -> resizeLabel((Label) label, width));
    }

    private void resizeLabel(Label label, double width) {
        label.setMaxWidth(width * 0.4);
        label.setWrapText(true);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModuleCard)) {
            return false;
        }

        // state check
        ModuleCard card = (ModuleCard) other;
        return module.equals(card.module);
    }
}
