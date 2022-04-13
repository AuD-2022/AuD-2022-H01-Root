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
        var outerHead = (ListItem<ListItem<Double>>) null;
        var outerTail = (ListItem<ListItem<Double>>) null;
        var innerTail = (ListItem<Double>) null;
        var currOuter = listOfLists;
        // traverse outer items
        while (currOuter != null) {
            var currInner = currOuter.key;
            var currSum = 0d;
            if (outerTail == null) {
                outerHead = outerTail = behaviour == AS_COPY ? new ListItem<>() : currOuter;
            } else {
                outerTail = outerTail.next = behaviour == AS_COPY ? new ListItem<>() : currOuter;
            }
            // traverse inner items
            while (currInner != null) {
                currSum += currInner.key;
                if (innerTail == null) {
                    outerTail.key = innerTail = behaviour == AS_COPY ? new ListItem<>() : currInner;
                } else {
                    innerTail = innerTail.next = behaviour == AS_COPY ? new ListItem<>() : currInner;
                }
                // set inner tail (no effect if in-place)
                innerTail.key = currInner.key;
                // check next element
                if (currInner.next != null && currSum + currInner.next.key > limit) {
                    var nextOuter = currOuter.next;
                    // create intermediate item
                    outerTail.next = new ListItem<>();
                    outerTail = outerTail.next;
                    outerTail.key = innerTail = behaviour == AS_COPY ? new ListItem<>() : currInner.next;
                    outerTail.key.key = currInner.next.key;
                    if (innerTail.key > limit) {
                        throw new RuntimeException();
                    }
                    if (behaviour == IN_PLACE) {
                        outerTail.next = nextOuter;
                        currInner.next = null;
                        currOuter = outerTail;
                        currInner = outerTail.key.next;
                    } else if (behaviour == AS_COPY) {
                        currInner = currInner.next.next;
                    }
                } else {
                    // set next inner item
                    currInner = currInner.next;
                }
            }
            currOuter = currOuter.next;
            innerTail = null;
        }
        return outerHead;
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

    private static ListItem<ListItem<Double>> partitionListsRecursively(
        ListItem<ListItem<Double>> currOuter,
        ListItem<Double> currInner,
        double limit,
        ListItem<ListItem<Double>> outerHead,
        ListItem<Double> innerHead,
        double sum,
        Behaviour behaviour
    ) {
        if (currOuter == null) {
            return null;
        }
        if (outerHead == null) {
            outerHead = behaviour == AS_COPY || currOuter.key != currInner ? new ListItem<>() : currOuter;
        }
        if (currInner != null) {
            sum += currInner.key;

            if (innerHead == null) {
                outerHead.key = innerHead = behaviour == AS_COPY ? new ListItem<>() : currInner;
            } else {
                innerHead = innerHead.next = behaviour == AS_COPY ? new ListItem<>() : currInner;
            }
            innerHead.key = currInner.key;
            if (currInner.next != null && sum + currInner.next.key > limit) {
                if (behaviour == IN_PLACE) {
                    currOuter.next = partitionListsRecursively(currOuter, currInner.next, limit, null, null, 0, behaviour);
                    currInner.next = null;
                } else {
                    outerHead.next = partitionListsRecursively(currOuter, currInner.next, limit, null, null, 0, behaviour);
                }
            } else {
                partitionListsRecursively(currOuter, currInner.next, limit, outerHead, innerHead, sum, behaviour);
            }
        } else if (currOuter.next != null) {
            outerHead.next = partitionListsRecursively(currOuter.next, currOuter.next.key, limit, null, null, 0, behaviour);
        }
        return outerHead;
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
