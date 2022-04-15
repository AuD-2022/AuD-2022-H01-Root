package h01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ListTest {

    private ListTest() {}

    /**
     * Checks deep equality for expected and actual list.
     * @param expected the expected list
     * @param actual the actual list
     * @return if expected and actual list are equal
     */
    private static boolean listsEquals(ListItem<?> expected, ListItem<?> actual) {
        if (expected != null && actual != null) {
            if (expected.key instanceof ListItem<?> expectedKey && actual.key instanceof ListItem<?> actualKey) {
                return listsEquals(expectedKey, actualKey) && listsEquals(expected.next, actual.next);
            }
            return Objects.equals(expected.key, actual.key) && listsEquals(expected.next, actual.next);
        }
        return expected == null && actual == null;
    }

    public static void assertListsEquals(ListItem<?> expected, ListItem<?> actual) {
        assertEquals(toString(expected), toString(actual));
    }

    @SafeVarargs
    public static <T> ListItem<T> toList(T... elements) {
        ListItem<T> head = null;
        ListItem<T> tail = null;

        for (var e : elements) {
            if (head == null) {
                head = tail = new ListItem<>();
            } else {
                tail = tail.next = new ListItem<>();
            }

            tail.key = e;
        }

        return head;
    }

    public static String toString(ListItem<?> list) {
        var builder = new StringBuilder("(");
        while (list != null) {
            builder.append(list.key instanceof ListItem<?> item ? toString(item) : list.key);
            if (list.next != null) {
                builder.append(" ");
            }
            list = list.next;
        }
        return builder.append(")").toString();
    }

}
