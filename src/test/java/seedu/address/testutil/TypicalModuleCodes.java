package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.module.ModuleCode;
import seedu.address.testutil.builders.ModuleCodeBuilder;

/**
 * A utility class containing a list of {@code ModuleCode} objects to be used in tests.
 */
public class TypicalModuleCodes {

    public static final ModuleCode CS50_CODE = new ModuleCodeBuilder()
            .withCode("CS50").build();
    public static final ModuleCode CS1010S_CODE = new ModuleCodeBuilder()
            .withCode("CS1010S").build();
    public static final ModuleCode CS1101S_CODE = new ModuleCodeBuilder()
            .withCode("CS1101S").build();
    public static final ModuleCode CS2030_CODE = new ModuleCodeBuilder()
            .withCode("CS2030").build();
    public static final ModuleCode CS2100_CODE = new ModuleCodeBuilder()
            .withCode("CS2100").build();
    public static final ModuleCode CS2103_CODE = new ModuleCodeBuilder()
            .withCode("CS2103").build();

    private TypicalModuleCodes() {} // prevents instantiation

    public static List<ModuleCode> getTypicalModuleCodes() {
        return new ArrayList<>(Arrays.asList(CS50_CODE, CS1010S_CODE, CS1101S_CODE,
                CS2030_CODE, CS2100_CODE, CS2103_CODE));
    }
}
