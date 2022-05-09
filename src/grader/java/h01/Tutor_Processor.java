package h01;

import java.util.LinkedList;

public class Tutor_Processor {

    public static ListItem<ListItem<Double>> partitionLists(ListItem<ListItem<Double>> listOfLists, double limit) {
        LinkedList<LinkedList<Double>> partitionedList = new LinkedList<>();
        int outerIndex = 0;

        for (ListItem<ListItem<Double>> pOuter = listOfLists; pOuter != null; pOuter = pOuter.next, outerIndex++) {
            int innerIndex = 0;
            double currentSum = 0;
            partitionedList.add(new LinkedList<>());
            for (ListItem<Double> pInner = pOuter.key; pInner != null; pInner = pInner.next, innerIndex++) {
                if (pInner.key > limit) {
                    throw new RuntimeException("element at (%d, %d) exceeds limit by %f"
                        .formatted(outerIndex, innerIndex, pInner.key - limit));
                }
                if (currentSum + pInner.key > limit) {
                    partitionedList.add(new LinkedList<>());
                    currentSum = 0;
                }
                partitionedList.getLast().add(pInner.key);
                currentSum += pInner.key;
            }
        }

        return ListItemUtils.fromList(partitionedList.stream().map(ListItemUtils::fromList).toList());
    }

    public static ListItem<ListItem<Double>> partitionLists_Alt(ListItem<ListItem<Double>> listOfLists, double limit) {
        LinkedList<LinkedList<Double>> partitionedList = new LinkedList<>();
        boolean firstListAdded = false;
        double currentSum = 0;
        int outerIndex = 0;

        for (ListItem<ListItem<Double>> pOuter = listOfLists; pOuter != null; pOuter = pOuter.next, outerIndex++) {
            if (!firstListAdded) {
                partitionedList.add(new LinkedList<>());
                firstListAdded = true;
            }
            int innerIndex = 0;
            for (ListItem<Double> pInner = pOuter.key; pInner != null; pInner = pInner.next, innerIndex++) {
                if (pInner.key > limit) {
                    throw new RuntimeException("element at (%d, %d) exceeds limit by %f"
                        .formatted(outerIndex, innerIndex, pInner.key - limit));
                }
                if (currentSum + pInner.key > limit) {
                    partitionedList.add(new LinkedList<>());
                    currentSum = 0;
                }
                partitionedList.getLast().add(pInner.key);
                currentSum += pInner.key;
            }
        }

        return ListItemUtils.fromList(partitionedList.stream().map(ListItemUtils::fromList).toList());
    }
}
