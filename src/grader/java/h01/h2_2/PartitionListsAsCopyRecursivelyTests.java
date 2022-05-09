package h01.h2_2;

import h01.*;
import h01.utils.MethodInterceptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

@TestForSubmission("h01")
public class PartitionListsAsCopyRecursivelyTests {

    @BeforeEach
    public void resetInvocations() {
        MethodInterceptor.reset();
    }

    @ParameterizedTest
    @ArgumentsSource(ListProviders.EmptyMainListProvider.class)
    public void testWithEmptyMainList(ListItem<ListItem<Double>> listOfLists) {
        H2_Test.test(listOfLists, DoubleListOfListsProcessor::partitionListsAsCopyRecursively);
    }

    @ParameterizedTest
    @ArgumentsSource(ListProviders.EmptySingleSubListProvider.class)
    public void testWithEmptySingleSubListProvider(ListItem<ListItem<Double>> listOfLists) {
        H2_Test.test(listOfLists, DoubleListOfListsProcessor::partitionListsAsCopyRecursively);
    }

    @ParameterizedTest
    @ArgumentsSource(ListProviders.EmptyMultipleSubListProvider.class)
    public void testWithEmptyMultipleSubListProvider(ListItem<ListItem<Double>> listOfLists) {
        H2_Test.test(listOfLists, DoubleListOfListsProcessor::partitionListsAsCopyRecursively);
    }

    @ParameterizedTest
    @ArgumentsSource(ListProviders.SingleSubListProvider.class)
    public void testWithSingleSubListProvider(ListItem<ListItem<Double>> listOfLists) {
        H2_Test.test(listOfLists, DoubleListOfListsProcessor::partitionListsAsCopyRecursively);
    }

    @ParameterizedTest
    @ArgumentsSource(ListProviders.MultipleSubListProvider.class)
    public void testWithMultipleSubListProvider(ListItem<ListItem<Double>> listOfLists) {
        H2_Test.test(listOfLists, DoubleListOfListsProcessor::partitionListsAsCopyRecursively);
    }
}
