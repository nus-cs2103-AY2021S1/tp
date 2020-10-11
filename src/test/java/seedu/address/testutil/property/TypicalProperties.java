package seedu.address.testutil.property;

import seedu.address.model.property.Property;

public class TypicalProperties {

    public static final Property PROPERTY_A = new PropertyBuilder().build();
    public static final Property PROPERTY_B = new PropertyBuilder()
            .withPropertyType("Condo")
            .withPropertyName("Bayfront Condominium")
            .withAddress("101 Bayfront Drive")
            .withAskingPrice(100000)
            .withSellerId(2)
            .build();

    private TypicalProperties() {} // prevent instantiation

}
