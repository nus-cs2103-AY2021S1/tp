package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTags.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.TagBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class TagCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newTag_success() {
        Tag validPerson = new TagBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addTag(validPerson);

        assertCommandSuccess(new TagCommand(validPerson), model,
                String.format(TagCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_duplicateTag_throwsCommandException() {
        Tag personInList = model.getAddressBook().getTagList().get(0);
        assertCommandFailure(new TagCommand(personInList), model, TagCommand.MESSAGE_DUPLICATE_TAG);
    }

}
