package h01;

import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;

import static h01.DoubleListOfListsProcessor.Behaviour.*;

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
        return partitionListsIteratively(listOfLists, limit, AS_COPY);
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
        return partitionListsIteratively(listOfLists, limit, IN_PLACE);
    }

    private static ListItem<ListItem<Double>> partitionListsIteratively(
        ListItem<ListItem<Double>> listOfLists,
        double limit,
        Behaviour behaviour
    ) {
        var newOuterHead = (ListItem<ListItem<Double>>) null;
        var newOuterTail = (ListItem<ListItem<Double>>) null;
        var newInnerTail = (ListItem<Double>) null;
        var i = 0;
        var j = 0;
        var currOuter = listOfLists;
        // traverse outer items
        while (currOuter != null) {
            var sumOfList = 0d;
            var currInner = currOuter.key;
            if (newOuterTail == null) {
                newOuterHead = newOuterTail = behaviour == AS_COPY ? new ListItem<>() : currOuter;
            } else {
                newOuterTail = newOuterTail.next = behaviour == AS_COPY ? new ListItem<>() : currOuter;
            }
            // traverse inner items
            while (currInner != null) {
                sumOfList += currInner.key;
                if (newInnerTail == null) {
                    newOuterTail.key = newInnerTail = behaviour == AS_COPY ? new ListItem<>() : currInner;
                } else {
                    newInnerTail = newInnerTail.next = behaviour == AS_COPY ? new ListItem<>() : currInner;
                }
                // set inner tail (no effect if in-place)
                newInnerTail.key = currInner.key;
                // check if next element exceeds limit
                if (currInner.next != null && sumOfList + currInner.next.key > limit)  {
                    if (currInner.next.key > limit) {
                        var delta = currInner.next.key - limit;
                        throw new RuntimeException(String.format("element at (%d, %d) exceeds limit by %f", i, j, delta));
                    }
                    newInnerTail = null;
                    var exNextOuter = currOuter.next;
                    var exNextInner = currInner.next;
                    // create intermediate item
                    newOuterTail = newOuterTail.next = new ListItem<>();
                    if (behaviour == IN_PLACE) {
                        newOuterTail.next = exNextOuter;
                        currInner.next = null;
                        currOuter = newOuterTail;
                    }
                    // set first = current inner item and reset sum
                    currInner = exNextInner;
                    sumOfList = 0;
                } else {
                    // set current inner item
                    currInner = currInner.next;
                }
                j++;
            }
            currOuter = currOuter.next;
            newInnerTail = null;
            i++;
            j = 0;
        }
        return newOuterHead;
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
        return partitionListsRecursively(listOfLists, limit, AS_COPY);
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
        return partitionListsRecursively(listOfLists, limit, IN_PLACE);
    }

    public static ListItem<ListItem<Double>> partitionListsRecursively(
        ListItem<ListItem<Double>> currOuter,
        double limit,
        Behaviour behaviour
    ) {
        if (currOuter == null) {
            return null;
        }
        return partitionListsRecursively(currOuter, currOuter.key, limit, null, null, 0, behaviour);
    }

    @SuppressWarnings("DuplicatedCode")
    private static ListItem<ListItem<Double>> partitionListsRecursively(
        ListItem<ListItem<Double>> currOuter,
        ListItem<Double> currInner,
        double limit,
        ListItem<ListItem<Double>> newOuterTail,
        ListItem<Double> newInnerTail,
        int i,
        int j,
        double sumOfList,
        Behaviour behaviour
    ) {
        if (currOuter == null) {
            return null;
        }
        if (newOuterTail == null) {
            newOuterTail = behaviour == AS_COPY ? new ListItem<>() : currOuter;
        }
        if (currInner != null) {
            sumOfList += currInner.key;
            if (newInnerTail == null) {
                newOuterTail.key = newInnerTail = behaviour == AS_COPY ? new ListItem<>() : currInner;
            } else {
                newInnerTail = newInnerTail.next = behaviour == AS_COPY ? new ListItem<>() : currInner;
            }
            // set inner tail (no effect if in-place)
            newInnerTail.key = currInner.key;
            // check if next element exceeds limit
            if (currInner.next != null && sumOfList + currInner.next.key > limit) {
                // next element exceeds limit
                if (currInner.next.key > limit) {
                    var delta = currInner.next.key - limit;
                    throw new RuntimeException(String.format("element at (%d, %d) exceeds limit by %f", i, j, delta));
                }
                newInnerTail = null;
                var exNextOuter = currOuter.next;
                var exNextInner = currInner.next;
                // create intermediate item
                newOuterTail = newOuterTail.next = new ListItem<>();
                if (behaviour == IN_PLACE) {
                    newOuterTail.next = exNextOuter;
                    currInner.next = null;
                    currOuter = newOuterTail;
                }
                // set first = current inner item and reset sum
                currInner = exNextInner;
                sumOfList = 0;
            } else {
                // set current inner item
                currInner = currInner.next;
            }
            partitionListsRecursively(currOuter, currInner, limit, newOuterTail, newInnerTail, i, j + 1, sumOfList, behaviour);
        } else if (currOuter.next != null) {
            newOuterTail.next = partitionListsRecursively(currOuter.next, currOuter.next.key, limit, null, null, i + 1, 0, 0, behaviour);
        }
        return newOuterTail;
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


    enum Behaviour {
        AS_COPY, IN_PLACE
    }
}
