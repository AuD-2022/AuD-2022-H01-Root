package h01;

import static org.junit.jupiter.api.Assertions.*;

public class ListTest {

    private ListTest() {}

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
