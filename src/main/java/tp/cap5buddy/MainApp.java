package tp.cap5buddy;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import tp.cap5buddy.contacts.ContactList;
import tp.cap5buddy.logic.LogicManager;
import tp.cap5buddy.modules.ModuleList;
import tp.cap5buddy.storage.Storage;
import tp.cap5buddy.storage.StorageManager;
import tp.cap5buddy.ui.gui.Ui;
import tp.cap5buddy.ui.gui.UiManager;

import java.util.logging.Logger;

public class MainApp extends Application {

    private static final Logger logger = LogsCenter.getLogger(tp.cap5buddy.MainApp.class);

    protected static Ui userInterface;
    protected static LogicManager manager;
    protected static StorageManager storage;
    protected static ModuleList moduleList;
    protected static ContactList contactList;

    @Override
    public void init() {
        logger.info("=============================[ Initializing Module Tracker ]===========================");
        userInterface = new UiManager();
        storage = null;
        moduleList = null;
        contactList = null;
        manager = new LogicManager(storage, moduleList, contactList);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        logger.info("Starting Module tracker now!");
        userInterface.start(primaryStage);
    }
}
