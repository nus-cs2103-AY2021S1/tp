// QuitCommand.java

package chopchop.logic.commands;

import chopchop.model.Model;

public class QuitCommand extends Command {

    public QuitCommand() {
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("", /* showHelp: */ false, /* exit: */ true);
    }
}
