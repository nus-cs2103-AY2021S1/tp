package seedu.address.logic.parser.parameter;

import org.apache.commons.cli.Option;

import seedu.address.logic.parser.exceptions.ParseException;

public abstract class AbstractParameter {
    private final Option option;
    private final String flag;
    private final String example;

    protected AbstractParameter(String name, String flag, String description, String example, boolean isRequired) {
        this.flag = flag;
        this.example = example;
        this.option = Option.builder(flag)
            .longOpt(name)
            .desc(description)
            .hasArg()
            .numberOfArgs(Option.UNLIMITED_VALUES)
            .required(isRequired)
            .build();
    }

    public abstract void setValue(String rawValue) throws ParseException;

    public String getFlag() {
        return this.flag;
    }

    public String getExample() {
        return this.example;
    }

    public Option asOption() {
        return this.option;
    }
}
