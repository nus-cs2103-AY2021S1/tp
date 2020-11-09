package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalItems.APPLE;
import static seedu.address.testutil.TypicalTags.TAG_ABC;
import static seedu.address.testutil.TypicalTags.getSingleTagSet;
import static seedu.address.testutil.TypicalTags.getTypicalTagSet;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ItemList;
import seedu.address.model.LocationList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.RecipeList;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.Item;
import seedu.address.testutil.ItemBuilder;

public class AddItemTagCommandIntegrationTest {

    private Model model;
    private Model expectedModel;
    private Item apple;

    @BeforeEach
    public void setUp() {
        Item.setIdCounter(0);
        model = new ModelManager(new ItemList(), new LocationList(), new RecipeList(), new UserPrefs());
        expectedModel = new ModelManager(new ItemList(), new LocationList(), new RecipeList(), new UserPrefs());
        apple = new ItemBuilder(APPLE).withTags(getSingleTagSet()).build();
    }

    @Test
    public void constructor_throwsNullException() {
        assertThrows(NullPointerException.class, () -> new AddItemTagCommand(null, null));
    }

    /**
     * Tests for successful addition of a tag to an item
     */
    @Test
    public void execute_addTag_success() {
        model.addItem(apple);
        AddItemTagCommand aic = new AddItemTagCommand(VALID_ITEM_NAME_APPLE, Set.copyOf(getTypicalTagSet()));

        // expected model should contain the edited apple
        Item editedApple = new ItemBuilder(apple).withTags(getTypicalTagSet()).build();

        String expectedMessage = String.format(EditItemCommand.MESSAGE_EDIT_ITEM_SUCCESS, editedApple);
        expectedModel.addItem(editedApple);

        assertCommandSuccess(aic, model, expectedMessage, expectedModel);
    }

    /**
     * Throws null pointer exception if null is passed into AddItemTagCommand.execute
     */
    @Test
    public void execute_nullModel_throwsException() {
        model.addItem(apple);
        AddItemTagCommand aic = new AddItemTagCommand(VALID_ITEM_NAME_APPLE, Set.copyOf(getTypicalTagSet()));

        assertThrows(NullPointerException.class, () -> aic.execute(null));
    }

    /**
     * Tests for unsuccessful addition of tag(s) due to being already inside the item.
     */
    @Test
    public void execute_duplicateTag_throwsException() {
        model.addItem(new ItemBuilder(apple).withTags(getTypicalTagSet()).build());

        AddItemTagCommand aic = new AddItemTagCommand(VALID_ITEM_NAME_APPLE, Set.of(TAG_ABC));

        assertThrows(CommandException.class, () -> aic.execute(model));
    }

    /**
     * Tests for unsuccessful addition of tag(s) due to being already inside the item.
     */
    @Test
    public void execute_duplicateTagSubset_throwsException() {

        AddItemTagCommand aic = new AddItemTagCommand(VALID_ITEM_NAME_APPLE, Set.of(TAG_ABC));

        assertThrows(CommandException.class, () -> aic.execute(model));
    }
}
