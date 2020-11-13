package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

public class UnlabelCommandTest {
    private ModelStubWithTag modelStub;

    @BeforeEach
    public void setUp() {
        Tag newTag = new TagBuilder().build();
        modelStub = new ModelStubWithTag(newTag);
    }

    @Test
    public void equals() {
        TagName oldTagName = new TagName("oldTag");
        TagName newTagName = new TagName("newTag");

        HashSet<Label> emptyLabels = new HashSet<>();
        HashSet<Label> labels = new HashSet<>();
        labels.add(new Label("testLabel"));

        UnlabelCommand firstUnlabelCommand = new UnlabelCommand(newTagName, emptyLabels);
        UnlabelCommand secondUnlabelCommand = new UnlabelCommand(oldTagName, labels);

        // same object -> returns true
        assertTrue(firstUnlabelCommand.equals(firstUnlabelCommand));

        // same values -> returns true
        UnlabelCommand firstUnlabelommandCopy = new UnlabelCommand(newTagName, emptyLabels);
        assertTrue(firstUnlabelCommand.equals(firstUnlabelommandCopy));

        // different types -> returns false
        assertFalse(firstUnlabelCommand.equals(1));

        // null -> returns false
        assertFalse(firstUnlabelCommand.equals(null));

        // different tagName -> returns false
        assertFalse(firstUnlabelCommand.equals(secondUnlabelCommand));

        // different labels -> returns false
        assertFalse(firstUnlabelCommand.equals(secondUnlabelCommand));

        // different tagName but same labels -> return false
        UnlabelCommand diffTagNameUnlabelCommand = new UnlabelCommand(oldTagName, emptyLabels);
        assertFalse(firstUnlabelCommand.equals(diffTagNameUnlabelCommand));
    }

    @Test
    public void execute_tagNotInModel_throwCommandException() {
        TagName nonExistingTagName = new TagName("noExist");
        HashSet<Label> labels = new HashSet<>();

        UnlabelCommand unlabelCommand = new UnlabelCommand(nonExistingTagName, labels);

        assertThrows(CommandException.class, String.format(UnlabelCommand.MESSAGE_TAG_NOT_FOUND,
                nonExistingTagName.tagName), () -> unlabelCommand.execute(modelStub));
    }

    @Test
    public void execute_emptyLabels_success() {
        Tag newTag = new TagBuilder().build();

        ModelStubWithTag expectedModelStub = new ModelStubWithTag(newTag);

        HashSet<Label> labels = new HashSet<>();

        UnlabelCommand unlabelCommand = new UnlabelCommand(newTag.getTagName(), labels);

        assertCommandSuccess(unlabelCommand, modelStub,
                String.format(unlabelCommand.MESSAGE_SUCCESS, newTag), expectedModelStub);
    }

    @Test
    public void execute_allFieldsPresent_success() {
        TagName defaultTagName = new TagBuilder().build().getTagName();

        // All labels are valid
        modelStub.addTag(new TagBuilder().withLabels("testLabel", "deletedLabel", "anotherDeletedLabel").build());
        Tag firstExpectedDeletedLabelTag = new TagBuilder().build();

        ModelStubWithTag firstExpectedModelStub = new ModelStubWithTag(firstExpectedDeletedLabelTag);

        HashSet<Label> firstLabels = new HashSet<>();
        firstLabels.add(new Label("deletedLabel"));
        firstLabels.add(new Label("anotherDeletedLabel"));

        UnlabelCommand firstUnlabelCommand = new UnlabelCommand(defaultTagName, firstLabels);

        assertCommandSuccess(firstUnlabelCommand, modelStub,
                String.format(UnlabelCommand.MESSAGE_SUCCESS, firstExpectedDeletedLabelTag), firstExpectedModelStub);

        // Some label is invalid
        modelStub.addTag(new TagBuilder().withLabels("testLabel", "deletedLabel").build());
        Tag secondExpectedDeletedLabelTag = new TagBuilder().build();

        ModelStubWithTag secondExpectedModelStub = new ModelStubWithTag(secondExpectedDeletedLabelTag);

        HashSet<Label> secondLabels = new HashSet<>();
        secondLabels.add(new Label("deletedLabel"));
        secondLabels.add(new Label("invalidLabel"));

        UnlabelCommand secondUnlabelCommand = new UnlabelCommand(defaultTagName, secondLabels);

        StringBuilder builder = new StringBuilder();
        builder.append(String.format(UnlabelCommand.MESSAGE_LABEL_MISSING, defaultTagName));
        builder.append(String.format(UnlabelCommand.MESSAGE_DASH, new Label("invalidLabel")));
        builder.append(UnlabelCommand.MESSAGE_INVALID_LABEL);

        String expectedMessage = builder.toString();

        assertCommandSuccess(secondUnlabelCommand, modelStub, expectedMessage, secondExpectedModelStub);
    }
}
