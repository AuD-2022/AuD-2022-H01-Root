package h01;

import java.io.BufferedReader;
import java.io.Writer;

import org.jetbrains.annotations.Nullable;

public class DoubleListOfListsProcessor {

    /**
     * Partitions a copy of {@code listOfLists} so that the sum of each sub-list does not exceed {@code limit}.
     * This implementation must not modify the input list.
     *
     * @param listOfLists a reference to the head of a list with arbitrary size
     * @param limit       the maximum value that may not be exceeded by the sum of any sub-list in the returned list
     * @return a partitioned list of lists
     * @throws RuntimeException if a single value exceeds {@code limit}
     */
    @Nullable
    public static ListItem<@Nullable ListItem<Double>> divideListsAsCopyIteratively(
        @Nullable ListItem<@Nullable ListItem<Double>> listOfLists,
        double limit
    ) {
        throw new RuntimeException("H2.1 - not implemented"); // TODO: H2.1 - remove if implemented
    }

    /**
     * Partitions a copy of {@code listOfLists} so that the sum of each sub-list does not exceed {@code limit}.
     * This implementation must only use recursion and not modify the input list.
     *
     * @param listOfLists a reference to the head of a list with arbitrary size
     * @param limit       the maximum value that may not be exceeded by the sum of any sub-list in the returned list
     * @return a partitioned list of lists
     * @throws RuntimeException if a single value exceeds {@code limit}
     */
    @Nullable
    public static ListItem<@Nullable ListItem<Double>> divideListsAsCopyRecursively(
        @Nullable ListItem<@Nullable ListItem<Double>> listOfLists,
        double limit
    ) {
        throw new RuntimeException("H2.2 - not implemented"); // TODO: H2.2 - remove if implemented
    }

    /**
     * Partitions a copy of {@code listOfLists} so that the sum of each sub-list does not exceed {@code limit}.
     * This implementation must not create new sub-lists.
     *
     * @param listOfLists a reference to the head of a list with arbitrary size
     * @param limit       the maximum value that may not be exceeded by the sum of any sub-list in the returned list
     * @return a partitioned list of lists
     * @throws RuntimeException if a single value exceeds {@code limit}
     */
    @Nullable
    public static ListItem<@Nullable ListItem<Double>> divideListsInPlaceIteratively(
        @Nullable ListItem<@Nullable ListItem<Double>> listOfLists,
        double limit
    ) {
        throw new RuntimeException("H2.3 - not implemented"); // TODO: H2.3 - remove if implemented
    }

    /**
     * Partitions a copy of {@code listOfLists} so that the sum of each sub-list does not exceed {@code limit}.
     * This implementation must only use recursion and not crete new sub-lists.
     *
     * @param listOfLists a reference to the head of a list with arbitrary size
     * @param limit       the maximum value that may not be exceeded by the sum of any sub-list in the returned list
     * @return a partitioned list of lists
     * @throws RuntimeException if a single value exceeds {@code limit}
     */
    @Nullable
    public static ListItem<@Nullable ListItem<Double>> divideListsInPlaceRecursively(
        @Nullable ListItem<@Nullable ListItem<Double>> listOfLists,
        double limit
    ) {
        throw new RuntimeException("H2.4 - not implemented"); // TODO: H2.4 - remove if implemented
    }

    /**
     * Writes out {@code listOfLists} (well, the sub-lists) to {@code writer}.
     *
     * @param writer      the writer to write to
     * @param listOfLists the list of lists to write out
     */
    public static void write(Writer writer, @Nullable ListItem<@Nullable ListItem<Double>> listOfLists) {
        throw new RuntimeException("H3.1 - not implemented"); // TODO: H3.1 - remove if implemented
    }

    /**
     * Reads a list of lists from {@code reader} and returns it.
     *
     * @param reader the reader to read from
     * @return a list of lists of double
     */
    public static @Nullable ListItem<@Nullable ListItem<Double>> read(BufferedReader reader) {
        throw new RuntimeException("H3.2 - not implemented"); // TODO: H3.2 - remove if implemented
    }
}
