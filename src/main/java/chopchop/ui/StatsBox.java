// StatsBox.java
//@@author trav1sT

package chopchop.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import chopchop.commons.util.Pair;
import chopchop.logic.commands.CommandResult;
import chopchop.model.Model;
import chopchop.model.usage.RecipeUsage;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * The UI component that is responsible for displaying pinned information.
 * Displays results of statistics.
 */
public class StatsBox extends UiPart<Region> {

    private static final String SUBTITLE_NO_RECIPES = "No recipes were made recently";
    private static final String SUBTITLE_NO_INGREDIENTS = "No ingredients were made recently";
    private static final String SUBTITLE_DEFAULT = "Showing recently made recipes";

    private static final ArrayList<Pair<String, String>> EMPTY_RESULT =
        new ArrayList<>(Collections.singletonList(new Pair<>("No results found", "")));

    private static final String FXML = "StatsBox.fxml";

    // MVC? what is that?
    private final Model model;

    @FXML
    private Label subtitle;

    @FXML
    private VBox recipeList;

    /**
     * Creates a {@code StatsBox}.
     */
    public StatsBox(Model model) {
        super(FXML);

        this.model = model;
        this.model.getObservableRecipeUsages().addListener((ListChangeListener<RecipeUsage>) e -> {

            // consume all
            while (e.next()) {
            }

            this.clearMessage();
        });
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
     * Sets the content based on the command result
     */
    public void setMessage(CommandResult result) {
        this.subtitle.setText(result.toString());
        this.showRecentRecipes(result.toString(), result.getStatsMessage());
    }

    /**
     * Clears the message and goes back to the recent recipes view
     */
    public void clearMessage() {
        var list = this.model.getRecentlyUsedRecipes(10)
            .stream()
            .map(u -> Pair.of(u.getName(), u.getPrintableDate()))
            .collect(Collectors.toList());

        this.showRecentRecipes(SUBTITLE_DEFAULT, list);
    }

    private void showRecentRecipes(String subtitle, List<Pair<String, String>> list) {

        this.recipeList.getChildren().clear();

        this.subtitle.setText(subtitle);
        for (var usage : list) {
            this.recipeList.getChildren().add(new StatsItemView(usage.fst(), usage.snd()).getRoot());
        }
    }
}
