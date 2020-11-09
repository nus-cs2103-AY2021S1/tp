package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.exercise.Template;

/**
 * An UI component that displays information of a {@code Template}.
 */
public class TemplateCard extends UiPart<Region> {
    private static final String FXML = "TemplateListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Template template;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label calories;
    @FXML
    private FlowPane muscleTags;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code ExerciseCode} with the given {@code Exercise} and index to display.
     */
    public TemplateCard(Template template, int displayedIndex) {
        super(FXML);
        this.template = template;
        id.setText(displayedIndex + ". ");
        name.setText(template.getName());
        calories.setText((template.getCalories()).toString());
        template.getMuscleTags().stream()
                .sorted(Comparator.comparing(tag -> tag.muscleTagName))
                .forEach(tag -> muscleTags.getChildren().add(new Label(tag.muscleTagName)));
        template.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TemplateCard)) {
            return false;
        }

        // state check
        TemplateCard card = (TemplateCard) other;
        return id.getText().equals(card.id.getText())
                && template.equals(card.template);
    }
}

