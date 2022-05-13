package h01;

import h01.utils.MethodInterceptor;
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
                "Partitioned lists differ for input list %s and limit %f".formatted(ListItemUtils.toSimpleString(listOfLists),
                    PARTITION_LIMIT)
            );
        } catch (AssertionFailedError e) {
            assertEquals(
                SIMPLE_STRING_FORMAT ? ListItemUtils.toSimpleString(expected) : ListItemUtils.toString(expected),
                SIMPLE_STRING_FORMAT ? ListItemUtils.toSimpleString(actual) : ListItemUtils.toString(actual),
                "Partitioned lists differ for input list %s and limit %f".formatted(ListItemUtils.toSimpleString(listOfLists),
                    PARTITION_LIMIT)
            );
        }
    }

    public static void testException(ListItem<ListItem<Double>> listOfLists,
                                     BiFunction<ListItem<ListItem<Double>>, Double, ListItem<ListItem<Double>>> function) {
        Exception e = assertThrows(RuntimeException.class,
            () -> function.apply(ListItemUtils.deepCopy(listOfLists), PARTITION_LIMIT));
        double delta = listOfLists.key.key - PARTITION_LIMIT;
        if (!e.getMessage().matches("[\\w ]*\\(<? *0 *>?, ?<? *0 *>?\\)[\\w ]*\\d([.,]\\d+)?$")) {
            assertEquals("element at (0, 0) exceeds limit by " + delta,
                e.getMessage(),
                "Actual exception message did not match expected one");
        }
    }

    public static void checkIllegalCalls(String signature) {
        boolean recursiveInvocationMatch = MethodInterceptor.getInvocations()
            .stream()
            .anyMatch(invocation -> invocation.signature()
                .equals(signature));

        assertFalse(recursiveInvocationMatch, "Method called itself recursively");
        checkIllegalCalls();
    }

    public static void checkIllegalCalls() {
        String illegalMethod = MethodInterceptor.getInvocations()
            .stream()
            .map(MethodInterceptor.Invocation::signature)
            .filter(signature -> !signature.matches("h01/.*"))
            .findAny()
            .orElse(null);

        assertTrue(illegalMethod == null || illegalMethod.equals("org/sourcegrade/jagr/core/executor/TimeoutHandler checkTimeout()V"),
            "Method called an illegal method: " + illegalMethod);
    }
}
