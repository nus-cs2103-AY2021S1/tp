package seedu.resireg.logic.commands;

/**
 * Encapsulates help messages describing the function and syntax for a Command. These messages are
 * displayed by the {@link HelpCommand}.
 */
public class Help {
    private final String commandWord;
    private final String summary;
    private final String longerDescription;

    /**
     * Creates a new instance of Help for a Command.
     *
     * @param commandWord Command word.
     * @param summary Summary of what the Command does.
     * @param longerDescription Longer description of Command, including any parameters.
     */
    Help(String commandWord, String summary, String longerDescription) {
        this.commandWord = commandWord;
        this.summary = summary.strip();
        this.longerDescription = longerDescription.strip();
    }

    Help(String commandWord, String summary) {
        this(commandWord, summary, "");
    }

    /**
     * Returns a single sentence summary of what the Command does. The summary will be prefixed by the command word.
     *
     * @return Summary message.
     */
    public final String getSummary() {
        return commandWord + ": " + summary;
    }

    /**
     * Returns the full help message for the Command, which is a concatenation of the summary and the
     * longer description.
     *
     * @return Full help message.
     */
    public final String getFullMessage() {
        return getSummary() + " " + longerDescription;
    }
}
