package seedu.address.testutil.builders;

import seedu.address.model.module.ModuleCode;

public class ModuleCodeBuilder {
    public static final String DEFAULT_CODE = "CS2103";
    private String code;
    /**
     * Creates a {@code ModuleBuilder} with the default details.
     */
    public ModuleCodeBuilder() {
        code = DEFAULT_CODE;
    }

    /**
     * Sets the {@code Code} of the {@code Module} that we are building.
     */
    public ModuleCodeBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public ModuleCode build() {
        return new ModuleCode(this.code);
    }
}

