package seedu.medibook.ui.patientprofile;

import java.util.HashSet;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.util.Pair;
import seedu.medibook.model.medicaldetail.Tag;
import seedu.medibook.model.patient.Patient;
import seedu.medibook.ui.UiPart;

/**
 * An UI component that displays the medical details of a {@code Patient}.
 */
public class MedicalDetailsCard extends UiPart<Region> {

    private static final String FXML = "ProfileDetailsCard.fxml";

    public final Patient patient;

    @FXML
    private Label cardHeader;
    @FXML
    private ListView<Pair<String, Set<Tag>>> detailsListView;

    /**
     * Creates a {@code MedicalDetailsCard} for the given {@code Patient}.
     */
    public MedicalDetailsCard(Patient patient) {
        super(FXML);
        this.patient = patient;

        cardHeader.setText("Medical Details: ");
        ObservableList<Pair<String, Set<Tag>>> fields = FXCollections.observableArrayList();
        Set<Tag> allergies = new HashSet<>(patient.getAllergies());
        Set<Tag> conditions = new HashSet<>(patient.getConditions());
        Set<Tag> treatments = new HashSet<>(patient.getTreatments());
        fields.add(new Pair<String, Set<Tag>>("Allergies: ", allergies));
        fields.add(new Pair<String, Set<Tag>>("Conditions: ", conditions));
        fields.add(new Pair<String, Set<Tag>>("Treatments: ", treatments));


        detailsListView.setItems(fields);
        detailsListView.setCellFactory(listView -> new MedicalDetailsListViewCell());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MedicalDetailsCard)) {
            return false;
        }

        // state check
        MedicalDetailsCard card = (MedicalDetailsCard) other;
        return patient.equals(card.patient);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a medical detail field using a {@code MedicalDetailsRow}.
     */
    class MedicalDetailsListViewCell extends ListCell<Pair<String, Set<Tag>>> {
        @Override
        protected void updateItem(Pair<String, Set<Tag>> categoryTagsPair, boolean empty) {
            super.updateItem(categoryTagsPair, empty);

            if (empty || categoryTagsPair == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new MedicalDetailsRow(categoryTagsPair.getKey(), categoryTagsPair.getValue()).getRoot());
            }
        }
    }
}
