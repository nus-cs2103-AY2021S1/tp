// QuitCommand.java

package chopchop.logic.commands;

import chopchop.logic.history.HistoryManager;
import chopchop.model.Model;

public class QuitCommand extends Command {
    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) {
        return new CommandResult("", /* showHelp: */ false, /* exit: */ true);
    }

    @Override
    public String toString() {
        return String.format("QuitCommand");
    }
}
