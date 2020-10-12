package com.eva.testutil;

import com.eva.model.EvaDatabase;
import com.eva.model.person.Person;

/**
 * A utility class to help with building EvaDatabase objects.
 * Example usage: <br>
 *     {@code EvaDatabase ab = new EvaDatabaseBuilder().withPerson("John", "Doe").build();}
 */
public class EvaDatabaseBuilder<P extends Person> {

    private EvaDatabase<P> evaDatabase;

    public EvaDatabaseBuilder() {
        evaDatabase = new EvaDatabase<>();
    }

    public EvaDatabaseBuilder(EvaDatabase<P> evaDatabase) {
        this.evaDatabase = evaDatabase;
    }

    /**
     * Adds a new {@code Person} to the {@code EvaDatabase} that we are building.
     */
    public EvaDatabaseBuilder<P> withPerson(P person) {
        evaDatabase.addPerson(person);
        return this;
    }

    public EvaDatabase<P> build() {
        return evaDatabase;
    }
}
