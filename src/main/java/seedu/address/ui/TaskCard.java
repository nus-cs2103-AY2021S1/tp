package seedu.address.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.task.Recurrence;
import seedu.address.model.task.Task;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class TaskCard extends UiPart<Region> {

    private static final String FXML = "TaskListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Task task;

    @FXML
    private HBox cardPane;
    @FXML
    private Label type;
    @FXML
    private Label description;
    @FXML
    private Label id;
    @FXML
    private Label dateTime;
    @FXML
    private Label statusIcon;
    @FXML
    private VBox additionalInfo;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public TaskCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        type.setText(task.getType());
        id.setText(displayedIndex + ". ");
        description.setText(task.getDescription());
        dateTime.setText(task.getDateTime());
        statusIcon.setText("Status: " + task.getStatusIcon());
        Hyperlink meetingLink = new Hyperlink();
        Label linkDescription = new Label();
        VBox linkContainer = new VBox();
        meetingLink.getStyleClass().add("meetingLink");
        linkDescription.getStyleClass().add("linkDescription");
        linkContainer.getStyleClass().add("linkContainer");

        Recurrence recurrence = task.getRecurrence();
        if (recurrence != null) {
            String text = "Recurring task: " + recurrence.getValue() + " " + recurrence.getUnit();
            Label recurring = new Label(text);
            recurring.getStyleClass().add("recurring");
            additionalInfo.getChildren().add(recurring);
        }

        if (task.getLink().isPresent()) {
            meetingLink.setText(task.getLink().get().getUrl());
            meetingLink.setOnAction(e -> {
                if (Desktop.isDesktopSupported()) {
                    new Thread(() -> {
                        try {
                            Desktop.getDesktop().browse(new URI(meetingLink.getText()));
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (URISyntaxException e1) {
                            e1.printStackTrace();
                        }
                    }).start();
                }
            });
            linkDescription.setText(task.getLink().get().getDescription());
            linkContainer.getChildren().addAll(meetingLink, linkDescription);
            additionalInfo.getChildren().add(linkContainer);
        }

        task.getTags().stream()
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
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        TaskCard card = (TaskCard) other;
        return id.getText().equals(card.id.getText())
                && task.equals(card.task);
    }
}
