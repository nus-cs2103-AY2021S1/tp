package seedu.resireg.ui;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Region;
import seedu.resireg.commons.core.LogsCenter;
import seedu.resireg.model.allocation.Allocation;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.student.Student;


/**
 * Panel containing the pie chart of allocated rooms.
 */
public class PiechartPanel extends UiPart<Region> {
    private static final String FXML = "PiechartPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PiechartPanel.class);

    private final ObservableList<Student> studentList;
    private final ObservableList<Allocation> allocationList;
    private final ObservableList<Room> roomList;
    private ObservableList<PieChart.Data> data;
    @FXML
    private PieChart piechart;

    /**
     * Creates a {@code PieChart} with the given {@code ObservableList}.
     */
    public PiechartPanel(ObservableList<Student> studentList,
                         ObservableList<Allocation> allocationList,
                         ObservableList<Room> roomList) {
        super(FXML);
        requireNonNull(studentList);
        requireNonNull(allocationList);
        requireNonNull(roomList);

        this.studentList = studentList;
        this.allocationList = allocationList;
        this.roomList = roomList;

        initData();
        setChart();
    }

    private void initData() {
        int allocatedCount = allocationList.size();
        int unallocatedCount = roomList.size() - allocatedCount;
        assert unallocatedCount >= 0;

        data = FXCollections.observableArrayList();
        data.add(new PieChart.Data("Allocated Rooms: " + allocatedCount, allocatedCount));
        data.add(new PieChart.Data("Unallocated Rooms: " + unallocatedCount, unallocatedCount));
    }

    private void setChart() {
        logger.info("Setting data for chart");
        piechart.setData(data);
        piechart.setTitle("Room Allocations");
    }
}
