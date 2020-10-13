package tp.cap5buddy.logic.parser;

import tp.cap5buddy.logic.commands.Command;
import tp.cap5buddy.logic.commands.EditContactCommand;
import tp.cap5buddy.logic.commands.EditContactCommand.EditContactDescriptor;
import tp.cap5buddy.logic.parser.exception.ParseException;

public class EditContactParser extends Parser {

    @Override
    public Command parse(String userInput) throws ParseException {
        Tokenizer tokenizer = new Tokenizer(userInput, PrefixList.CONTACT_INDEX_PREFIX,
                PrefixList.CONTACT_NAME_PREFIX, PrefixList.CONTACT_EMAIL_PREFIX);
        String[] parsedArguments = tokenizer.tokenize();
        try {
            int contactID = Integer.parseInt(parsedArguments[0]);
            String name = parsedArguments[1];
            String email = parsedArguments[2];
            EditContactDescriptor editPersonDescriptor = new EditContactDescriptor();
            editPersonDescriptor.setName(ParserUtil.parseName(name));
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(email));
            if (!editPersonDescriptor.isAnyFieldEdited()) {
                String error = "At least one contact field must be edited";
                throw new ParseException(error);
            }
            return new EditContactCommand(contactID, editPersonDescriptor);
        } catch (NumberFormatException ex) {
            String error = "No contact ID provided";
            throw new ParseException(error);
        } catch (ParseException ex) {
            String error = "error";
            throw new ParseException(error);
        }
    }
}
