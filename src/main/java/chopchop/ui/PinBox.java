package chopchop.ui;

import static chopchop.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;

import chopchop.commons.util.Pair;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
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
    private ObservableSet<Pair<String, LocalDateTime>> observableRecords;

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
    public PinBox(ObservableSet<Pair<String, LocalDateTime>> records) {
        super(FXML);
        this.observableRecords = records;
        pins.setText("Statistics\n");
        header1.setText("Recently cooked recipe");
        if (records.isEmpty()) {
            body1.setText(EMPTY_PROMPT);
        } else {
            body1.setText(records.toString());
        }
        header2.setText("Ingredients expiring soon");
        observableRecords.addListener(new SetChangeListener<Pair<String, LocalDateTime>>() {
            @Override
            public void onChanged(Change<? extends Pair<String, LocalDateTime>> c) {
                //todo: expiring ingredient.
                setStatisticsToUser(observableRecords.toString(), "To be implemented");
            }
        });
    }

    private void setStatisticsToUser(String recentRecipes, String expiringIngredients) {
        requireAllNonNull(recentRecipes, expiringIngredients);
        body1.setText(recentRecipes);
        body2.setText(expiringIngredients);
    }
}
