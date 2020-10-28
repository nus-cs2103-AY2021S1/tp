package seedu.address.logic.parser;

import java.util.Optional;

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

        String saveOrLoad = argsArr[0];

        boolean save = saveOrLoad.equals("save") || saveOrLoad.equals("s");
        Optional<Name> presetName = Optional.empty();
        if (!argsArr[1].equals("")) {
            presetName = Optional.of(ParserUtil.parseName(argsArr[1]));
        }

        return save ? new SavePresetCommand(presetName) : new LoadPresetCommand(presetName);
    }
}
