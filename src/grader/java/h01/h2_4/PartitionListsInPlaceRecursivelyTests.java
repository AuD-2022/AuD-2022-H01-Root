package h01.h2_4;

import h01.*;
import h01.utils.MethodInterceptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestForSubmission("h01")
public class PartitionListsInPlaceRecursivelyTests {

    private static final String FULLY_QUALIFIED_METHOD_NAME = "h01.DoubleListOfListsProcessor#"
        + "partitionListsInPlaceRecursively(ListItem, double)";

    @BeforeEach
    public void resetInvocations() {
        MethodInterceptor.reset();
    }

    @ParameterizedTest
    @ArgumentsSource(ListProviders.EmptyMainListProvider.class)
    public void testWithEmptyMainList(ListItem<ListItem<Double>> listOfLists) {
        ListItem<ListItem<Double>> copyExpected = ListItemUtils.deepCopy(listOfLists);
        ListItem<ListItem<Double>> copyActual = ListItemUtils.deepCopy(listOfLists);

        assertTrue(ListItemUtils.deepEquals(Tutor_DoubleListOfListsProcessor.partitionListsInPlaceRecursively(copyExpected, 100),
                DoubleListOfListsProcessor.partitionListsInPlaceRecursively(copyActual, 100)),
            FULLY_QUALIFIED_METHOD_NAME + " did not return the intended result for an empty input list");
    }

    @ParameterizedTest
    @ArgumentsSource(ListProviders.EmptySingleSubListProvider.class)
    public void testWithEmptySingleSubListProvider(ListItem<ListItem<Double>> listOfLists) {
        ListItem<ListItem<Double>> copyExpected = ListItemUtils.deepCopy(listOfLists);
        ListItem<ListItem<Double>> copyActual = ListItemUtils.deepCopy(listOfLists);

        assertTrue(ListItemUtils.deepEquals(Tutor_DoubleListOfListsProcessor.partitionListsInPlaceRecursively(copyExpected, 100),
                DoubleListOfListsProcessor.partitionListsInPlaceRecursively(copyActual, 100)),
            FULLY_QUALIFIED_METHOD_NAME + " did not return the intended result for an input list that has exactly one empty "
                + "sublist");
    }

    @ParameterizedTest
    @ArgumentsSource(ListProviders.EmptyMultipleSubListProvider.class)
    public void testWithEmptyMultipleSubListProvider(ListItem<ListItem<Double>> listOfLists) {
        ListItem<ListItem<Double>> copyExpected = ListItemUtils.deepCopy(listOfLists);
        ListItem<ListItem<Double>> copyActual = ListItemUtils.deepCopy(listOfLists);

        assertTrue(ListItemUtils.deepEquals(Tutor_DoubleListOfListsProcessor.partitionListsInPlaceRecursively(copyExpected, 100),
                DoubleListOfListsProcessor.partitionListsInPlaceRecursively(copyActual, 100)),
            FULLY_QUALIFIED_METHOD_NAME + " did not return the intended result for an input list that has multiple empty "
                + "sublists");
    }

    @ParameterizedTest
    @ArgumentsSource(ListProviders.SingleSubListProvider.class)
    public void testWithSingleSubListProvider(ListItem<ListItem<Double>> listOfLists) {
        ListItem<ListItem<Double>> copyExpected = ListItemUtils.deepCopy(listOfLists);
        ListItem<ListItem<Double>> copyActual = ListItemUtils.deepCopy(listOfLists);

        assertTrue(ListItemUtils.deepEquals(Tutor_DoubleListOfListsProcessor.partitionListsInPlaceRecursively(copyExpected, 100),
                DoubleListOfListsProcessor.partitionListsInPlaceRecursively(copyActual, 100)),
            FULLY_QUALIFIED_METHOD_NAME + " did not return the intended result for an input list that has exactly one sublist");
    }

    @ParameterizedTest
    @ArgumentsSource(ListProviders.MultipleSubListProvider.class)
    public void testWithMultipleSubListProvider(ListItem<ListItem<Double>> listOfLists) {
        ListItem<ListItem<Double>> copyExpected = ListItemUtils.deepCopy(listOfLists);
        ListItem<ListItem<Double>> copyActual = ListItemUtils.deepCopy(listOfLists);

        assertTrue(ListItemUtils.deepEquals(Tutor_DoubleListOfListsProcessor.partitionListsInPlaceRecursively(copyExpected, 100),
                DoubleListOfListsProcessor.partitionListsInPlaceRecursively(copyActual, 100)),
            FULLY_QUALIFIED_METHOD_NAME + " did not return the intended result for an input list that has multiple sublists");
    }
}
