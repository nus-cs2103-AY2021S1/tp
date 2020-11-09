package tp.acecs2103.logic.parser;

import org.junit.jupiter.api.Test;

import tp.acecs2103.logic.commands.FilterCommand;
import tp.acecs2103.logic.commands.exceptions.CommandException;
import tp.acecs2103.logic.parser.exceptions.ParseException;
import tp.acecs2103.model.task.WeekNumber;

public class FilterCommandParserTest {
    @Test
    public void parse_validFilterCommandWkl_success() {
        FilterCommandParser filterCommandParser = new FilterCommandParser();
        String parametersStub = " w/4 k/pending l/official";
        FilterCommand expected = null;
        try {
            expected = new FilterCommand("pending", "official", new WeekNumber("4"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            assert filterCommandParser.parse(parametersStub).equals(expected);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (CommandException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void parse_validFilterCommandKl_success() {
        FilterCommandParser filterCommandParser = new FilterCommandParser();
        String parametersStub = " k/pending l/official";
        FilterCommand expected = null;
        try {
            expected = new FilterCommand("pending", "official");
            assert filterCommandParser.parse(parametersStub).equals(expected);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_validFilterCommandWk_success() {
        FilterCommandParser filterCommandParser = new FilterCommandParser();
        String parametersStub = " w/4 k/done";
        FilterCommand expected = null;
        try {
            expected = new FilterCommand("done", new WeekNumber("4"));
            assert filterCommandParser.parse(parametersStub).equals(expected);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_validFilterCommandK_success() {
        FilterCommandParser filterCommandParser = new FilterCommandParser();
        String parametersStub = " k/done";
        FilterCommand expected = null;
        try {
            expected = new FilterCommand("done");
            assert filterCommandParser.parse(parametersStub).equals(expected);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_invalidFilterCommandK_fail() {
        FilterCommandParser filterCommandParser = new FilterCommandParser();
        String parametersStub = " k/pending";
        try {
            filterCommandParser.parse(parametersStub);
            assert false;
        } catch (ParseException | CommandException e) {
            assert true;
        }
    }

    @Test
    public void parse_invalidFilterCommandKL_fail() {
        FilterCommandParser filterCommandParser = new FilterCommandParser();
        String parametersStub = " k/done l/official";
        try {
            filterCommandParser.parse(parametersStub);
            assert false;
        } catch (ParseException | CommandException e) {
            assert true;
        }
    }

    @Test
    public void parse_invalidFilterCommandwkl_fail() {
        FilterCommandParser filterCommandParser = new FilterCommandParser();
        String parametersStub = " w/4 k/done l/official";
        try {
            filterCommandParser.parse(parametersStub);
            assert false;
        } catch (ParseException | CommandException e) {
            assert true;
        }
    }

    @Test
    public void parse_invalidFilterCommandemptyArgument_fail() {
        FilterCommandParser filterCommandParser = new FilterCommandParser();
        String parametersStub = " ";
        try {
            filterCommandParser.parse(parametersStub);
            assert false;
        } catch (ParseException | CommandException e) {
            assert true;
        }
    }
}
