package seedu.address.logic.parser;

import seedu.address.logic.commands.AddPolicyCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import seedu.address.model.policy.Policy;
import seedu.address.model.policy.PolicyDescription;
import seedu.address.model.policy.PolicyName;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

public class AddPolicyCommandParser implements Parser<AddPolicyCommand> {

    public AddPolicyCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args,
                PREFIX_POLICY_NAME,
                PREFIX_POLICY_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_POLICY_NAME, PREFIX_POLICY_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPolicyCommand.MESSAGE_USAGE));
        }

        PolicyName name = ParserUtil.parsePolicyName(argMultimap.getValue(PREFIX_POLICY_NAME).get());
        PolicyDescription description =  ParserUtil.parsePolicyDescription(
                argMultimap.getValue(PREFIX_POLICY_DESCRIPTION).get());

        Policy policy = new Policy(name, description);

        return new AddPolicyCommand(policy);
    }
}
