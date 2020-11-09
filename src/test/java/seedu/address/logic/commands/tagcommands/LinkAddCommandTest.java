package seedu.address.logic.commands.tagcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.net.MalformedURLException;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.ModelManager;
import seedu.address.model.tag.MeetingLink;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.TagBuilder;

public class LinkAddCommandTest {

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LinkAddCommand(null,
                new MeetingLink("http://www.google.com/")));
    }

    @Test
    public void constructor_nullLink_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LinkAddCommand(Index.fromOneBased(1), null));
    }

    @Test
    public void execute_linkAcceptedByModel_addSuccessful() throws Exception {
        ModelManager modelManager = new ModelManager();
        Tag validTag = new TagBuilder().build();
        modelManager.addTag(validTag);
        MeetingLink validLink = new MeetingLink("http://zoom.com/");
        CommandResult commandResult = new LinkAddCommand(Index.fromZeroBased(0), validLink).execute(modelManager);

        assertEquals(String.format(LinkAddCommand.MESSAGE_SUCCESS, validLink), commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() throws MalformedURLException {
        MeetingLink zoom = new MeetingLink("http://zoom.com/");
        MeetingLink skype = new MeetingLink("http://skype.com/");
        LinkAddCommand addZoomCommand = new LinkAddCommand(Index.fromZeroBased(1), zoom);
        LinkAddCommand addSkypeCommand = new LinkAddCommand(Index.fromZeroBased(1), skype);

        // same object -> returns true
        assertTrue(addZoomCommand.equals(addZoomCommand));

        // same values -> returns true
        LinkAddCommand addZoomCommandCopy = new LinkAddCommand(Index.fromZeroBased(1), zoom);
        assertTrue(addZoomCommandCopy.equals(addZoomCommand));

        // different types -> returns false
        assertFalse(addZoomCommand.equals(1));

        // null -> returns false
        assertFalse(addZoomCommand.equals(null));

        // different links -> returns false
        assertFalse(addSkypeCommand.equals(addZoomCommand));
    }

}
