package seedu.address.logic.parser.parameter;

import java.util.Optional;

import seedu.address.logic.parser.exceptions.ParseException;

public class Parameter<T> extends AbstractParameter {
    private final ParameterConverter<T> converter;
    private Optional<T> value = Optional.empty();

    public Parameter(String name, String flag, String description, String example, ParameterConverter<T> converter) {
        super(name, flag, description, example, true);
        this.converter = converter;
    }

    @Override
    public void setValue(String rawValue) throws ParseException {
        this.value = Optional.of(converter.apply(rawValue));
    }

    public T consume() {
        assert this.value.isPresent() : "Command being run before being initialized correctly.";
        return this.value.get();
    }
}
