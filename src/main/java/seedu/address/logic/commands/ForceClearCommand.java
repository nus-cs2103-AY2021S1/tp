package seedu.address.logic.commands;

/**
 * Force-clears the GradPad.
 */
public class ForceClearCommand extends ClearCommand {

    @Override
    public boolean requiresStall() {
        return false;
    }
}
