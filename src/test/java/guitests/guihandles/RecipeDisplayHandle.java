package guitests.guihandles;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A handle to the {@code RecipeDisplay} in the GUI.
 */
public class RecipeDisplayHandle extends NodeHandle<Node> {
    private static final String NAME_FIELD_ID = "#recipeName";
    private static final String INGREDIENT_LIST_FIELD_ID = "#ingredientList";
    private static final String STEP_LIST_FIELD_ID = "#stepList";
    private static final String TAG_LIST_FIELD_ID = "#tagList";

    private final Label nameLabel;
    private final List<Text> tagTexts;
    private final List<Text> ingredientTexts;
    private final List<Text> stepTexts;

    /**
     * Constructs a {@code RecipeDisplayHandle} with the given {@code recipeDisplayNode}.
     */
    public RecipeDisplayHandle(Node recipeDisplayHandleNode) {
        super(recipeDisplayHandleNode);

        nameLabel = getChildNode(NAME_FIELD_ID);

        Region tagsContainer = getChildNode(TAG_LIST_FIELD_ID);
        tagTexts = tagsContainer
                .getChildrenUnmodifiable()
                .stream()
                .map(Text.class::cast)
                .collect(Collectors.toList());

        Region ingredientContainer = getChildNode(TAG_LIST_FIELD_ID);
        ingredientTexts = ingredientContainer
                .getChildrenUnmodifiable()
                .stream()
                .map(Text.class::cast)
                .collect(Collectors.toList());

        Region stepContainer = getChildNode(TAG_LIST_FIELD_ID);
        stepTexts = stepContainer
            .getChildrenUnmodifiable()
            .stream()
            .map(Text.class::cast)
            .collect(Collectors.toList());
    }

    /**
     * Retrieves the name of the recipe in the recipe display.
     */
    public String getName() {
        return nameLabel.getText();
    }

    /**
     * Retrieves the list of tags of the recipe in the recipe display.
     */
    public List<String> getTags() {
        return tagTexts
                .stream()
                .map(Text::getText)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves the list of tags of the recipe in the recipe display.
     */
    public List<String> getIngredients() {
        return ingredientTexts
                .stream()
                .map(Text::getText)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves the list of tags of the recipe in the recipe display.
     */
    public List<String> getSteps() {
        return stepTexts
                .stream()
                .map(Text::getText)
                .collect(Collectors.toList());
    }
}
