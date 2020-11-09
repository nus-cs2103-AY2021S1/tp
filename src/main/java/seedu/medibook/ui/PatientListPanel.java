package seedu.medibook.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import seedu.medibook.commons.core.LogsCenter;
import seedu.medibook.model.patient.Patient;

/**
 * Panel containing the list of patients.
 */
public class PatientListPanel extends UiPart<Region> {
    private static final String FXML = "PatientListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PatientListPanel.class);

    @FXML
    private TableView<Patient> patientTableView;

    /**
     * Creates a {@code PatientListPanel} with the given {@code ObservableList}.
     */
    public PatientListPanel(ObservableList<Patient> patientList) {
        super(FXML);
        List<TableColumn<Patient, String>> columns = new ArrayList<>();
        TableColumn<Patient, String> columnIndex = new TableColumn<>("#");
        columnIndex.setCellValueFactory(column-> new ReadOnlyObjectWrapper<>(
                Integer.toString(patientTableView.getItems().indexOf(column.getValue()) + 1)));
        columns.add(columnIndex);

        TableColumn<Patient, String> columnIc = new TableColumn<>("IC");
        columns.add(columnIc);

        TableColumn<Patient, String> columnName = new TableColumn<>("Name");
        columns.add(columnName);

        TableColumn<Patient, String> columnDob = new TableColumn<>("DOB");
        columns.add(columnDob);

        TableColumn<Patient, String> columnPhone = new TableColumn<>("Phone");
        columns.add(columnPhone);

        TableColumn<Patient, String> columnEmail = new TableColumn<>("Email");
        columns.add(columnEmail);

        TableColumn<Patient, String> columnAddress = new TableColumn<>("Address");
        columns.add(columnAddress);

        TableColumn<Patient, String> columnHeight = new TableColumn<>("Height");
        columns.add(columnHeight);

        TableColumn<Patient, String> columnWeight = new TableColumn<>("Weight");
        columns.add(columnWeight);

        TableColumn<Patient, String> columnBmi = new TableColumn<>("BMI");
        columns.add(columnBmi);

        TableColumn<Patient, String> columnBloodType = new TableColumn<>("Blood Type");
        columns.add(columnBloodType);

        columns.forEach(col -> {
            String text = col.getText();
            if (!text.equals("#")) {
                String[] words = text.split(" ");
                IntStream.range(0, words.length).forEach(i -> {
                    words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
                });
                String property = "string" + String.join("", words);
                col.setCellValueFactory(new PropertyValueFactory<>(property));
            }
            col.setResizable(false);
            col.setSortable(false);
            patientTableView.getColumns().add(col);
        });

        columnIndex.prefWidthProperty().bind(patientTableView.widthProperty().multiply(0.04));
        columnIc.prefWidthProperty().bind(patientTableView.widthProperty().multiply(0.075));
        columnName.prefWidthProperty().bind(patientTableView.widthProperty().multiply(0.125));
        columnDob.prefWidthProperty().bind(patientTableView.widthProperty().multiply(0.07));
        columnPhone.prefWidthProperty().bind(patientTableView.widthProperty().multiply(0.07));
        columnEmail.prefWidthProperty().bind(patientTableView.widthProperty().multiply(0.15));
        columnAddress.prefWidthProperty().bind(patientTableView.widthProperty().multiply(0.22));
        columnHeight.prefWidthProperty().bind(patientTableView.widthProperty().multiply(0.0625));
        columnWeight.prefWidthProperty().bind(patientTableView.widthProperty().multiply(0.0625));
        columnBmi.prefWidthProperty().bind(patientTableView.widthProperty().multiply(0.04));
        columnBloodType.prefWidthProperty().bind(patientTableView.widthProperty().multiply(0.075));

        patientTableView.getItems().addAll(patientList);
        patientList.addListener((ListChangeListener<Patient>) c -> patientTableView.setItems(patientList));

    }
}
