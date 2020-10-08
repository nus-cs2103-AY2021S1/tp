package tp.cap5buddy.logic.parser;

import tp.cap5buddy.logic.commands.AddFinalGradeCommand;
import tp.cap5buddy.logic.commands.Command;
import tp.cap5buddy.logic.parser.exception.ParseException;

/**
 * Represents the parser that handles Add Final Grade command.
 */
public class AddFinalGradeParser extends Parser {
    /**
     * Represents the function call that passes info into the Command object.
     *
     * @param userInput tokenised information.
     * @return Command the respective command type.
     */
    public Command parse(String userInput) throws ParseException {
        Tokenizer token = new Tokenizer(userInput, PrefixList.MODULE_NAME_PREFIX, PrefixList.GRADE_RESULTS_PREFIX);
        String[] parsedArguments = token.tokenize();
        String modName = parsedArguments[0];
        double gradeResults = Double.parseDouble(parsedArguments[1]);
        return new AddFinalGradeCommand(modName, gradeResults);
    }
}
