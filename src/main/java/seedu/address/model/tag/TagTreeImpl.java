package seedu.address.model.tag;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

/**
 * A concrete implementation of the TagTree. It uses two HashMap to keep track of the two-way relationship of tags.
 */
public class TagTreeImpl extends TagTree {

    private static final String MESSAGE_NOT_VALID_SUBTAG = "%s is not a valid subtag of %s";
    private static final String MESSAGE_NOT_VALID_SUPERTAG = "%s is not a valid supertag of %s";
    private static final String MESSAGE_CYCLIC_RELATIONSHIP = "%s is already a subtag of %s. "
            + "Avoid cyclic relationships!";

    private Map<Tag, Set<Tag>> tagSubTagMap;
    private Map<Tag, Set<Tag>> tagSuperTagMap;

    public TagTreeImpl() {
        tagSubTagMap = new HashMap<>();
        tagSuperTagMap = new HashMap<>();
    }

    public TagTreeImpl(Map<Tag, Set<Tag>> tagSubTagMap, Map<Tag, Set<Tag>> tagSuperTagMap) {
        this.tagSubTagMap = tagSubTagMap;
        this.tagSuperTagMap = tagSuperTagMap;
    }

    @Override
    public Set<Tag> getSubTagsOf(Tag tag) {
        return tagSubTagMap.containsKey(tag) ? Set.copyOf(tagSubTagMap.get(tag)) : Set.of();
    }

    @Override
    public void addSubTagTo(Tag superTag, Tag subTag) {
        if (isSubTagOf(subTag, superTag)) {
            throw new IllegalArgumentException(String.format(MESSAGE_CYCLIC_RELATIONSHIP, superTag, subTag));
        }
        addToMapSet(tagSubTagMap, superTag, subTag);
        addToMapSet(tagSuperTagMap, subTag, superTag);
    }

    /**
     * Adds the {@code tagToAdd} to the set of sub-tags assigned to {@code key}.
     * Adds a new HashSet if the {@code key} did not have any sub-tags.
     */
    private void addToMapSet(Map<Tag, Set<Tag>> map, Tag key, Tag tagToAdd) {
        map.merge(key, new HashSet<>(Set.of(tagToAdd)),
                (set1, set2) -> {set1.addAll(set2); return set1;});
    }

    @Override
    public void removeSubTagFrom(Tag superTag, Tag subTag) {
        if (!tagSubTagMap.get(superTag).contains(subTag)) {
            throw new NoSuchElementException(String.format(MESSAGE_NOT_VALID_SUBTAG, subTag, superTag));
        }
        if (!tagSuperTagMap.get(subTag).contains(superTag)) {
            throw new NoSuchElementException(String.format(MESSAGE_NOT_VALID_SUPERTAG, superTag, subTag));
        }
        removeEntryFromMap(tagSubTagMap, superTag, subTag);
        removeEntryFromMap(tagSuperTagMap, subTag, superTag);
    }

    /**
     * Removes {@code tagToRemove} from the set corresponding to the {@code key}.
     * If the resulting set is empty, the key-value pair is removed from the map.
     */
    private void removeEntryFromMap(Map<Tag, Set<Tag>> map, Tag key, Tag tagToRemove) {
        map.get(key).remove(tagToRemove);
        if (map.get(key).isEmpty()) {
            map.remove(key);
        }
    }

    @Override
    public void deleteTag(Tag tag) {
        Set<Tag> subTagSet = tagSubTagMap.get(tag);
        boolean hasChildTags = subTagSet != null;
        Set<Tag> superTagSet = tagSuperTagMap.get(tag);
        boolean hasParentTags = superTagSet != null;

        if (hasParentTags && hasChildTags) {
            connectParentWithChildTags(superTagSet, subTagSet);
        }
        if (hasParentTags) {
            superTagSet.forEach(superTag -> removeEntryFromMap(tagSubTagMap, superTag, tag));
        }
        if (hasChildTags) {
            subTagSet.forEach(subTag -> removeEntryFromMap(tagSuperTagMap, subTag, tag));
        }

        tagSuperTagMap.remove(tag);
        tagSubTagMap.remove(tag);
    }

    /**
     * Unions the {@code subTagSet} to existing sub-tags for each tag in the superTagSet.
     */
    private void connectParentWithChildTags(Set<Tag> superTagSet, Set<Tag> subTagSet) {
        superTagSet.forEach(superTag -> addSubTagsTo(superTag, subTagSet));
    }

    public boolean isSubTagOf(Tag superTag, Tag subTag) {
        boolean hasNoSubTags = tagSubTagMap.get(superTag) == null;
        if (hasNoSubTags) {
            return false;
        }

        boolean hasSubTagAsDirectChild = tagSubTagMap.get(superTag).contains(subTag);
        if (hasSubTagAsDirectChild) {
            System.out.println(superTag + " " + subTag);
            return true;
        }

        return tagSubTagMap.get(superTag).stream().anyMatch(childTag -> isSubTagOf(childTag, subTag));
    }

    @Override
    public String toString() {
        return "Sub-tag map: " + tagSubTagMap + "\nSuper-tag map: " + tagSuperTagMap;
    }

    boolean hasDirectSuperTag(Tag subTag, Tag superTag) {
        return tagSuperTagMap.get(subTag) != null && tagSuperTagMap.get(subTag).contains(superTag);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        } else if (!(object instanceof TagTreeImpl)) {
            return false;
        } else {
            TagTreeImpl otherTree = (TagTreeImpl) object;
            return otherTree.tagSuperTagMap.equals(tagSuperTagMap)
                    && otherTree.tagSubTagMap.equals(tagSubTagMap);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TagTree tree = new TagTreeImpl();
        String currentString = "";
        while (!currentString.equals("end")) {
            currentString = scanner.nextLine();
            try {
                String[] lineArgs = currentString.split(" ");
                switch (lineArgs[0]) {
                case "add":
                    tree.addSubTagTo(new Tag(lineArgs[1]), new Tag(lineArgs[2]));
                    break;
                case "remove":
                    tree.removeSubTagFrom(new Tag(lineArgs[1]), new Tag(lineArgs[2]));
                    break;
                case "delete":
                    tree.deleteTag(new Tag(lineArgs[1]));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid command!");
                }
                System.out.println(tree);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


}
