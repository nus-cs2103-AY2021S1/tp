package com.eva.ui.profile;

import com.eva.model.person.Person;
import com.eva.ui.UiPart;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class BasicInfoDisplay extends UiPart<Region> {
    private static final String FXML = "BasicInfoDisplay.fxml";

    public final Person person;

    @FXML
    private HBox displayPane;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;

    /**
     * Creates a {@code BasicInfoDisplay} with the given {@code Person}.
     */
    public BasicInfoDisplay(Person person) {
        super(FXML);
        this.person = person;
        name.setText(this.person.getName().fullName);
        phone.setText(this.person.getPhone().value);
        address.setText(this.person.getAddress().value);
        email.setText(this.person.getEmail().value);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BasicInfoDisplay)) {
            return false;
        }

        // state check
        BasicInfoDisplay card = (BasicInfoDisplay) other;
        return person.equals(card.person);
    }
}
