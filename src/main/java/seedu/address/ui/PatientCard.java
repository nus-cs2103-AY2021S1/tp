package seedu.address.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.ProfilePicture;

/**
 * An UI component that displays information of a {@code Patient}.
 */
public class PatientCard extends UiPart<Region> {

    private static final String FXML = "PatientListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Patient patient;
    private final Logic logic;

    @FXML
    private HBox cardPane;
    @FXML
    private ImageView profilePicture;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PatientCode} with the given {@code Patient} and index to display.
     */
    public PatientCard(Patient patient, int displayedIndex, Logic logic) {
        super(FXML);
        this.patient = patient;
        this.logic = logic;

        ProfilePicture thisProfilePic = patient.getProfilePicture();
        File profilePic = new File(thisProfilePic.toString());

        try {
            FileInputStream fileInputStream = new FileInputStream(profilePic);
            Image finalProfilePic = new Image(fileInputStream);
            profilePicture.setImage(finalProfilePic);
        } catch (IOException error) {
            error.getMessage();
        }

        id.setText(displayedIndex + ". ");
        name.setText(patient.getName().fullName);
        phone.setText(patient.getPhone().value);
        address.setText(patient.getAddress().value);
        email.setText(patient.getEmail().value);
        patient.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @FXML
    private void dragPictureOver(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        if (dragboard.hasFiles()) {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        } else {
            // Do nothing.
        }
    }

    @FXML
    private void dropPicture(DragEvent event) throws FileNotFoundException, CommandException, IllegalValueException {
        Dragboard dragboard = event.getDragboard();
        List<File> fileToTransfer = dragboard.getFiles();
        File imageFile = fileToTransfer.get(0);
        FileInputStream fileInputStream = new FileInputStream(imageFile);
        Image image = new Image(fileInputStream);
        profilePicture.setImage(image);
        logic.runImageTransfer(patient, imageFile);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PatientCard)) {
            return false;
        }

        // state check
        PatientCard card = (PatientCard) other;
        return id.getText().equals(card.id.getText())
                && patient.equals(card.patient);
    }
}
