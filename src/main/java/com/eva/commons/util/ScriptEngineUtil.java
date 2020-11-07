package com.eva.commons.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Utility related to script executing
 */
public class ScriptEngineUtil {
    private static final ScriptEngineManager manager = new ScriptEngineManager();
    private static final ScriptEngine engine = manager.getEngineByExtension("js");

    /**
     * execute a JavaScript file.
     * @param scriptFilePath The path to the script.
     * @throws FileNotFoundException Throws if the file does not exist.
     * @throws ScriptException Throws if an error occurred when executing the script.
     */
    public static void executeScriptFile(String scriptFilePath)
            throws FileNotFoundException, ScriptException {
        FileReader reader = new FileReader(scriptFilePath);
        engine.eval(reader);
    }
}
