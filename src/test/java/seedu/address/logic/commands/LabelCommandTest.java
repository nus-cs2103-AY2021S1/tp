package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LABEL;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.label.Label;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagName;
import seedu.address.testutil.TagBuilder;

public class LabelCommandTest {
    private ModelStubWithTag modelStub;

    @BeforeEach
    public void setUp() {
        Tag newTag = new TagBuilder().build();
        modelStub = new ModelStubWithTag(newTag);
    }

    @Test
    public void execute_allFieldsPresent_success() {
        Tag newTag = new TagBuilder().withLabels("testLabel", VALID_LABEL).build();

        ModelStubWithTag expectedModelStub = new ModelStubWithTag(newTag);

        HashSet<Label> labels = new HashSet<>();
        labels.add(new Label("testLabel"));
        labels.add(new Label(VALID_LABEL));

        LabelCommand labelCommand = new LabelCommand(newTag.getTagName(), labels);

        assertCommandSuccess(labelCommand, modelStub,
                String.format(LabelCommand.MESSAGE_SUCCESS, newTag), expectedModelStub);
    }

    @Test
    public void execute_emptyLabels_success() {
        Tag newTag = new TagBuilder().withLabels("testLabel").build();

        ModelStubWithTag expectedModelStub = new ModelStubWithTag(newTag);

        HashSet<Label> labels = new HashSet<>();

        LabelCommand labelCommand = new LabelCommand(newTag.getTagName(), labels);

        assertCommandSuccess(labelCommand, modelStub,
                String.format(LabelCommand.MESSAGE_SUCCESS, newTag), expectedModelStub);
    }

    @Test
    public void execute_tagNotInModel_throwCommandException() {
        TagName nonExistingTagName = new TagName("noExist");
        HashSet<Label> labels = new HashSet<>();

        LabelCommand labelCommand = new LabelCommand(nonExistingTagName, labels);

        assertThrows(CommandException.class, String.format(LabelCommand.MESSAGE_TAG_NOT_FOUND,
                nonExistingTagName.tagName), () -> labelCommand.execute(modelStub));
    }

    @Test
    public void execute_fileNotFound_throwCommandError() {
        Tag tagWithInvalidFileAddress = new TagBuilder().withFileAddress("C:\\somewhereOverTheRainbow").build();
        modelStub = new ModelStubWithTag(tagWithInvalidFileAddress);

        HashSet<Label> labels = new HashSet<>();
        labels.add(new Label("testLabelNew"));

        LabelCommand labelCommand = new LabelCommand(tagWithInvalidFileAddress.getTagName(), labels);

        assertThrows(CommandException.class, () -> labelCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        TagName oldTagName = new TagName("oldTag");
        TagName newTagName = new TagName("newTag");

        HashSet<Label> emptyLabels = new HashSet<>();
        HashSet<Label> labels = new HashSet<>();
        labels.add(new Label("testLabel"));

        LabelCommand labelCommand1 = new LabelCommand(newTagName, emptyLabels);
        LabelCommand labelCommand2 = new LabelCommand(oldTagName, labels);
        LabelCommand labelCommand3 = new LabelCommand(newTagName, labels);

        // same object -> returns true
        assertTrue(labelCommand1.equals(labelCommand1));

        // same values -> returns true
        LabelCommand labelommand1Copy = new LabelCommand(newTagName, emptyLabels);
        assertTrue(labelCommand1.equals(labelommand1Copy));

        // different types -> returns false
        assertFalse(labelCommand1.equals(1));

        // null -> returns false
        assertFalse(labelCommand1.equals(null));

        // different tagName -> returns false
        assertFalse(labelCommand3.equals(labelCommand2));

        // different labels -> returns false
        assertFalse(labelCommand1.equals(labelCommand3));
    }
}
