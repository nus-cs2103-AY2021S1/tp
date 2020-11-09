package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

public class TagCommandParser implements Parser<TagCommand> {
    @Override
    public TagCommand parse(String args) throws ParseException {
        String trimArgs = args.trim();
        String[] argsArr = trimArgs.split(" +");
        ParserUtil.argsLengthAtLeast(argsArr, TagCommand.COMMAND_WORD, TagCommand.MESSAGE_USAGE, 2);

        Index index = ParserUtil.parseIndex(argsArr[0], "Order Item Index");
        StringBuilder tagText = new StringBuilder();
        for (int i = 1; i < argsArr.length; i++) {
            tagText.append((i > 1 ? " " : "") + argsArr[i]);
        }
        Tag tag = ParserUtil.parseTag(tagText.toString());

        return new TagCommand(index, tag);
    }
}
