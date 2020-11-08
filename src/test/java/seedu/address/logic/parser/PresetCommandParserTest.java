package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.LoadPresetCommand;
import seedu.address.logic.commands.PresetCommand;
import seedu.address.logic.commands.SavePresetCommand;
import seedu.address.model.vendor.Name;



public class PresetCommandParserTest {

    private PresetCommandParser parser = new PresetCommandParser();

    @Test
    public void parse_allFieldsSpecified_success() {
        assertParseSuccess(parser, "load Preset", new LoadPresetCommand(Optional.of(new Name("Preset"))));
        assertParseSuccess(parser, "load Preset 2", new LoadPresetCommand(Optional.of(new Name("Preset 2"))));
        assertParseSuccess(parser, "save Preset", new SavePresetCommand(Optional.of(new Name("Preset"))));
    }

    @Test
    public void parse_optionalFieldNull_success() {
        assertParseSuccess(parser, "load", new LoadPresetCommand(Optional.empty()));
        assertParseSuccess(parser, "save", new SavePresetCommand(Optional.empty()));
    }

    @Test
    public void parse_invalidValues_failure() {
        assertParseFailure(parser, "delete", Messages.MESSAGE_NO_INPUT_NAME);
        assertParseFailure(parser, "", PresetCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "delte", PresetCommand.MESSAGE_USAGE);
    }
}
