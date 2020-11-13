package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.deliverable.deliverable.Deliverable;

/**
 * Panel containing the expanded details of a deliverable.
 */
public class DeliverableDetailsPanel extends UiPart<Region> {
    private static final String FXML = "DeliverableDetailsPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(DeliverableDetailsPanel.class);

    @FXML
    private Label title;
    @FXML
    private Label milestone;
    @FXML
    private Label description;
    @FXML
    private Label deadline;
    @FXML
    private VBox contactList;
    @FXML
    private Label isCompleted;

    /**
     * Creates a {@code DeliverableDetailsPanel} with the given {@code Deliverable}
     */
    public DeliverableDetailsPanel(Deliverable deliverable) {
        super(FXML);
        assert deliverable != null;
        title.setText(deliverable.getTitle().value);
        milestone.setText((deliverable.getMilestone().toString()));
        description.setText(deliverable.getDescription().toString());
        deadline.setText(deliverable.getDeadline().toString());
        deliverable.getContacts().value.ifPresentOrElse(contacts -> {
            for (String contact : contacts.split(",")) {
                String cleanedContact = contact.trim().replaceAll("\\s+", " ");
                Label label = new Label("-" + cleanedContact);
                label.setWrapText(true);
                label.setStyle("-fx-pref-width: 400");
                contactList.getChildren().add(label);
            }
        }, () -> contactList.getChildren().add(new Label(deliverable.getContacts().toString())));

        if (deliverable.getIsComplete()) {
            isCompleted.setText("completed");
            isCompleted.setStyle("-fx-background-color: #32cd32");
        } else {
            isCompleted.setText("on-going");
            isCompleted.setStyle("-fx-background-color: #ffa500");
        }

    }
}
