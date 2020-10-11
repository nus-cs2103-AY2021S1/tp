package jimmy.mcgymmy.logic.parser.parameter;

import jimmy.mcgymmy.logic.parser.exceptions.ParseException;

/**
 * Functional interface for parameter parsers.
 *
 * @param <R> Output type of this parser.
 */
@FunctionalInterface
public interface ParameterConverter<R> {
    R apply(String t) throws ParseException;
}
