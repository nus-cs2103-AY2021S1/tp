package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyContactList;
import seedu.address.model.ReadOnlyModuleList;
import seedu.address.model.ReadOnlyTodoList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends ModuleListStorage, ContactListStorage, TodoListStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getModuleListFilePath();

    @Override
    Optional<ReadOnlyModuleList> readModuleList() throws DataConversionException, IOException;

    @Override
    void saveModuleList(ReadOnlyModuleList moduleList) throws IOException;

    @Override
    Path getContactListFilePath();

    @Override
    Optional<ReadOnlyContactList> readContactList() throws DataConversionException, IOException;

    @Override
    void saveContactList(ReadOnlyContactList contactList) throws IOException;

    @Override
    Path getTodoListFilePath();

    @Override
    Optional<ReadOnlyTodoList> readTodoList() throws DataConversionException, IOException;

    @Override
    void saveTodoList(ReadOnlyTodoList todoList) throws IOException;

}
