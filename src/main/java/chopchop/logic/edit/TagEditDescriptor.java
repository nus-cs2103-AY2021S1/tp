// TagEditDescriptor.java

package chopchop.logic.edit;

import chopchop.logic.edit.exceptions.IllegalEditOperation;
import chopchop.model.attributes.Tag;

public class TagEditDescriptor {

    private final String tagName;
    private final EditOperationType editType;

    /**
     * Creates a new descriptor representing tag editing.
     *
     * @param editType the type of edit; must be either ADD or DELETE
     * @param tag      the tag name; it must be valid according to Tag::isValidTagName
     * @throws IllegalArgumentException if either argument was invalid
     */
    public TagEditDescriptor(EditOperationType editType, String tag) throws IllegalEditOperation {

        if (editType != EditOperationType.ADD && editType != EditOperationType.DELETE) {
            throw new IllegalEditOperation("tag edit type must be either add or delete");
        } else if (!Tag.isValidTagName(tag)) {
            throw new IllegalEditOperation(Tag.MESSAGE_CONSTRAINTS);
        }

        this.tagName = tag;
        this.editType = editType;
    }


    public EditOperationType getEditType() {
        return this.editType;
    }

    public String getTagName() {
        return this.tagName;
    }
}
