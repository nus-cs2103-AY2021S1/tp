package tp.acecs2103.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import tp.acecs2103.logic.parser.exceptions.ParseException;
import tp.acecs2103.model.task.TaskCategory;

public class ParserUtilTest {

    @Test
    public void parserUtil_parseIndexValid1_success() {
        try {
            assert "1".equals(ParserUtil.parseIndex(" 1"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parserUtil_parseIndexValid2_success() {
        try {
            assert "50".equals(ParserUtil.parseIndex("     50   "));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parserUtil_parseIndexInvalid1_fail() {
        try {
            ParserUtil.parseIndex("     0   ");
        } catch (ParseException e) {
            assert true;
        }
    }

    @Test
    public void parserUtil_parseIndexInvalid2_fail() {
        try {
            ParserUtil.parseIndex("-1");
        } catch (ParseException e) {
            assert true;
        }
    }

    @Test
    public void parserUtil_parseWeekNumberValid_success() {
        try {
            assertEquals(10, ParserUtil.parseWeekNumber(" 10 "));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parserUtil_parseWeekNumberInvalid1_fail() {
        try {
            ParserUtil.parseWeekNumber("           ");
        } catch (ParseException e) {
            assert true;
        }
    }

    @Test
    public void parserUtil_parseWeekNumberInvalid2_fail() {
        try {
            ParserUtil.parseWeekNumber(" abababa ");
        } catch (ParseException e) {
            assert true;
        }
    }

    @Test
    public void parserUtil_parseWeekNumberInvalid3_fail() {
        try {
            ParserUtil.parseWeekNumber(" 0 ");
        } catch (ParseException e) {
            assert true;
        }
    }

    @Test
    public void parserUtil_parseWeekNumberInvalid4_fail() {
        try {
            ParserUtil.parseWeekNumber(" 14 ");
        } catch (ParseException e) {
            assert true;
        }
    }

    @Test
    public void parserUtil_parseDescriptionValid_success() {
        try {
            assertEquals("a a", ParserUtil.parseDescription("       a a"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parserUtil_parseCustomizedDeadlineValid1_success() {
        try {
            assertEquals(LocalDate.parse("2020-10-01"), ParserUtil.parseCustomizedDeadline("   2020-10-01   "));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parserUtil_parseCustomizedDeadlineValid2_success() {
        try {
            assertEquals(LocalDate.parse("2020-02-29"), ParserUtil.parseCustomizedDeadline("   2020-02-29   "));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parserUtil_parseCustomizedDeadlineInvalid1_fail() {
        try {
            ParserUtil.parseCustomizedDeadline("      ");
        } catch (ParseException e) {
            assert true;
        }
    }

    @Test
    public void parserUtil_parseCustomizedDeadlineInvalid2_fail() {
        try {
            ParserUtil.parseCustomizedDeadline("   2020-13-14   ");
        } catch (ParseException e) {
            assert true;
        }
    }

    @Test
    public void parserUtil_parseCustomizedDeadlineInvalid3_fail() {
        try {
            ParserUtil.parseCustomizedDeadline("   2020-02-30   ");
        } catch (ParseException e) {
            assert true;
        }
    }

    @Test
    public void parserUtil_parseCustomizedDeadlineInvalid4_fail() {
        try {
            ParserUtil.parseCustomizedDeadline("   2019-02-29   ");
        } catch (ParseException e) {
            assert true;
        }
    }

    @Test
    public void parserUtil_parseRemarkValid_success() {
        try {
            assertEquals("a a", ParserUtil.parseRemark("       a a"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void parserUtil_parseTypeValid_success() {
        try {
            assert "a".equals(ParserUtil.parseType("a"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parserUtil_parseTypeInvalid_fail() {
        try {
            ParserUtil.parseType("       ");
        } catch (ParseException e) {
            assert true;
        }
    }

    @Test
    public void parserUtil_parseCategoryValid_success() {
        try {
            assert ParserUtil.parseCategory("Admin") == TaskCategory.ADMIN
                    && ParserUtil.parseCategory("Topic") == TaskCategory.TOPIC
                    && ParserUtil.parseCategory("Ip") == TaskCategory.IP
                    && ParserUtil.parseCategory("Tp") == TaskCategory.TP;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parserUtil_parseCategoryInvalid_fail() {
        try {
            ParserUtil.parseCategory("dummy");
        } catch (ParseException e) {
            assert true;
        }
    }
}
