package trackitnus.logic.parser.module;

import org.junit.jupiter.api.Test;

import trackitnus.commons.core.Messages;
import trackitnus.logic.commands.module.AddModuleCommand;
import trackitnus.logic.parser.CommandParserTestUtil;
import trackitnus.model.commons.Code;
import trackitnus.model.commons.Name;
import trackitnus.model.module.Module;

public class AddModuleCommandParserTest {
    private final AddModuleCommandParser parser = new AddModuleCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        AddModuleCommand expectedCs1231s = new AddModuleCommand(new Module(new Code("CS1231S"), new Name("Discrete "
            + "Structures")));
        AddModuleCommand expectedMkt3761d =
            new AddModuleCommand(new Module(new Code("MKT3761D"), new Name("TIM: Sustainability Marketing")));
        CommandParserTestUtil.assertParseSuccess(parser, " n/Discrete Structures m/CS1231S", expectedCs1231s);
        CommandParserTestUtil.assertParseSuccess(parser, " m/CS1231S n/Discrete Structures ", expectedCs1231s);
        CommandParserTestUtil.assertParseSuccess(parser, " m/CS1231S n/Discrete Structures   ", expectedCs1231s);
        CommandParserTestUtil
            .assertParseSuccess(parser, " n/TIM: Sustainability Marketing m/MKT3761D", expectedMkt3761d);
        CommandParserTestUtil
            .assertParseSuccess(parser, " m/MKT3761D n/TIM: Sustainability Marketing ", expectedMkt3761d);
        CommandParserTestUtil
            .assertParseSuccess(parser, " m/MKT3761D n/TIM: Sustainability Marketing   ", expectedMkt3761d);
    }

    @Test
    public void parse_missingName_failure() {
        CommandParserTestUtil
            .assertParseFailure(parser, " m/CS1231S", String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                AddModuleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingCode_failure() {
        CommandParserTestUtil
            .assertParseFailure(parser, " n/Discrete Structures", String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                AddModuleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyName_failure() {
        CommandParserTestUtil
            .assertParseFailure(parser, " m/CS1231S n/",
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidCode_failure() {
        CommandParserTestUtil
            .assertParseFailure(parser, " m/CS123135S n/",
                Code.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidName_failure() {
        CommandParserTestUtil
            .assertParseFailure(parser, " m/CS1231S n/   ",
                Name.MESSAGE_CONSTRAINTS);

    }
}
