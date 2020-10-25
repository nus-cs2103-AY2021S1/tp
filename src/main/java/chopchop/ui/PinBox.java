package chopchop.ui;

import static chopchop.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import chopchop.commons.util.Pair;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * The UI component that is responsible for displaying pinned information.
 * Displays results of statistics.
 */
public class PinBox extends UiPart<Region> {
    private static final String EMPTY_PROMPT = "You haven't cooked anything yet.";
    private static final String FXML = "PinBox.fxml";

    @FXML
    private TextArea pins;

    @FXML
    private TextArea header1;

    @FXML
    private TextArea header2;

    @FXML
    private TextArea body1;

    @FXML
    private TextArea body2;

    /**
     * Creates a {@code PinBox}.
     */
    public PinBox(String output) {
        super(FXML);
        pins.setText("Statistics\n");
        header1.setText("Recently cooked recipe:");
        if (output.isEmpty()) {
            body1.setText(EMPTY_PROMPT);
        } else {
            body1.setText("");
        }
        header2.setText("Ingredients expiring soon:");
        body2.setText("To be implemented");
        /*
        observableRecords.addListener(new ListChangeListener<Pair<String, LocalDateTime>>() {
            @Override
            public void onChanged(Change<? extends Pair<String, LocalDateTime>> c) {
                //todo: expiring ingredient.
                setStatisticsToUser(formatRecords(observableRecords), "To be implemented");
            }
        });
         */
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

    private void setStatisticsToUser(String recentRecipes, String expiringIngredients) {
        requireAllNonNull(recentRecipes, expiringIngredients);
        body1.setText(recentRecipes);
        body2.setText(expiringIngredients);
    }
}
