//@@author fall9x

package chopchop.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import chopchop.commons.util.Pair;
import chopchop.model.usage.RecipeUsage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Callback;

/**
 * The UI component that is responsible for displaying pinned information.
 * Displays results of statistics.
 */
public class StatsBox extends UiPart<Region> {
    private static final String EMPTY_PROMPT = "You haven't cooked anything.";
    private static final ArrayList<Pair<String, String>> EMPTY_RESULT =
        new ArrayList<>(Collections.singletonList(new Pair<>("No results found", "")));
    private static final String FXML = "PinBox.fxml";

    @FXML
    private TextArea pins;

    @FXML
    private TextFlow header1;

    @FXML
    private ListView<Pair<String, String>> listView;

    /**
     * Creates a {@code PinBox}.
     */
    public StatsBox(List<RecipeUsage> lst) {
        super(FXML);
        pins.setText("Statistics\n");
        if (lst.size() == 0) {
            setStatsMessage(EMPTY_PROMPT, true);
            renderList(EMPTY_RESULT);
        } else {
            setStatsMessage("Here is the list of recipes made recently.", false);
            renderList(lst.stream().map(x -> new Pair<>(x.getName(), x.getPrintableDate()))
                .collect(Collectors.toList()));
        }
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

    /**
     * A vertical scrollable list. Useful for showing a bunch of Strings wrapped in each
     * panel.
     */
    public void renderList(List<Pair<String, String>> inputList) {
        if (inputList.isEmpty()) {
            inputList = EMPTY_RESULT;
        }
        ObservableList<Pair<String, String>> items = FXCollections.observableArrayList(inputList);
        listView.setItems(items);

        //-------------------------------style-----------------------------------------
        listView.setPrefWidth(100);
        listView.setPrefHeight(240);

        listView.setCellFactory(new Callback<ListView<Pair<String, String>>, ListCell<Pair<String, String>>>() {
            @Override
            public ListCell<Pair<String, String>> call(ListView<Pair<String, String>> param) {
                return new PairListCell();
            }
        });
    }

    public void setStatsMessage(String msg, boolean isEmpty) {
        this.header1.getChildren().clear();
        var content = this.header1.getChildren();
        var txt = new Text(msg);
        if (isEmpty) {
            txt.setFill(Color.RED);
        }
        content.add(txt);
    }

    public static class PairListCell extends ListCell<Pair<String, String>> {
        private static final String PAIR_NAME = "pair-name";
        private static final String PAIR_SECOND = "pair-second";

        private GridPane grid = new GridPane();
        private Label name = new Label();
        private Label second = new Label();

        /**
         * Constructs {@code PairListCell}.
         */
        public PairListCell() {
            configureGrid();
            configureName();
            configureSecond();
            addControlsToGrid();
        }

        private void configureGrid() {
            grid.setHgap(8);
            grid.setVgap(4);
            grid.setPadding(new Insets(0, 10, 0, 8));
        }

        private void configureName() {
            //for CSS
            name.getStyleClass().add(PAIR_NAME);
        }

        private void configureSecond() {
            second.getStyleClass().add(PAIR_SECOND);
        }

        private void addControlsToGrid() {
            grid.add(name, 1, 0);
            grid.add(second, 1, 1);
        }

        @Override
        public void updateItem(Pair<String, String> item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                setText(null);
                name.setText(item.fst());
                second.setText(item.snd());
                setGraphic(grid);
            }
        }
    }
}
