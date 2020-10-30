//@@author fall9x

package chopchop.ui;

import chopchop.model.recipe.Recipe;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * An UI component that displays information of a {@code Recipe}.
 */
public class RecommendationCard extends UiPart<Region> {

    private static final String FXML = "RecommendationCard.fxml";
    private static final String RECOMMENDED_MESSAGE = "These recommended recipes all contain ingredients that you "
            + "currently have.";
    private static final String EXPIRING_MESSAGE = "Consider cooking this recipe to use ingredients that are about to"
            + " expire.";
    private static final String OLD_MESSAGE = "Consider cooking this recipe that you haven't tried for a while.";

    public final Recipe recipe;

    @FXML
    private TextFlow recommendationText;

    @FXML
    private StackPane recipeCardContainer;

    /**
     * Creates a {@code RecipeCard} with the given {@code Recipe}.
     */
    public RecommendationCard(Recipe recipe, int id) {
        super(FXML);
        this.recipe = recipe;

        var recipeCard = new RecipeCard(recipe, id);
        this.recipeCardContainer.getChildren().add(recipeCard.getRoot());
        this.recommendationText.getChildren().add(new Text(EXPIRING_MESSAGE));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof RecommendationCard)) {
            return false;
        }
        // state check
        RecommendationCard card = (RecommendationCard) other;
        return recipe.equals(card.recipe);
    }
}
