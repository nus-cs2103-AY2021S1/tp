package seedu.pivot.logic.parser;

import static seedu.pivot.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.pivot.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.casecommands.AddCaseCommand;
import seedu.pivot.model.investigationcase.ArchiveStatus;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.model.investigationcase.Description;
import seedu.pivot.model.investigationcase.Document;
import seedu.pivot.model.investigationcase.Status;
import seedu.pivot.model.investigationcase.Title;
import seedu.pivot.model.investigationcase.caseperson.Suspect;
import seedu.pivot.model.investigationcase.caseperson.Victim;
import seedu.pivot.model.investigationcase.caseperson.Witness;
import seedu.pivot.model.tag.Tag;

public class AddCommandParserMainPageTest {

    public static final String VALID_TITLE = "I am a valid Title";
    public static final String PREFIX_TITLE = " " + CliSyntax.PREFIX_TITLE.getPrefix();
    public static final String TYPE_CASE = "case";

    private static Index caseIndex = Index.fromZeroBased(INDEX_FIRST_PERSON.getZeroBased());

    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_validArgs_returnsAddCaseCommand() {
        Title title = new Title(VALID_TITLE);
        Description description = new Description("");
        Status status = Status.createStatus("active");
        List<Document> documents = new ArrayList<>();
        List<Suspect> suspects = new ArrayList<>();
        List<Victim> victims = new ArrayList<>();
        List<Witness> witnesses = new ArrayList<>();
        Set<Tag> tagList = new HashSet<>();
        Case validCase = new Case(title, description, status, documents,
                suspects, victims, witnesses, tagList, ArchiveStatus.DEFAULT);
        assertParseSuccess(parser, TYPE_CASE + PREFIX_TITLE + VALID_TITLE, new AddCaseCommand(validCase));
    }

}
