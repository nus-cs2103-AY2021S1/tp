package chopchop.ui;

import chopchop.logic.Logic;
import chopchop.model.ingredient.Ingredient;
import chopchop.model.recipe.Recipe;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Controller class for the swappable display region.
 */
public class DisplayController extends UiPart<Region> {

    private static final String FXML = "DisplayPanel.fxml";
    private static final String WELCOME_MESSAGE = "Welcome to ChopChop, a food recipe management system!";

    private final TextDisplay textDisplay;
    private final NotificationWindow notificationWindow;
    private final ObservableList<Recipe> recipeObservableList;
    private final ObservableList<Ingredient> ingredientObservableList;

    @FXML
    private StackPane displayAreaPlaceholder;

    @FXML
    private Button recipeButton;

    @FXML
    private Button ingredientButton;

    @FXML
    private Button recommendationButton;

    /**
     * Creates a {@code DisplayController} with the given {@code Logic}.
     * @param logic
     */
    public DisplayController(Logic logic) {
        super(FXML);
        this.textDisplay = new TextDisplay(WELCOME_MESSAGE);
        this.notificationWindow = new NotificationWindow();
        this.recipeObservableList = logic.getFilteredRecipeList();
        this.ingredientObservableList = logic.getFilteredIngredientList();

        // TODO: Edit to account for loading of recipes/ingredients after UI
        this.recipeObservableList.addListener((ListChangeListener<Recipe>) c -> {
            c.next();

            /*
             * Check if a recipe was replaced in the recipe book, with an extra check to account for
             * updateFilteredRecipeList(PREDICATE_SHOW_ALL_ENTRIES).
             */
            if (c.wasReplaced() && !c.getAddedSubList().equals(c.getRemoved())) {
                this.displayRecipe(c.getAddedSubList().get(0));
            } else if (c.wasAdded()) {
                this.displayRecipe(c.getAddedSubList().get(c.getAddedSize() - 1));
            } else {
                this.displayRecipeList();
            }
        });
        this.ingredientObservableList.addListener((ListChangeListener<Ingredient>) c -> this.displayIngredientList());

        this.displayAreaPlaceholder.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode().equals(KeyCode.ESCAPE)) {
                this.displayWelcomeMessage();
                this.resetButtons();
            }
        });

        if (!logic.getFilteredRecipeList().isEmpty()) {
            this.displayRecipeList();
        } else if (!logic.getFilteredIngredientList().isEmpty()) {
            this.displayIngredientList();
        } else {
            this.displayWelcomeMessage();
        }
    }

    /**
     * Displays the RecipeViewPanel on the swappable display region.
     */
    protected void displayWelcomeMessage() {
        this.displayAreaPlaceholder.getChildren().setAll(this.textDisplay.getRoot());
    }

    /**
     * Displays the RecipeViewPanel on the swappable display region.
     */
    protected void displayRecipeList() {
        RecipeViewPanel recipeViewPanel = new RecipeViewPanel(recipeObservableList);
        displayAreaPlaceholder.getChildren().setAll(recipeViewPanel.getRoot());
        selectRecipeButton();
    }

    /**
     * Displays the RecipeDisplay on the swappable display region.
     */
    protected void displayRecipe(Recipe recipe) {
        RecipeDisplay recipeDisplay = new RecipeDisplay(recipe);
        displayAreaPlaceholder.getChildren().setAll(recipeDisplay.getRoot());
        selectRecipeButton();
    }

    /**
     * Displays the IngredientViewPanel on the swappable display region.
     */
    protected void displayIngredientList() {
        IngredientViewPanel ingredientViewPanel = new IngredientViewPanel(ingredientObservableList);
        displayAreaPlaceholder.getChildren().setAll(ingredientViewPanel.getRoot());
        selectIngredientButton();
    }

    /**
     * Opens the notification window or focuses on it if it's already opened.
     */
    public void handleNotification() {
        if (!notificationWindow.isShowing()) {
            notificationWindow.show();
        } else {
            notificationWindow.focus();
        }
    }

    /**
     * Resets buttons in navigation bar to default style.
     */
    private void resetButtons() {
        recipeButton.getStyleClass().remove("tab-button-selected");
        ingredientButton.getStyleClass().remove("tab-button-selected");
        recommendationButton.getStyleClass().remove("tab-button-selected");
    }

    /**
     * Changes the recipe button style to selected.
     */
    private void selectRecipeButton() {
        resetButtons();
        recipeButton.getStyleClass().add("tab-button-selected");
    }

    private void selectIngredientButton() {
        resetButtons();
        ingredientButton.getStyleClass().add("tab-button-selected");
    }

    /**
     * Displays the recipe panel.
     */
    @FXML
    public void handleRecipePanel(ActionEvent event) {
        displayRecipeList();
    }


    /**
     * Displays the recipe panel.
     */
    @FXML
    public void handleIngredientPanel(ActionEvent event) {
        displayIngredientList();
    }

    /**
     * Displays the recommendations panel.
     */
    @FXML
    public void handleRecommendations(ActionEvent event) {
        // To add more code.
        handleNotification();
    }
}
