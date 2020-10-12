package tp.cap5buddy.logic.parser;

import tp.cap5buddy.logic.commands.AddGradeCommand;
import tp.cap5buddy.logic.commands.Command;
import tp.cap5buddy.logic.parser.exception.ParseException;

/**
 * Represents the parser that handles Add Grade command.
 */
public class AddGradeParser extends Parser {
    /**
     * Represents the function call that passes info into the Command object.
     *
     * @param userInput tokenised information.
     * @return Command the respective command type.
     */
    public Command parse(String userInput) throws ParseException {
        Tokenizer token = new Tokenizer(userInput, PrefixList.MODULE_NAME_PREFIX, PrefixList.GRADE_PERCENTAGE_PREFIX,
                PrefixList.GRADE_RESULTS_PREFIX);
        String[] parsedArguments = token.tokenize();
        String modName = parsedArguments[0];
        int gradePercentage = Integer.parseInt(parsedArguments[1]);
        double gradeResults = Double.parseDouble(parsedArguments[2]);
        return new AddGradeCommand(modName, gradePercentage, gradeResults);
    }
}
