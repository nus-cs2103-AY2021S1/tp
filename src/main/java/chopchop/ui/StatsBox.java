package chopchop.ui;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import chopchop.commons.util.Pair;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * The UI component that is responsible for displaying pinned information.
 * Displays results of statistics.
 */
public class StatsBox extends UiPart<Region> {
    private static final String EMPTY_PROMPT = "You haven't cooked anything.";
    private static final String FXML = "PinBox.fxml";

    @FXML
    private TextFlow body;

    /**
     * Creates a {@code PinBox}.
     */
    public StatsBox() {
        super(FXML);
        body.getChildren().add(new Text(EMPTY_PROMPT));
    }

    private String formatRecords(ObservableList<Pair<String, LocalDateTime>> records) {
        List<Pair<String, LocalDateTime>> outputList = new ArrayList<>();
        int i = 0;
        while (i < 3 && records.size() != 0) {
            outputList.add(records.remove(records.size() - 1));
            i++;
        }
        String output = outputList.stream()
            .map(x -> String.format("%s, %s", x.fst(),
                x.snd().format(DateTimeFormatter.ofPattern("dd-MMM-yy hh:mm a"))))
            .collect(Collectors.joining("\n"));
        return output.toString();
    }

    public void setBoxContent(String boxContent) {
        requireNonNull(boxContent);
        body.getChildren().clear();
        body.getChildren().add(new Text(boxContent));
    }
}
