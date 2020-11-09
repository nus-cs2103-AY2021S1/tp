package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_NO_TAGS;
import static seedu.address.commons.core.Messages.MESSAGE_TAGS_SUCCESS;
import static seedu.address.testutil.TypicalModules.getTypicalGradPad;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class TagsCommandTest {

    private Model modelWithTags;
    private Model modelWithoutTags;

    @BeforeEach
    public void setUp() {
        modelWithTags = new ModelManager(getTypicalGradPad(), new UserPrefs());
        modelWithoutTags = new ModelManager();
    }

    @Test
    public void execute_modelWithTags_printsTags() {
        List<String> tagNames = modelWithTags.getGradPad().getTags().getTagNames();
        String expectedMessage = MESSAGE_TAGS_SUCCESS + String.join("\n", tagNames);
        TagsCommand tagsCommand = new TagsCommand();

        // actual and expected model are the same as there should be no change
        CommandTestUtil.assertCommandSuccess(tagsCommand, modelWithTags, expectedMessage, modelWithTags);
    }

    @Test
    public void execute_modelWithoutTags_noTagsMessage() {
        String expectedMessage = MESSAGE_NO_TAGS;
        TagsCommand tagsCommand = new TagsCommand();

        // actual and expected model are the same as there should be no change
        CommandTestUtil.assertCommandSuccess(tagsCommand, modelWithoutTags, expectedMessage, modelWithoutTags);
    }
}
