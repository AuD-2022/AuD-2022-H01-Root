package h01;

import org.jetbrains.annotations.Nullable;

import java.io.Reader;
import java.io.Writer;

public class DoubleListOfListsProcessor {

    /**
     * Partitions a copy of {@code listOfLists} so that the sum of each sub-list does not exceed {@code limit}.
     * This implementation must use a single while-loop and not modify the input list.
     *
     * @param listOfLists a reference to the head of a list with arbitrary size
     * @param limit       the maximum value that may not be exceeded by the sum of any sub-list in the returned list
     * @return a partitioned list of lists
     * @throws ListOfListsOfDoubleException if a single value exceeds {@code limit}
     */
    @Nullable
    public static ListItem<ListItem<Double>> divideListsAsCopyIteratively(
        @Nullable ListItem<ListItem<Double>> listOfLists,
        double limit
    ) {
        // TODO: remove exception and implement method
        throw new UnsupportedOperationException();
    }

    /**
     * Partitions a copy of {@code listOfLists} so that the sum of each sub-list does not exceed {@code limit}.
     * This implementation must only use recursion and not modify the input list.
     *
     * @param listOfLists a reference to the head of a list with arbitrary size
     * @param limit       the maximum value that may not be exceeded by the sum of any sub-list in the returned list
     * @return a partitioned list of lists
     * @throws ListOfListsOfDoubleException if a single value exceeds {@code limit}
     */
    @Nullable
    public static ListItem<ListItem<Double>> divideListsAsCopyRecursively(
        @Nullable ListItem<ListItem<Double>> listOfLists,
        double limit
    ) {
        // TODO: remove exception and implement method
        throw new UnsupportedOperationException();
    }

    /**
     * Partitions a copy of {@code listOfLists} so that the sum of each sub-list does not exceed {@code limit}.
     * This implementation must use a single while-loop and not create new sub-lists.
     *
     * @param listOfLists a reference to the head of a list with arbitrary size
     * @param limit       the maximum value that may not be exceeded by the sum of any sub-list in the returned list
     * @return a partitioned list of lists
     * @throws ListOfListsOfDoubleException if a single value exceeds {@code limit}
     */
    @Nullable
    public static ListItem<ListItem<Double>> divideListsInPlaceIteratively(
        @Nullable ListItem<ListItem<Double>> listOfLists,
        double limit
    ) {
        // TODO: remove exception and implement method
        throw new UnsupportedOperationException();
    }

    /**
     * Partitions a copy of {@code listOfLists} so that the sum of each sub-list does not exceed {@code limit}.
     * This implementation must only use recursion and not crete new sub-lists.
     *
     * @param listOfLists a reference to the head of a list with arbitrary size
     * @param limit       the maximum value that may not be exceeded by the sum of any sub-list in the returned list
     * @return a partitioned list of lists
     * @throws ListOfListsOfDoubleException if a single value exceeds {@code limit}
     */
    @Nullable
    public static ListItem<ListItem<Double>> divideListsInPlaceRecursively(
        @Nullable ListItem<ListItem<Double>> listOfLists,
        double limit
    ) {
        // TODO: remove exception and implement method
        throw new UnsupportedOperationException();
    }

    /**
     * Writes out {@code listOfLists} (well, the sub-lists) to {@code writer}.
     *
     * @param writer      an instance of a {@link Writer}
     * @param listOfLists the list of lists to write out
     */
    public static void write(Writer writer, ListItem<ListItem<Double>> listOfLists) {
        throw new UnsupportedOperationException();
    }

    /**
     * Reads a list of lists from {@code reader} and returns it.
     *
     * @param reader the reader to read from
     * @return a list of lists of double
     */
    public static ListItem<ListItem<Double>> read(Reader reader) {
        throw new UnsupportedOperationException();
    }
}
