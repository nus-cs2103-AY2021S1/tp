package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_BANANA;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalItems.APPLE;
import static seedu.address.testutil.TypicalTags.TAG_ABC;
import static seedu.address.testutil.TypicalTags.TAG_BERT;
import static seedu.address.testutil.TypicalTags.getSingleTagSet;
import static seedu.address.testutil.TypicalTags.getTypicalTagSet;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ItemList;
import seedu.address.model.item.Item;
import seedu.address.testutil.ItemBuilder;

public class AddItemTagCommandTest {

    private EditItemCommandTest.ModelStubWithItemList modelStub;
    private EditItemCommandTest.ModelStubWithItemList expectedModelStub;

    private Item apple;
    private ItemList itemList;
    private ItemList expectedItemList;

    @BeforeEach
    public void setUp() {
        apple = new ItemBuilder(APPLE).withTags(getSingleTagSet()).build();
        itemList = new ItemList();
        expectedItemList = new ItemList();
    }

    @Test
    public void constructor_throwsNullException() {
        assertThrows(NullPointerException.class, () -> new AddItemTagCommand(null, null));
    }

    /**
     * Tests for successful addition of tag to item
     */
    @Test
    public void execute_addTag_success() {
        itemList.addItem(apple);
        modelStub = new EditItemCommandTest.ModelStubWithItemList(itemList);

        AddItemTagCommand aic = new AddItemTagCommand(VALID_ITEM_NAME_APPLE, Set.copyOf(getTypicalTagSet()));

        // expected model should contain the edited apple
        Item editedApple = new ItemBuilder(apple).withTags(getTypicalTagSet()).build();

        String expectedMessage = String.format(EditItemCommand.MESSAGE_EDIT_ITEM_SUCCESS, editedApple);
        expectedItemList.addItem(editedApple);
        expectedModelStub = new EditItemCommandTest.ModelStubWithItemList(expectedItemList);

        assertCommandSuccess(aic, modelStub, expectedMessage, expectedModelStub);
    }

    /**
     * Throws null pointer exception if null is passed into AddItemTagCommand.execute
     */
    @Test
    public void execute_nullModel_throwsException() {
        itemList.addItem(apple);
        AddItemTagCommand aic = new AddItemTagCommand(VALID_ITEM_NAME_APPLE, Set.copyOf(getTypicalTagSet()));

        assertThrows(NullPointerException.class, () -> aic.execute(null));
    }

    /**
     * Tests for unsuccessful addition of tag(s) due to being already inside the item.
     */
    @Test
    public void execute_duplicateTag_throwsException() {
        itemList.addItem(apple);
        modelStub = new EditItemCommandTest.ModelStubWithItemList(itemList);

        AddItemTagCommand aic = new AddItemTagCommand(VALID_ITEM_NAME_APPLE, Set.of(TAG_ABC));

        assertThrows(CommandException.class, () -> aic.execute(modelStub));
    }

    @Test
    public void equals() {
        AddItemTagCommand aic1 = new AddItemTagCommand(VALID_ITEM_NAME_APPLE, Set.of(TAG_ABC));
        AddItemTagCommand aic2 = new AddItemTagCommand(VALID_ITEM_NAME_APPLE, Set.of(TAG_ABC));

        // same values -> returns true
        assertEquals(aic1, aic2);

        // same object -> returns true
        assertEquals(aic1, aic1);

        // null -> returns false
        assertNotEquals(null, aic1);

        // different types -> returns false
        assertNotEquals(aic1, new ListItemCommand());

        // different Tag -> returns false
        AddItemTagCommand aic3 = new AddItemTagCommand(VALID_ITEM_NAME_APPLE, Set.of(TAG_BERT));
        assertNotEquals(aic1, aic3);

        // different name -> returns false
        AddItemTagCommand aic4 = new AddItemTagCommand(VALID_ITEM_NAME_BANANA, Set.of(TAG_ABC));
        assertNotEquals(aic1, aic4);
    }
}
