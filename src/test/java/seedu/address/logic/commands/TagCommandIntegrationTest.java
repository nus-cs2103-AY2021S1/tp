package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.TagBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class TagCommandIntegrationTest {

    @Test
    public void execute_newTag_success() {
        Model model = new ModelStubWithTagAndTaglist();
        Tag validTag = new TagBuilder().build();

        Model expectedModel = new ModelStubWithTagAndTaglist();
        expectedModel.addTag(validTag.toAbsolute());

        assertCommandSuccess(new TagCommand(validTag), model,
                String.format(TagCommand.MESSAGE_SUCCESS, validTag), expectedModel);
    }

    @Test
    public void execute_duplicateTag_throwsCommandException() {
        Model model = new ModelManager();
        Tag validTag = new TagBuilder().build();
        model.addTag(validTag.toAbsolute());
        assertCommandFailure(new TagCommand(validTag), model, TagCommand.MESSAGE_DUPLICATE_TAG);
    }

}
