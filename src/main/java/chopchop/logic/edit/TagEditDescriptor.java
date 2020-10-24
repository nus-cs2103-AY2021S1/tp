// TagEditDescriptor.java

package chopchop.logic.edit;

import chopchop.model.attributes.Tag;

public class TagEditDescriptor extends EditDescriptor {

    private final String tagName;
    private final EditOperationType editType;

    /**
     * Creates a new descriptor representing tag editing.
     *
     * @param editType the type of edit; must be either ADD or DELETE
     * @param tag      the tag name; it must be valid according to Tag::isValidTagName
     */
    public TagEditDescriptor(EditOperationType editType, String tag) {

        assert Tag.isValidTagName(tag);
        assert editType == EditOperationType.ADD || editType == EditOperationType.DELETE;

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
