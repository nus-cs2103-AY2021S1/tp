package guitests.guihandles;

import javafx.scene.Node;
import javafx.scene.control.Label;
import seedu.address.model.client.Client;

/**
 * Provides a handle to a person card in the person list panel.
 */
public class ClientCardHandle extends NodeHandle<Node> {
    private static final String ID_FIELD_ID = "#id";
    private static final String NAME_FIELD_ID = "#name";
    private static final String ADDRESS_FIELD_ID = "#address";
    private static final String PHONE_FIELD_ID = "#phone";
    private static final String EMAIL_FIELD_ID = "#email";
    private static final String COUNTRY_FIELD_ID = "#country";
    private static final String TIMEZONE_FIELD_ID = "#timezone";
    private final Label idLabel;
    private final Label nameLabel;
    private final Label addressLabel;
    private final Label phoneLabel;
    private final Label emailLabel;
    private final Label countryLabel;
    private final Label timezoneLabel;

    /**
     * Constructor for handler.
     *
     * @param cardNode
     */
    public ClientCardHandle(Node cardNode) {
        super(cardNode);

        idLabel = getChildNode(ID_FIELD_ID);
        nameLabel = getChildNode(NAME_FIELD_ID);
        addressLabel = getChildNode(ADDRESS_FIELD_ID);
        phoneLabel = getChildNode(PHONE_FIELD_ID);
        emailLabel = getChildNode(EMAIL_FIELD_ID);
        countryLabel = getChildNode(COUNTRY_FIELD_ID);
        timezoneLabel = getChildNode(TIMEZONE_FIELD_ID);
    }

    public String getId() {
        return idLabel.getText();
    }

    public String getName() {
        return nameLabel.getText();
    }

    public String getAddress() {
        return addressLabel.getText();
    }

    public String getPhone() {
        return phoneLabel.getText();
    }

    public String getEmail() {
        return emailLabel.getText();
    }

    public String getCountry() {
        return countryLabel.getText();
    }

    public String getTimezone() {
        return timezoneLabel.getText();
    }

    /**
     * Returns true if this handle contains {@code client}.
     */
    public boolean equals(Client client) {
        return getName().equals(client.getName().fullName)
                && getAddress().equals(client.getAddress().value)
                && getPhone().equals(client.getPhone().value)
                && getEmail().equals(client.getEmail().value);
    }
}
