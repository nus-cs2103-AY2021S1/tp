package seedu.tr4cker.logic.parser;

import static seedu.tr4cker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tr4cker.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.tr4cker.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;
import java.time.YearMonth;

import org.junit.jupiter.api.Test;

import seedu.tr4cker.logic.commands.PlannerCommand;
import seedu.tr4cker.model.task.TaskDueInPredicate;
import seedu.tr4cker.model.util.GotoDateUtil;

class PlannerCommandParserTest {

    private static final String MESSAGE_INVALID_SWITCH_TAB_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            PlannerCommand.MESSAGE_SWITCH_TAB_USAGE);
    private static final String MESSAGE_INVALID_GOTO_FORMAT = PlannerCommand.MESSAGE_GOTO_USAGE;
    private final PlannerCommandParser plannerCommandParser = new PlannerCommandParser();
    private final PlannerCommand plannerCommand = new PlannerCommand();

    @Test
    public void parse_switchPlannerTab_success() {
        assertParseSuccess(plannerCommandParser, "", plannerCommand);
    }

    @Test
    public void parse_switchPlannerTab_failure() {
        assertParseFailure(plannerCommandParser, "plannerrr", MESSAGE_INVALID_SWITCH_TAB_FORMAT);
        assertParseFailure(plannerCommandParser, "plannerrr haha", MESSAGE_INVALID_SWITCH_TAB_FORMAT);
    }

    @Test
    public void parse_gotoDay_success() {
        String message1 = GotoDateUtil.parseGotoDay(LocalDate.now()) + " (TODAY)";
        PlannerCommand plannerCommand = new PlannerCommand(message1, LocalDate.now(), null, new TaskDueInPredicate());
        assertParseSuccess(plannerCommandParser, " goto/today", plannerCommand);
        assertParseSuccess(plannerCommandParser, " goto/tdy", plannerCommand);

        LocalDate tomorrow = LocalDate.now().plusDays(1);
        String message2 = GotoDateUtil.parseGotoDay(tomorrow) + " (TOMORROW)";
        plannerCommand = new PlannerCommand(message2, tomorrow, null, new TaskDueInPredicate(tomorrow));
        assertParseSuccess(plannerCommandParser, " goto/tomorrow", plannerCommand);
        assertParseSuccess(plannerCommandParser, " goto/tmr", plannerCommand);

        LocalDate localDate1 = LocalDate.of(2020, 10, 18);
        String message3 = GotoDateUtil.parseGotoDay(localDate1);
        plannerCommand = new PlannerCommand(message3, localDate1, null, new TaskDueInPredicate(localDate1));
        assertParseSuccess(plannerCommandParser, " goto/18-Oct-2020", plannerCommand);
        assertParseSuccess(plannerCommandParser, " goto/18-10-2020", plannerCommand);

        YearMonth yearMonth1 = YearMonth.of(2020, 10);
        String message4 = GotoDateUtil.parseGotoMonth(yearMonth1);
        plannerCommand = new PlannerCommand(message4, null, yearMonth1, new TaskDueInPredicate(yearMonth1));
        assertParseSuccess(plannerCommandParser, " goto/Oct-2020", plannerCommand);
        assertParseSuccess(plannerCommandParser, " goto/10-2020", plannerCommand);
    }

    @Test
    public void parse_gotoDay_failure() {
        assertParseFailure(plannerCommandParser, " goto/AAA-2020", MESSAGE_INVALID_GOTO_FORMAT);
        assertParseFailure(plannerCommandParser, " goto/blah", MESSAGE_INVALID_GOTO_FORMAT);
        assertParseFailure(plannerCommandParser, " goto/20-20-2020", MESSAGE_INVALID_GOTO_FORMAT);
    }

}
