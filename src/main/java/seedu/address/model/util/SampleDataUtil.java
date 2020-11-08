package seedu.address.model.util;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.FlashcardBook;
import seedu.address.model.QuizBook;
import seedu.address.model.ReadOnlyFlashcardBook;
import seedu.address.model.ReadOnlyQuizBook;
import seedu.address.model.person.Definition;
import seedu.address.model.person.Flashcard;
import seedu.address.model.person.Priority;
import seedu.address.model.person.Title;
import seedu.address.model.quiz.Attempt;
import seedu.address.model.quiz.Mcq;
import seedu.address.model.quiz.Performance;
import seedu.address.model.quiz.Question;
import seedu.address.model.quiz.Response;
import seedu.address.model.quiz.TrueFalse;
import seedu.address.model.quiz.UniqueResponseList;
import seedu.address.model.quiz.exceptions.InvalidQuestionAnswerException;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Flashcard[] getSamplePersons() {
        return new Flashcard[]{
            new Flashcard(new Title("Quicksort"),
                new Definition("Picks a pivot element A[q] and partitions the array into two subarrays:"
                    + " A[p, ..., q - 1] in which all elements are less than A[q], and A[q + 1, ..., r]"
                    + " in which all elements are greater than or equal to A[q]. The algorithm then sorts"
                    + " the subarrays A[p, ..., q - 1] and A[q + 1, ..., r] recursively until the entire"
                    + "array is sorted."),
                getTagSet("sorting"), Priority.LOW),
            new Flashcard(new Title("Breadth first search"),
                new Definition("Visits all vertices in graph G that are k edges away from source vertex"
                    + " s before visiting any vertex k + 1 edges away. Algorithm repeats until no more"
                    + " vertices can be reached from s."),
                getTagSet("searching"), Priority.HIGH),
            new Flashcard(new Title("Chaining"),
                new Definition("Technique for avoiding collisions in hash tables. The hash table is an array of"
                    + " linked lists and all key-value pairs mapping to the same index will be stored "
                    + "in linked list  of that index."),
                getTagSet("hashing"), Priority.LOW),
            new Flashcard(new Title("Heap"),
                new Definition("Data structure that is usually implemented with an array, and can be thought of"
                    + " as a tree. The root of the heap is the topmost element, and a leaf is a node at the"
                    + " bottom of the tree. In a max-heap, the parent node has a value that is greater "
                    + "than or equal to that of its children, whereas in a min-heap, the parent node has "
                    + "a value that is less than or equal to that of its children."),
                getTagSet("heaps"), Priority.MEDIUM)

        };
    }

    public static ReadOnlyFlashcardBook getSampleAddressBook() {
        FlashcardBook sampleAb = new FlashcardBook();
        for (Flashcard sampleFlashcard : getSamplePersons()) {
            sampleAb.addFlashcard(sampleFlashcard);
        }
        return sampleAb;
    }

    public static Question[] getSampleQuestions() {
        return new Question[]{
            new TrueFalse("G is a weighted, undirected graph, and u is a node in the graph. "
                    + "Edge (u, v) is the edge adjacent to u with the smallest weight. Then some"
                    + " minimum spanning tree of G contains edge (u, v)", true),
            new TrueFalse("Given an array sorted from smallest to largest, we can build a min-heap"
                    + " (i.e, a heap where the smallest element is at the root) in time <= O(logn)", true),
            new TrueFalse("Assume you have a graph with no positive weight cycles. Then you can find the "
                    + "longest path in the graph by negating all the edge weights "
                    + "and running Dijkstra's algorithm", false),
            new TrueFalse("Every directed acyclic graph has exactly one valid topological ordering", false),
            new TrueFalse("The sequence [20, 15, 18, 7, 9, 5, 12, 3, 6, 2] is a max-heap", true),
            new Mcq("The maximum number of rotations necessary to rebalance an AVL tree "
                    + "containing n elements during the insertion of a new item is:", 3,
                     Arrays.asList("0", "1", "2", "3")),
            new Mcq("Assume that [19, 7, 8, 1, 16, 25, 62, 47, 80] was just partitioned by"
                    + " a Quicksort partitioning algorithm. Which option is a possible pivot?", 4,
                     Arrays.asList("19", "8", "16", "25")),
            new Mcq("Which of the following is a good loop invariant for the outer loop in InsertionSort?", 1,
                     Arrays.asList("For all k such that k < i: A[k] <= A[k+1]",
                        "For all k such that j < k < i: A[k] <= A[i]",
                        "The subarray A[0..i] contains the i+1 smallest elements in the array",
                        "The subarray A[0..i-1] contains the i smallest elements in the array")),
            new Mcq("Assume that comparing two strings of length k1 and k2 takes min(k1, k2) time. "
                    + "The worst-case running time for inserting a string of length L into an AVL tree of size n "
                    + "where all the keys in the tree have length L is: ", 4,
                     Arrays.asList("O(1)", "O(L)", "O(nlogn)", "O(Llogn)")),
            new Mcq("What is the time complexity of Bubble Sort?", 4,
                     Arrays.asList("O(1)", "O(n)", "O(nlogn)", "O(n^2)")),
            new Mcq("What is the time complexity of Selection Sort?", 4,
                     Arrays.asList("O(1)", "O(n)", "O(nlogn)", "O(n^2)")),
            new Mcq("What is the time complexity of Insertion Sort?", 4,
                     Arrays.asList("O(1)", "O(n)", "O(nlogn)", "O(n^2)")),
            new Mcq("What is the time complexity of Merge Sort?", 3,
                     Arrays.asList("O(1)", "O(n)", "O(nlogn)", "O(n^2)"))
        };
    }

    public static ReadOnlyQuizBook getSampleQuizBook() {
        QuizBook sampleQb = new QuizBook();
        for (Question sampleQuestion : getSampleQuestions()) {
            sampleQb.addQuestion(sampleQuestion);
        }
        return sampleQb;
    }

    public static Attempt[] getSampleAttempts() {
        UniqueResponseList sampleResponses = new UniqueResponseList();
        for (Question sampleQ : getSampleQuestions()) {
            Response r = sampleQ.isMcq() ? new Response("1", sampleQ)
                    : new Response("True", sampleQ);
            try {
                r.markResponse();
                sampleResponses.add(r);
            } catch (InvalidQuestionAnswerException e) {
                e.printStackTrace();
            }

        }
        return new Attempt[]{
            new Attempt(sampleResponses, LocalDateTime.now())
        };
    }

    public static Performance getSamplePerformance() {
        Performance sampleP = new Performance();
        for (Attempt sampleAttempt : getSampleAttempts()) {
            sampleP.addAttempt(sampleAttempt);
        }
        return sampleP;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
            .map(Tag::new)
            .collect(Collectors.toSet());
    }
}
