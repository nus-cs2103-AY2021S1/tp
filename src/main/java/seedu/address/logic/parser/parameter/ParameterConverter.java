package seedu.address.logic.parser.parameter;

import seedu.address.logic.parser.exceptions.ParseException;

@FunctionalInterface
public interface ParameterConverter<R> {
    R apply(String t) throws ParseException;
}
