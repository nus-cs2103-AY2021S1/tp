package seedu.address.logic.parser;

import seedu.address.logic.commands.LoadPresetCommand;
import seedu.address.logic.commands.PresetCommand;
import seedu.address.logic.commands.SavePresetCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.vendor.Name;

import java.util.Optional;

public class PresetCommandParser implements Parser<PresetCommand> {

    @Override
    public PresetCommand parse(String args) throws ParseException {
        String trimArgs = args.trim();
        String[] argsArr = trimArgs.split(" ");
        ParserUtil.checkArgsLength(argsArr, PresetCommand.COMMAND_WORD, PresetCommand.MESSAGE_USAGE, 1, 2);

        String saveOrLoad = argsArr[0].trim().toLowerCase();
        //TODO: make parserutil function for this
        assert saveOrLoad.equals("save") || saveOrLoad.equals("load")
                || saveOrLoad.equals("s") || saveOrLoad.equals("l");

        boolean save = saveOrLoad.equals("save") || saveOrLoad.equals("s");
        Optional<Name> presetName = Optional.empty();
        if (argsArr.length == 2) {
            presetName = Optional.of(ParserUtil.parseName(argsArr[1]));
        }

        return save ? new SavePresetCommand(presetName) : new LoadPresetCommand(presetName);
    }
}
