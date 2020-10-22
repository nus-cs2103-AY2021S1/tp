package seedu.address.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ProfileUtil;
import seedu.address.logic.Logic;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.ProfilePicture;
import seedu.address.model.visit.Visit;


/**
 * Panel containing detailed information of the specified {@code Person} including
 * the usual details on {@code PersonCard}, and also associated {@code VisitReport} information.
 */
public class ProfileWindow extends UiPart<Stage> {
    private static final String FXML = "ProfileWindow.fxml";
    private static final Logger logger = LogsCenter.getLogger(ProfileWindow.class);

    private Logic logic;
    private Patient person;

    @FXML
    private TextArea nameField;

    @FXML
    private TextArea allergyField;

    @FXML
    private TextArea phoneField;

    @FXML
    private TextArea emailField;

    @FXML
    private TextArea addressField;

    @FXML
    private ListView<Visit> profileVisitList;

    @FXML
    private TextArea icNumberField;

    @FXML
    private TextArea sexField;

    @FXML
    private TextArea bloodTypeField;

    @FXML
    private ImageView profilePictureField;

    /**
     * Instantiates a ProfileWindow object.
     */
    public ProfileWindow(Stage root) {
        super(FXML, root);
        populateVisitList(FXCollections.observableArrayList());
    }

    /**
     * Creates a new {@code ProfilePanel}.
     */
    public ProfileWindow() {
        this(new Stage());

        /*
         * [IMPORTANT]
         * Makes Profile Window monopolize the application focus.
         * Fixes previous concurrency issues when modifying Person while Profile Window is open.
         */
        this.getRoot().initModality(Modality.APPLICATION_MODAL);

        // Add handlers to ProfileWindow for user actions.
        // (esc, q - Exit), (p - Generate .txt for user Profile)
        getRoot().addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            if (KeyCode.ESCAPE == event.getCode()) {
                logger.info("User pressed 'esc'. Closing Profile Panel..");
                getRoot().hide();
                logger.info("Profile Panel Closed.");
            } else if (KeyCode.Q == event.getCode()) {
                logger.info("User pressed 'q'. Closing Profile Panel.");
                getRoot().hide();
                logger.info("Profile Panel Closed.");
            }
        }
        );
    }

    /**
     * Initializes the Profile Window with the particulars from the Person instance.
     *
     * @param person Person instance to show in the Profile Window
     */
    public void setup(Patient person, Logic logic) {
        this.logic = logic;
        this.person = person;

        // Set Person Particulars into relevant fields
        nameField.setText(ProfileUtil.stringifyName(person.getName()));
        allergyField.setText(ProfileUtil.stringifyTags(person.getAllergies()));
        phoneField.setText(ProfileUtil.stringifyPhone(person.getPhone()));
        emailField.setText(ProfileUtil.stringifyEmail(person.getEmail()));
        addressField.setText(ProfileUtil.stringifyAddress(person.getAddress()));
        bloodTypeField.setText(ProfileUtil.stringifyBloodType(person.getBloodType()));
        sexField.setText(ProfileUtil.stringifySex(person.getSex()));
        icNumberField.setText(ProfileUtil.stringifyIcNumber(person.getIcNumber()));

        ProfilePicture thisProfilePic = person.getProfilePicture();
        File profilePic = new File(thisProfilePic.toString());
        try {
            assert profilePic != null : "Profile picture cannot be null";
            FileInputStream fileInputStream = new FileInputStream(profilePic);
            Image finalProfilePic = new Image(fileInputStream);
            profilePictureField.setImage(finalProfilePic);
        } catch (IOException error) {
            error.getMessage();
        }

        // Set Person Visits into ListView
        populateVisitList(person.getVisitHistory().getObservableVisits());
    }

    /**
     * Populates the ProfileWindow's ListView with the ProfileVisitListCells representing the VisitReport
     * instances contained within an ObservableList&lt;VisitReport&gt; instance.
     *
     * @param visitList ObservableList&lt;VisitReport&gt; instance containing the VisitReports to be
     *                  visualized.
     */
    public void populateVisitList(ObservableList<Visit> visitList) {
        profileVisitList.setItems(visitList);
        profileVisitList.setCellFactory(listView -> new ProfileVisitListCell());
    }


    /**
     * Custom {@code ListCell} that displays the graphics of a {@code VisitReport} using a {@code ProfileVisitCard}.
     */
    class ProfileVisitListCell extends ListCell<Visit> {
        @Override
        protected void updateItem(Visit report, boolean empty) {
            super.updateItem(report, empty);

            if (empty || report == null) {
                setGraphic(null);
                setText(null);
            } else {
                ProfileVisitCard card = new ProfileVisitCard(report);
                setGraphic(card.getRoot());
            }
        }
    }

    /**
     * Shows the Profile Panel.
     *
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code ProfileWindow} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.info("Showing Profile Panel");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the Profile panel is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the Profile panel.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the Profile panel.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    @FXML
    void mouseEnterExit(MouseEvent e) {
        Label exitLabel = (Label) e.getSource();
        exitLabel.setUnderline(true);
    }

    @FXML
    void mouseLeaveExit(MouseEvent e) {
        Label exitLabel = (Label) e.getSource();
        exitLabel.setUnderline(false);
    }

    @FXML
    void mouseClickExit(MouseEvent e) {
        logger.info("User pressed 'close'. Closing Profile Panel..");
        getRoot().hide();
        logger.info("Profile Panel Closed.");
    }
}

