package seedu.address.logic.parser.parameter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.junit.jupiter.api.Test;

public class McGymmyParserTest { //TODO
    @Test
    void provide_values_success() {
        ParameterSet parameterSet = new ParameterSet();
        Parameter<String> resultParameter1 = makeDummyParameter("test", "t");
        Parameter<String> resultParameter2 = makeDummyParameter("test2", "t2");
        try {
            parameterSet.addParameter(resultParameter1);
            parameterSet.addParameter(resultParameter2);
            Options options = parameterSet.asOptions();
            CommandLine cmd = this.parser.parse(options, "-t aaaa -t2 bbbb".split(" "));
            parameterSet.provideValues(cmd);
            assertEquals(resultParameter1.consume(), "aaaa");
            assertEquals(resultParameter2.consume(), "bbbb");

        } catch (Exception e) {
            fail("Unexpected exception was caught: " + e.getMessage());
        }

    }
}
