package seedu.medibook.ui.patientprofile;

import static seedu.medibook.model.patient.Patient.OPTIONAL_FIELD_EMPTY_MESSAGE;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.util.Pair;
import seedu.medibook.model.patient.Height;
import seedu.medibook.model.patient.Patient;
import seedu.medibook.model.patient.Weight;
import seedu.medibook.ui.UiPart;

/**
 * An UI component that displays the personal details of a {@code Patient}.
 */
public class PersonalDetailsCard extends UiPart<Region> {

    private static final String FXML = "ProfileDetailsCard.fxml";

    public final Patient patient;

    @FXML
    private Label cardHeader;
    @FXML
    private ListView<Pair<String, String>> detailsListView;

    /**
     * Creates a {@code PersonalDetailsCard} for the given {@code Patient}.
     */
    public PersonalDetailsCard(Patient patient) {
        super(FXML);
        this.patient = patient;

        cardHeader.setText("Personal Details: ");
        fillListView();
    }

    private void fillListView() {
        ObservableList<Pair<String, String>> fields = FXCollections.observableArrayList();

        fields.add(new Pair<String, String>("Name: ", patient.getStringName()));
        fields.add(new Pair<String, String>("IC: ", patient.getStringIc()));
        fields.add(new Pair<String, String>("Date Of Birth: ", patient.getStringDob()));
        fields.add(new Pair<String, String>("Phone Number: ", patient.getStringPhone()));

        // email
        if (patient.getEmail().isPresent()) {
            fields.add(new Pair<String, String>("Email: ", patient.getStringEmail()));
        } else {
            fields.add(new Pair<String, String>("Email: ", OPTIONAL_FIELD_EMPTY_MESSAGE));
        }

        // address
        if (patient.getAddress().isPresent()) {
            fields.add(new Pair<String, String>("Address: ", patient.getStringAddress()));
        } else {
            fields.add(new Pair<String, String>("Address: ", OPTIONAL_FIELD_EMPTY_MESSAGE));
        }

        // height
        if (patient.getHeight().isPresent()) {
            fields.add(new Pair<String, String>("Height: ", patient.getStringHeight() + Height.HEIGHT_UNIT));
        } else {
            fields.add(new Pair<String, String>("Height: ", OPTIONAL_FIELD_EMPTY_MESSAGE));
        }

        // weight
        if (patient.getWeight().isPresent()) {
            fields.add(new Pair<String, String>("Weight: ", patient.getStringWeight() + Weight.WEIGHT_UNIT));
        } else {
            fields.add(new Pair<String, String>("Weight: ", OPTIONAL_FIELD_EMPTY_MESSAGE));
        }

        // bmi
        if (patient.getBmi().isPresent()) {
            fields.add(new Pair<String, String>("BMI: ", patient.getStringBmi()));
        } else {
            fields.add(new Pair<String, String>("BMI: ", OPTIONAL_FIELD_EMPTY_MESSAGE));
        }

        // bloodType
        if (patient.getBloodType().isPresent()) {
            fields.add(new Pair<String, String>("Blood Type: ", patient.getStringBloodType()));
        } else {
            fields.add(new Pair<String, String>("Blood Type: ", OPTIONAL_FIELD_EMPTY_MESSAGE));
        }

        detailsListView.setItems(fields);
        detailsListView.setCellFactory(listView -> new PersonalDetailsListViewCell());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonalDetailsCard)) {
            return false;
        }

        // state check
        PersonalDetailsCard card = (PersonalDetailsCard) other;
        return patient.equals(card.patient);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a patient field using a {@code PersonalDetailsRow}.
     */
    class PersonalDetailsListViewCell extends ListCell<Pair<String, String>> {
        @Override
        protected void updateItem(Pair<String, String> fieldValuePair, boolean empty) {
            super.updateItem(fieldValuePair, empty);

            if (empty || fieldValuePair == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonalDetailsRow(fieldValuePair.getKey(), fieldValuePair.getValue()).getRoot());
            }
        }
    }
}
