package seedu.address.logic.parser;

import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.DeletePresetCommand;
import seedu.address.logic.commands.LoadPresetCommand;
import seedu.address.logic.commands.PresetCommand;
import seedu.address.logic.commands.SavePresetCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.vendor.Name;

public class PresetCommandParser implements Parser<PresetCommand> {

    @Override
    public PresetCommand parse(String args) throws ParseException {
        String[] argsArr = ParserUtil.checkPresetSyntax(args);
        ParserUtil.checkArgsLength(argsArr, PresetCommand.COMMAND_WORD, PresetCommand.MESSAGE_USAGE, 2, 2);

        String modeType = argsArr[0];

        boolean save = modeType.startsWith("s");
        boolean load = false;
        if (!save) {
            load = modeType.startsWith("l");
        }
        Optional<Name> presetName = Optional.empty();
        if (!argsArr[1].equals("")) {
            presetName = Optional.of(ParserUtil.parseName(argsArr[1]));
        }

        if (!save && !load && argsArr[1].equals("")) {
            throw new ParseException(Messages.MESSAGE_NO_INPUT_NAME);
        }

        return save ? new SavePresetCommand(presetName)
                : load ? new LoadPresetCommand(presetName)
                : new DeletePresetCommand(presetName);
    }
}
