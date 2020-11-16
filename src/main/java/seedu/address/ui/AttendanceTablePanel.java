package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of student attendance.
 */
public class AttendanceTablePanel extends UiPart<Region> {
    private static final String FXML = "AttendanceTablePanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AttendanceTablePanel.class);

    @FXML
    private TableView<Person> attendanceTableView;
    @FXML
    private TableColumn<Person, String> nameCol;
    @FXML
    private TableColumn<Person, String> attendanceCol;

    /**
     * Creates a {@code AttendanceTablePanel} with the given {@code ObservableList}.
     */
    public AttendanceTablePanel(ObservableList<Person> personList) {
        super(FXML);

        nameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("name")
        );
        attendanceCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("attendances")
        );

        attendanceTableView.setItems(personList);
    }

}
