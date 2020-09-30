package tp.cap5buddy.logic.parser;

import tp.cap5buddy.logic.commands.Command;
import tp.cap5buddy.logic.commands.DeleteModuleCommand;

/**
 * Represents the parser that handles Delete Module command.
 */
public class DeleteModuleParser extends Parser {

    /**
     * Represents the function call that passes info into the Command object.
     *
     * @param userInput tokenised information.
     * @return Command the respective command type.
     */
    @Override
    public Command parse(String userInput) {
        Tokenizer token = new Tokenizer(userInput);
        String[] mod = token.getWords();
        int position = Integer.parseInt(mod[2]);
        return new DeleteModuleCommand(position);
    }
}
