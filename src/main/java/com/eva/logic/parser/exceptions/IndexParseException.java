package com.eva.logic.parser.exceptions;

import com.eva.commons.exceptions.IllegalValueException;

/**
 * Represents a parse error encountered by a parser related to index.
 */
public class IndexParseException extends IllegalValueException {
    public IndexParseException(String message) {
        super(message);
    }

    public IndexParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
