package seedu.resireg.logic.commands;

import static seedu.resireg.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.resireg.logic.CommandHistory;
import seedu.resireg.model.Model;
import seedu.resireg.model.ModelManager;
import seedu.resireg.testutil.TypicalPositions;

public class HistoryCommandTest {
    private CommandHistory history = new CommandHistory();

    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    private final String labelOne = "1" + "\t";
    private final String labelTwo = "2" + "\t";
    private final String labelThree = "3" + "\t";

    @Test
    public void execute() {
        assertCommandSuccess(new HistoryCommand(), model, history, HistoryCommand.MESSAGE_NO_HISTORY, expectedModel);

        String firstCommand = "rooms";
        history.add(firstCommand);

        String firstEntry = TypicalPositions.FIRST_LABEL + firstCommand;

        assertCommandSuccess(new HistoryCommand(), model, history,
                String.format(HistoryCommand.MESSAGE_SUCCESS, firstEntry), expectedModel);

        String secondCommand = "invalid";
        String thirdCommand = "clear";
        history.add(secondCommand);
        history.add(thirdCommand);

        String secondEntry = TypicalPositions.SECOND_LABEL + secondCommand;
        String thirdEntry = TypicalPositions.THIRD_LABEL + thirdCommand;

        String expectedMessage = String.format(HistoryCommand.MESSAGE_SUCCESS,
                String.join("\n",
                        firstEntry, secondEntry, thirdEntry));
        assertCommandSuccess(new HistoryCommand(), model, history, expectedMessage, expectedModel);
    }

}
