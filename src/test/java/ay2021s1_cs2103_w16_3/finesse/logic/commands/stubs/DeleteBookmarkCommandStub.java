package ay2021s1_cs2103_w16_3.finesse.logic.commands.stubs;

import static ay2021s1_cs2103_w16_3.finesse.commons.core.Messages.MESSAGE_METHOD_SHOULD_NOT_BE_CALLED;

import ay2021s1_cs2103_w16_3.finesse.commons.core.index.Index;
import ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandResult;
import ay2021s1_cs2103_w16_3.finesse.logic.commands.bookmark.DeleteBookmarkCommand;
import ay2021s1_cs2103_w16_3.finesse.model.Model;

/**
 * A {@code DeleteBookmarkCommand} stub that takes in an {@code Index} and returns the same
 * {@code Index} when its {@code getTargetIndex} method is called. All of its other
 * methods fail when called.
 */
public class DeleteBookmarkCommandStub extends DeleteBookmarkCommand {
    private final Index targetIndex;

    /**
     * Constructs a {@code DeleteBookmarkCommandStub} object.
     *
     * @param targetIndex The target index in the displayed list.
     */
    public DeleteBookmarkCommandStub(Index targetIndex) {
        super(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    protected Index getTargetIndex() {
        return targetIndex;
    }

    @Override
    public CommandResult execute(Model model) {
        throw new AssertionError(MESSAGE_METHOD_SHOULD_NOT_BE_CALLED);
    }

    @Override
    public boolean equals(Object other) {
        throw new AssertionError(MESSAGE_METHOD_SHOULD_NOT_BE_CALLED);
    }
}
