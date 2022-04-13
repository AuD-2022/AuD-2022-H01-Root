package h01;

import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;

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
    public static @Nullable ListItem<@Nullable ListItem<Double>> partitionListsAsCopyIteratively(
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
    public static @Nullable ListItem<@Nullable ListItem<Double>> partitionListsAsCopyRecursively(
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
    public static @Nullable ListItem<@Nullable ListItem<Double>> partitionListsInPlaceIteratively(
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
    public static @Nullable ListItem<@Nullable ListItem<Double>> partitionListsInPlaceRecursively(
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
        try {
            for (var currOuter = listOfLists; currOuter != null; currOuter = currOuter.next) {
                for (var currInner = currOuter.key; currInner != null; currInner = currInner.next) {
                    writer.write(currInner.key + (currInner.next != null ? "#" : ""));
                }
                writer.write(currOuter.next != null ? "\n" : "#");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads a list of lists from {@code reader} and returns it.
     *
     * @param reader the reader to read from
     * @return a list of lists of double
     */
    public static @Nullable ListItem<@Nullable ListItem<Double>> read(BufferedReader reader) {
        var outerHead = (ListItem<ListItem<Double>>) null;
        var outerTail = (ListItem<ListItem<Double>>) null;
        try {
            var br = new BufferedReader(reader);
            var str = (String) null;
            while ((str = br.readLine()) != null) {
                var innerHead = (ListItem<Double>) null;
                var innerTail = (ListItem<Double>) null;
                for (String d : str.split("#")) {
                    if (d.isEmpty())
                        continue;
                    if (innerHead == null) {
                        innerHead = innerTail = new ListItem<>();
                    } else {
                        innerTail = innerTail.next = new ListItem<>();
                    }
                    innerTail.key = Double.valueOf(d);
                }
                if (outerHead == null) {
                    outerHead = outerTail = new ListItem<>();
                } else {
                    outerTail = outerTail.next = new ListItem<>();
                }
                outerTail.key = innerHead;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outerHead;
    }
}
