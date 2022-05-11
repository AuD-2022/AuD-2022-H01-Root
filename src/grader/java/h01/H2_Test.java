package h01;

import org.opentest4j.AssertionFailedError;

import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.*;

public final class H2_Test {

    public static final long SEED = 0L;

    public static final int MAX_MAIN_LIST_SIZE = 3;

    public static final int MAX_SUBLIST_SIZE = 5;

    public static final double DOUBLE_LIMIT = 100d;

    public static final int METHOD_CALLS = 10;

    public static final boolean SIMPLE_STRING_FORMAT = true;

    public static final double PARTITION_LIMIT = 100d;

    public static void test(ListItem<ListItem<Double>> listOfLists,
                            BiFunction<ListItem<ListItem<Double>>, Double, ListItem<ListItem<Double>>> function) {
        ListItem<ListItem<Double>> expected = Tutor_Processor.partitionLists(ListItemUtils.deepCopy(listOfLists),
            PARTITION_LIMIT);
        ListItem<ListItem<Double>> expected_alt = Tutor_Processor.partitionLists_Alt(ListItemUtils.deepCopy(listOfLists),
            PARTITION_LIMIT);
        ListItem<ListItem<Double>> actual = function.apply(ListItemUtils.deepCopy(listOfLists), PARTITION_LIMIT);

        try {
            assertEquals(
                SIMPLE_STRING_FORMAT ? ListItemUtils.toSimpleString(expected_alt) : ListItemUtils.toString(expected_alt),
                SIMPLE_STRING_FORMAT ? ListItemUtils.toSimpleString(actual) : ListItemUtils.toString(actual),
                "Partitioned lists differ for input list " + ListItemUtils.toSimpleString(listOfLists)
            );
        } catch (AssertionFailedError e) {
            assertEquals(
                SIMPLE_STRING_FORMAT ? ListItemUtils.toSimpleString(expected) : ListItemUtils.toString(expected),
                SIMPLE_STRING_FORMAT ? ListItemUtils.toSimpleString(actual) : ListItemUtils.toString(actual),
                "Partitioned lists differ for input list " + ListItemUtils.toSimpleString(listOfLists)
            );
        }
    }

    public static void testException(ListItem<ListItem<Double>> listOfLists,
                                     BiFunction<ListItem<ListItem<Double>>, Double, ListItem<ListItem<Double>>> function) {
        Exception e = assertThrows(RuntimeException.class,
            () -> function.apply(ListItemUtils.deepCopy(listOfLists), PARTITION_LIMIT));
        double delta = listOfLists.key.key - PARTITION_LIMIT;
        if (!e.getMessage().matches("[Ee]lement at \\(0, ?0\\) exceeds limit by .+")) {
            assertEquals("element at (0, 0) exceeds limit by " + delta,
                e.getMessage(),
                "Actual exception message did not match expected one");
        }
    }
}
