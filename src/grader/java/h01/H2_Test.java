package h01;

import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        ListItem<ListItem<Double>> actual = function.apply(ListItemUtils.deepCopy(listOfLists), PARTITION_LIMIT);

        assertEquals(
            SIMPLE_STRING_FORMAT ? ListItemUtils.toSimpleString(expected) : ListItemUtils.toString(expected),
            SIMPLE_STRING_FORMAT ? ListItemUtils.toSimpleString(actual) : ListItemUtils.toString(actual),
            "Partitioned lists differ for input list " + ListItemUtils.toSimpleString(listOfLists)
        );
    }
}
