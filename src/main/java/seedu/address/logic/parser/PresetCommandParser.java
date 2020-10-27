package seedu.address.logic.parser;

import seedu.address.logic.commands.PresetCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.vendor.Name;

public class PresetCommandParser implements Parser<PresetCommand> {

    @Override
    public PresetCommand parse(String args) throws ParseException {
        String trimArgs = args.trim();
        String[] argsArr = trimArgs.split(" ");
        ParserUtil.checkArgsLength(argsArr, PresetCommand.COMMAND_WORD, PresetCommand.MESSAGE_USAGE, 2, 2);

        String saveOrLoad = argsArr[0].trim().toLowerCase();
        assert saveOrLoad.equals("save") || saveOrLoad.equals("load");

        boolean save = saveOrLoad.equals("save");
        Name presetName = ParserUtil.parseName(argsArr[1]);

        return new PresetCommand(save, presetName);
    }
}
