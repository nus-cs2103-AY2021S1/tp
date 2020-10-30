package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import seedu.address.model.person.Person;


/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";
    private static final double BORDER_SIZE = 5;

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on ClientList level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private FlowPane phone;
    @FXML
    private FlowPane address;
    @FXML
    private FlowPane email;
    @FXML
    private FlowPane note;
    @FXML
    private FlowPane priority;
    @FXML
    private FlowPane clientSources;
    @FXML
    private FlowPane policyName;
    @FXML
    private FlowPane policyDescription;
    @FXML
    private Region fillerShape;
    @FXML
    private HBox priorityShape;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);

        if (person.getPhone() != null) {
            phone.getChildren().add(new Label(person.getPhone().value));
        }

        if (person.getAddress() != null) {
            address.getChildren().add(new Label(person.getAddress().value));
        }

        if (person.getEmail() != null) {
            email.getChildren().add(new Label(person.getEmail().value));
        }

        if (person.getNote() != null) {
            note.getChildren().add(new Label(person.getNote().noteName));
        }

        if (person.getPolicy() != null) {
            policyName.getChildren().add(new Label("Policy name: " + person.getPolicy().getPolicyName().value));
            policyDescription.getChildren().add(new Label("Description: " + person.getPolicy().getDescription().value));
        }
        priority.getChildren().add(new Label(person.getPriority().value));

        person.getClientSources().stream()
                .sorted(Comparator.comparing(clientSource -> clientSource.clientSourceName))
                .forEach(clientSource -> clientSources.getChildren().add(
                        new Label(clientSource.clientSourceName)
                ));

        setPriorityShape(person);
    }

    private void setPriorityShape(Person person) {
        switch (person.getPriority().value) {
        case "low":
            if (!person.getIsArchive()) {
                priorityShape.setBorder(new Border(new BorderStroke(ColorPicker.TURQUOISE_BORDER,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(BORDER_SIZE))));
                priorityShape.setBackground(new Background(new BackgroundFill(ColorPicker.TURQUOISE, CornerRadii.EMPTY,
                        Insets.EMPTY)));
            } else {
                priorityShape.setBorder(new Border(new BorderStroke(ColorPicker.TURQUOISE_BORDER_DARK,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(BORDER_SIZE))));
                priorityShape.setBackground(new Background(new BackgroundFill(ColorPicker.TURQUOISE_DARK,
                        CornerRadii.EMPTY, Insets.EMPTY)));
            }
            break;
        case "medium":
            if (!person.getIsArchive()) {
                priorityShape.setBorder(new Border(new BorderStroke(ColorPicker.ORANGE_BORDER,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(BORDER_SIZE))));
                priorityShape.setBackground(new Background(new BackgroundFill(ColorPicker.ORANGE, CornerRadii.EMPTY,
                        Insets.EMPTY)));
            } else {
                priorityShape.setBorder(new Border(new BorderStroke(ColorPicker.ORANGE_BORDER_DARK,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(BORDER_SIZE))));
                priorityShape.setBackground(new Background(new BackgroundFill(ColorPicker.ORANGE_DARK,
                        CornerRadii.EMPTY, Insets.EMPTY)));
            }
            break;
        case "high":
            if (!person.getIsArchive()) {
                priorityShape.setBorder(new Border(new BorderStroke(ColorPicker.RED_BORDER,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(BORDER_SIZE))));
                priorityShape.setBackground(new Background(new BackgroundFill(ColorPicker.RED, CornerRadii.EMPTY,
                        Insets.EMPTY)));
            } else {
                priorityShape.setBorder(new Border(new BorderStroke(ColorPicker.RED_BORDER_DARK,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(BORDER_SIZE))));
                priorityShape.setBackground(new Background(new BackgroundFill(ColorPicker.RED_DARK,
                        CornerRadii.EMPTY, Insets.EMPTY)));
            }
            break;
        case "undefined":
            if (!person.getIsArchive()) {
                priorityShape.setBorder(new Border(new BorderStroke(ColorPicker.WHITE_BORDER,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(BORDER_SIZE))));
                priorityShape.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY,
                        Insets.EMPTY)));
            } else {
                priorityShape.setBorder(new Border(new BorderStroke(ColorPicker.WHITE_BORDER_DARK,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(BORDER_SIZE))));
                priorityShape.setBackground(new Background(new BackgroundFill(ColorPicker.WHITE_DARK,
                        CornerRadii.EMPTY, Insets.EMPTY)));
            }
            break;
        default:
            assert false : "priority shape UI cannot find priority keyword";
            break;
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
